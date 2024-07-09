package tests;

import org.junit.jupiter.api.*;

public class BooksCartTests extends BaseTest {
    @Test
    void RemoveBookFromCartTest() {
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
