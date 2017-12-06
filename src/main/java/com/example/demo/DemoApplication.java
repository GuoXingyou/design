package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {


	public static void main(String[] args) throws InterruptedException {

		System.out.println("虽然我们无法回到从前,但该有的梦想还是得继续!你们也是这样想的吧?");
		System.out.println("——东尼大木");
		SpringApplication.run(DemoApplication.class, args);


	}
}
