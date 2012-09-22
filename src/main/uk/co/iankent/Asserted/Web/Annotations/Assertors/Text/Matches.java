package uk.co.iankent.Asserted.Web.Annotations.Assertors.Text;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Asserted - A JUnit/Concordion/Selenium integration framework for automated web testing
 * Copyright (c) Ian Kent, 2012
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Matches {
    String value();
}
