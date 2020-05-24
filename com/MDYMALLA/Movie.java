package com.MDYMALLA;

/**
 * Movie is class that when instantiated will represent each movie object in library
 */
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

    /**
     * Set movie data:
     * method used to set all properties of movie object - used in BST to replace a deleted node
     */
    public void setMovieData(String title, String genre, Rating rating, int copies, int borrowed) {
        this.title = title;
        this.genre = genre;
        this.movieRating = rating;
        this.copies = copies;
        this.timesBorrowed = borrowed;
    }

    /**
     * Borrowing a movie:
     * If a movie is borrowed its number of copies available will decrease
     */
    public void setCopies() {
        this.copies -= 1;
    }

    /**
     * Returning a movie:
     * if a movie is returned by a registered member its number of copies will increase
     */
    public void returnCopy() {
        this.copies += 1;
    }

    /**
     * Borrowing a movie:
     * to track how popular a movie is each time it is borrowed its timesBorrowed increases
     */
    public void setTimesBorrowed() {
        this.timesBorrowed += 1;
    }

    /**
     * flag to determine if a movie can be rented
     * If a movie in the library has 0 copies available it cannot be borrowed
     */
    public boolean canRent() {
        if (copies > 0) {
            return true;
        }
        System.out.println("*** Movie has no copies left to borrow ***\n");
        return false;
    }
}
