package uk.co.iankent.Asserted.Web;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Ian
 * Date: 18/09/12
 * Time: 19:21
 * To change this template use File | Settings | File Templates.
 */
public class Browser implements WebDriver {

    protected Logger logger = Logger.getLogger(getClass());
    protected WebDriver webDriver;

    public static Browser getBrowser() {
        return new Browser(new FirefoxDriver());
    }

    public Browser(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void get(String url) {
        webDriver.get(url);
    }

    public void close() {
        webDriver.close();
    }

    @Override
    public String getCurrentUrl() {
        logger.trace("Current URL is " + webDriver.getCurrentUrl());
        return webDriver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        logger.trace("Current title is " + webDriver.getTitle());
        return webDriver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return webDriver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return webDriver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return webDriver.getPageSource();
    }

    @Override
    public void quit() {
        webDriver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return webDriver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return webDriver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return webDriver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return webDriver.navigate();
    }

    @Override
    public Options manage() {
        return webDriver.manage();
    }
}
