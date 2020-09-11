package com.lemon.test.TestCases;

import com.lemon.test.Common.BaseTest;
import com.lemon.test.PageObject.JoinClassPage;
import com.lemon.test.PageObject.LoginPage;
import com.lemon.test.Utils.Constants;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Collections;

public class JoinClassTest extends BaseTest {
    private Logger logger = Logger.getLogger(JoinClassTest.class);
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
    public void joinClassOperation(String inputClassCode,String expectedJoinClass,String inputPassword,String expectedQuitClass){
        //（1）实例化LoginPage对象
        JoinClassPage joinClassPage = new JoinClassPage(driver);
        //判断第一个课程是否为考核项目
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(joinClassPage.isExamClassVisible() == true){
            //第一个课程点击更多按钮
            joinClassPage.ClickMore();
            //点击更多-退课按钮
            joinClassPage.ClickQuitClass();
            //输入登录密码验证
            joinClassPage.InputPassword(Constants.LOGIN_PASSWORD);
            //点击退课按钮
            joinClassPage.ClickQuitClassBtn();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //（2）调用对象的行为
        //点击加入课程
        joinClassPage.JoinClass();
        //输入课程加课验证码
        joinClassPage.InputClassCode(inputClassCode);
        //点击加入按钮
        joinClassPage.ClickJoinClass();
        if(expectedJoinClass.equals(Constants.JOIN_CLASS_SUCCESS)){
            //加入课堂弹出toast提示内容判断
            Assert.assertEquals(joinClassPage.JoinClassSuccess(),expectedJoinClass);
            //点击第一个课程标题
            joinClassPage.ClickCheckClassExam();
            //点击菜单-课堂，返回上一个页面
            joinClassPage.ClickMenu();
            joinClassPage.ClickClass();
            //进行退出课堂操作
            quitClassOperation(inputPassword,expectedQuitClass);
        }else{
            //弹出toast提示内容判断
            Assert.assertEquals(joinClassPage.JoinClassFailure(),expectedJoinClass);
        }
    }

    //退出课程操作
    public void quitClassOperation(String loginpassword,String expectedQuitClass){
        //调用对象的行为
        JoinClassPage joinClassPage = new JoinClassPage(driver);
        //第一个课程点击更多按钮
        joinClassPage.ClickMore();
        //点击更多-退课按钮
        joinClassPage.ClickQuitClass();
        //弹框确定要退课内容判断
        Assert.assertEquals(joinClassPage.SureToQuitClass(),Constants.CLASS_TITLE);
        //输入登录密码验证
        joinClassPage.InputPassword(loginpassword);
        //点击退课按钮
        joinClassPage.ClickQuitClassBtn();
        if(expectedQuitClass.equals(Constants.QUIT_CLASS_SUCCESS)){
            //退课成功后toast提示内容判断
            Assert.assertEquals(joinClassPage.QuitClassSuccessToast(),expectedQuitClass);
        }else{
            //退课失败后toast提示内容判断
            Assert.assertEquals(joinClassPage.QuitClassFailureToast(),expectedQuitClass);
        }

    }

    @Test(dataProvider = "JoinClassData")
    public void testJoinClass(String inputClassCode,String expectedJoinClass,String inputPassword,String expectedQuitClass){
        //题目一：1.1 课堂：加入班级、进入班级、 退课
        //进行加入课堂操作
        joinClassOperation(inputClassCode,expectedJoinClass,inputPassword,expectedQuitClass);
        logger.info("1.1考核作业测试结束");
    }

    @DataProvider
    public Object[][] JoinClassData(){
        Object[][] datas = {{"1", "加课验证码必须是6位字符", "不用密码", "不用退课"},
                {"123456",  "该加课码不存在或者已经失效", "不用密码", "不用退课"},
                {"1中文@#￥$%asd",  "该加课码不存在或者已经失效", "不用密码", "不用退课"},
                {"7J6JC7",  "加入课堂成功", "123456", "密码错误"},
                {"7J6JC7",  "加入课堂成功", "cs8888.", "课程退课成功"}
        };
        return datas;
    }

    @AfterMethod
    public void tearDownTest(){
        browserQuit();
    }
}
