package uk.co.iankent.Asserted.Web.Element;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import uk.co.iankent.Asserted.Web.Annotations.Assertors.Enabled;
import uk.co.iankent.Asserted.Web.Annotations.Assertors.Present;
import uk.co.iankent.Asserted.Web.Annotations.Assertors.Resources.Alt;
import uk.co.iankent.Asserted.Web.Annotations.Assertors.Resources.Href;
import uk.co.iankent.Asserted.Web.Annotations.Assertors.Resources.Src;
import uk.co.iankent.Asserted.Web.Annotations.Assertors.Resources.Title;
import uk.co.iankent.Asserted.Web.Annotations.Assertors.Tag;
import uk.co.iankent.Asserted.Web.Annotations.Assertors.Text.Contains;
import uk.co.iankent.Asserted.Web.Annotations.Assertors.Text.Equals;
import uk.co.iankent.Asserted.Web.Annotations.Assertors.Text.Matches;
import uk.co.iankent.Asserted.Web.Annotations.Assertors.Visible;
import uk.co.iankent.Asserted.Web.Annotations.Element;
import uk.co.iankent.Asserted.Web.Annotations.Locators.Locator;
import uk.co.iankent.Asserted.Web.Browser;

import java.lang.reflect.Field;

/**
 * Asserted - A JUnit/Concordion/Selenium integration framework for automated web testing
 * Copyright (c) Ian Kent, 2012
 */
public class ElementBase {

    protected Logger logger = Logger.getLogger(getClass());

    protected Element element;
    protected Field field;
    protected Browser browser;

    public ElementBase(Field field) {
        this.field = field;
        this.element = field.getAnnotation(Element.class);
    }

    public void attach(Browser browser) {
        this.browser = browser;
    }

    public String get() {
        return getWebElement().getText();
    }

    public void click() {
        getWebElement().click();
    }

    public boolean test() {
        Present present = field.getAnnotation(Present.class);
        Visible visible = field.getAnnotation(Visible.class);
        Enabled enabled = field.getAnnotation(Enabled.class);
        Contains contains = field.getAnnotation(Contains.class);
        Equals equals = field.getAnnotation(Equals.class);
        Matches matches = field.getAnnotation(Matches.class);
        Alt alt = field.getAnnotation(Alt.class);
        Href href = field.getAnnotation(Href.class);
        Src src = field.getAnnotation(Src.class);
        Title title = field.getAnnotation(Title.class);
        Tag tag = field.getAnnotation(Tag.class);

        WebElement e = null;
        try {
            e = getWebElement();
        } catch (NoSuchElementException ex) {
            logger.debug(ex, ex);
        }

        if(present != null) {
            if(present.value() && e == null)
                throw new AssertionError("Field " + field.getName() + " is marked @Present(true) but element was not found");
            if(!present.value() && e != null)
                throw new AssertionError("Field " + field.getName() + " is marked @Present(false) but element was found");
            logger.info("Field " + field.getName() + " @Presence matches element presence: " + present.value());
        }

        if(visible != null) {
            if(e == null)
                throw new AssertionError("Field " + field.getName() + " is marked @Visible(" + visible.value() + ") but element was not found");
            if(e.isDisplayed() != visible.value())
                throw new AssertionError("Field " + field.getName() + " is marked @Visible(" + visible.value() + ") but element visibility is " + e.isDisplayed());
            logger.info("Field " + field.getName() + " @Visible matches element visibility: " + visible.value());
        }

        if(enabled != null) {
            if(e == null)
                throw new AssertionError("Field " + field.getName() + " is marked @Enabled(" + enabled.value() + ") but element was not found");
            if(e.isEnabled() != enabled.value())
                throw new AssertionError("Field " + field.getName() + " is marked @Enabled(" + enabled.value() + ") but element enabled is " + e.isEnabled());
            logger.info("Field " + field.getName() + " @Enabled matches element enabled: " + enabled.value());
        }

        if(contains != null) {
            if(e == null)
                throw new AssertionError("Field " + field.getName() + " is marked @Contains(" + contains.value() + ") but element was not found");
            if(!get().contains(contains.value()))
                throw new AssertionError("Field " + field.getName() + " is marked @Contains(" + contains.value() + ") but element get() is \"" + get() + "\"");
            logger.info("Field " + field.getName() + " @Contains matches element value: " + contains.value());
        }

        if(equals != null) {
            if(e == null)
                throw new AssertionError("Field " + field.getName() + " is marked @Equals(" + equals.value() + ") but element was not found");
            if(!get().equals(equals.value()))
                throw new AssertionError("Field " + field.getName() + " is marked @Equals(" + equals.value() + ") but element get() is \"" + get() + "\"");
            logger.info("Field " + field.getName() + " @Equals matches element value: " + equals.value());
        }

        if(matches != null) {
            if(e == null)
                throw new AssertionError("Field " + field.getName() + " is marked @Matches(" + matches.value() + ") but element was not found");
            if(!get().matches(matches.value()))
                throw new AssertionError("Field " + field.getName() + " is marked @Matches(" + matches.value() + ") but element get() is \"" + get() + "\"");
            logger.info("Field " + field.getName() + " @Matches matches element value: " + matches.value());
        }

        if(alt != null) {
            if(e == null)
                throw new AssertionError("Field " + field.getName() + " is marked @Alt(" + alt.value() + ") but element was not found");
            if((getWebElement().getAttribute("alt") == null || !getWebElement().getAttribute("alt").equals(alt.value())))
                throw new AssertionError("Field " + field.getName() + " is marked @Alt(" + alt.value() + ") but element getAttribute('alt') is \"" + getWebElement().getAttribute("alt") + "\"");
            logger.info("Field " + field.getName() + " @Alt matches element alt: " + alt.value());
        }

        if(href != null) {
            if(e == null)
                throw new AssertionError("Field " + field.getName() + " is marked @Href(" + href.value() + ") but element was not found");
            if((getWebElement().getAttribute("href") == null || !getWebElement().getAttribute("href").equals(href.value())))
                throw new AssertionError("Field " + field.getName() + " is marked @Href(" + href.value() + ") but element getAttribute('href') is \"" + getWebElement().getAttribute("href") + "\"");
            logger.info("Field " + field.getName() + " @Href matches element href: " + href.value());
        }

        if(src != null) {
            if(e == null)
                throw new AssertionError("Field " + field.getName() + " is marked @Src(" + src.value() + ") but element was not found");
            if((getWebElement().getAttribute("src") == null || !getWebElement().getAttribute("src").equals(src.value())))
                throw new AssertionError("Field " + field.getName() + " is marked @Src(" + src.value() + ") but element getAttribute('src') is \"" + getWebElement().getAttribute("src") + "\"");
            logger.info("Field " + field.getName() + " @Src matches element src: " + src.value());
        }

        if(title != null) {
            if(e == null)
                throw new AssertionError("Field " + field.getName() + " is marked @Title(" + title.value() + ") but element was not found");
            if((getWebElement().getAttribute("title") == null || !getWebElement().getAttribute("title").equals(title.value())))
                throw new AssertionError("Field " + field.getName() + " is marked @Title(" + title.value() + ") but element getAttribute('title') is \"" + getWebElement().getAttribute("title") + "\"");
            logger.info("Field " + field.getName() + " @Title matches element title: " + visible.value());
        }

        if(tag != null) {
            if(e == null)
                throw new AssertionError("Field " + field.getName() + " is marked @Tag(" + tag.value() + ") but element was not found");
            if((getWebElement().getTagName() == null || !getWebElement().getTagName().equals(tag.value())))
                throw new AssertionError("Field " + field.getName() + " is marked @Tag(" + title.value() + ") but element getTagName() is \"" + getWebElement().getTagName() + "\"");
            logger.info("Field " + field.getName() + " @Tag matches element tag: " + tag.value());
        }

        return true;
    }

    protected WebElement getWebElement() {
        Locator l = element.locator();
        if(l.id().length() > 0) {
            return browser.findElement(By.id(l.id()));
        } else if (l.name().length() > 0) {
            return browser.findElement(By.name(l.name()));
        } else if (l.xpath().length() > 0) {
            return browser.findElement(By.xpath(l.xpath()));
        }
        throw new NoSuchElementException(element.locator().toString());
    }

}
