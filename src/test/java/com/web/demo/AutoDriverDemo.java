package com.web.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/13/11:15
 * @Desc:
 **/
public class AutoDriverDemo {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "F:/chromedriver_win32/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("http://www.baidu.com");

        WebElement kw = driver.findElement(By.id("kw"));
        kw.sendKeys("Hello,world.");
        kw.submit();

        WebElement su = driver.findElement(By.id("su"));
        su.click();

        System.out.println("The title of the page is" + driver.getTitle());

    }
}
