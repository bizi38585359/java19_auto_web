package com.lemon.test.PageObject;

import com.lemon.test.Common.BasePage;
import com.lemon.test.Utils.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IndexPage extends BasePage {
    public IndexPage(WebDriver driver){
        super(driver);
    }
    //主页登录昵称元素
    private By usernameBy = By.xpath("//img[@title='" + Constants.LOGIN_USER_NAME +"']");
    //主页退出元素
    private By quitBy = By.xpath("//a[text()='退出账户']");
    //课堂派首页点击登录按钮
    private By clickloginBy = By.className("login");
    //获取登录昵称
    public String getLoginName(){
        return getElementAttributeValue(usernameBy,"title");
    }
    //退出账号
    public void clickQuit(){
        click(quitBy);
    }
    //课堂派首页点击登录按钮
    public void clickFirstLogin(){
        click(clickloginBy);
    }
}
