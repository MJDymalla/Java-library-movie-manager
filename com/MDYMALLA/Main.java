package com.MDYMALLA;

import java.util.*;

public class Main {

    public static void displayMainUI() {
        System.out.println("Welcome to the Community Library");
        System.out.println("===========Main Menu===========");
        System.out.println("1. Staff Login");
        System.out.println("2. Member Login");
        System.out.println("0. Exit");
        System.out.println("===============================");
        System.out.print("Please make a selection (1-2, or 0 to exit): ");
    }

    public static void displayStaffUI() {
        System.out.println("==========Staff Menu==========");
        System.out.println("1. Add a new movie DVD");
        System.out.println("2. Remove a movie DVD");
        System.out.println("3. Register a new Member");
        System.out.println("4. Find a registered member's phone number");
        System.out.println("5. Show all registered members");
        System.out.println("0. Return to main menu");
        System.out.println("===============================");
        System.out.print("Please make a selection (1-4 or 0 to return to main menu): ");
    }

    public static void displayMemberUI() {
        System.out.println("==========Member Menu==========");
        System.out.println("1. Display all movies");
        System.out.println("2. Borrow a movie DVD");
        System.out.println("3. Return a movie DVD");
        System.out.println("4. List current borrowed movie DVDs");
        System.out.println("5. Display top 10 most popular movies");
        System.out.println("0. Return to main menu");
        System.out.println("===============================");
        System.out.print("Please make a selection (1-5 or 0 to return to main menu): ");
    }

    public static void menuReturn() {
        Scanner input = new Scanner(System.in);
        System.out.print("~ hit ENTER to return to menu ~");
        input.nextLine();
        System.out.print("\n");
    }

    public static void main(String[] args) {
        /* program online for user */
        boolean online = true;

        /* staff member logged in */
        boolean staffStatus;

        /* member logged in */
        String currentUser = "";

        /* status dependent on who is logged in - default main menu */
        int sessionStatus = 1;

        /* instantiate a base staff user */
        Staff staffUser = new Staff();

        /* instantiate collection of library members */
        MemberCollection memberCollection = new MemberCollection();

        /* instantiate collection of movie DVDs */
        MovieCollection movieCollection = new MovieCollection();

        /* instantiate scanner for user input */
        Scanner input = new Scanner(System.in);

        /* Main program state */
        do {
            /* MAIN MENU */
            if (sessionStatus == 1) {
                displayMainUI();
                try {
                    int selected = input.nextInt();
                    /* validate staff member */
                    if (selected == 1) {
                        staffStatus = staffUser.login(input);

                        /* check input match */
                        if (staffStatus) {
                            sessionStatus = 2;
                        } else {
                            menuReturn();
                        }
                    }
                    /* validate registered member */
                    else if (selected == 2) {
                        /* input: registered member name & password */
                        try {
                            System.out.println("\n------------------------------------------------------");
                            System.out.println("|                *** Member Login ***                |");
                            System.out.println("------------------------------------------------------");
                            System.out.println("| * username: first name and last name as one word   |");
                            System.out.println("| * reminder: username is case sensitive             |");
                            System.out.println("------------------------------------------------------");
                            System.out.print("| Username: ");
                            String username = input.next();
                            System.out.print("| Password: ");
                            int password = input.nextInt();
                            System.out.println("----------------------------------------------------");

                            /* check input match */
                            if (memberCollection.login(username, password)) {
                                currentUser = username;
                                sessionStatus = 3;
                            } else {
                                menuReturn();
                            }
                        } catch (Exception e) {
                            System.out.println("----------------------------------------------------");
                            System.out.println("*** Reminder: password for members contains only numbers ***");
                            menuReturn();
                        }
                    }
                    /* exit program */
                    else if (selected == 0) {
                        System.out.print("~ Are you sure you want to exit? (y/n): ");
                        String confirmation = input.next();
                        if (confirmation.equals("y")) {
                            online = false;
                            input.close();
                        } else if (confirmation.equals("N")) {
                            System.out.println("*** Returning to main menu ***\n");
                            continue;
                        }
                    }
                    else {
                        System.out.println("\n*** Option unavailable - please enter from available ***\n");
                    }
                } catch (Exception e) {
                    /* catch garbage input at menu screen */
                    System.out.println("\n*** Invalid input - please enter from options available ***\n");
                    input.next();
                }
            }

            /* STAFF MENU */
            if (sessionStatus == 2) {
                displayStaffUI();
                try {
                    int selected = input.nextInt();
                    /* Add new movie to library */
                    if (selected == 1) {
                        staffUser.addMovie(movieCollection, input);
                        menuReturn();
                    }
                    /* Remove a movie from the library */
                    else if (selected == 2) {
                        staffUser.removeMovie(movieCollection, input);
                        menuReturn();
                    }
                    /* Register a new user */
                    else if (selected == 3) {
                        staffUser.addNewMember(memberCollection, input);
                        menuReturn();
                    }
                    /* Find a registered members phone number */
                    else if (selected == 4) {
                        staffUser.findPhoneNumber(memberCollection, input);
                        menuReturn();
                    }
                    /* Show all registered members */
                    else if (selected == 5) {
                        memberCollection.showMembers();
                        menuReturn();
                    }
                    /* Return to main menu */
                    else if (selected == 0) {
                        System.out.println("\n***** Staff member logged out *****");
                        System.out.println("***** Returning to main menu *****\n");
                        sessionStatus = 1;
                    }
                    else {
                        System.out.println("\n*** Option unavailable - please enter from available ***\n");
                    }
                } catch (Exception e) {
                    /* catch garbage input at menu screen */
                    System.out.println("\n*** Invalid input - please enter from staff options available ***\n");
                    input.next();
                }
            }

            /* MEMBER MENU */
            if (sessionStatus == 3) {
                displayMemberUI();
                Member member = memberCollection.getMember(currentUser);
                try {
                    int selected = input.nextInt();
                    /* Show all library movies */
                    if (selected == 1) {
                        member.displayAllMovies(movieCollection);
                        menuReturn();
                    }
                    /* Borrow a library movie DVD */
                    else if (selected == 2) {
                        member.borrowMovie(movieCollection, input);
                        menuReturn();
                    }
                    /* Return a movie */
                    else if (selected == 3) {
                        member.returnMovie(movieCollection, input);
                        menuReturn();
                    }
                    /* Show all borrowed movies */
                    else if (selected == 4) {
                        member.showMovies();
                        menuReturn();
                    }
                    /* Display top rented movies */
                    else if (selected == 5) {
                        movieCollection.moviePopularity(movieCollection.root);
                        menuReturn();
                    }
                    /* Return to main menu */
                    else if (selected == 0) {
                        System.out.println("\n***** Returning to main menu *****");
                        System.out.println("***** Library member " + member.getFirstName() + " " + member.getLastName() + " logged out *****\n");
                        sessionStatus = 1;
                        currentUser = "";
                    } else {
                        System.out.println("\n*** Option unavailable - please enter from available ***\n");
                    }
                } catch (Exception e) {
                    /* catch garbage input at menu screen */
                    System.out.println("\n*** Invalid input - please enter from member options available ***\n");
                    input.next();
                }
            }
        } while (online);
        System.out.println("\n******* later nerd *******");
    }
}
