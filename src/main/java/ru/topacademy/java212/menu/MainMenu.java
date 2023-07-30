package ru.topacademy.java212.menu;

import ru.topacademy.java212.model.ComicBook;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static ru.topacademy.java212.Main.comicShop;
import static ru.topacademy.java212.Main.currentUser;
import static ru.topacademy.java212.menu.AuthMenu.out;
import static ru.topacademy.java212.menu.ShopMenu.shopMenu;
import static ru.topacademy.java212.menu.ShopMenu.showFromArrayList;
import static ru.topacademy.java212.menu.StartPage.startPage;

public class MainMenu {
    public static void mainMenu() {
        System.out.println("\nMAIN MENU");
        System.out.println("[1] Open shop");
        if (currentUser.isAdmin()) {
            System.out.println("[2] Admin menu");
        } else {
            System.out.println("[2] User page");
        }
        System.out.println("[3] Log out");
        System.out.println("[4] Exit");
        System.out.print("Insert number: ");
        Scanner scanner = new Scanner(System.in);
        try {
            int value = scanner.nextInt();
            switch (value) {
                case 1 -> shopMenu();
                case 2 -> {
                    if (currentUser.isAdmin()) {
                        adminMenu();
                    } else {
                        userMenu();
                    }
                }
                case 3 -> startPage();
                case 4 -> out();
                default -> {
                    System.out.println("Error. Try again");
                    mainMenu();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Error. Try again");
            mainMenu();
        }
    }

    public static void userMenu() {
        System.out.println("\nUSER " + currentUser.getUsername());
        System.out.println("[1] Show purchased comic book");
        System.out.println("[2] Show my wish list");
        System.out.println("[0] <<<Back to main menu");
        System.out.print("Insert number: ");
        Scanner scanner = new Scanner(System.in);
        try {
            int value = scanner.nextInt();
            switch (value) {
                case 1 -> showPurchased();
                case 2 -> showWishlist();
                case 0 -> mainMenu();
                default -> {
                    System.out.println("Error. Try again");
                    userMenu();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Error. Try again");
            userMenu();
        }
    }

    public static void adminMenu() {
        System.out.println("\nADMINISTRATOR " + currentUser.getUsername());
        System.out.println("[1] Show cancelled comic book");
        System.out.println("[2] Set total discount");
        System.out.println("[3] Add new comic book");
        System.out.println("[0] <<<Back to main menu");
        System.out.print("Insert number: ");
        Scanner scanner = new Scanner(System.in);
        try {
            int value = scanner.nextInt();
            switch (value) {
                case 1 -> showCancelled();
                case 2 -> setTotalDiscount();
                case 3 -> addNewComicBook();
                case 0 -> mainMenu();
                default -> {
                    System.out.println("Error. Try again");
                    adminMenu();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Error. Try again");
            adminMenu();
        }
    }

    private static void showWishlist() {
        System.out.println("\nUSER'S WISHLIST");
        showFromArrayList(currentUser.getWishList());
    }

    private static void showPurchased() {
        System.out.println("\nUSER'S PURCHASED");
        showFromArrayList(currentUser.getPurchasedList());
    }

    private static void showCancelled() {
        System.out.println("\nCANCELLED BOOKS");
        ArrayList<ComicBook> list = comicShop.cancelledBooks();
        showFromArrayList(list);
    }

    public static void setTotalDiscount() {
        System.out.print("\nSET TOTAL DISCOUNT: ");
        Scanner scanner = new Scanner(System.in);
        int value = scanner.nextInt();
        if (value>0 && value < 100) {
            ArrayList<ComicBook> list = comicShop.allBooks();
            for (ComicBook book : list) {
                comicShop.setSaleParameters(book, value);
            }
        }
        System.out.println("Total discount is changed!");
        adminMenu();
    }

    private static void addNewComicBook() {
        try {
            System.out.print("\nADD NEW BOOK: ");
            System.out.print("\n");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a name: ");
            String name = scanner.nextLine();
            System.out.print("Enter author name: ");
            String author = scanner.nextLine();
            System.out.print("Enter publishing house: ");
            String publishingHouse = scanner.nextLine();
            System.out.print("Enter count of pages: ");
            int pages = scanner.nextInt();
            System.out.print("Enter genre: ");
            String genre = scanner.nextLine();
            System.out.print("Enter year of publishing: ");
            int year = scanner.nextInt();
            System.out.print("Enter series: ");
            String series = scanner.nextLine();
            System.out.print("Enter available count: ");
            int available = scanner.nextInt();
            System.out.print("Enter cost price: ");
            int costPrice = scanner.nextInt();
            System.out.print("Enter sell price: ");
            int sellPrice = scanner.nextInt();
            ComicBook newBook = new ComicBook(name, author, publishingHouse, pages, genre, year, series);
            comicShop.add(newBook, available, costPrice, sellPrice);
            System.out.println("Comic book added successfully!");
            adminMenu();
        } catch (InputMismatchException e) {
            System.out.println("Error! Incorrect value");
            adminMenu();
        }
    }
}