package uk.co.iankent.Asserted.Web.Annotations.Assertors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 * User: Ian
 * Date: 20/09/12
 * Time: 17:50
 * To change this template use File | Settings | File Templates.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Enabled {
    boolean value() default true;
}
