package com.happy.util.excel;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.happy.entity.Student;
import com.happy.util.Reflects;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/10/18/16:55
 * @Desc:
 **/
public class ExcelUtil {

    private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);


    /**
     * web导出
     * @param order
     * @param response
     * @throws IOException
     */
    public static void export(ExcelOrder order, HttpServletResponse
            response) throws IOException {
        Workbook wb = init(order);
        exportExcel(wb, order, response);
    }

    public static <E> List<E> getExcelData(ExcelOrder<E> order) throws IOException, InstantiationException, IllegalAccessException {
        Workbook wb = getWorkBook(order);
        List<E> list = Lists.newArrayList();

        if(null != wb){
            Sheet sheet = wb.getSheetAt(0);//默认只有一个sheet
            if(sheet == null){
                return list;
            }

            //获得当前sheet的开始行
            int firstRowNum  = sheet.getFirstRowNum();
            //获得当前sheet的结束行
            int lastRowNum = sheet.getLastRowNum();

            //循环除了第一行的所有行
            for(int rowNum = firstRowNum + 1;rowNum <= lastRowNum;rowNum++){
                //获得当前行
                Row row = sheet.getRow(rowNum);
                if(row == null){
                    continue;
                }
                //获得当前行的开始列
                int firstCellNum = row.getFirstCellNum();
                //获得当前行的列数
                int lastCellNum = row.getPhysicalNumberOfCells();
                String[] cells = new String[row.getPhysicalNumberOfCells()];
                E e = order.newInstance();
                //循环当前行
                for(int cellNum = firstCellNum; cellNum < lastCellNum;cellNum++){
                    List<Field> fields = Arrays.asList(e.getClass().getDeclaredFields());
                    List<Field> fieldList = Lists.newArrayList(fields);//此处为了调用remove后不报错重新生成一个集合

                    Iterator<Field> it = fieldList.iterator();

                    while (it.hasNext()){//此处为了调用remove后不报错使用迭代器遍历
                        Field field = it.next();
                        if(field.isAnnotationPresent(Excel.class)){
                            Excel excel = field.getAnnotation(Excel.class);
                            int index = excel.index();

                            if(index == cellNum){
                                Cell cell = row.getCell(cellNum);
                                String value = getCellValue(cell);

                                try {
                                    Reflects.setValueByReflect(field, e, value, excel.dateFmt());
                                } catch (ParseException e1) {
                                    logger.error("数据转换异常：", e);
                                }

                                it.remove();//减少循环量
                            }
                        }
                    }
                }
                list.add(e);
            }
        }
        return list;
    }


    /**
     * 生成
     * @param order
     */
    public static  void generate(ExcelOrder order){
        Workbook wb = init(order);
        writeExcel(wb, order);
    }

    /**
     * 初始化
     * @param order
     * @return
     */
    private static Workbook init(ExcelOrder order){
        Workbook wb = createHead(order.getFileName(), order);
        fillData(order.getList() ,wb, order.getFileName());
        return wb;
    }

    /**
     * 生成workbook和表头
     * @param excelName
     * @param order
     * @return
     */
    private static Workbook createHead(String excelName, ExcelOrder order){
        List<Field> fields = Arrays.asList(order.getClazz().getDeclaredFields());

        if(CollectionUtils.isEmpty(fields)){
            logger.warn("字段获取异常");
            return null;
        }

        Workbook wb;
        Sheet sheet;
        Row row;

        if(ExcelType.XLS.equals(order.getType())){
            wb = new HSSFWorkbook();
        }else {
            wb = new XSSFWorkbook();
        }
        sheet = wb.createSheet(excelName);
        sheet.autoSizeColumn(0);//自适应列宽

        row = sheet.createRow(0);

        for(Field field : fields){
            if(field.isAnnotationPresent(Excel.class)){
                Excel excel = field.getAnnotation(Excel.class);
                int index = excel.index();
                String cellName = excel.name();

                Cell cell = row.createCell(index);
                cell.setCellValue(cellName);
            }
        }

        return wb;
    }


    /**
     * 填充数据
     * @param list
     * @param workbook
     * @param excelName
     */
    private static void fillData(List list, Workbook workbook, String excelName){

        if(CollectionUtils.isEmpty(list)){
            return;
        }

        Sheet sheet = workbook.getSheet(excelName);
        for(int i = 0; i < list.size();i++){
            Object obj = list.get(i);
            Class<?> c = obj.getClass();
            Row row = sheet.createRow(i+1);

            List<Field> fields = Arrays.asList(c.getDeclaredFields());
            for(Field field : fields){
                if(field.isAnnotationPresent(Excel.class)){
                    Excel excel = field.getAnnotation(Excel.class);

                    try {
                        row.createCell(excel.index()).setCellValue(Reflects.getValueByReflect(field,
                                obj));
                    } catch (IllegalAccessException e) {
                        //转换异常.
                        logger.error("反射获取数据异常", e);
                    }
                }
            }
        }
    }


    /**
     * 写出Excel
     * @param wb
     * @param order
     */
    private static void writeExcel(Workbook wb, ExcelOrder order){

        if(StringUtils.isEmpty(order.getFilePath())){
            logger.warn("文件路径不能为空！");
            return;
        }

        try{
            FileOutputStream fos = new FileOutputStream(order.getWholeFilePath());
            wb.write(fos);
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 导出Excel
     * @param wb
     * @param order
     * @param response
     * @throws IOException
     */
    private static void exportExcel(Workbook wb, ExcelOrder order, HttpServletResponse response) throws IOException {
        String fileName = order.getFileName() + ExcelType.XLS.code();

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        OutputStream outputStream = response.getOutputStream();
        wb.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }


    /**
     * 读取文件获取workbook
     * @param order
     * @return
     * @throws IOException
     */
    public static Workbook getWorkBook(ExcelOrder order)throws IOException {
        //获得文件
        File file = order.getFile();
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            //获取excel文件的io流
            InputStream is = new FileInputStream(file);
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if(ExcelType.XLS.equals(order.getType())){
                //2003
                workbook = new HSSFWorkbook(is);
            }else {
                //2007
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
        return workbook;
    }

    /**
     * 获取单元格值
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell){
        String cellValue = "";
        if(cell == null){
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况
        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        //判断数据的类型
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_NUMERIC: //数字
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK: //空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }




    public static void main(String[] args) throws Exception {

//        List list = new ArrayList();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
//
//        Student user1 = new Student(1, "张三", 16, df.parse("1997-03-12"));
//        Student user2 = new Student(2, "李四", 17, df.parse("1996-08-12"));
//        Student user3 = new Student(3, "王五", 26, df.parse("1985-11-12"));
//        list.add(user1);
//        list.add(user2);
//        list.add(user3);
//
//
//        ExcelUtil.generate(new ExcelOrder(Student.class, list, "students"));


        ExcelOrder<Student> order = new ExcelOrder<Student>(ExcelType.XLS,Student.class,"students",
                "F:/");
        order.setType(ExcelType.XLSX);

        List<Student> list = ExcelUtil.getExcelData(order);

        System.out.println(JSON.toJSON(list));
    }



}
