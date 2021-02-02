package com.jaming.java.dy;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BrowserPic {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "F:\\chromedriver76-0-3809-126\\chromedriver88.0.4324.27.exe");

        ChromeOptions co = new ChromeOptions();
        co.addArguments("--incognito");
        co.addArguments("-headless");
        co.addArguments("--ignore-certificate-errors");
        co.setHeadless(true);

        ChromeDriver driver = new ChromeDriver(co);
//        driver.
        driver.get("https://localhost:8443/test/");
        System.out.println("----打开页面----");

        try {
            WebElement securebutton = driver.findElement(By.id("details-button"));
            securebutton.click();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement gobutton = driver.findElement(By.id("proceed-link"));
            gobutton.click();
        }catch (Exception e){
            System.out.println("----不需要点击安全页面----");
        }
        WebElement account = driver.findElement(By.id("account"));
        account.clear();
        account.sendKeys("test001");
        System.out.println("----输入账号----");
        WebElement password = driver.findElement(By.id("password"));
        password.clear();
        password.sendKeys("111111");
        System.out.println("----输入密码----");

//        String filename1 = "D:\\b.png";
//        screenshot(driver,filename1);

        //登录
        WebElement loginbutton = driver.findElement(By.id("button_submit"));
        loginbutton.click();
        System.out.println("----登录----");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(10000);
//        String filename2 = "D:\\c.png";
//        screenshot(driver,filename2);

        driver.get("https://localhost:8443/test/aaa.do?plantuserid=1958151109&isFollow=1&wholeNetworkType=null");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("----打开个人详情页面 sleep 10s----");
        Thread.sleep(10000);
        List<WebElement> elementList = driver.findElementsByLinkText("分析数据");
        elementList.get(0).click();
        System.out.println("----点击分析数据tap----");
        Thread.sleep(10000);

        driver.manage().window().maximize();
        System.out.println("----页面最大化----");
        Thread.sleep(5000);
        //拖动滚轮到底部
//        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
//        Thread.sleep(5000);
//        System.out.println("----滑动滚轮----");

        String filename = "D:\\a.png";
        screenshot(driver,filename);
        System.out.println("----保存图片成功----");

        driver.quit();//退出浏览器
    }

    private static File screenshot(WebDriver driver,String filename) throws Exception {
        try {
            /**
             * WebDriver自带了一个智能等待的方法。
             dr.manage().timeouts().implicitlyWait(arg0, arg1）；
             Arg0：等待的时间长度，int 类型 ；
             Arg1：等待时间的单位 TimeUnit.SECONDS 一般用秒作为单位。
             */
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Thread.sleep(10000);//等等页面加载完成
        /**
         * dr.quit()和dr.close()都可以退出浏览器,简单的说一下两者的区别：第一个close，
         * 如果打开了多个页面是关不干净的，它只关闭当前的一个页面。第二个quit，
         * 是退出了所有Webdriver所有的窗口，退的非常干净，所以推荐使用quit最为一个case退出的方法。
         */
        byte[] imageBytes = (byte[]) ((ChromeDriver) driver).getScreenshotAs(new OutputType<Object>() {
            public Object convertFromBase64Png(String s) {
                try {
                    return (new BASE64Decoder()).decodeBuffer(s);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            public Object convertFromPngBytes(byte[] bytes) {
                return bytes;
            }
        });
        ByteArrayInputStream bytes = new ByteArrayInputStream(imageBytes);
        BufferedImage image = ImageIO.read(bytes);
        File file = new File(filename);
        ImageIO.write(image, "png", file);
        return file;
    }
}
