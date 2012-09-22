package uk.co.iankent.Asserted.Web.Annotations;

import uk.co.iankent.Asserted.Web.Annotations.Locators.URL;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 * User: Ian
 * Date: 16/09/12
 * Time: 19:15
 * To change this template use File | Settings | File Templates.
 */
@Retention(RetentionPolicy.RUNTIME) @Target(value = ElementType.TYPE)
public @interface Page {
    URL url() default @URL("$NULL");
}
