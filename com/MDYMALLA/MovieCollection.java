package com.MDYMALLA;

public class MovieCollection {
    Movie root;
    private int collectionSize;

    public void increaseCollectionSize() { this.collectionSize += 1; }
    public void reduceCollectionSize() { this.collectionSize -= 1; }
    public int getCollectionSize() { return this.collectionSize; }

    /**
     * Add a movie to the library collection
     */
    public void addMovie(String title, String genre, Rating rating, int copies, int borrowed) {
        root = addNode(root, title, genre, rating, copies, borrowed);
    }

    public Movie addNode(Movie root, String title, String genre, Rating rating, int copies, int borrowed) {
        if (root == null) {
            root = new Movie(title, genre, rating, copies, borrowed);
            System.out.println("*** New movie added to library collection ***");
            increaseCollectionSize();
            return root;
        }

        if (title.compareToIgnoreCase(root.getTitle()) < 0) {
            root.leftChild = addNode(root.leftChild, title, genre, rating, copies, borrowed);
        } else if (title.compareToIgnoreCase(root.getTitle()) > 0) {
            root.rightChild = addNode(root.rightChild, title, genre, rating, copies, borrowed);
        } else if (title.equals(root.getTitle())) {
            System.out.println("*** This movie already exists in library ***");
        }
        return root;
    }

    /**
     * Traverse tree using in order traversal
     * prints list of available movies in lexicographical order
     */
    public void displayMovies(Movie movie) {
        if (movie != null) {
            displayMovies(movie.leftChild);
            System.out.println(
                        "| TITLE: " + movie.getTitle() +
                        " | GENRE: " + movie.getGenre() +
                        " | RATING: " + movie.getRating() +
                        " | COPIES AVAILABLE: " + movie.getCopies()
            );
            displayMovies(movie.rightChild);
        }
    }

    /**
     * Find a movie given title
     * Needs to handle exception if nothing found - returning null
     */
    public Movie findMovie(Movie node, String title) {
        String movieName = node.getTitle();
        try {
            if (movieName.equals(title) || node == null) {
                return node;
            }
            if (movieName.compareTo(title) > 0) {
                return findMovie(node.leftChild, title);
            }
            return findMovie(node.rightChild, title);
        } catch (Exception e) {
            System.out.println("*** Couldn't find movie - check spelling ***");
            return null;
        }
    }

    /**
     * Remove Movie DVD from library collection
     */
    public Movie removeMovie(Movie root, String title) {
        if (root == null){
            return null;
        }
        if (title.compareToIgnoreCase(root.getTitle()) < 0) {
            root.leftChild = removeMovie(root.leftChild, title);
        } else if (title.compareToIgnoreCase(root.getTitle()) > 0) {
            root.rightChild = removeMovie(root.rightChild, title);
        } else {
            if (root.leftChild == null && root.rightChild == null) {
                return null;
            } else if (root.leftChild == null) {
                return root.rightChild;
            } else if (root.rightChild == null) {
                return root.leftChild;
            } else {
                Movie successor = findSuccessor(root.rightChild);
                root.setMovieData(
                        successor.getTitle(),
                        successor.getGenre(),
                        successor.getRating(),
                        successor.getCopies(),
                        successor.getTimesBorrowed()
                );
                root.rightChild = removeMovie(root.rightChild, successor.getTitle());
            }
        }
        return root;
    }

    /**
     * Find in-order successor
     * Used to replace deleted node that has both right and left child nodes
     */
    private Movie findSuccessor(Movie node) {
        if (node.leftChild != null) {
            return findSuccessor(node.leftChild);
        }
        return node;
    }

    /**
     * Store and sort movies based on popularity
     */
    public void moviePopularity(Movie root) {
        Movie[] temp = new Movie[collectionSize];
        int topMovies;
        if (collectionSize < 10) {
            topMovies = collectionSize;
        } else {
            topMovies = 10;
        }
        if (root != null) {
            int size = treeToArray( root, temp, 0);
            sort(temp, 0, size - 1);
            System.out.println("\n------------------------------------------------------");
            System.out.println("|          *** Top movies in library ***          |");
            System.out.println("------------------------------------------------------");
            for (int i = 0; i < topMovies; i++) {
                System.out.println("| " + (i+1) + " - MOVIE: " + temp[i].getTitle() + " | TIMES BORROWED: " + temp[i].getTimesBorrowed());
            }
            System.out.println("------------------------------------------------------");
        } else {
            System.out.println("------------------------------------------------------");
            System.out.println("*** Currently no movies in library ***");
        }
    }

    /**
     * Add items from binary tree to array for sorting
     */
    public int treeToArray(Movie movie, Movie[] a, int i) {
        if (movie.leftChild != null) {
            i = treeToArray(movie.leftChild, a, i);
        }
        a[i] = movie;
        i++;
        if (movie.rightChild != null) {
            i = treeToArray(movie.rightChild, a, i);
        }
        return i;
    }

    /**
     * Merge Sort based on times movie has been borrowed
     */
    public void merge(Movie[] collection, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Movie[] L = new Movie[n1];
        Movie[] R = new Movie[n2];

        for (int i = 0; i < n1; ++i) {
            L[i] = collection[left + i];
        }
        for (int j = 0; j < n2; ++j) R[j] = collection[mid + 1 + j];
        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (L[i].getTimesBorrowed() > R[j].getTimesBorrowed()){
                collection[k] = L[i];
                i++;
            } else {
                collection[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            collection[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            collection[k] = R[j];
            j++;
            k++;
        }
    }

    public void sort(Movie[] collection, int left, int right) {
        if (left < right) {
            int m = (left + right) / 2;
            sort(collection, left, m);
            sort(collection,m+1, right);
            merge(collection, left, m, right);
        }
    }

}
