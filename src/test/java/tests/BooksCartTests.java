package tests;

import org.junit.jupiter.api.*;

@Tag("Book Removing")
public class BooksCartTests extends BaseTest {
    @Test
    void removeBookFromCartTest() {
        // находим первую книгу
        var isbn = steps.getIsbnOfFirstBookFromStore();
        // добавить книгу в корзину через API
        steps.addBookToCart(token, steps.userId, isbn);
        // открыть сайт и удалить книгу по кнопке - мы уже залогинены благодаря токену
        steps.openCartAndRemoveBookFromCartByButton();
        // проверить, что книга удалена
        steps.checkThatCartIsEmpty();
    }
}
