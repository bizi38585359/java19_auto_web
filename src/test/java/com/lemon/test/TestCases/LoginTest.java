package com.lemon.test.TestCases;

import com.lemon.test.Common.BaseTest;
import com.lemon.test.PageObject.IndexPage;
import com.lemon.test.PageObject.LoginPage;
import com.lemon.test.Utils.Constants;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest extends BaseTest {
    @Parameters({"browserName"})
    @BeforeMethod
    public void setUpTest(String browserName){
        //1、打开浏览器
        openBrowser(browserName);
        //2、访问登录的URL地址
        getUrl(Constants.INDEX_URL);
        //3、浏览器最大化
        browserMaxmize();
    }

    @Test
    public void testLoginSuccess() throws InterruptedException {
        //使用Page Object模式
        //1、实例化LoginPage对象
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickCloseFrame();
        loginPage.clickFirstLogin();
        //2、调用对象的行为
        loginPage.inputMobilePhone(Constants.LOGIN_USER);
        loginPage.inputPassword(Constants.LOGIN_PASSWORD);
        loginPage.clickSecondLogin();
        //断言是否登录成功，页面变化/提示信息
        //(1)根据跳转之后页面的地址-主页
        Thread.sleep(3000);
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl,Constants.MAIN_URL);
        //(2)跳转之后的主页是否有当前用户的昵称
        IndexPage indexPage = new IndexPage(driver);
        String userName = indexPage.getLoginName();
        Assert.assertEquals(userName,Constants.LOGIN_USER_NAME);
    }
    @Test(dataProvider = "getLoginFailureData01")
    public void testLoginFailure01(String phone,String password,String expectedValue){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickCloseFrame();
        loginPage.clickFirstLogin();
        loginPage.inputMobilePhone(phone);
        loginPage.inputPassword(password);
        loginPage.clickSecondLogin();
        //断言
        Assert.assertEquals(loginPage.getMobileErrorInfoText(),expectedValue);
    }

    @Test(dataProvider = "getLoginFailureData02")
    public void testLoginFailure02(String phone,String password,String expectedValue){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickCloseFrame();
        loginPage.clickFirstLogin();
        loginPage.inputMobilePhone(phone);
        loginPage.inputPassword(password);
        loginPage.clickSecondLogin();
        //断言
        boolean state = loginPage.getPwdErrorInfoText().contains(expectedValue);
        Assert.assertTrue(state);
    }

    @DataProvider
    public Object[][] getLoginFailureData01(){
        Object[][] datas = {{"", "cs8888.", "账号不能为空"},
                {"123123", "cs8888.", "用户不存在"}
        };
        return datas;
    }

    @DataProvider
    public Object[][] getLoginFailureData02(){
        Object[][] datas = {{"13590408096", "", "密码不能为空"},
                {"13590408096", "123", "密码有效长度是6到30个字符"},
                {"13590408096", "123456", "密码错误, 你还可以尝试"},
        };
        return datas;
    }

    @AfterMethod
    public void tearDownTest(){
        browserQuit();
    }
}
