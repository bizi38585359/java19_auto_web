package com.lemon.test.Listener;

import com.lemon.test.Common.BaseTest;
import com.lemon.test.Utils.ScreenShotUtil;
import io.qameta.allure.Attachment;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;

public class TestResultListener implements IHookable {
    @Override
    public void run(IHookCallBack iHookCallBack, ITestResult iTestResult){
        //每一条用例里面的@Test注解标注的测试方法都会被当前这个run替换掉
        //需求：正常执行测试方法，并且需要知道用例执行结果
        iHookCallBack.runTestMethod(iTestResult);
        //iTestResult保存用例执行结果，getThrowable-->获取异常
        if(iTestResult.getThrowable() != null){
            //用例结果统计了异常信息（包括用例失败）
            //iTestResult提供了一个方法getInstance,这个方法可以获取当前测试类的实例（对象）
            //写法一：
            //Object object = iTestResult.getInstance();
            //LoginTest loginTest = (LoginTest)object;
            //写法二
            //问题点：只能适用于LoginTest测试类的执行情况，如果换了其他测试类，这里会报错
            //LoginTest loginTest = (BaseTest)iTestResult.getInstance();
            //ScreenShotUtil.TestScreenShot(loginTest.driver);
            BaseTest baseTest = (BaseTest)iTestResult.getInstance();
            byte[] arr = ScreenShotUtil.TestScreenShotByte(baseTest.driver);
            //把截图作为附件添加到Allure报表中
            saveScreenshot(arr);
        }
    }
    //value:附件名 type:附件类型
    //Alluer报表添加附件注解
    @Attachment
    public byte[] saveScreenshot(byte[] screenShot){
        return screenShot;
    }
}
