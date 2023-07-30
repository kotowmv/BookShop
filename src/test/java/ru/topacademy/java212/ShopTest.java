package ru.topacademy.java212;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.topacademy.java212.model.ComicBook;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class ShopTest {
    Shop myComicShop = new Shop();
    ComicBook book1 = new ComicBook("Book1", "A.Gabrelyanov", "Bubble", 50, "Action", 2011, "Grom");
    ComicBook book2 = new ComicBook("Book2", "A.Gabrelyanov", "Bubble", 60, "Action", 2016, "Grom");
    ComicBook book3 = new ComicBook("Book3", "S.Lee", "Marvel", 50, "Fantastic", 2008, "Avengers");
    ComicBook book4 = new ComicBook("Book4", "S.Lee", "Marvel", 40, "Fantastic", 2012, "Avengers");
    ComicBook book5 = new ComicBook("Book5", "S.Lee", "Marvel", 55, "Fantastic", 2017, "Avengers");

    @BeforeEach
    public void setup() {
        myComicShop.add(book1, 5, 350, 450);
        myComicShop.add(book2, 5, 350, 450);
        myComicShop.add(book3, 5, 350, 450);
        myComicShop.add(book4, 5, 350, 450);
        myComicShop.add(book5, 5, 350, 450);
        myComicShop.setSellCountParameters(book1, 3);
        myComicShop.setSellCountParameters(book2, 5);
        myComicShop.setSellCountParameters(book3, 1);
        myComicShop.setSellCountParameters(book4, 8);
        myComicShop.setSellCountParameters(book5, 2);
    }

    @Test
    public void add_test() {
        ComicBook book6 = new ComicBook("6", "2", "3", 50, "4", 2011, "5");
        Assertions.assertEquals(5, myComicShop.getBookListSize());
        myComicShop.add(book6, 10, 400, 420);
        Assertions.assertEquals(6, myComicShop.getBookListSize());
        Assertions.assertEquals(book6, myComicShop.get("6"));
    }

    @Test
    public void get_by_name_test() {
        Assertions.assertEquals(book3, myComicShop.get("Book3"));
    }

    @Test
    public void get_by_index_test() {
        Assertions.assertEquals(book3, myComicShop.get(2));
    }

    @Test
    public void get_parameters_test() {
        Assertions.assertEquals(3, myComicShop.getParameters(book1).getSellCount());
        Assertions.assertEquals(5, myComicShop.getParameters(book1).getAvailable());
        Assertions.assertEquals(350, myComicShop.getParameters(book1).getCostPrice());
        Assertions.assertEquals(450, myComicShop.getParameters(book1).getSellPrice());
        Assertions.assertFalse(myComicShop.getParameters(book1).isNewBook());
    }

    @Test
    public void remove_by_name_test() {
        Assertions.assertEquals(5, myComicShop.getBookListSize());
        myComicShop.remove("Book1");
        Assertions.assertEquals(4, myComicShop.getBookListSize());
    }

    @Test
    public void remove_by_index_test() {
        Assertions.assertEquals(5, myComicShop.getBookListSize());
        myComicShop.remove(0);
        Assertions.assertEquals(4, myComicShop.getBookListSize());
    }

    @Test
    public void cancel_by_name_test() {
        Assertions.assertTrue(myComicShop.listContains(book1));
        Assertions.assertFalse(myComicShop.cancelledContains(book1));
        myComicShop.cancel("Book1");
        Assertions.assertTrue(myComicShop.cancelledContains(book1));
        Assertions.assertFalse(myComicShop.listContains(book1));
    }

    @Test
    public void cancel_by_index_test() {
        Assertions.assertTrue(myComicShop.listContains(book1));
        Assertions.assertFalse(myComicShop.cancelledContains(book1));
        myComicShop.cancel(0);
        Assertions.assertTrue(myComicShop.cancelledContains(book1));
        Assertions.assertFalse(myComicShop.listContains(book1));
    }

    @Test
    public void restore_by_name_test() {
        myComicShop.cancel("Book1");
        Assertions.assertTrue(myComicShop.cancelledContains(book1));
        Assertions.assertFalse(myComicShop.listContains(book1));
        myComicShop.restore("Book1");
        Assertions.assertTrue(myComicShop.listContains(book1));
        Assertions.assertFalse(myComicShop.cancelledContains(book1));

    }

    @Test
    public void restore_by_index_test() {
        myComicShop.cancel(0);
        Assertions.assertTrue(myComicShop.cancelledContains(book1));
        Assertions.assertFalse(myComicShop.listContains(book1));
        myComicShop.restore(0);
        Assertions.assertTrue(myComicShop.listContains(book1));
        Assertions.assertFalse(myComicShop.cancelledContains(book1));
    }

    @Test
    public void when_getIndex_of_existing_book_return_value() {
        Assertions.assertNotEquals(-1, myComicShop.getIndex("Book1"));
    }

    @Test
    public void when_getIndex_of_existing_book_return_minus_1() {
        Assertions.assertEquals(-1, myComicShop.getIndex("Booook"));
    }

    @Test
    public void when_getCancelledIndex_of_existing_book_return_value() {
        myComicShop.cancel("Book1");
        Assertions.assertNotEquals(-1, myComicShop.getCancelledIndex("Book1"));
    }

    @Test
    public void when_getCancelledIndex_of_existing_book_return_minus_1() {
        Assertions.assertEquals(-1, myComicShop.getCancelledIndex("Book1"));
    }

    @Test
    public void book_list_size_test() {
        Assertions.assertEquals(5, myComicShop.getBookListSize());
    }

    @Test
    public void if_search_by_name_of_existing_book_return_not_empty_array() {
        ArrayList<ComicBook> test = new ArrayList<>() {{
            add(book5);
        }};
        Assertions.assertEquals(test, myComicShop.searchByName("Book5"));
    }

    @Test
    public void if_search_by_name_of_not_existing_book_return_empty_array() {
        ArrayList<ComicBook> test = new ArrayList<>();
        Assertions.assertEquals(test, myComicShop.searchByName("Book6"));
    }

    @Test
    public void if_search_by_existing_author_return_not_empty_array() {
        ArrayList<ComicBook> test = new ArrayList<>() {{
            add(book3);
            add(book4);
            add(book5);
        }};
        Assertions.assertEquals(test, myComicShop.searchByAuthor("S.Lee"));
    }

    @Test
    public void if_search_by_not_existing_author_return_empty_array() {
        ArrayList<ComicBook> test = new ArrayList<>();
        Assertions.assertEquals(test, myComicShop.searchByAuthor("J.Bloch"));
    }

    @Test
    public void if_search_by_existing_genre_return_not_empty_array() {
        ArrayList<ComicBook> test = new ArrayList<>() {{
            add(book3);
            add(book4);
            add(book5);
        }};
        Assertions.assertEquals(test, myComicShop.searchByGenre("Fantastic"));
    }

    @Test
    public void if_search_by_not_existing_genre_return_empty_array() {
        ArrayList<ComicBook> test = new ArrayList<>();
        Assertions.assertEquals(test, myComicShop.searchByGenre("Adventure"));
    }

    @Test
    public void if_search_by_existing_new_books_return_not_empty_array() {
        myComicShop.setNewParameters(book2,true);
        myComicShop.setNewParameters(book5,true);
        ArrayList<ComicBook> test = new ArrayList<>() {{
            add(book2);
            add(book5);
        }};
        Assertions.assertEquals(test, myComicShop.newComicBook());
    }

    @Test
    public void if_search_by_not_existing_new_books_return_empty_array() {
        ArrayList<ComicBook> test = new ArrayList<>();
        Assertions.assertEquals(test, myComicShop.newComicBook());
    }

    @Test
    public void most_selled_test() {
        Assertions.assertEquals("[Book4 (S.Lee, Fantastic, Avengers, 2012), Book2 (A.Gabrelyanov, Action, Grom, 2016), Book1 (A.Gabrelyanov, Action, Grom, 2011), Book5 (S.Lee, Fantastic, Avengers, 2017), Book3 (S.Lee, Fantastic, Avengers, 2008)]", myComicShop.mostSelled().toString());
    }

    @Test
    public void popular_author_test() {
        Assertions.assertEquals("{S.Lee=11, A.Gabrelyanov=8}", myComicShop.popularAuthor().toString());
    }

    @Test
    public void popular_genre_test() {
        ComicBook book6 = new ComicBook("Book6", "E.Fedotov", "Bubble", 55, "Action", 2020, "Grom");
        myComicShop.add(book6,3,450,550);
        myComicShop.setSellCountParameters(book6,5);
        Assertions.assertEquals("{Action=13, Fantastic=11}", myComicShop.popularGenre().toString());
    }

    @Test
    public void popular_series_test() {
        ComicBook book6 = new ComicBook("Book6", "E.Fedotov", "Bubble", 55, "Action", 2020, "Grom");
        myComicShop.add(book6,3,450,550);
        myComicShop.setSellCountParameters(book6,6);
        Assertions.assertEquals("{Grom=14, Avengers=11}", myComicShop.popularSeries().toString());
    }

    @Test
    public void hashMap_sort_test() {
        HashMap<String, Integer> testMap = new HashMap<>(Map.of(
                "key1", 5,
                "key2", 3,
                "key3", 1,
                "key4", 2,
                "key5", 4)
        );
        Assertions.assertEquals("{key1=5, key5=4, key2=3, key4=2, key3=1}", myComicShop.hashMapSort(testMap).toString());
    }

    @Test
    public void list_contains_test() {
        Assertions.assertTrue(myComicShop.listContains(book1));
    }

    @Test
    public void cancelled_contains_test() {
        myComicShop.cancel("Book1");
        Assertions.assertTrue(myComicShop.cancelledContains(book1));
    }
}