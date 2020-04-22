package com.MDYMALLA;

import java.util.Scanner;

public class Main {

    public static void displayMain() {
        System.out.println("Welcome to the Community Library");
        System.out.println("===========Main Menu===========");
        System.out.println("1. Staff Login");
        System.out.println("2. Member Login");
        System.out.println("0. Exit");
        System.out.println("===============================");
        System.out.println("Please make a selection (1-2, or 0 to exit):");
    }

    public static void displayStaff() {
        System.out.println("==========Staff Menu==========");
        System.out.println("1. Add a new movie DVD");
        System.out.println("2. Remove a movie DVD");
        System.out.println("3. Register a new Member");
        System.out.println("4. Find a registered member's phone number");
        System.out.println("0. Return to main menu");
        System.out.println("===============================");
        System.out.println("Please make a selection (1-4 or 0 to return to main menu):");
    }

    public static void displayMember() {
        System.out.println("==========Member Menu==========");
        System.out.println("1. Display all movies");
        System.out.println("2. Borrow a movie DVD");
        System.out.println("3. Return a movie DVD");
        System.out.println("4. List current borrowed movie DVDs");
        System.out.println("5. Display top 10 most popular movies");
        System.out.println("0. Return to main menu");
        System.out.println("===============================");
        System.out.println("Please make a selection (1-5 or 0 to return to main menu):");
    }

    // staff login flag
    public static boolean staffLogin(String username, String password) {
        String defaultUsername = "staff";
        String defaultPassword = "today123";
        if (username.equals(defaultUsername) && password.equals(defaultPassword)) {
            System.out.println("\n*** Successfully logged in as a staff member ***\n");
            return true;
        }
        else {
            System.out.println("Please try again");
            return false;
        }
    }

    // member login flag
    public static boolean memberLogin(Member user, String fullname, int password) {
        String username = user.getFullName();
        int pass = user.getPassword();
        if (username.equals(fullname) && pass == password) {
            System.out.println("\n*** Successfully logged in as member ***\n");
            return true;
        }
        else {
            System.out.println("Please try again");
            return false;
        }
    }

    public static void main(String[] args) {
        boolean online = true;
        boolean staffStatus = false;
        boolean memberStatus = false;
        int sessionStatus = 1;
        Scanner input = new Scanner(System.in);
        Scanner userIn = new Scanner(System.in);

        while (online) {
            // Display main menu
            if (sessionStatus == 1) {
                displayMain();
                int selected = input.nextInt();

                if (selected == 1) {
                    while (!staffStatus) {
                        System.out.println("------------------------------");
                        System.out.print("Username: ");
                        String username = userIn.nextLine();
                        System.out.print("Password: ");
                        String password = userIn.nextLine();
                        System.out.println("------------------------------");

                        boolean loginStatus = staffLogin(username, password);
                        if (loginStatus) {
                            staffStatus = true;
                        }
                    }
                    if (staffStatus) {
                        sessionStatus = 2;
                    }
                }

                else if (selected == 2) {
                    while (!memberStatus) {
                        System.out.println("------------------------------");
                        System.out.print("Username: ");
                        String username = userIn.nextLine();
                        System.out.print("Password: ");
                        String password = userIn.nextLine();
                        System.out.println("------------------------------");

                        //boolean loginStatus = memberLogin()
                    }
                    if (memberStatus) {
                        sessionStatus = 3;
                    }
                }

                else if (selected == 0) {
                    online = false;
                }
            }

            // Display staff menu
            if (sessionStatus == 2) {
                displayStaff();
                Staff staffUser = new Staff();
                int selected = input.nextInt();

                if (selected == 3) {
                    System.out.println("*** Register a new user ***");
                    System.out.print("First name: ");
                    String firstName = userIn.nextLine();
                    System.out.print("Last name: ");
                    String lastName = userIn.nextLine();
                    System.out.print("Password: ");
                    int password = userIn.nextInt();

                    staffUser.registerMember(firstName, lastName, password);
                    System.out.println("*** Successfully created user - returning to staff menu ***");
                }
                if (selected == 0) {
                    sessionStatus = 1;
                }
            }

            // Display member menu
            if (sessionStatus == 3) {
                displayMember();
                int selected = input.nextInt();
                if (selected == 0) {
                    sessionStatus = 1;
                }
            }
        }
        if(!online) {
            System.out.println("Peace out");
        }
    }
}
