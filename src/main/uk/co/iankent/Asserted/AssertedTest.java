package uk.co.iankent.Asserted;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import uk.co.iankent.Asserted.Web.Browser;
import uk.co.iankent.Asserted.Web.Element.ElementBase;
import uk.co.iankent.Asserted.Web.PageBase;
import uk.co.iankent.Asserted.Web.PageMap;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 * User: Ian
 * Date: 16/09/12
 * Time: 19:12
 * To change this template use File | Settings | File Templates.
 */
public abstract class AssertedTest {

    protected Logger logger = Logger.getLogger(getClass());
    protected Browser browser;
    protected PageMap pageMap;

    protected PageBase currentPage;

    @Before
    public void before() {
        pageMap = PageMap.getPageMap();
        browser = Browser.getBrowser();
    }

    @Test
    public abstract void test() throws IOException;

    public void openUrl(String url) {
        browser.get(url);
    }

    public PageBase currentPage() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {

        }
        currentPage = pageMap.findPage(browser);
        return currentPage;
    }

    public void click(String field) {
        getField(field).click();
    }

    public ElementBase getField(String field) {
        return currentPage.getField(field);
    }

    @After
    public void after() {
        browser.close();
    }

}
