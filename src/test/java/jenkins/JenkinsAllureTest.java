package jenkins;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class JenkinsAllureTest {
    private static final String searchValue = "Selenide";
    private static final String visibleTab = "Issues";

    @BeforeAll
    static void beforeAll(){
        Configuration.browserSize = System.getProperty("browserSize");
        Configuration.browser = System.getProperty("browser", "chrome");

        //password and user for remote browser
        //String login = System.getProperty("login");
       // String password = System.getProperty("password");
       // Configuration.remote = "https://" + login + ":" + password + "@selenoid.autotests.cloud/wd/hub";

    }


    @Test
    public void firstIssueSelenideNameCheckWithSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открыть github", () -> {
            open("https://github.com/");
        });

        step("Ввести в поиске Selenide", () -> {
            $(".header-search-input").setValue(searchValue);
            $(".header-search-input").submit();
        });

        step("Открыть первую ссылку", () -> {
            $(".repo-list li a").click();
        });


        step("check: В навигации есть таб с названием Issues", () -> {
            $(".UnderlineNav-body").shouldHave((text(visibleTab)));
        });
    }
}
