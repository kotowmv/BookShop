package ru.topacademy.java212.model;

public class ComicBook {
    private String name;
    private String author;
    private String publishingHouse;
    private int pages;
    private String genre;
    private int publicationYear;
    private String series;

    public ComicBook(String name,
                     String author,
                     String publishingHouse,
                     int pages,
                     String genre,
                     int publicationYear,
                     String series) {
        this.name = name;
        this.author = author;
        this.publishingHouse = publishingHouse;
        this.pages = pages;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.series = series;
    }

    @Override
    public String toString() {
        return (name + " (" + author + ", " + genre + ", " + series + ", " + publicationYear + ")");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }
}
