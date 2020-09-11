package com.lemon.test.Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenShotUtil {
    //截图工具方法，截图的时候以File文件形势输出
    public static void TestScreenShotFile(WebDriver driver){
        //截图API-->takeScreenShot
        TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        //把File保存到本地目录，并且是图片格式（.jpg/.png）
        //保证每一次生成的图片名字都不一样-->时间戳
        //把图片生成在项目的根目录ScreenShot的目录
        //得到当前的时间戳
        long currentTime = System.currentTimeMillis();
        String picPath = System.getProperty("user.dir") + "\\" + "screenshot" + "\\" + currentTime + ".png";
        File targetFile = new File(picPath);
        //common-io
        try{
            FileUtils.copyFile(srcFile,targetFile);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static byte[] TestScreenShotByte(WebDriver driver){
        TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
        //OutputType.Bytes-->输入是字节数组
        byte[] arr = takesScreenshot.getScreenshotAs(OutputType.BYTES);
        return arr;
    }
}
