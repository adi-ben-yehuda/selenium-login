package example.example.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Field;

/**
 * A factory for creating PageInstances objects.
 */
public class PageInstancesFactory {
	public static <T> void initElements(WebDriver driver, T pageObject)
			throws IllegalAccessException {
		PageFactory.initElements(driver, pageObject);

		for (Field f:pageObject.getClass().getDeclaredFields()) {
			if(f.getType().equals(WebElement.class)){
				boolean accessible = f.isAccessible();
				f.setAccessible(true);

				f.set(pageObject, ElementGuard.guard((WebElement) f.get(pageObject)));
				f.setAccessible(accessible);
			}
		}
	}
}
