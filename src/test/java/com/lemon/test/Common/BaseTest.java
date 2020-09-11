package com.lemon.test.Common;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public class BaseTest {
    private Logger logger = Logger.getLogger(BaseTest.class);
    public WebDriver driver;
    public void openBrowser(String browserName){
        if(browserName.equals("chrome")){
            System.setProperty("webdriver.chrome.driver","src\\test\\resources\\chromedriver.exe");
            ChromeDriver chromeDriver = new ChromeDriver();
            driver = chromeDriver;
            logger.info("===================打开chrome浏览器===================");
        }else if(browserName.equals("firefox")){
            System.setProperty("webdriver.gecko.driver","src\\test\\resources\\geckodriver.exe");
            FirefoxDriver firefoxDriver = new FirefoxDriver();
            driver = firefoxDriver;
            logger.info("===================打开Firefox浏览器===================");
        }else if(browserName.equals("ie")){
            //取消IE安全设置（忽略IE的Protected Mode的设置）
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,true);
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
            System.setProperty("webdriver.ie.driver","src\\test\\resources\\IEDriverServer.exe");
            InternetExplorerDriver iedriver = new InternetExplorerDriver(capabilities);
            driver = iedriver;
            logger.info("===================打开IE浏览器===================");
        }
    }

    //访问对应的页面
    public void getUrl(String urlPath){
        logger.info("访问对应的URL页面【" + urlPath + "】");
        driver.get(urlPath);
    }
    //最大化浏览器
    public void browserMaxmize(){
        logger.info("浏览器最大化");
        driver.manage().window().maximize();
    }
    //浏览器全屏
    public void browserFullScreen(){
        logger.info("浏览器全屏");
        driver.manage().window().fullscreen();
    }
    //浏览器前进操作
    public void browserForward(){
        logger.info("浏览器前进");
        driver.navigate().forward();
    }
    //浏览器后退操作
    public void browserBackrward(){
        logger.info("浏览器后退");
        driver.navigate().back();
    }
    //浏览器刷新操作
    public void browserRefresh(){
        logger.info("浏览器刷新");
        driver.navigate().refresh();
    }
    //浏览器退出操作
    public void browserQuit(){
        logger.info("浏览器退出");
        driver.quit();
    }
}
