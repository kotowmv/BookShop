package ru.topacademy.java212.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;


class UsersTest {
    @Test
    public void add_user_test() {
        Users testList = new Users("src/test/resources/users1.csv");
        Assertions.assertTrue(testList.usersIsEmpty());
        try {
            testList.addUser("testUser", 12345678);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertFalse(testList.usersIsEmpty());
        Assertions.assertEquals(0, testList.getUserIndex("testUser"));
        try {
            testList.removeUser(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void get_user_index_test() {
        Users testList = new Users("src/test/resources/users2.csv");
        Assertions.assertTrue(testList.usersIsEmpty());
        try {
            testList.addUser("testUser1", 12345678);
            testList.addUser("testUser2", 12345678);
            testList.addUser("testUser3", 12345678);
            testList.addUser("testUser4", 12345678);
            testList.addUser("testUser5", 12345678);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(2, testList.getUserIndex("testUser3"));
    }

    @Test
    public void get_user_from_csv_test() {
        Users testList3 = new Users("src/test/resources/users3.csv");
        try {
            ArrayList<Users.User> testUsers = testList3.getUsers();
            Assertions.assertEquals("testUser3", testUsers.get(2).getUsername());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void users_should_have_different_wishList_test() {
        Users.User user1 = new Users.User("user1",1234,false);
        Users.User user2 = new Users.User("user2",5678,false);
        ComicBook book1 = new ComicBook("Book1", "A.Gabrelyanov", "Bubble", 50, "Action", 2011, "Grom");
        ComicBook book2 = new ComicBook("Book2", "A.Gabrelyanov", "Bubble", 60, "Action", 2016, "Grom");
        user1.addToWishList(book1);
        user2.addToWishList(book2);
        Assertions.assertEquals("Book1",user1.getWishList().get(0).getName());
        Assertions.assertEquals("Book2",user2.getWishList().get(0).getName());
    }
}