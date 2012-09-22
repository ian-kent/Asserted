package uk.co.iankent.Asserted.Web;

import org.apache.log4j.Logger;
import org.openqa.selenium.net.UrlChecker;
import uk.co.iankent.Asserted.Web.Annotations.Element;
import uk.co.iankent.Asserted.Web.Annotations.Locators.FriendlyName;
import uk.co.iankent.Asserted.Web.Annotations.Locators.Locator;
import uk.co.iankent.Asserted.Web.Annotations.Locators.URL;
import uk.co.iankent.Asserted.Web.Annotations.Page;
import uk.co.iankent.Asserted.Web.Element.ElementBase;
import uk.co.iankent.Asserted.Web.Element.Text;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Asserted - A JUnit/Concordion/Selenium integration framework for automated web testing
 * Copyright (c) Ian Kent, 2012
 */
public class PageBase {

    protected Logger logger = Logger.getLogger(this.getClass());
    protected Browser browser;

    protected Hashtable<String, Field> friendlyNames = new Hashtable<String, Field>();

    public PageBase() {
        Class klass = getClass();
        while(klass != null) {
            Field[] fields = klass.getDeclaredFields();
            for(Field f : fields) {
                if(!f.isAccessible()) f.setAccessible(true);
                logger.trace("Checking field " + klass.getName() + "." + f.getName());
                Element e = f.getAnnotation(Element.class);
                if(e != null) {
                    logger.trace("Field " + klass.getName() + "." + f.getName() + " has @Element annotation");
                    if(ElementBase.class.isAssignableFrom(f.getType())) {
                        logger.trace("Field " + klass.getName() + "." + f.getName() + " inherits from ElementBase");
                        try {
                            logger.debug("Creating " + f.getType().getName() + " instance for " + klass.getName() + "." + f.getName());
                            Object o = f.getType().getConstructor(Field.class).newInstance(new Object[] { f });
                            f.set(this, o);

                            FriendlyName friendlyName = f.getAnnotation(FriendlyName.class);
                            if(friendlyName != null) {
                                logger.trace("Adding friendly name '" + friendlyName.value() + "' for " + klass.getName() + "." + f.getName());
                                friendlyNames.put(friendlyName.value(), f);
                            }
                        } catch (NoSuchMethodException ex) {
                            logger.error(ex, ex);
                            throw new RuntimeException(ex);
                        } catch (IllegalAccessException ex) {
                            logger.error(ex, ex);
                            throw new RuntimeException(ex);
                        } catch (InstantiationException ex) {
                            logger.error(ex, ex);
                            throw new RuntimeException(ex);
                        } catch (InvocationTargetException ex) {
                            logger.error(ex, ex);
                            throw new RuntimeException(ex);
                        }
                    } else {
                        logger.error("Class " + klass.getName() + " does not inherit from ElementBase");
                    }
                }
            }
            klass = klass.getSuperclass();
            if(klass.equals(Object.class)) klass = null;
        }
    }

    public boolean test() throws AssertionError {
        Class klass = getClass();
        while(klass != null) {
            for(Field f : klass.getDeclaredFields()) {
                if(f.getAnnotation(Element.class) != null) {
                    if(!f.isAccessible()) f.setAccessible(true);
                    try {
                        ElementBase e = (ElementBase)f.get(this);
                        e.test();
                    } catch (IllegalAccessException e) {
                        logger.error(e, e);
                    }
                }
            }
            klass = klass.getSuperclass();
            if(klass.equals(Object.class)) klass = null;
        }
        return true;
    }

    public void attach(Browser browser) {
        this.browser = browser;
        Class klass = getClass();
        while(klass != null) {
            for(Field f : klass.getDeclaredFields()) {
                if(!f.isAccessible()) f.setAccessible(true);
                if(f.getAnnotation(Element.class) != null) {
                    try {
                        ((ElementBase)f.get(this)).attach(browser);
                    } catch (IllegalAccessException e) {
                        logger.error(e, e);
                    }
                }
            }
            klass = klass.getSuperclass();
            if(klass.equals(Object.class)) klass = null;
        }
    }

    public ElementBase getField(String field) {
        if(friendlyNames.containsKey(field)) {
            Field f = friendlyNames.get(field);
            try {
                if(!f.isAccessible()) f.setAccessible(true);
                return (ElementBase)f.get(this);
            } catch (IllegalAccessException e) {
                //logger.error(e, e);
            }
        }

        Class klass = getClass();
        while(klass != null) {
            try {
                Field f = klass.getDeclaredField(field);
                if(!f.isAccessible()) f.setAccessible(true);
                return (ElementBase)f.get(this);
            } catch (NoSuchFieldException e) {
                //logger.error(e, e);
            } catch (IllegalAccessException e) {
                //logger.error(e, e);
            }
            klass = klass.getSuperclass();
            if(klass.equals(Object.class)) klass = null;
        }
        throw new AssertionError("Field " + field + " could not be found in " + getClass().getSimpleName());
    }

    public String getTitle() {
        return browser.getTitle();
    }

    public String getName() {
        return getClass().getSimpleName();
    }

    public String getFriendlyName() {
        FriendlyName friendlyName = getClass().getAnnotation(FriendlyName.class);
        if(friendlyName != null) {
            return friendlyName.value();
        }
        return getName();
    }

}
