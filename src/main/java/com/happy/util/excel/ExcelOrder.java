package com.happy.util.excel;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/10/19/11:29
 * @Desc:
 **/
@Getter
@Setter
public class ExcelOrder<E> {

    private ExcelType type = ExcelType.XLS;

    private Class<?> clazz;

    private List list;

    private String fileName;
    
    private String filePath;


    public ExcelOrder(Class<?> clazz, List list, String fileName) {
        this.clazz = clazz;
        this.list = list;
        this.fileName = fileName;
    }

    public ExcelOrder(ExcelType type, Class<?> clazz, String fileName, String filePath) {
        this.type = type;
        this.clazz = clazz;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    /**
     * 获取文件完整路径
     * @return
     */
    public String getWholeFilePath(){

        return this.filePath + this.fileName + this.type.code();
    }

    public File getFile() throws IOException {

        File file = new File(getWholeFilePath());
        if(null == file){
            throw new IOException("找不到文件，请检查路径：" + getWholeFilePath());
        }

        String name = file.getName();
        if(!name.endsWith(ExcelType.XLS.code()) && !name.endsWith(ExcelType.XLSX.code())){
            throw new IOException(name + "不是excel文件");
        }

        return file;
    }

    public E newInstance() throws IllegalAccessException, InstantiationException {
        E e = (E) clazz.newInstance();
        return e;
    }
}
