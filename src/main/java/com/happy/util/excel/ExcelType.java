package com.happy.util.excel;

/**
 * @Author: Jax
 * @Date: 2017-10-2017/10/19-10:30
 * @Desc:
 **/
public enum ExcelType {

    XLS(".xls","XLS格式"),

    XLSX(".xlsx","XLSX格式")

    ;

    private String code;

    private String message;

    private ExcelType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }

    public static ExcelType getByCode(String code) {
        for (ExcelType result : values()) {
            if (result.code().equals(code)) {
                return result;
            }
        }
        return null;
    }

    /**
     * 获取全部枚举值
     *
     * @return List<String>
     */
    public static java.util.List<String> getAllEnumCode() {
        java.util.List<String> list = new java.util.ArrayList<String>(values().length);
        for (ExcelType _enum : values()) {
            list.add(_enum.code());
        }
        return list;
    }

    /**
     * 获取全部枚举
     *
     * @return List<ListType>
     */
    public static java.util.List<ExcelType> getAllEnum() {
        java.util.List<ExcelType> list = new java.util.ArrayList<ExcelType>(values().length);
        for (ExcelType _enum : values()) {
            list.add(_enum);
        }
        return list;
    }

    /**
     * 获取全部枚举描述
     *
     * @return List<ListType>
     */
    public static java.util.List<String> getAllEnumMessage() {
        java.util.List<String> list = new java.util.ArrayList<String>(values().length);
        for (ExcelType _enum : values()) {
            list.add(_enum.message);
        }
        return list;
    }

}
