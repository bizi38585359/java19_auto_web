package com.lemon.test.Common;


import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    private Logger logger = Logger.getLogger(BasePage.class);
    public WebDriver driver;
    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    //等待元素可见二次封装
    public WebElement waitElementVisible(By by){
        WebDriverWait webDriverWait = new WebDriverWait(driver,10);
        return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    //等待元素可被点击二次封装
    public WebElement waitElementClickable(By by){
        WebDriverWait webDriverWait = new WebDriverWait(driver,10);
        return webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
    }
    //显示等待并且点击有元素进行封装
    public void click(By by){
        waitElementClickable(by).click();
        logger.info("点击了元素【" + by + "】");
    }
    //显示等待并且给元素输入数据
    public void input(By by,String data){
        waitElementVisible(by).clear();
        waitElementVisible(by).sendKeys(data);
        logger.info("往元素【" + by + "】里面输入了数据【" + data + "】");
    }
    //显示等待并且获取元素文本值
    public String getElementText(By by){
        String elementText = waitElementVisible(by).getText();
        logger.info("获取元素【" + by + "】文本值【" + elementText + "】");
        return elementText;
    }
    //显示等待并且获取元素某一个属性的值
    public String getElementAttributeValue(By by,String attributeName){
        String elementValue = waitElementVisible(by).getAttribute(attributeName);
        logger.info("获取元素【" + by + "】属性名【" + attributeName + "】属性值【" + elementValue + "】");
        return elementValue;
    }
    //显示等待并且等待元素可见
    public boolean isElementVisible(By by){
        boolean isDisplay = waitElementVisible(by).isDisplayed();
        logger.info("获取元素【" + by + "】可见状态【" + isDisplay + "】");
        return isDisplay;
    }
    //切换至iframe
    public void switchIFrame(By by){
        WebDriverWait webDriverWait = new WebDriverWait(driver,10);
        webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
        logger.info("切换iframe【" + by + "】");
    }
    //输入按键操作
    public void inputKey(By by , Keys keys){
        logger.info("给元素【" + by + "】输入按键操作【" + keys.name() + "】");
        waitElementVisible(by).sendKeys(keys);
    }
    //元素是否存在
    public boolean isElementExist(By by){
        try{
//            waitElementVisible(by).isDisplayed();
            driver.findElement(by);
            logger.info("元素【" + by + "】存在");
            return true;
        }catch(NoSuchElementException e){
            return false;
        }
//        boolean elementValue = waitElementVisible(by).isDisplayed();
//        logger.info("元素【" + by + "】是否存在：" + elementValue);
//        return elementValue;
    }
}
