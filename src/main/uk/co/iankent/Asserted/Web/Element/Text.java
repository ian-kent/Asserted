package uk.co.iankent.Asserted.Web.Element;

import java.lang.reflect.Field;

/**
 * Asserted - A JUnit/Concordion/Selenium integration framework for automated web testing
 * Copyright (c) Ian Kent, 2012
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
