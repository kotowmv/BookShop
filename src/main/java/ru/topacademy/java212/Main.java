package ru.topacademy.java212;

import ru.topacademy.java212.model.ComicBook;
import ru.topacademy.java212.model.Users;

import static ru.topacademy.java212.menu.StartPage.startPage;

public class Main {
    public static Shop comicShop = new Shop();
    public static Users userlist = new Users("src/main/resources/users.csv");
    public static Users.User currentUser;

    public static void main(String[] args) {
        initialization();
        startPage();
    }

    public static void initialization() {
        ComicBook book1 = new ComicBook("Book1", "A.Gabrelyanov", "Bubble", 50, "Action", 2011, "Grom");
        ComicBook book2 = new ComicBook("Book2", "A.Gabrelyanov", "Bubble", 60, "Action", 2016, "Grom");
        ComicBook book3 = new ComicBook("Book3", "S.Lee", "Marvel", 50, "Fantastic", 2008, "Avengers");
        ComicBook book4 = new ComicBook("Book4", "S.Lee", "Marvel", 40, "Fantastic", 2012, "Avengers");
        ComicBook book5 = new ComicBook("Book5", "S.Lee", "Marvel", 55, "Fantastic", 2017, "Avengers");
        comicShop.add(book1, 5, 350, 450);
        comicShop.add(book2, 5, 350, 450);
        comicShop.add(book3, 5, 350, 450);
        comicShop.add(book4, 5, 350, 450);
        comicShop.add(book5, 5, 350, 450);
        comicShop.setSellCountParameters(book1, 3);
        comicShop.setSellCountParameters(book2, 5);
        comicShop.setSellCountParameters(book3, 1);
        comicShop.setSellCountParameters(book4, 8);
        comicShop.setSellCountParameters(book5, 2);
        Users users = new Users("src/main/resources/users.csv");
        if (!users.checkUsersFile()) {
            users.createUsersFile();
        }
    }
}