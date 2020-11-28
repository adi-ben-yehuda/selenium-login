package example.example.context;

import org.openqa.selenium.WebDriver;

// The Class is responsible in maintaining single instance of webDriver in any given thread.

public class WebDriverContext {
	
	private static final InheritableThreadLocal<WebDriver> driverInstance = new
			InheritableThreadLocal<>();

	/**
	 * Gets the driver.
	 *
	 * @return the driver
	 */
	public static WebDriver getDriver() {
		if (driverInstance.get() == null)
			throw new IllegalStateException(
					"WebDriver has not been set, Please set WebDriver instance by" +
							" WebDriverContext.setDriver...");
		else
			return driverInstance.get();
	}

	/**
	 * Sets the driver.
	 *
	 * @param driver the new driver
	 */
	public static void setDriver(WebDriver driver) {
		driverInstance.set(driver);
	}
}
