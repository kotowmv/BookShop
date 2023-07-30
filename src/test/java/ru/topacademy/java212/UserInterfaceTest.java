package ru.topacademy.java212;

import org.junit.jupiter.api.Test;
import ru.topacademy.java212.menu.ShopMenu;
import ru.topacademy.java212.model.ComicBook;

class UserInterfaceTest {

    @Test
    public void showBook_test() {
        ComicBook book = new ComicBook("Book", "S.Lee", "Marvel", 50, "Fantastic", 2008, "Avengers");
        ShopMenu.showBook(book);
    }
}