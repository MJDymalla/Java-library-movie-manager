package com.MDYMALLA;

public class Member {
    private String firstName;
    private String lastName;
    private String fullName;
    private int password;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + lastName;
    }

    public int getPassword() {
        return password;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public void setPassword(int pass) {
        this.password = pass;
    }
}
