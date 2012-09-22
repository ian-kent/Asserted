package uk.co.iankent.Asserted.Web.Element;

import java.lang.reflect.Field;

/**
 * Asserted - A JUnit/Concordion/Selenium integration framework for automated web testing
 * Copyright (c) Ian Kent, 2012
 */
public class Input extends Text {
    public Input(Field field) {
        super(field);
    }
}
