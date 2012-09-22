package uk.co.iankent.Asserted.Web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import uk.co.iankent.Asserted.Web.Annotations.Locators.URL;
import uk.co.iankent.Asserted.Web.Annotations.Page;

import javax.lang.model.type.NullType;
import java.io.InvalidClassException;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Ian
 * Date: 18/09/12
 * Time: 19:32
 * To change this template use File | Settings | File Templates.
 */
public class PageMap {

    public static PageMap getPageMap() {
        return new PageMap();
    }

    protected PageMap() {
        init();
    }

    public PageBase findPage(Browser browser) {
        String currentUrl = browser.getCurrentUrl();
        logger.trace("Finding page for URL " + currentUrl);

        for(Page page : pages.keySet()) {
            URL url = page.url();
            if(url.value().equals("$NULL")) continue;

            logger.trace("Testing URL against @URL(" + url.value() + ")");

            Pattern p = Pattern.compile(url.value());
            if(p.matcher(currentUrl).matches()) {
                PageBase pb = pages.get(page);
                pb.attach(browser);
                logger.trace("Found a match, returning " + pb.getName());
                return pb;
            }
        }

        logger.trace("No match found, returning null");
        return null;
    }

    protected Logger logger = Logger.getLogger(PageMap.class);
    protected HashMap<Page, PageBase> pages;

    protected void init() {
        pages = new HashMap<Page, PageBase>();

        ClassPathScanningCandidateComponentProvider scanner = new
                ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Page.class));

        ArrayList<Class> classes = new ArrayList<Class>();
        Package[] packages = Package.getPackages();
        for(Package pkg : packages) {
            logger.trace("Found package " + pkg.getName());
            for(BeanDefinition bd : scanner.findCandidateComponents(pkg.getName())) {
                try {
                    String klass = bd.getBeanClassName();
                    logger.debug("Found class " + klass);
                    classes.add(Class.forName(klass));
                } catch (ClassNotFoundException e) {
                    logger.error(e, e);
                }
            }
        }

        for(Class klass : classes) {
            try {
                if(!PageBase.class.isAssignableFrom(klass)) {
                    throw new InvalidClassException("Class " + klass.getName() + " does not subclass " + PageBase.class.getName());
                }
                logger.trace("Creating instance of " + klass.getName());
                Page pageAnnotation = (Page)klass.getAnnotation(Page.class);
                PageBase pageClass = (PageBase)klass.getConstructor().newInstance(new Object[] {});
                pages.put(pageAnnotation, pageClass);
            } catch (NoSuchMethodException e) {
                logger.error("Class " + klass.getName() + " does not provide a default constructor", e);
            } catch (IllegalAccessException e) {
                logger.error("Class " + klass.getName() + " does not provide a public constructor", e);
            } catch (InstantiationException e) {
                logger.error("Class " + klass.getName() + " could not be instantiated", e);
            } catch (InvocationTargetException e) {
                logger.error("Class " + klass.getName() + " invocation target exception", e);
            } catch (InvalidClassException e) {
                logger.error("Class " + klass.getName() + " does not inherit from appropriate class", e);
            }
        }
    }

}
