package com.MDYMALLA;

public enum Rating {
    G (0),
    PG(0),
    M(15),
    MA(15),
    R(18);

    private int ageRestriction;

    Rating(int ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public int getAgeRestriction() {
        return ageRestriction;
    }
}
