package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.restassured.RestAssured;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import steps.BookStoreSteps;

import java.util.Map;

import static com.codeborne.selenide.Configuration.browserCapabilities;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {
    public final BookStoreSteps steps;
    public String token;

    public BaseTest() {
        steps = new BookStoreSteps();
    }

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        RestAssured.baseURI = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 10000;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        browserCapabilities.setCapability(ChromeOptions.CAPABILITY, options);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));

        browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeEach
    void beforeEach() {
        // создать юзера через API
        steps.createUser();
        // токен
        token = steps.getToken();
        //залогиниться через API
        steps.login();
    }

    @AfterEach
    void afterEach() {
        // удалить юзера через API
        steps.removeUser(token, steps.userId);
        closeWebDriver();
    }
}
