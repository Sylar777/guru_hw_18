package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.*;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static specs.RequestResponseSpecs.*;

public class BookStoreSteps {
    public String userId;
    public String password;
    private final RequestJson requestJson;

    public BookStoreSteps() {
        requestJson = new RequestJson();
    }

    @Step("Create user via API")
    public Response createUser() {
        return given(commonRequestSpecification)
                .body(requestJson)
                .when()
                .post("/Account/v1/User")
                .then()
                .statusCode(201)
                .spec(responseSpec)
                .extract().response();
    }

    @Step("Login via API")
    public ResponseAuthJson login(String token) {
        ResponseAuthJson response = given(commonRequestSpecification)
                .body(requestJson)
                .when()
                .post("/Account/v1/Login")
                .then()
                .statusCode(200)
                .spec(responseSpec)
                .extract().as(ResponseAuthJson.class);

        System.out.println("*** response.getUserId() = " + response.getUserId());
        System.out.println("*** response.getToken() = " + response.getToken());
        System.out.println("*** response.getExpires().toString() = " + response.getExpires().toString());

        userId = response.getUserId();

        open("/images/WB.svg");

        getWebDriver().manage().addCookie(new Cookie("userID", response.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", response.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", response.getExpires().toString()));

        return response;
    }

    @Step("Get generated Token via API")
    public String getToken() {
        return given(commonRequestSpecification)
                .body(requestJson)
                .when()
                .post("/Account/v1/GenerateToken")
                .then()
                .statusCode(200)
                .spec(responseSpec)
                .extract().path("token");
    }

    @Step("Get ISBN of first book from store via API")
    public String getIsbnOfFirstBookFromStore() {
        return given(commonRequestSpecification)
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .statusCode(200)
                .spec(responseSpec)
                .extract().path("books[0].isbn");
    }

    @Step("Add book to cart via API")
    public void addBookToCart(String token, String userID, String isbn) {
        PostBooksRequestJson postBooksRequestJson = new PostBooksRequestJson();
        postBooksRequestJson.setUserId(userID);
        postBooksRequestJson.addIsbns(isbn);

        given(authorizedRequestSpec(token))
                .body(postBooksRequestJson)
                .when()
                .post("/BookStore/v1/Books/")
                .then()
                .spec(responseSpec)
                .statusCode(201);
    }

    @Step
    public void openCartAndRemoveBookFromCartByButton() {
        open("/profile");
        $("#delete-record-undefined").click();
        $("#closeSmallModal-ok").click();
        switchTo().alert().accept();
    }

    @Step
    public void checkThatCartIsEmpty() {
        $(".profile-wrapper").shouldHave(text("No rows found"));
    }

    @Step
    public void removeUser(String token, String userId) {
        given(authorizedRequestSpec(token))
                .when()
                .delete("/Account/v1/User/" + userId)
                .then()
                .statusCode(204)
                .spec(responseSpec);
    }
}
