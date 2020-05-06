package com.MDYMALLA;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    public void movieTest() {
        MovieCollection movieCollection = new MovieCollection();

        movieCollection.addMovie("Space Jam", "sport", Rating.M, 2, 5);
        movieCollection.addMovie("Space Man", "sport", Rating.M, 1, 2);
        movieCollection.addMovie("Jaws", "sport", Rating.M, 1, 3);
        movieCollection.addMovie("Jaws 2", "sport", Rating.M, 1, 6);
        movieCollection.addMovie("Waterboy", "sport", Rating.M, 1, 8);
        movieCollection.addMovie("Inception", "sport", Rating.M, 1, 1);
        movieCollection.addMovie("Interstellar", "sport", Rating.M, 1, 9);
        movieCollection.addMovie("Uncle Drew", "sport", Rating.M, 1, 11);

        assertEquals(movieCollection.getCollectionSize(), 8);

    }
}