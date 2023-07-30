package ru.topacademy.java212.menu;

import java.io.IOException;
import java.util.Scanner;

import static ru.topacademy.java212.Main.currentUser;
import static ru.topacademy.java212.Main.userlist;
import static ru.topacademy.java212.menu.MainMenu.mainMenu;
import static ru.topacademy.java212.menu.StartPage.startPage;

public class AuthMenu {
    protected static void logIn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nAUTHENTICATION");
        System.out.print("Enter username: ");
        String name = scanner.nextLine();
        int index = -1;
        if (userlist.getUserIndex(name) == (-1)) {
            System.out.println("Incorrect username! Try again");
            startPage();
        } else if (userlist.getUserIndex(name) == (-2)) {
            System.out.println("Error! Users data not found!");
            startPage();
        } else {
            index = userlist.getUserIndex(name);
            System.out.print("Enter password: ");
            String password = scanner.nextLine() + "secretword";
            Integer passwordHash = password.hashCode();
            if (passwordHash.equals(userlist.getUser(index).getPasswordHash())) {
                currentUser = userlist.getUser(index);
                System.out.println("Authorization was successful!");
                mainMenu();
            } else {
                System.out.println("Incorrect password!");
                startPage();
            }
        }
    }

    protected static void registration() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nREGISTRATION");
        System.out.print("Enter new username: ");
        String name = scanner.nextLine();
        if (userlist.getUserIndex(name) == (-1)) {
            System.out.print("Enter new password: ");
            String password = scanner.nextLine() + "secretword";
            int passwordHash = password.hashCode();
            userlist.addUser(name, passwordHash);
            System.out.println("Success!");
            startPage();
        } else {
            System.out.println("This user already exists!");
            startPage();
        }
    }

    public static void out() {
        return;
    }
}
