package com.MDYMALLA;

public class Movie {
    private String title;
    private String genre;
    private Rating movieRating;
    private int copies;
    private int timesBorrowed;

    Movie leftChild;
    Movie rightChild;

    /**
     * Movie Constructor
     */
    public Movie(String title, String genre, Rating rating, int copies, int borrowed) {
        this.title = title;
        this.genre = genre;
        this.movieRating = rating;
        this.copies = copies;
        this.timesBorrowed = borrowed;
    }

    public String getTitle() { return title; }

    public String getGenre() { return genre; }

    public Rating getRating() { return movieRating; }

    public int getCopies() { return this.copies; }

    public int getTimesBorrowed() { return timesBorrowed; }

    public void setMovieData(String title, String genre, Rating rating, int copies, int borrowed) {
        this.title = title;
        this.genre = genre;
        this.movieRating = rating;
        this.copies = copies;
        this.timesBorrowed = borrowed;
    }

    public void setCopies() {
        this.copies -= 1;
    }

    public void returnCopy() {
        this.copies += 1;
    }

    public void setTimesBorrowed() {
        this.timesBorrowed += 1;
    }

    public boolean canRent() {
        if (copies > 0) {
            return true;
        }
        System.out.println("*** Movie has no copies left to borrow ***\n");
        return false;
    }
}
