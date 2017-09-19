package com.study.simplefactory.demo.enums;

/**
 * @Author: Jax
 * @Date: 2017-09-2017/9/8-14:28
 * @Desc:
 **/
public enum OperateEnum {

    ADD("+","加法"),

    SUB("-","减法"),

    MUL("*","乘法"),

    DIV("/","除法")

    ;

    private String code;

    private String message;

    private OperateEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }

    public static OperateEnum getByCode(String code) {
        for (OperateEnum result : values()) {
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
        for (OperateEnum _enum : values()) {
            list.add(_enum.code());
        }
        return list;
    }

    /**
     * 获取全部枚举
     *
     * @return List<ListType>
     */
    public static java.util.List<OperateEnum> getAllEnum() {
        java.util.List<OperateEnum> list = new java.util.ArrayList<OperateEnum>(values().length);
        for (OperateEnum _enum : values()) {
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
        for (OperateEnum _enum : values()) {
            list.add(_enum.message);
        }
        return list;
    }

}
