package example.example.factory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ElementProxy implements InvocationHandler {
    private final WebElement element;

    @FindBy(xpath = "//span[@class='onUnloadPopup__CloseModalBtn-v34ylr-1 gFThxT']")
    private WebElement popup;

    public ElementProxy(WebElement element) {
        this.element = element;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // Before invoking actual method check for the popup
        this.checkForPopupAndKill();

        // At this point, popup would have been closed if it had appeared.
        // element action can be called safely now.
        Object result = method.invoke(element, args);

        return result;
    }

    private void checkForPopupAndKill() {
        if (popup != null && popup.isDisplayed()) {
            popup.click();
        }
    }
}
