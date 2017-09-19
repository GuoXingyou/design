package com.study.strategy.demo.enums;

/**
 * @Author: Jax
 * @Date: 2017-09-2017/9/12-14:40
 * @Desc:
 **/
public enum Solution {

    FIXED_DISCOUNT_75("FIXED_DISCOUNT_75","固定75折"),

    BUY_MORE_1("BUY_MORE_1","多买多送方案1")

    ;

    private String code;

    private String message;

    private Solution(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }

    public static Solution getByCode(String code) {
        for (Solution result : values()) {
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
        for (Solution _enum : values()) {
            list.add(_enum.code());
        }
        return list;
    }

    /**
     * 获取全部枚举
     *
     * @return List<ListType>
     */
    public static java.util.List<Solution> getAllEnum() {
        java.util.List<Solution> list = new java.util.ArrayList<Solution>(values().length);
        for (Solution _enum : values()) {
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
        for (Solution _enum : values()) {
            list.add(_enum.message);
        }
        return list;
    }

}
