package ru.topacademy.java212.menu;

import ru.topacademy.java212.model.ComicBook;

import java.util.InputMismatchException;
import java.util.Scanner;

import static ru.topacademy.java212.Main.comicShop;
import static ru.topacademy.java212.menu.ShopMenu.shopMenu;

public class EditMenu {
    static void edit(ComicBook book) {
        System.out.println("\nEDIT MENU - " + book.getName());
        System.out.println("[ 1] Edit name");
        System.out.println("[ 2] Edit author");
        System.out.println("[ 3] Edit publishing house");
        System.out.println("[ 4] Edit count of pages");
        System.out.println("[ 5] Edit genre");
        System.out.println("[ 6] Edit publication year");
        System.out.println("[ 7] Edit series");
        System.out.println("[ 8] Edit available count");
        System.out.println("[ 9] Edit cost price");
        System.out.println("[10] Edit sell price");
        System.out.println("[11] Edit new or not");
        System.out.println("[12] Edit discount");
        System.out.println("[ 0] <<<Back to main menu");
        System.out.print("Insert number: ");
        Scanner scanner = new Scanner(System.in);
        try {
            int value = scanner.nextInt();
            switch (value) {
                case 1 -> editName(book);
                case 2 -> editAuthor(book);
                case 3 -> editPublishingHouse(book);
                case 4 -> editCountOfPages(book);
                case 5 -> editGenre(book);
                case 6 -> editYear(book);
                case 7 -> editSeries(book);
                case 8 -> editAvailable(book);
                case 9 -> editCostPrice(book);
                case 10 -> editSellPrice(book);
                case 11 -> editIsNew(book);
                case 12 -> editDiscount(book);
                case 0 -> shopMenu();
                default -> {
                    System.out.println("Error. Try again");
                    edit(book);
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Error. Try again");
            edit(book);
        }
    }

    private static void editName(ComicBook book) {
        System.out.println("Current name: " + book.getName());
        System.out.print("New name: ");
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine();
        book.setName(temp);
        System.out.println("Name is changed!");
        shopMenu();
    }

    private static void editAuthor(ComicBook book) {
        System.out.println("Current author: " + book.getAuthor());
        System.out.print("New author: ");
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine();
        book.setAuthor(temp);
        System.out.println("Author is changed!");
        shopMenu();
    }

    private static void editPublishingHouse(ComicBook book) {
        System.out.println("Current publishing house: " + book.getPublishingHouse());
        System.out.print("New publishing house: ");
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine();
        book.setPublishingHouse(temp);
        System.out.println("Publishing house is changed!");
        shopMenu();
    }

    private static void editCountOfPages(ComicBook book) {
        System.out.println("Current count of pages: " + book.getPages());
        System.out.print("New count of pages: ");
        Scanner scanner = new Scanner(System.in);
        int temp = scanner.nextInt();
        book.setPages(temp);
        System.out.println("Count of pages is changed!");
        shopMenu();
    }

    private static void editGenre(ComicBook book) {
        System.out.println("Current genre: " + book.getGenre());
        System.out.print("New genre: ");
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine();
        book.setGenre(temp);
        System.out.println("Genre is changed!");
        shopMenu();
    }

    private static void editYear(ComicBook book) {
        System.out.println("Current publication year: " + book.getPublicationYear());
        System.out.print("New publication year: ");
        Scanner scanner = new Scanner(System.in);
        int temp = scanner.nextInt();
        book.setPublicationYear(temp);
        System.out.println("Publication year is changed!");
        shopMenu();
    }

    private static void editSeries(ComicBook book) {
        System.out.println("Current series: " + book.getSeries());
        System.out.print("New series: ");
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine();
        book.setSeries(temp);
        System.out.println("Series is changed!");
        shopMenu();
    }

    private static void editAvailable(ComicBook book) {
        System.out.println("Current available count: " + comicShop.getParameters(book).getAvailable());
        System.out.print("New available count: ");
        Scanner scanner = new Scanner(System.in);
        int temp = scanner.nextInt();
        comicShop.setAvailableParameters(book,temp);
        System.out.println("Available count is changed!");
        shopMenu();
    }

    private static void editCostPrice(ComicBook book) {
        System.out.println("Current cost price: " + comicShop.getParameters(book).getCostPrice());
        System.out.print("New cost price: ");
        Scanner scanner = new Scanner(System.in);
        int temp = scanner.nextInt();
        comicShop.setCostPriceParameters(book,temp);
        System.out.println("Cost price is changed!");
        shopMenu();
    }

    private static void editSellPrice(ComicBook book) {
        System.out.println("Current sell price: " + comicShop.getParameters(book).getSellPrice());
        System.out.print("New sell price: ");
        Scanner scanner = new Scanner(System.in);
        int temp = scanner.nextInt();
        comicShop.setSellPriceParameters(book,temp);
        System.out.println("Sell price is changed!");
        shopMenu();
    }

    private static void editIsNew(ComicBook book) {
        System.out.println("Current book is new: " + comicShop.getParameters(book).isNewBook());
        System.out.print("New state: ");
        Scanner scanner = new Scanner(System.in);
        boolean temp = scanner.nextBoolean();
        comicShop.setNewParameters(book,temp);
        System.out.println("State is changed!");
        shopMenu();
    }

    public static void editDiscount(ComicBook book) {
        System.out.println("Current discount: " + comicShop.getParameters(book).getDiscountPercent() + "%");
        System.out.print("New discount percent: ");
        Scanner scanner = new Scanner(System.in);
        int temp = scanner.nextInt();
        comicShop.setSaleParameters(book, temp);
        System.out.println("Discount percent is changed!");
        shopMenu();
    }
}
