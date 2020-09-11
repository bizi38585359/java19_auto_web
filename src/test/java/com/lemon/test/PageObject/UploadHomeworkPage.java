package com.lemon.test.PageObject;

import com.lemon.test.Common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UploadHomeworkPage extends BasePage {
    public UploadHomeworkPage(WebDriver driver){
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
    //点击新加入课程-更多
    private By clickmoreBy = By.xpath("//*[@id='viewer-container-toplists']/dl[1]/dt/a/span");
    //点击新加入课程-退课
    private By clickquitclassBy = By.xpath("//*[@id='viewer-container-toplists']/dl[1]/dt/ul/li[1]");
    //输入登录密码验证
    private By inputpasswordBy = By.xpath("//input[@placeholder='请输入登录密码验证']");
    //弹出toast提示
    private By joinclassfailureBy = By.xpath("//*[@id='error-tip']/span");
    //查看作业
    private By checkhomeworkBy = By.xpath("//a[text()='作业']");
    //上传作业
    private By uploadhomeworkBy = By.xpath("//*[@id='homework-page']/div[2]/div[1]/div/div/div[2]/div[2]/a");
    //添加作业文件
    private By addhomeworkfileBy = By.xpath("//i[@class='sc-btn iconfont iconxinjian1 webuploader-container']/div[2]");
    //提交
    private By submitBy = By.xpath("//*[@id='viewer-handup']/div[1]/div[1]/a");
    //更新提交
    private By updatesubmitBy = By.xpath("//a[@class='new-tj1'][text()='更新提交']");////*[@id='viewer-handup']/div[1]/div[1]/a[1]
    //再次更新提交
    private By updatesubmitagainBy = By.xpath("//*[@id='viewer-handup']/div[1]/div[1]/a[2]");
    //弹框提示作业提交成功内容
    private By alertsubmitsuccessBy = By.xpath("//div[@class='weui_dialog_bd']");
    //弹框提示知道了
    private By knowitBy = By.xpath("//a[@class='weui_btn_dialog primary']");
    //更新作业弹框确定按钮
    private By comfirmBy = By.xpath("//*[@id='update-pop']/div[2]/a[2]");
    //更新提交作业删除前面上传作业
    private By deletehomeworkBy = By.xpath("//a[@class='cancel hide']");
    //退课
    private By quitclassBy = By.xpath("//a[@class='btn btn-positive'][text()='退课']");
    //退课后toast提示
    private By quitclasssuccesstoastBy = By.xpath("//*[@id='show-tip']/span");
    //Java-Web阶段考核项目专用课程内容
    private By checkclassexamBy = By.xpath("//a[@title='Java-Web阶段考核项目专用']");
    //点击留言
    private By clickmessageBy = By.xpath("//*[@id='mess1']/span[2]");
    //添加留言
    private By commitmessageBy = By.xpath("//*[@id='comment']");
    //保存留言
    private By savemessageBy = By.xpath("//*[@id='viewer-handup']/div[2]/div[4]/a");
    //查看日志
    private By checklogBy = By.xpath("//a[@class='togglesee']");
    //获取提交日志
    private By getcommitlogBy = By.xpath("//*[@id='logbox']/div/ul/li");
    //私信
    private By privateletterBy = By.xpath("//div[@class='privateLetter']/a");
    //私信页面输入框
    private By inputmessageBy = By.xpath("//*[@id='chat']/div[3]/div[3]/textarea");
    //点击发送
    private By sentmessageBy = By.xpath("//a[@class='btn btn-positive']");

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

    //查看作业
    public void CheckHomework(){
        click(checkhomeworkBy);
    }

    //上传作业
    public void UploadHomework(){
        click(uploadhomeworkBy);
    }

    //添加作业文件
    public void AddHomeworkFile(){
        click(addhomeworkfileBy);
    }

    //上传作业文件后提交
    public void SubmitHomework(){
        click(submitBy);
    }

    //上传作业文件后点击更新提交
    public void UpdateSubmitHomework(){
        click(updatesubmitBy);
    }

    //重新上传作业文件后再次点击更新提交
    public void UpdateSubmitHomeworkAgain(){
        click(updatesubmitagainBy);
    }

    //更新作业弹框提示点击确定按钮
    public void ConfirmUpdateHomework(){
        click(comfirmBy);
    }

    //更新作业时删除前面提交作业
    public void DeleteHomework(){
        click(deletehomeworkBy);
    }

    //弹框提示作业提交成功内容
    public String SubmitHomeworkSuccess(){
        return getElementText(alertsubmitsuccessBy);
    }

    //弹框提示作业提交成功后点击知道了
    public void ClickKnowIt(){
        click(knowitBy);
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

    //判断第一个课程是否为考核项目
    public boolean isExamClassVisible(){
        return isElementExist(checkclassexamBy);
    }

    //点击保存留言
    public void ClickMessage(){
        click(clickmessageBy);
    }

    //添加留言
    public void CommitMessage(String message){
        input(commitmessageBy,message);
    }

    //点击保存留言
    public void ClickSaveMessage(){
        click(savemessageBy);
    }

    //点击查看日志
    public void ClickCheckLog(){
        click(checklogBy);
    }

    //获取提交日志内容
    public String GetCommitLog(){
        return getElementText(getcommitlogBy);
    }

    //点击私信按钮
    public void ClickPrivateLetter(){
        click(privateletterBy);
    }

    //私信页面输入框输入
    public void InputMessage(String message){
        input(inputmessageBy,message);
    }

    //点击发送按钮
    public void ClickSentMessage(){
        click(sentmessageBy);
    }

    //判断上传作业页面是否为提交按钮
    public boolean isSubmitBtnVisible(){
        return isElementExist(submitBy);
    }

    //判断上传作业页面是否为更新提交按钮
    public boolean isUpdateSubmitBtnVisible(){
        return isElementExist(updatesubmitBy);
    }
}
