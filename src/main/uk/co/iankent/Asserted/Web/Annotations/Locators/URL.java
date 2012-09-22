package uk.co.iankent.Asserted.Web.Annotations.Locators;

import java.lang.annotation.*;

/**
 * Asserted - A JUnit/Concordion/Selenium integration framework for automated web testing
 * Copyright (c) Ian Kent, 2012
 */
@Inherited @Target(ElementType.ANNOTATION_TYPE) @Retention(RetentionPolicy.RUNTIME)
public @interface URL {
    String value();
}
