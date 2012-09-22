package uk.co.iankent.Asserted.Web.Element;

import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 * User: Ian
 * Date: 16/09/12
 * Time: 19:13
 * To change this template use File | Settings | File Templates.
 */
public class Image extends ElementBase {

    public Image(Field field) {
        super(field);
    }

    public String alt() {
        return getWebElement().getAttribute("alt");
    }

    public String title() {
        return getWebElement().getAttribute("title");
    }

    public String src() {
        return getWebElement().getAttribute("src");
    }

}
