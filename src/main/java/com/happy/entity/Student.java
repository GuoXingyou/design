package com.happy.entity;

import com.happy.util.excel.Excel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/10/18/16:52
 * @Desc:
 **/
@Getter
@Setter
public class Student {





    @Excel(index = 0, name = "ID")
    private int id;

    @Excel(index = 1, name = "名字")
    private String name;

    @Excel(index = 2, name = "年龄")
    private int age;

    @Excel(index = 3, name = "生日", dateFmt = "yyyy-MM-dd")
    private Date birthday;

    public Student(int id, String name, int age, Date birthday) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Boolean.valueOf(true);
    }
}
