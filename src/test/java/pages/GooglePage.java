package pages;

import net.serenitybdd.core.annotations.findby.By;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;


@DefaultUrl("https://www.google.ru/")
public class GooglePage extends PageObject {

    @FindBy(name="q")
    WebElement search;

    public void search(String keywords) {
        search.sendKeys(keywords, Keys.ENTER);
        waitFor(titleContains("Поиск в Google"));
    }

    public List<String> getResultUrls() {
        return findAll(By.cssSelector("div.rc > h3.r > a")).stream()
                .map(el -> decode(el.getAttribute("href")))
                .collect(Collectors.toList());
    }

    public void openResultByUrl(String url) {
        find(By.cssSelector("div.rc > h3.r > a[href^='" + url + "']")).click();
    }

    public void switchToTab(String title) {
        waitForCondition().until(d ->
            d.getWindowHandles().stream().anyMatch(h -> {
                d.switchTo().window(h);
                return d.getTitle().equalsIgnoreCase(title);
            })
        );
    }

    static String decode(String url) {
        try {
            return URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return url;
        }
    }

}
