package uk.co.iankent.Asserted.Web.Annotations.Assertors.Text;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created with IntelliJ IDEA.
 * User: Ian
 * Date: 20/09/12
 * Time: 17:52
 * To change this template use File | Settings | File Templates.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Contains {
    String value();
}
