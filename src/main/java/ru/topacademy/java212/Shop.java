package ru.topacademy.java212;

import ru.topacademy.java212.model.ComicBook;
import java.util.*;

public class Shop {
    private final ArrayList<ComicBook> comicBookList = new ArrayList<>();
    private final ArrayList<ComicBook> canceledComicList = new ArrayList<>();
    private final HashMap<ComicBook, BookParameters> parameters = new HashMap<>();

    public void add(ComicBook book, int available, double costPrice, double sellPrice) {
        comicBookList.add(book);
        BookParameters bookParameters = new BookParameters(available, costPrice, sellPrice);
        parameters.put(book, bookParameters);
    }

    public ComicBook get(String name) {
        int index = getIndex(name);
        return comicBookList.get(index);
    }

    public ComicBook get(int index) {
        return comicBookList.get(index);
    }

    public BookParameters getParameters(ComicBook book) {
        return parameters.get(book);
    }

    public void remove(String name) {
        int index = getIndex(name);
        remove(index);
    }

    public void remove(int index) {
        parameters.remove(comicBookList.get(index));
        comicBookList.remove(index);
    }

    public void cancel(String name) {
        int index = getIndex(name);
        cancel(index);
    }

    public void cancel(int index) {
        ComicBook temp = comicBookList.get(index);
        canceledComicList.add(temp);
        comicBookList.remove(index);
    }

    public void restore(String name) {
        int index = getCancelledIndex(name);
        restore(index);
    }

    public void restore(int index) {
        ComicBook temp = canceledComicList.get(index);
        comicBookList.add(temp);
        canceledComicList.remove(index);
    }

    public int getIndex(String name) {
        for (int i = 0; i < comicBookList.size(); i++) {
            if (comicBookList.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public int getCancelledIndex(String name) {
        for (int i = 0; i < canceledComicList.size(); i++) {
            if (canceledComicList.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public boolean isCancelled (ComicBook book) {
       return canceledComicList.contains(book);
    }

    public int getBookListSize() {
        return comicBookList.size();
    }

    @Override
    public String toString() {
        return comicBookList.toString();
    }

    public String toStringShort() {
        return comicBookList.toString();
    }

    public ArrayList<ComicBook> allBooks() {
        return comicBookList;
    }

    public ArrayList<ComicBook> cancelledBooks() {
        return canceledComicList;
    }

    public ArrayList<ComicBook> searchByName(String name) {
        ArrayList<ComicBook> result = new ArrayList<>();
        for (ComicBook comicBook : comicBookList) {
            if (comicBook.getName().equals(name)) {
                result.add(comicBook);
            }
        }
        return result;
    }

    public ArrayList<ComicBook> searchByAuthor(String author) {
        ArrayList<ComicBook> result = new ArrayList<>();
        for (ComicBook comicBook : comicBookList) {
            if (comicBook.getAuthor().equals(author)) {
                result.add(comicBook);
            }
        }
        return result;
    }

    public ArrayList<ComicBook> searchByGenre(String genre) {
        ArrayList<ComicBook> result = new ArrayList<>();
        for (ComicBook comicBook : comicBookList) {
            if (comicBook.getGenre().equals(genre)) {
                result.add(comicBook);
            }
        }
        return result;
    }

    public ArrayList<ComicBook> searchBySeries(String series) {
        ArrayList<ComicBook> result = new ArrayList<>();
        for (ComicBook comicBook : comicBookList) {
            if (comicBook.getSeries().equals(series)) {
                result.add(comicBook);
            }
        }
        return result;
    }

    public ArrayList<ComicBook> newComicBook() {
        ArrayList<ComicBook> result = new ArrayList<>();
        for (ComicBook comicBook : comicBookList) {
            if (parameters.get(comicBook).newBook) {
                result.add(comicBook);
            }
        }
        return result;
    }

    public ArrayList<ComicBook> mostSelled() {
        ArrayList<ComicBook> sorted = new ArrayList<>(comicBookList);
        for (int i = 0; i < sorted.size() - 1; i++) {
            for (int j = 0; j < sorted.size() - 1; j++) {
                if (parameters.get(sorted.get(j + 1)).sellCount > parameters.get(sorted.get(j)).sellCount) {
                    ComicBook temp = sorted.get(j);
                    sorted.set(j, sorted.get(j + 1));
                    sorted.set(j + 1, temp);
                }
            }
        }
        return sorted;
    }

    public HashMap<String, Integer> popularAuthor() {
        HashMap<String, Integer> result = new HashMap<>();
        for (ComicBook comicBook : comicBookList) {
            String author = comicBook.getAuthor();
            int countOfSales = parameters.get(comicBook).sellCount;
            if (!result.containsKey(author)) {
                result.put(author, countOfSales);
            } else {
                int temp = result.get(author);
                result.put(author, temp + countOfSales);
            }
        }
        return hashMapSort(result);
    }

    public HashMap<String, Integer> popularGenre() {
        HashMap<String, Integer> result = new HashMap<>();
        for (ComicBook comicBook : comicBookList) {
            String genre = comicBook.getGenre();
            int countOfSales = parameters.get(comicBook).sellCount;
            if (!result.containsKey(genre)) {
                result.put(genre, countOfSales);
            } else {
                int temp = result.get(genre);
                result.put(genre, temp + countOfSales);
            }
        }
        return hashMapSort(result);
    }

    public HashMap<String, Integer> popularSeries() {
        HashMap<String, Integer> result = new HashMap<>();
        for (ComicBook comicBook : comicBookList) {
            String series = comicBook.getSeries();
            int countOfSales = parameters.get(comicBook).sellCount;
            if (!result.containsKey(series)) {
                result.put(series, countOfSales);
            } else {
                int temp = result.get(series);
                result.put(series, temp + countOfSales);
            }
        }
        return hashMapSort(result);
    }

    protected LinkedHashMap<String, Integer> hashMapSort(HashMap<String, Integer> map) {
        ArrayList<Map.Entry<String, Integer>> mappings = new ArrayList<>(map.entrySet());
        mappings.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));
        LinkedHashMap<String, Integer> sorted = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : mappings) {
            sorted.put(entry.getKey(), entry.getValue());
        }
        return sorted;
    }

    public boolean listContains(ComicBook book) {
        return comicBookList.contains(book);
    }

    public boolean cancelledContains(ComicBook book) {
        return canceledComicList.contains(book);
    }

    public void setAvailableParameters(ComicBook book, int available) {
        parameters.get(book).available = available;
    }
    protected void setSellCountParameters(ComicBook book, int sellCount) {
        parameters.get(book).sellCount = sellCount;
    }

    public void setCostPriceParameters(ComicBook book, int costPrice) {
        parameters.get(book).costPrice = costPrice;
    }

    public void setSellPriceParameters(ComicBook book, int sellPrice) {
        parameters.get(book).sellPrice = sellPrice;
    }

    public void setNewParameters(ComicBook book, boolean newBook) {
        parameters.get(book).newBook = newBook;
    }

    public void setSaleParameters(ComicBook book, int discountPercent) {
        if (discountPercent == 0) {
            parameters.get(book).setSale(false);
        } else if (discountPercent > 0 && discountPercent < 100){
            parameters.get(book).discountPercent = discountPercent;
            parameters.get(book).setSale(true);
        }
    }

    public static class BookParameters {
        private int available;
        private int sellCount;
        private int discountPercent;
        private double costPrice;
        private double sellPrice;
        private boolean newBook;
        private boolean sale;

        public BookParameters(int available, double costPrice, double sellPrice) {
            this.available = available;
            this.sellCount = 0;
            this.costPrice = costPrice;
            this.sellPrice = sellPrice;
            this.discountPercent = 0;
            this.newBook = false;
            this.sale = false;
        }

        public int getAvailable() {
            return available;
        }

        public void setAvailable(int available) {
            this.available = available;
        }

        public int getSellCount() {
            return sellCount;
        }

        public void setSellCount(int sellCount) {
            this.sellCount = sellCount;
        }

        public double getCostPrice() {
            return costPrice;
        }

        public void setCostPrice(double costPrice) {
            this.costPrice = costPrice;
        }

        public double getSellPrice() {
            return sellPrice;
        }

        public void setSellPrice(double sellPrice) {
            this.sellPrice = sellPrice;
        }

        public boolean isNewBook() {
            return newBook;
        }

        public void setNewBook(boolean newBook) {
            this.newBook = newBook;
        }

        public int getDiscountPercent() {
            return discountPercent;
        }

        public void setDiscountPercent(int discountPercent) {
            this.discountPercent = discountPercent;
        }

        public boolean isSale() {
            return sale;
        }

        public void setSale(boolean sale) {
            this.sale = sale;
        }
    }
}
