package uk.co.iankent.Asserted.Web.Annotations;

import uk.co.iankent.Asserted.Web.Annotations.Locators.Locator;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: Ian
 * Date: 16/09/12
 * Time: 19:15
 * To change this template use File | Settings | File Templates.
 */
@Inherited @Target(ElementType.FIELD) @Retention(RetentionPolicy.RUNTIME)
public @interface Element {
    Locator locator();
}
