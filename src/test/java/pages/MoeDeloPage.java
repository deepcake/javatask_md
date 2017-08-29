package pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.At;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

@DefaultUrl("https://www.moedelo.org")
public class MoeDeloPage extends PageObject {


    public static final String URL = "https://www.moedelo.org/";
    public static final String TITLE = "Интернет-бухгалтерия «Моё дело»";


    @FindBy(css = "a.login-elem")
    WebElement loginBtn;


    public WebElementFacade loginBtn() {
        return element(loginBtn);
    }

    boolean isReady() {
        return ((String) evaluateJavascript("return document.readyState")).equalsIgnoreCase("complete");
    }

}
