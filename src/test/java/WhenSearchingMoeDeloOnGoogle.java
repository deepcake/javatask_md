import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import pages.GooglePage;
import pages.MoeDeloPage;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SerenityRunner.class)
public class WhenSearchingMoeDeloOnGoogle {


    @Managed(driver = "chrome")
    WebDriver driver;

    GooglePage googlePage;
    MoeDeloPage moeDeloPage;

    @Test
    public void shouldFoundCorrectSiteWithLoginButton() {
        googlePage.open();
        googlePage.search("моё дело");

        assertThat(googlePage.getResultUrls().stream().anyMatch(url -> url.equalsIgnoreCase(MoeDeloPage.URL))).isTrue();

        googlePage.openResultByUrl(MoeDeloPage.URL);
        googlePage.switchToTab(MoeDeloPage.TITLE);

        assertThat(moeDeloPage.getDriver().getCurrentUrl()).isEqualToIgnoringCase(MoeDeloPage.URL);
        assertThat(moeDeloPage.loginBtn().isDisplayed()).isTrue();
    }
}
