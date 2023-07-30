package ru.topacademy.java212.model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Users {

    protected ArrayList<User> users = new ArrayList<>();
    private final String absoluteFilePath;

    public void addUser(String username, int passwordHash) throws IOException {
        users = getUsers();
        User user = new User(username, passwordHash, false);
        users.add(user);
        storeUsers();
    }

    public Users(String pathToFile) {
        this.absoluteFilePath = pathToFile;
    }

    public User getUser(int index) {
        return users.get(index);
    }

    public int getUserIndex(String name) {
        try {
            users = getUsers();
            for (int i = 0; i < users.size(); i++) {
                if (name.equals(users.get(i).username)) {
                    return i;
                }
            }
            return -1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected ArrayList<User> getUsers() throws IOException {
        ArrayList<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(absoluteFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineSplitted = line.split(","); // use comma as separator
                try {
                    User user = new User(lineSplitted[0], Integer.parseInt(lineSplitted[1]), Boolean.parseBoolean(lineSplitted[2]));
                    users.add(user);
                } catch (ArrayIndexOutOfBoundsException e) {
                    return users; //throw new IOException("File has wrong format!");
                }
            }
        }
        return users;
    }

    protected void storeUsers() throws IOException {
        try (PrintStream out = new PrintStream(absoluteFilePath)) {
            for (User user : users) {
                out.print(user.username);
                out.print(",");
                out.print(user.passwordHash);
                out.print(",");
                out.print(user.isAdmin());
                out.print("\n");
                out.flush();
            }
        }
    }

    public boolean checkUsersFile() {
        return Files.exists(Path.of(absoluteFilePath));
    }

    public void createUsersFile() {
        File file = new File(absoluteFilePath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUser(int index) throws IOException {
        users = getUsers();
        users.remove(index);
        storeUsers();
    }

    public boolean usersIsEmpty() {
        return users.isEmpty();
    }

    public static class User {
        private String username;
        private int passwordHash;
        private boolean admin;
        private final ArrayList<ComicBook> purchasedList;
        private final ArrayList<ComicBook> wishList;

        public User(String username, int passwordHash, boolean isAdmin) {
            this.username = username;
            this.passwordHash = passwordHash;
            this.admin = isAdmin;
            purchasedList = new ArrayList<>();
            wishList = new ArrayList<>();
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getPasswordHash() {
            return passwordHash;
        }

        public void setPasswordHash(int passwordHash) {
            this.passwordHash = passwordHash;
        }

        public void addToPurchasedList(ComicBook book) {
            purchasedList.add(book);
        }

        public void addToWishList(ComicBook book) {
            wishList.add(book);
        }

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }

        public void removeFromWishList(ComicBook book) {
            wishList.remove(book);
        }

        public ArrayList<ComicBook> getPurchasedList() {
            return purchasedList;
        }

        public ArrayList<ComicBook> getWishList() {
            return wishList;
        }
    }
}