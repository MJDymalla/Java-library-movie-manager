package com.MDYMALLA;

import java.util.*;

public class Staff {
    private String username = "staff";
    private String password = "today123";

    /**
     * Staff login
     * Username & password cannot be changed for staff - default details always used
     */
    public boolean login(Scanner input) {
        System.out.println("\n--------------------------------------------------");
        System.out.println("|              *** Staff Login ***               |");
        System.out.println("--------------------------------------------------");
        System.out.println("| * reminder: username & password case sensitive |");
        System.out.println("--------------------------------------------------");
        System.out.print("| Username: ");
        String user = input.next();
        System.out.print("| Password: ");
        String pass = input.next();
        System.out.println("--------------------------------------------------");

        if (user.equals(username) && pass.equals(password)) {
            System.out.println("\n*** Successfully logged in as a staff member ***\n");
            return true;
        }
        System.out.println("*** Login failed - check that details are correct ***");
        return false;
    }

    /**
     * Staff option 1:
     * Add a new movie DVD to the library collection
     */
    public void addMovie(MovieCollection collection, Scanner input) {
        try {
            System.out.println("\n---------------------------------------------------------------------");
            System.out.println("|            *** Add a new movie to the library ***                 |");
            System.out.println("---------------------------------------------------------------------");
            System.out.println("| * A movie title can contain spaces and symbols                    |");
            System.out.println("| * Rating must be an one of the approved ratings: G, PG, M, MA, R, |");
            System.out.println("|   as this will correspond to what a library member can borrow     |");
            System.out.println("|   (Rating is case sensitive - use uppercase)                      |");
            System.out.println("| * Copies is a numeric value (e.g. 2) available to library members |");
            System.out.println("---------------------------------------------------------------------");
            System.out.print("| Title: ");
            input.nextLine();
            String title = input.nextLine();
            System.out.print("| Genre: ");
            String genre = input.next();
            System.out.print("| Rating: ");
            Rating rating = Rating.valueOf(input.next());
            System.out.print("| Copies: ");
            int copies = input.nextInt();
            System.out.println("---------------------------------------------------------------------");
            collection.addMovie(title, genre, rating, copies, 0);
        } catch (Exception e) {
            System.out.println("---------------------------------------------------------------------");
            System.out.println("*** Adding movie failed: please check that your input is in the format directed ***");
        }
    }

    /**
     * Staff option 2:
     * Remove a movie DVD from the library collection
     */
    public void removeMovie(MovieCollection collection, Scanner input) {
        System.out.println("\n----------------------------------------------");
        System.out.println("|   *** Remove a movie from the library ***    |");
        System.out.println("-----------------------------------------------");
        System.out.println("| * To remove a movie just enter its title     |");
        System.out.println("| * Will remove all copies of specified movie, |");
        System.out.println("|   if a member has this movie rented, it will |");
        System.out.println("|   have to be removed again once returned     |");
        System.out.println("| * Reminder: Movie title is case sensitive    |");
        System.out.println("-----------------------------------------------");
        System.out.print("| Movie title: ");
        input.nextLine();
        String movieName = input.nextLine();
        System.out.println("-------------------------------------------");

        if (collection.findMovie(collection.root, movieName) != null) {
            collection.removeMovie(collection.root, movieName);
            System.out.println("*** " + movieName + " removed from library collection ***");
            collection.reduceCollectionSize();
        }
    }

    /**
     * Staff option 3:
     * Register a new member to the library
     */
    public void addNewMember(MemberCollection collection, Scanner input) {
        try {
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("|                   *** Register a new user ***                   |");
            System.out.println("------------------------------------------------------------------");
            System.out.println("| * All fields are required and cannot be empty                   |");
            System.out.println("| * First and last name must be more than one character in length,|");
            System.out.println("|   they must also be only one word each                          |");
            System.out.println("| * Age is in number format (e.g. 10)                             |");
            System.out.println("| * Phone number must be between 8-10 digits in length            |");
            System.out.println("| * Password is a number - 4 digits in length                     |");
            System.out.println("------------------------------------------------------------------");
            System.out.print("| First name: ");
            String firstName = input.next();
            System.out.print("| Last name: ");
            String lastName = input.next();
            System.out.print("| Age: ");
            int age = input.nextInt();
            System.out.print("| Phone Number: ");
            int phoneNumber = input.nextInt();
            System.out.print("| Password: ");
            int password = input.nextInt();
            System.out.println("------------------------------------------------------------------");

            /* check input and is member already exists in system */
            Member newMember = registerMember(firstName, lastName, age,phoneNumber, password);
            collection.addMember(newMember);
        } catch (Exception e) {
            System.out.println("------------------------------------------------------------------");
            System.out.println("*** Registration failed: please check that your input is in the format directed ***");
        }
    }

    /**
     * Input validation for registering new member
     * Name: names should be more than one character
     * Age: should be an integer
     * Phone: should be an integer between 8-10 digits long
     * Password: should be integer 4 digits long
     */
    public static Member registerMember(String firstName, String lastName, int age, int phoneNumber, int password) {
        if (firstName.length() > 1 && lastName.length() > 1) {
            if (phoneNumber > 10000000) {
                if (password > 999 && password < 10000) {
                    Member newMember = new Member(firstName, lastName, age,phoneNumber, password);
                    return newMember;
                } else {
                    System.out.println("*** Reminder: password must be 4 digits in length ***");
                }
            } else {
                System.out.println("*** Reminder: phone number must be between 8-10 digits in length ***");
            }
        } else {
            System.out.println("*** Reminder: first name and last name must be more that 2 letters ***");
        }
        return null;
    }

    /**
     * Staff option 4:
     * Find a registered members phone number
     */
    public void findPhoneNumber(MemberCollection collection, Scanner input) {
        /* get input: registered members first and last name */
        System.out.println("\n-----------------------------------------------");
        System.out.println("| *** Find a library members phone number *** |");
        System.out.println("-----------------------------------------------");
        System.out.println("| * Reminder: names are case sensitive        |");
        System.out.println("-----------------------------------------------");
        System.out.print("| First name: ");
        String firstName = input.next();
        System.out.print("| Last name: ");
        String lastName = input.next();
        System.out.println("-----------------------------------------------");

        /* get phone number from input if it exists */
        collection.getPhoneNumber(firstName, lastName);
    }
}
