package ru.topacademy.java212.menu;

import ru.topacademy.java212.model.ComicBook;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import static ru.topacademy.java212.Main.comicShop;
import static ru.topacademy.java212.Main.currentUser;
import static ru.topacademy.java212.menu.EditMenu.edit;
import static ru.topacademy.java212.menu.MainMenu.mainMenu;

public class ShopMenu {
    public static void shopMenu() {
        System.out.println("\nSHOP MENU");
        System.out.println("[1] Show all comic book");
        System.out.println("[2] Show new comic book");
        System.out.println("[3] Show most selled");
        System.out.println("[4] Show most popular");
        System.out.println("[5] Search");
        System.out.println("[0] <<<Back to main menu");
        System.out.print("Insert number: ");
        Scanner scanner = new Scanner(System.in);
        try {
            int value = scanner.nextInt();
            switch (value) {
                case 1 -> showAll();
                case 2 -> showNew();
                case 3 -> showMostSelled();
                case 4 -> showMostPopular();
                case 5 -> searchMenu();
                case 0 -> mainMenu();
                default -> {
                    System.out.println("Error. Try again");
                    shopMenu();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Error. Try again");
            shopMenu();
        }
    }

    public static void showAll() {
        System.out.println("\nCOMIC BOOK IN SALE");
        ArrayList<ComicBook> list = comicShop.allBooks();
        showFromArrayList(list);
    }

    private static void showNew() {
        System.out.println("\nNEW COMIC BOOKS");
        ArrayList<ComicBook> list = comicShop.newComicBook();
        showFromArrayList(list);
    }

    private static void showMostSelled() {
        System.out.println("\nMOST SELLED COMIC BOOK");
        ArrayList<ComicBook> list = comicShop.mostSelled();
        showFromArrayList(list);
    }

    private static void showMostPopular() {
        System.out.println("\nMOST POPULAR COMIC BOOK");
        System.out.println("[1] By author");
        System.out.println("[2] By genre");
        System.out.println("[3] By series");
        System.out.println("[0] <<<Back to shop menu");
        System.out.print("Insert number: ");
        Scanner scanner = new Scanner(System.in);
        try {
            int value = scanner.nextInt();
            switch (value) {
                case 1 -> showPopularByAuthor();
                case 2 -> showPopularByGenre();
                case 3 -> showPopularBySeries();
                case 0 -> shopMenu();
                default -> {
                    System.out.println("Error. Try again");
                    showMostPopular();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Error. Try again");
            showMostPopular();
        }
    }

    private static void showPopularByAuthor() {
        HashMap<String, Integer> map = comicShop.popularAuthor();
        int i = 0;
        String[] keys = new String[map.size()];
        System.out.println("\nMOST POPULAR BY AUTHOR");
        for (String author : map.keySet()) {
            Integer value = map.get(author);
            System.out.println("[" + (i + 1) + "] " + author + " (Number of sales: " + value + ")");
            keys[i] = author;
            i++;
        }
        System.out.println("[0] <<<Back to most popular menu");
        System.out.print("Insert number: ");
        Scanner scanner = new Scanner(System.in);
        try {
            int value = scanner.nextInt();
            if (value == 0) {
                showMostPopular();
            } else if (value > 0 && value <= map.size()) {
                showFromArrayList(comicShop.searchByAuthor(keys[value - 1]));
            } else {
                System.out.println("Error. Try again");
                showPopularByAuthor();
            }
        } catch (InputMismatchException e) {
            System.out.println("Error. Try again");
            showPopularByAuthor();
        }
    }

    private static void showPopularByGenre() {
        HashMap<String, Integer> map = comicShop.popularGenre();
        int i = 0;
        String[] keys = new String[map.size()];
        System.out.println("\nMOST POPULAR BY GENRE");
        for (String genre : map.keySet()) {
            Integer value = map.get(genre);
            System.out.println("[" + (i + 1) + "] " + genre + " (Number of sales: " + value + ")");
            keys[i] = genre;
            i++;
        }
        System.out.println("[0] <<<Back to most popular menu");
        System.out.print("Insert number: ");
        Scanner scanner = new Scanner(System.in);
        try {
            int value = scanner.nextInt();
            if (value == 0) {
                showMostPopular();
            } else if (value > 0 && value <= map.size()) {
                showFromArrayList(comicShop.searchByGenre(keys[value - 1]));
            } else {
                System.out.println("Error. Try again");
                showPopularByGenre();
            }
        } catch (InputMismatchException e) {
            System.out.println("Error. Try again");
            showPopularByGenre();
        }
    }

    private static void showPopularBySeries() {
        HashMap<String, Integer> map = comicShop.popularSeries();
        int i = 0;
        String[] keys = new String[map.size()];
        System.out.println("\nMOST POPULAR BY SERIES");
        for (String series : map.keySet()) {
            Integer value = map.get(series);
            System.out.println("[" + (i + 1) + "] " + series + " (Number of sales: " + value + ")");
            keys[i] = series;
            i++;
        }
        System.out.println("[0] <<<Back");
        System.out.print("Insert number: ");
        Scanner scanner = new Scanner(System.in);
        try {
            int value = scanner.nextInt();
            if (value == 0) {
                showMostPopular();
            } else if (value > 0 && value <= map.size()) {
                showFromArrayList(comicShop.searchBySeries(keys[value - 1]));
            } else {
                System.out.println("Error. Try again");
                showPopularBySeries();
            }
        } catch (InputMismatchException e) {
            System.out.println("Error. Try again");
            showPopularBySeries();
        }
    }

    private static void searchMenu() {
        System.out.println("\nSEARCH");
        System.out.println("[1] By name");
        System.out.println("[2] By author");
        System.out.println("[3] By genre");
        System.out.println("[4] By series");
        System.out.println("[0] <<<Back");
        System.out.print("Insert number: ");
        Scanner scanner = new Scanner(System.in);
        try {
            int value = scanner.nextInt();
            switch (value) {
                case 1 -> searchByName();
                case 2 -> searchByAuthor();
                case 3 -> searchByGenre();
                case 4 -> searchBySeries();
                case 0 -> shopMenu();
                default -> {
                    System.out.println("Error. Try again");
                    searchMenu();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Error. Try again");
            searchMenu();
        }
    }

    private static void searchByName() {
        System.out.println("\nSEARCH BY NAME");
        System.out.println("Enter a request (or 0 to back to search menu)");
        System.out.print("Insert value: ");
        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();
        try {
            if (Integer.parseInt(value) == 0) {
                searchMenu();
            } else {
                showFromArrayList(comicShop.searchByName(value));
            }
        } catch (NumberFormatException e) {
            showFromArrayList(comicShop.searchByName(value));
        }
    }

    private static void searchByAuthor() {
        System.out.println("\nSEARCH BY AUTHOR");
        System.out.println("Enter a request (or 0 to back to search menu)");
        System.out.print("Insert value: ");
        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();
        try {
            if (Integer.parseInt(value) == 0) {
                searchMenu();
            } else {
                showFromArrayList(comicShop.searchByAuthor(value));
            }
        } catch (NumberFormatException e) {
            showFromArrayList(comicShop.searchByAuthor(value));
        }
    }

    private static void searchByGenre() {
        System.out.println("\nSEARCH BY GENRE");
        System.out.println("Enter a request (or 0 to back to search menu)");
        System.out.print("Insert value: ");
        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();
        try {
            if (Integer.parseInt(value) == 0) {
                searchMenu();
            } else {
                showFromArrayList(comicShop.searchByGenre(value));
            }
        } catch (NumberFormatException e) {
            showFromArrayList(comicShop.searchByGenre(value));
        }
    }

    private static void searchBySeries() {
        System.out.println("\nSEARCH BY SERIES");
        System.out.println("Enter a request (or 0 to back to search menu)");
        System.out.print("Insert value: ");
        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();
        try {
            if (Integer.parseInt(value) == 0) {
                searchMenu();
            } else {
                showFromArrayList(comicShop.searchBySeries(value));
            }
        } catch (NumberFormatException e) {
            showFromArrayList(comicShop.searchBySeries(value));
        }
    }

    static void showFromArrayList(ArrayList<ComicBook> list) {
        if (list.size() == 0) {
            System.out.println("Sorry, nothing found!");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + list.get(i).toString());
            }
        }
        System.out.println("[0] <<<Back");
        System.out.print("Insert number: ");
        Scanner scanner = new Scanner(System.in);
        try {
            int value = scanner.nextInt();
            if (value == 0) {
                mainMenu();
            } else if (value > 0 && value <= list.size()) {
                shopBookMenu(list.get(value - 1));
            } else {
                System.out.println("Error. Try again");
                showFromArrayList(list);
            }
        } catch (InputMismatchException e) {
            System.out.println("Error. Try again");
            showFromArrayList(list);
        }
    }

    public static void shopBookMenu(ComicBook book) {
        showBook(book);
        if (comicShop.getParameters(book).isSale()) {
            double discount = (double)(100 - comicShop.getParameters(book).getDiscountPercent()) / 100;
            double price = (comicShop.getParameters(book).getSellPrice() * discount);
            System.out.println("Sell price: " + price
                    + ", (Old price: " + comicShop.getParameters(book).getSellPrice()
                    + ", -" + comicShop.getParameters(book).getDiscountPercent() + "%)");
        } else {
            System.out.println("Sell price: " + comicShop.getParameters(book).getSellPrice());
        }
        System.out.println("Available: " + comicShop.getParameters(book).getAvailable());
        System.out.println("Selled: " + comicShop.getParameters(book).getSellCount());
        if (comicShop.getParameters(book).isNewBook()) {
            System.out.println("NEW!");
        }
        if (currentUser.isAdmin()) {
            System.out.println("[1] Edit");
            if (comicShop.isCancelled(book)) {
                System.out.println("[2] Restore");
            } else {
                System.out.println("[2] Cancel");
            }
            System.out.println("[3] Remove");
            System.out.println("[0] <<<Back");
            System.out.print("Insert number: ");
            Scanner scanner = new Scanner(System.in);
            try {
                int value = scanner.nextInt();
                switch (value) {
                    case 1 -> edit(book);
                    case 2 -> {
                        if (comicShop.isCancelled(book)) {
                            restore(book);
                        } else {
                            cancel(book);
                        }
                    }
                    case 3 -> remove(book);
                    case 0 -> shopMenu();
                    default -> {
                        System.out.println("Error. Try again");
                        searchMenu();
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Error. Try again");
                searchMenu();
            }
            System.out.println("[0] <<<Back");
        } else {
            System.out.println("[1] Buy");
            System.out.println("[2] Add to wish list");
            System.out.println("[0] <<<Back");
            System.out.print("Insert number: ");
            Scanner scanner = new Scanner(System.in);
            try {
                int value = scanner.nextInt();
                switch (value) {
                    case 1 -> buy(book);
                    case 2 -> toWishList(book);
                    case 0 -> shopMenu();
                    default -> {
                        System.out.println("Error. Try again");
                        searchMenu();
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Error. Try again");
                searchMenu();
            }
            System.out.println("[0] <<<Back");
        }
    }

    private static void cancel(ComicBook book) {
        comicShop.cancel(book.getName());
        System.out.println("ComicBook was cancelled!");
        shopMenu();
    }

    private static void restore(ComicBook book) {
        comicShop.restore(book.getName());
        System.out.println("ComicBook was restored!");
        shopMenu();
    }

    private static void remove(ComicBook book) {
        comicShop.remove(book.getName());
        System.out.println("ComicBook was removed!");
        shopMenu();
    }

    private static void buy(ComicBook book) {
        int available = comicShop.getParameters(book).getAvailable();
        int sellCount = comicShop.getParameters(book).getSellCount();
        if (available > 0) {
            currentUser.addToPurchasedList(book);
            comicShop.getParameters(book).setAvailable(available - 1);
            comicShop.getParameters(book).setSellCount(sellCount + 1);
            System.out.println("Success!");
            shopMenu();
        } else {
            System.out.println("Out of stock!");
            shopMenu();
        }
    }

    private static void toWishList(ComicBook book) {
        currentUser.addToWishList(book);
        System.out.println("Success!");
        shopMenu();
    }

    public static void userBookMenu(ComicBook book) {
        showBook(book);
        System.out.println("[0] <<<Back");
    }

    public static void showBook(ComicBook book) {
        System.out.println("\n" + book.getName().toUpperCase());
        System.out.println(
                "Author: " + book.getAuthor() +
                        "\nPublishing house: " + book.getPublishingHouse() +
                        "\nCount of pages: " + book.getPages() +
                        "\nGenre: " + book.getGenre() +
                        "\nYear of publication: " + book.getPublicationYear() +
                        "\nSeries: " + book.getSeries());
    }
}
