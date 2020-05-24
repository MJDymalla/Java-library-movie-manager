package com.MDYMALLA;

/**
 * MemberCollection is class that when instantiated will hold registered member objects in library
 * Collection represented as an array structure
 */
public class MemberCollection {

    /**
     * Array to hold collection of Member objects
     * initialized at zero and will increase each time a new member is added until capacity reached
     */
    private Member[] memberCollection = new Member[0];
    private int currentCapacity = 0;
    private int fullCapacity = 0;
    private final int maxCapacity = 10;

    /**
     * Add a member to collection
     * completes checks on collection size and if new member is duplicate
     */
    public void addMember(Member member) {
        if (member != null) {
            if (currentCapacity == fullCapacity && currentCapacity < maxCapacity) {
                if (checkExistence(member.getFullName())) {
                    increaseCapacity();
                    memberCollection[currentCapacity] = member;
                    System.out.println("*** Successfully created user ***");
                    currentCapacity++;
                }
            }
            else if (currentCapacity == maxCapacity){
                System.out.println("\n*** Cannot register member - library capacity reached ***");
            }
        }
    }

    /**
     * Check if a member already exists in the collection
     * system will not permit duplicate members
     */
    public boolean checkExistence(String name) {
        for (Member member: memberCollection) {
            if (member.getFullName().equals(name)) {
                System.out.println("*** Registration failed - member already exists ***");
                return false;
            }
        }
        return true;
    }

    /**
     * Increase capacity of member collection
     * initialize a new array at increased size by copying items across
     */
    private void increaseCapacity() {
        Member[] temp = new Member[fullCapacity + 1];
        for (int i = 0; i < fullCapacity; i++) temp[i] = memberCollection[i];
        memberCollection = temp;
        fullCapacity++;
    }

    /**
     * Login as a member against registered library members
     * return true if input matches member details
     */
    public boolean login(String username, int password) {
        Member member = getMember(username);
        if (member != null && password == member.getPassword()) {
            System.out.println("\n*** Successfully logged in as a library member ***\n");
            return true;
        } else {
            System.out.println("*** Login failed - check details ***");
        }
        return false;
    }

    /**
     * Get a Member object given a name
     * return member if they exist in collection
     */
    public Member getMember(String name) {
        for (Member member: memberCollection) {
            if (member.getFullName().equals(name)){
                return member;
            }
        }
        return null;
    }

    /**
     * Show all current registered library members
     */
    public void showMembers() {
        if (memberCollection.length > 0) {
            System.out.println("\n--------------------------------------");
            System.out.println("| *** Current Registered Members *** |");
            System.out.println("--------------------------------------");
            for (Member member: memberCollection) {
                System.out.println(member.getFirstName() + " " + member.getLastName());
            }
            System.out.println("--------------------------------------");
        } else {
            System.out.println("--------------------------------------");
            System.out.println("*** No members currently registered ***");
        }

    }

    /**
     * Get a registered members phone number given a name
     * return printed phone number if member can be found by provided input
     */
    public int getPhoneNumber(String firstName, String lastName) {
        String username = firstName + lastName;
        Member member = getMember(username);
        if (member != null) {
            int number = member.getPhoneNumber();
            System.out.println("\n----------------------------------------------");
            System.out.println("| *** Found library members phone number *** |");
            System.out.println("----------------------------------------------");
            System.out.println("| Member: " + firstName + " " + lastName);
            System.out.println("| Phone number: " + number);
            System.out.println("----------------------------------------------");
            return number;
        } else {
            System.out.println("*** Could not find member ***");
        }
        return 0;
    }
}
