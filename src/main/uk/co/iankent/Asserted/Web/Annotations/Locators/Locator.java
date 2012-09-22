package uk.co.iankent.Asserted.Web.Annotations.Locators;

import java.lang.annotation.*;

/**
 * Asserted - A JUnit/Concordion/Selenium integration framework for automated web testing
 * Copyright (c) Ian Kent, 2012
 */
@Inherited @Target(ElementType.FIELD) @Retention(RetentionPolicy.RUNTIME)
public @interface Locator {
    String id() default "";
    String xpath() default "";
    String name() default "";
}
