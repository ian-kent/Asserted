package uk.co.iankent.Asserted.Web.Annotations.Assertors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Asserted - A JUnit/Concordion/Selenium integration framework for automated web testing
 * Copyright (c) Ian Kent, 2012
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Enabled {
    boolean value() default true;
}
