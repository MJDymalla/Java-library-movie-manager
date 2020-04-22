package com.MDYMALLA;

public class Staff {

    public static Member registerMember(String firstName, String lastName, int password) {
        Member newMember = new Member();
        newMember.setFirstName(firstName);
        newMember.setLastName(lastName);
        newMember.setPassword(password);

        return newMember;
    }
}
