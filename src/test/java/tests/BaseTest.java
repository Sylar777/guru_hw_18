package tests;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import steps.BookStoreSteps;

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
    }

    @BeforeEach
    void beforeEach() {
        // создать юзера через API
        steps.createUser();
        // токен
        token = steps.getToken();
        //залогиниться через API
        steps.login(token);
    }

    @AfterEach
    void afterEach() {
        // удалить юзера через API
        steps.removeUser(token, steps.userId);
        closeWebDriver();
    }
}
