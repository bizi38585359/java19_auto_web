package com.lemon.test.PageObject;

import com.lemon.test.Common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JoinClassPage extends BasePage {
    public JoinClassPage(WebDriver driver){
        super(driver);
    }

    //加入课程
    private By joinclassBy = By.xpath("//div[@class='ktcon1 cl clearfix']/div[1]");
    //输入课程加课验证码
    private By inputclasscodeBy = By.xpath("//input[@placeholder='请输入课程加课验证码']");
    //加入
    private By clickjoinclassBy = By.xpath("//a[text()='加入']");
    //加入课堂成功
    private By joinclasssuccessBy = By.xpath("//*[@id='show-tip']/span");
    //点击菜单
    private By clickmenuBy = By.xpath("/html/body/div[5]/ul[1]/li[1]/a/i");
    //点击课堂
    private By clickclassBy = By.xpath("//a[@class='leftnavclass']");
    //点击新加入课程-更多
    private By clickmoreBy = By.xpath("//*[@id='viewer-container-toplists']/dl[1]/dt/a/span");
    //点击新加入课程-退课
    private By clickquitclassBy = By.xpath("//*[@id='viewer-container-toplists']/dl[1]/dt/ul/li[1]");
    //输入登录密码验证
    private By inputpasswordBy = By.xpath("//input[@placeholder='请输入登录密码验证']");
    //弹出toast提示
    private By joinclassfailureBy = By.xpath("//*[@id='error-tip']/span");
    //弹框提示确定要退课内容
    private By quitclasscontentBy = By.xpath("//span[text()='Java-Web阶段考核项目专用']");
    //退课
    private By quitclassBy = By.xpath("//a[@class='btn btn-positive'][text()='退课']");
    //退课后toast提示
    private By quitclasssuccesstoastBy = By.xpath("//*[@id='show-tip']/span");
    //Java-Web阶段考核项目专用课程内容
    private By checkclassexamBy = By.xpath("//a[@title='Java-Web阶段考核项目专用']");
    //查看作业
    private By checkhomeworkBy = By.xpath("//a[text()='作业']");
    //获取课程标题
    private By classtitleBy = By.xpath("//h1[text()='Java-Web阶段考核项目专用']");

    //点击加入课程
    public void JoinClass(){
        click(joinclassBy);
    }

    //输入课程加课验证码
    public void InputClassCode(String classcode){
        input(inputclasscodeBy,classcode);
    }

    //点击加入
    public void ClickJoinClass(){
        click(clickjoinclassBy);
    }

    //弹出toast提示加入课堂成功
    public String JoinClassSuccess(){
        return getElementText(joinclasssuccessBy);
    }

    //点击菜单按钮
    public void ClickMenu(){
        click(clickmenuBy);
    }

    //点击课堂按钮
    public void ClickClass(){
        click(clickclassBy);
    }

    //点击新加入课程-更多按钮
    public void ClickMore(){
        click(clickmoreBy);
    }

    //点击新加入课程-退课按钮
    public void ClickQuitClass(){
        click(clickquitclassBy);
    }

    //输入登录密码验证-退课
    public void InputPassword(String password){
        input(inputpasswordBy,password);
    }

    //弹出toast提示加入课程失败
    public String JoinClassFailure(){
        return getElementText(joinclassfailureBy);
    }

    //弹框提示确定要退课内容
    public String SureToQuitClass(){
        return getElementText(quitclasscontentBy);
    }

    //点击新加入课程-退课按钮
    public void ClickQuitClassBtn(){
        click(quitclassBy);
    }

    //退课后toast提示失败
    public String QuitClassFailureToast(){
        //加入课堂和退出课堂失败时弹出toast提示xpath一样
        return getElementText(joinclassfailureBy);
    }

    //退课后toast提示成功
    public String QuitClassSuccessToast(){
        return getElementText(quitclasssuccesstoastBy);
    }

    //点击Java-Web阶段考核项目专用课程
    public void ClickCheckClassExam(){
        click(checkclassexamBy);
    }

    //点击作业按钮
    public void ClickCheckHomeWork(){
        click(checkhomeworkBy);
    }

    //查看课程标题
    public void CheckClassTitle(){
        getElementText(classtitleBy);
    }

    //判断第一个课程是否为考核项目
    public boolean isExamClassVisible(){
        return isElementExist(checkclassexamBy);
    }
}
