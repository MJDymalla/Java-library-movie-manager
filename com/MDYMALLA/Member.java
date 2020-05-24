package com.MDYMALLA;

import java.util.*;

/**
 * Member is class that when instantiated will represent a registered member in library system
 * Member behaves as basic user and is able to list, borrow, and return movies in the system
 */
public class Member {
    private final String firstName;
    private final String lastName;
    private final String fullName;
    private final int phoneNumber;
    private final int password;
    private final int age;

    /**
     * List to hold registered members current borrowed movies
     */
    private List<Movie> moviesRented = new ArrayList<>();

    /**
     * Member constructor
     */
    public Member(String firstName, String lastName, int age,int phoneNumber, int password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.fullName = firstName + lastName;
    }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getFullName() { return fullName; }

    public int getPhoneNumber() { return this.phoneNumber; }

    public int getPassword() { return password; }

    /**
     * Member option 1:
     * Display all movies in library
     */
    public void displayAllMovies(MovieCollection collection) {
        if (collection.root != null) {
            System.out.println("\n--------------------------------------------------------------");
            System.out.println("|             *** Current movies in library ***              |");
            System.out.println("--------------------------------------------------------------");
            collection.displayMovies(collection.root);
            System.out.println("--------------------------------------------------------------");
        } else {
            System.out.println("-----------------------------------------");
            System.out.println("*** Currently no movies in library ***");
        }
    }

    /**
     * Member option 2:
     * Borrow a movie DVD from library
     * requires checks on members age, copies available, and if movie isn't currently in possession already
     * on success - add movie to members collection, reduce available copies, and increase times borrowed
     */
    public void borrowMovie(MovieCollection collection, Scanner input) {
        System.out.println("\n--------------------------------------------");
        System.out.println("|        *** Borrow a new movie ***         |");
        System.out.println("--------------------------------------------");
        System.out.println("| * Reminder: Movie title is case sensitive |");
        System.out.println("--------------------------------------------");
        System.out.print("| Title: ");
        input.nextLine();
        String title = input.nextLine();
        System.out.println("--------------------------------------------");
        Movie rentedMovie = collection.findMovie(collection.root, title);
        if (rentedMovie != null) {
            if (ageCheck(rentedMovie.getRating()) && rentedMovie.canRent() && movieCheck(rentedMovie)) {
                rentedMovie.setCopies();
                rentedMovie.setTimesBorrowed();
                moviesRented.add(rentedMovie);
                System.out.println("*** Successfully rented: " + rentedMovie.getTitle() + " ***");
            }
        }
    }

    /**
     * Member option 3:
     * Return a movie DVD to the library
     * requires checks on if member has movie currently in possession and if staff has removed movie while borrowed
     * on success - remove movie from member and increase copies available
     */
    public void returnMovie(MovieCollection collection, Scanner input) {
        System.out.println("\n--------------------------------------------");
        System.out.println("|           *** Return a movie ***          |");
        System.out.println("--------------------------------------------");
        System.out.println("| * Reminder: Movie title is case sensitive |");
        System.out.println("--------------------------------------------");
        System.out.print("| Title: ");
        input.nextLine();
        String title = input.nextLine();
        System.out.println("--------------------------------------------");
        Movie returnedMovie = collection.findMovie(collection.root, title);
        if (returnedMovie != null) {
            if (moviesRented.contains(returnedMovie)) {
                moviesRented.remove(returnedMovie);
                returnedMovie.returnCopy();
                System.out.println("*** Returned movie: " + returnedMovie.getTitle() + " ***");
            } else {
                System.out.println("*** You haven't got this movie borrowed currently ***");
            }
        } else {
            for (Movie movie: moviesRented) {
                if (movie.getTitle().equals(title)) {
                    moviesRented.remove(movie);
                    System.out.println("*** Returned movie: " + movie.getTitle() + " ***");
                }
            }
        }
    }

    /**
     * Member option 4:
     * Display all movie DVDs currently borrowed by member
     */
    public void showMovies() {
        if (moviesRented.isEmpty()) {
            System.out.println("*** Currently no movies borrowed ***");
        } else {
            System.out.println("\n-----------------------------------");
            System.out.println("| *** Current movies borrowed *** |");
            System.out.println("-----------------------------------");
            for (Movie movie: moviesRented) {
                System.out.println("| Title: " + movie.getTitle());
            }
            System.out.println("-----------------------------------");
        }
    }

    /**
     * Registered members age corresponds to what rating of movie they can borrow
     */
    public boolean ageCheck(Rating rating) {
        if (rating.getAgeRestriction() > age) {
            System.out.println("*** Couldn't rent movie ***");
            System.out.println(
                    "*** You need to be at least " +
                    rating.getAgeRestriction() +
                    " years old to rent this movie ***"
            );
            return false;
        }
        return true;
    }

    /**
     * Member cannot borrow movie they already have in possession
     */
    public boolean movieCheck(Movie movie) {
        if (moviesRented.contains(movie)) {
            System.out.println("*** You already have this movie borrowed ***");
            return false;
        }
        return true;
    }

}
