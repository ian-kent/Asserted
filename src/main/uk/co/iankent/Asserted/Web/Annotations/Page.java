package uk.co.iankent.Asserted.Web.Annotations;

import uk.co.iankent.Asserted.Web.Annotations.Locators.URL;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * Asserted - A JUnit/Concordion/Selenium integration framework for automated web testing
 * Copyright (c) Ian Kent, 2012
 */
@Retention(RetentionPolicy.RUNTIME) @Target(value = ElementType.TYPE)
public @interface Page {
    URL url() default @URL("$NULL");
}
