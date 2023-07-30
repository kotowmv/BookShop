package ru.topacademy.java212.menu;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static ru.topacademy.java212.menu.AuthMenu.*;

public class StartPage {
    public static void startPage() {
        System.out.println("\nWelcome to ComicShop!");
        System.out.println("AUTHORIZATION");
        System.out.println("Available options: ");
        System.out.println("[1] - Log in");
        System.out.println("[2] - New user");
        System.out.println("[3] - Exit");
        System.out.print("Insert number: ");
        Scanner scanner = new Scanner(System.in);
        try {
            int num = scanner.nextInt();
            switch (num) {
                case 1 -> logIn();
                case 2 -> registration();
                case 3 -> out();
                default -> {
                    System.out.println("Error. Try again");
                    startPage();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Error. Try again");
            startPage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
