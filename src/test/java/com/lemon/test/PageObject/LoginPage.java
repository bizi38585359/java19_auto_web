package com.lemon.test.PageObject;

import com.lemon.test.Common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver){
        super(driver);
    }
    //课堂派首页点击登录按钮
    private By clickfirstloginBy = By.className("login");
    //手机号输入框
    private By mobilephoneBy = By.xpath("//input[@name='account']");
    //密码输入框
    private By passwordBy = By.xpath("//input[@name='pass']");
    //账号登录页面点击登录按钮
    private By clicksecondloginBy = By.xpath("//*[@id='login']/div[2]/a[1]");
    //手机号输入错误提示内容
    private By mobileerrorinfoBy = By.xpath("//*[@id='login']/div[2]/div[1]/p");
    //密码输入错误提示内容
    private By pwderrorinfoBy = By.xpath("//p[@class='error-tips']");
    //新版本提醒弹框获取关闭按钮
    private By clickcloseframeBy = By.xpath("//*[@id='layui-layer1']/span/a");


    //课堂派首页点击登录按钮
    public void clickFirstLogin(){
        click(clickfirstloginBy);
    }
    //输入手机号码
    public void inputMobilePhone(String mobilephone){
        input(mobilephoneBy,mobilephone);
    }
    //输入密码
    public void inputPassword(String password){
        input(passwordBy,password);
    }
    //手机号获取错误提示信息内容
    public String getMobileErrorInfoText(){
        return getElementText(mobileerrorinfoBy);
    }
    //密码获取错误提示信息内容
    public String getPwdErrorInfoText(){
        return getElementText(pwderrorinfoBy);
    }
    //账号登录页面点击登录按钮
    public void clickSecondLogin(){
        click(clicksecondloginBy);
    }
    //关闭新版本提醒弹框
    public void clickCloseFrame(){
        click(clickcloseframeBy);
    }
}
