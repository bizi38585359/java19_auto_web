package com.lemon.test.TestCases;

import com.lemon.test.Common.BaseTest;
import com.lemon.test.PageObject.JoinClassPage;
import com.lemon.test.PageObject.LoginPage;
import com.lemon.test.PageObject.UploadHomeworkPage;
import com.lemon.test.Utils.Constants;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

public class UploadHomeworkTest extends BaseTest {
    private Logger logger = Logger.getLogger(UploadHomeworkTest.class);
    @Parameters({"browserName"})
    @BeforeMethod
    public void setupTest(String browserName){
        //1、打开浏览器
        openBrowser(browserName);
        //2、访问登录页面的URL地址
        getUrl(Constants.USER_LOGIN_URL);
        //3、浏览器最大化
        browserMaxmize();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputMobilePhone(Constants.LOGIN_USER);
        loginPage.inputPassword(Constants.LOGIN_PASSWORD);
        loginPage.clickSecondLogin();
    }

    //加入课程操作
    public void joinClassOperation(String inputClassCode, String expectedJoinClass){
        //（1）实例化LoginPage对象
        UploadHomeworkPage uploadHomeworkPage = new UploadHomeworkPage(driver);
        //判断第一个课程是否为考核项目
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(uploadHomeworkPage.isExamClassVisible() == false){
            //（2）调用对象的行为
            //点击加入课程
            uploadHomeworkPage.JoinClass();
            //输入课程加课验证码
            uploadHomeworkPage.InputClassCode(inputClassCode);
            //点击加入按钮
            uploadHomeworkPage.ClickJoinClass();
            //加入课堂弹出toast提示内容判断
            Assert.assertEquals(uploadHomeworkPage.JoinClassSuccess(),expectedJoinClass);
        }
        //点击第一个课程标题
        uploadHomeworkPage.ClickCheckClassExam();
    }

    @Test
    public void testUploadHomework() throws IOException, InterruptedException {
        //进行加入课程操作
        joinClassOperation(Constants.CLASSCODE,Constants.JOIN_CLASS_SUCCESS);
        UploadHomeworkPage uploadHomeworkPage = new UploadHomeworkPage(driver);
        //1.2 作业：上传作业、作业留言、查看作业提交状态
        //查看作业
        uploadHomeworkPage.CheckHomework();
        //点击上传作业
        uploadHomeworkPage.UploadHomework();
        Thread.sleep(5000);
        //判断更新按钮是否存在
        boolean buttonNameFlag = uploadHomeworkPage.isUpdateSubmitBtnVisible();
        logger.info(buttonNameFlag);
        Thread.sleep(2000);
        //判断是否为更新提交按钮，已提交后更新作业会弹框确认
        if(buttonNameFlag == true){
            //点击更新提交按钮
            uploadHomeworkPage.UpdateSubmitHomework();
            Thread.sleep(2000);
            //弹框提示更新作业，点击确定按钮
            uploadHomeworkPage.ConfirmUpdateHomework();
            Thread.sleep(2000);
            //删除前面已上传作业
            uploadHomeworkPage.DeleteHomework();
        }
        //点击添加作业文件
        uploadHomeworkPage.AddHomeworkFile();
        Thread.sleep(2000);
        //文件上传，得到java运行时对象
        Runtime runtime = Runtime.getRuntime();
        //执行本地的autoit的exe文件
        runtime.exec("src\\test\\resources\\upfile.exe");
        Thread.sleep(3000);
        //上传作业后提交成功
//        Alert alert = driver.switchTo().alert();
//        Assert.assertEquals(alert.getText(),Constants.UPLOADSUCCESS);
//        alert.accept();
        //点击留言
        uploadHomeworkPage.ClickMessage();
        //添加留言
        uploadHomeworkPage.CommitMessage(Constants.INPUTMESSAGE);
        //点击保存按钮
        uploadHomeworkPage.ClickSaveMessage();
        //判断是否点击提交还是更新提交按钮
        if(buttonNameFlag == true){
            //重新提交作业后再次点击更新提交按钮
            uploadHomeworkPage.UpdateSubmitHomeworkAgain();
        }else{
            //上传作业成功后点击提交
            uploadHomeworkPage.SubmitHomework();
        }
        Thread.sleep(2000);
        //获取弹框内容判断更新作业是否成功
        Assert.assertEquals(uploadHomeworkPage.SubmitHomeworkSuccess(),Constants.UPLOADSUCCESS);
        //点击知道了
        uploadHomeworkPage.ClickKnowIt();
        //点击查看日志
        uploadHomeworkPage.ClickCheckLog();
        //获取提交日志内容
        logger.info("查看日志内容：" + uploadHomeworkPage.GetCommitLog());
        logger.info("1.2考核作业测试结束");
        //1.3 私信：发送私信
        //点击私信按钮
        uploadHomeworkPage.ClickPrivateLetter();
        //切换window  -- 句柄handle——>窗口唯一标识
        //通过新的窗口的句柄来切换
        //获取当前打开的所有窗口对应的句柄
        Set<String> handles = driver.getWindowHandles();
        for (String handle: handles){
            //找到新窗口的句柄--根据窗口的特殊标识-title（推荐）、URL
            //如果当前driver的焦点在对应的窗口的话，那么它获取到的title也是对应窗口的
            if(driver.getCurrentUrl().equals(Constants.PRIVATELETTER_URL)){
                break;
            }else{
                //如果当前driver的焦点不在对应的窗口的话
                driver.switchTo().window(handle);
            }
        }
        uploadHomeworkPage.InputMessage(Constants.INPUTMESSAGE);
        uploadHomeworkPage.ClickSentMessage();
        logger.info("1.3考核作业测试结束");
    }

    @AfterMethod
    public void tearDownTest(){
        browserQuit();
    }
}
