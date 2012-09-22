package uk.co.iankent.Asserted.Web.Element;

import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 * User: Ian
 * Date: 16/09/12
 * Time: 19:13
 * To change this template use File | Settings | File Templates.
 */
public class Text extends ElementBase {
    public Text(Field field) {
        super(field);
    }

    public void set(String value) {
        getWebElement().clear();
        getWebElement().sendKeys(value);
    }

    public void append(String value) {
        getWebElement().sendKeys(value);
    }
}
