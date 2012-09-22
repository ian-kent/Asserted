package uk.co.iankent.Asserted.Web.Element;

import java.lang.reflect.Field;

/**
 * Asserted - A JUnit/Concordion/Selenium integration framework for automated web testing
 * Copyright (c) Ian Kent, 2012
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
