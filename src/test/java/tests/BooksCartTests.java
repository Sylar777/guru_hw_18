package tests;

import org.junit.jupiter.api.*;

@Tag("Book_Removing")
public class BooksCartTests extends BaseTest {
    @Test
    void removeBookFromCartTest() {
        var isbn = steps.getIsbnOfFirstBookFromStore();
        steps.addBookToCart(token, steps.userId, isbn);
        steps.openCartAndRemoveBookFromCartByButton();
        steps.checkThatCartIsEmpty();
    }
}
