package com.example.demo.web.controller;


import com.example.demo.web.anotation.TestAno;
import com.google.common.collect.Lists;
import com.happy.util.excel.ExcelOrder;
import com.happy.util.excel.ExcelUtil;
import com.happy.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/10/17/9:23
 * @Desc:
 **/
@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/say")
    @ResponseBody
    @TestAno
    public void sayHello(HttpServletResponse response) throws ParseException, IOException {

        List list = new ArrayList();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");

        Student user1 = new Student(1, "张三", 16, df.parse("1997-03-12"));
        Student user2 = new Student(2, "李四", 17, df.parse("1996-08-12"));
        Student user3 = new Student(3, "王五", 26, df.parse("1985-11-12"));
        list.add(user1);
        list.add(user2);
        list.add(user3);



        ExcelUtil.export(new ExcelOrder<Student>(Student.class, list,
                        "student"), response);

    }

//    @TestAno
    public String think(){
        return "I'm thinking...";
    }


    public static void main(String[] args) {
        List<Optional<Student>> students = Lists.newArrayList();

        Student student = new Student("小民");
        Student student1 = new Student("小平");
        Student student2 = new Student("小来");

        students.add(Optional.of(student));
        students.add(Optional.of(student1));
        students.add(Optional.of(student2));
        students.add(Optional.ofNullable(null));




        students.forEach(Optional::get);


        students.stream().filter(optional -> optional.isPresent()).forEach(optional -> System.out
                .println(optional.get().getName()));

//        students.forEach(optional -> optional.orElseThrow(NullPointerException::new ));
    }

}
