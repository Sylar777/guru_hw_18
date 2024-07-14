package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CartPage {
    public final SelenideElement shelves = $(".profile-wrapper");
    public final SelenideElement removeButton = $("#delete-record-undefined");
    public final SelenideElement closeModalButton = $("#closeSmallModal-ok");
}
