//-----------------------------------------------------
// Title:       Movie Database with BST
// Author:      Erinç Polat
//              Çağla Su Keşan
// ID:          13715949830
//              ~~other id~~
// Section:     01
// Assignment:  4
// Description: This class defines the BST in order to hold Movie objects.
//-----------------------------------------------------

public class MovieBST {

    private Node root;

    class Node {
        // Description: This class defines a single node of the BST tree.
        // A node holds a Movie object as its data.
        Movie data;
        Node left = null;
        Node right = null;

        public Node(Movie data) {
            this.data = data;
        }
    }

    public MovieBST() {
        this.root = null;
    }

    // calls the initial add method
    public void add(Movie newMovie) {

        this.root = this.add(this.root, newMovie);
    }

    private Node add(Node root, Movie data) {
        // --------------------------------------------------------
        // Summary: Adds a new node with the given movie data.
        // Postcondition: Given movie is added.
        // --------------------------------------------------------

        // leaf nodes or empty tree
        if (root == null) {
            root = new Node(data);
            return root;
        }

        // if the new movie's release year is bigger than the current node's movie's,
        // add it to the right subtree.
        if (root.data.getReleaseYear() <= data.getReleaseYear()) {
            root.right = add(root.right, data);
        }
        // otherwise, add it to the left subtree
        else {
            root.left = add(root.left, data);
        }

        // initial root node can't change.
        return root;
    }

    // calls the initial find method
    public Movie findByTitle(String movieTitle) {
        return this.findByTitle(root, movieTitle);
    }

    private Movie findByTitle(Node root, String movieTitle) {
        // --------------------------------------------------------
        // Summary: Finds the given movie's node recursively and return its data.
        // Precondition: Given movie member should exists.
        // Postcondition: Wanted node is returned if it exists. Otherwise, returned
        // null.
        // --------------------------------------------------------

        // if tree is empty or leaf nodes
        if (root == null) {
            return null;
        }

        // Method should traverse all nodes.
        // Can't use the benefits of the BST.
        // Tree isn't constructed with respect to the movie titles.
        Movie foundLeft = null;
        Movie foundRight = null;

        if (root.data.getTitle().equals(movieTitle)) {
            return root.data;
        }

        if (root.right != null) {
            foundRight = findByTitle(root.right, movieTitle);
        }

        if (root.left != null) {
            foundLeft = findByTitle(root.left, movieTitle);
        }

        if (foundLeft != null) {
            return foundLeft;
        }

        if (foundRight != null) {
            return foundRight;
        }

        return null;
    }

    // calls the initial remove method
    public void remove(String movieTitle) {
        this.root = remove(this.root, movieTitle);
    }

    private Node remove(Node root, String movieTitle) {
        // --------------------------------------------------------
        // Summary: Removes the given movie.
        // Precondition: Movie should exist.
        // Postcondition: Given movie is removed if it exists.
        // --------------------------------------------------------

        // if the tree is empty or leaf nodes
        if (root == null) {
            return null;
        }

        // if it's not the searched node
        if (!root.data.getTitle().equals(movieTitle)) {
            root.right = remove(root.right, movieTitle);
            root.left = remove(root.left, movieTitle);
        }

        // if it's the searched node
        else {
            if (root.left == null) {
                return root.right;
            }

            if (root.right == null) {
                return root.left;
            }

            // two children node
            // find the smallest among the larger values
            root.data = findSuccessor(root.right);

            // delete
            root.right = remove(root.right, root.data.getTitle());

        }

        return root;

    }

    // TODO: Work on it
    private Movie findSuccessor(Node root) {
        // Summary: Finds the successor (the next node that can be replaced with the
        // removed node).
        while (root.left != null) {
            root = root.left;
        }
        return root.data;
    }

    // calls the initial role finder method
    public String findActorRoles(String castName) {
        return findActorRoles(this.root, castName);
    }

    private String findActorRoles(Node root, String castName) {
        // --------------------------------------------------------
        // Summary: Find and print the actor's movies in DESCENDING form in a recursive
        // manner.
        // Precondition: Actor should have movies.
        // Postcondition: Printed if there is any movie. Otherwise, returned empty
        // string;
        // --------------------------------------------------------

        // tree is empty or leaf nodes
        if (root == null) {
            return "";
        }

        // checks the cast of the current node's movie.
        Cast foundCast = root.data.findCast(castName);

        String output = "";

        if (foundCast != null) {
            output += String.format("%s, %s, %d\n", foundCast.getRoleTitle(), root.data.getTitle(),
                    root.data.getReleaseYear());
        }

        return findActorRoles(root.right, castName) + output + findActorRoles(root.left, castName);

    }

    // calls the initial director movie finder method
    public String findDirectorMovies(String directorName) {
        return findDirectorMovies(this.root, directorName);
    }

    private String findDirectorMovies(Node root, String directorName) {
        // --------------------------------------------------------
        // Summary: Finds the given director's movies and print them in DESCENDING
        // order.
        // Precondition: Director should have movies.
        // Postcondition: Printed if movies exists. Otherwise, returned empty string.
        // --------------------------------------------------------

        // tree is empty or leaf nodes
        if (root == null) {
            return "";
        }

        String output = "";

        // if it's the searched node
        if (root.data.getDirectorFullName().equals(directorName)) {

            Movie foundMovie = root.data;
            output += String.format("Movie %s, %d/%d/%d\n", foundMovie.getTitle(), foundMovie.getReleaseDay(),
                    foundMovie.getReleaseMonth(), foundMovie.getReleaseYear());

        }

        return findDirectorMovies(root.right, directorName) + output + findDirectorMovies(root.left, directorName);
    }

    // calls the initial film finder method
    public String findByYear(int releaseYear) {
        return this.findByYear(this.root, releaseYear);
    }

    private String findByYear(Node root, int releaseYear) {
        // --------------------------------------------------------
        // Summary: Finds the movies in the given year recursively.
        // Precondition: Films should exist in the given year.
        // Postcondition: Printed if they exist. Otherwise, return empty string.
        // --------------------------------------------------------

        // tree is empty or leaf nodes
        if (root == null) {
            return "";
        }

        String output = "";

        // if it is found
        // can be the same year in the right subtree too, keep searching from the right
        // subtree
        if (releaseYear == root.data.getReleaseYear()) {

            Movie foundMovie = root.data;
            output += String.format("Movie %s, %s, %d/%d\n", foundMovie.getTitle(), foundMovie.getDirectorFullName(),
                    foundMovie.getReleaseDay(), foundMovie.getReleaseMonth());

            return findByYear(root.right, releaseYear) + output;
        }

        // if it's bigger, right subtree
        if (releaseYear > root.data.getReleaseYear()) {
            return findByYear(root.right, releaseYear);
        }

        // if it's smaller, left subtree
        return findByYear(root.left, releaseYear);

    }

    // calls the initial movie finder by interval method
    public String findByInterval(int intervalStart, int intervalEnd) {
        return this.findByInterval(this.root, intervalStart, intervalEnd);
    }

    private String findByInterval(Node root, int intervalStart, int intervalEnd) {
        // --------------------------------------------------------
        // Summary: Finds movies that have shot within the given interval
        // Precondition: Movies should exist.
        // Postcondition: Printed if they exist. Otherwise, return empty string.
        // --------------------------------------------------------

        // empty tree or leaf nodes
        if (root == null) {
            return "";
        }

        String output = "";

        int releaseYear = root.data.getReleaseYear();

        // if it's one of the wanted ones
        if (releaseYear >= intervalStart && releaseYear <= intervalEnd) {

            Movie foundMovie = root.data;
            output += String.format("Movie %s, %s, %d\n", foundMovie.getTitle(), foundMovie.getDirectorFullName(),
                    foundMovie.getReleaseYear());

        }

        return findByInterval(root.right, intervalStart, intervalEnd) + output
                + findByInterval(root.left, intervalStart, intervalEnd);

    }

    // calls the initial print method
    public String printAscendingByRelease() {

        return printAscendingByRelease(this.root);
    }

    private String printAscendingByRelease(Node root) {
        // --------------------------------------------------------
        // Summary: Prints all movies in ASCENDING form with respect to their release
        // years.
        // Precondition: Tree shouldn't be empty.
        // Postcondition: Printed if tree isn't empty. Otherwise, returned empty string.
        // --------------------------------------------------------

        // tree is empty or leaf nodes
        if (root == null) {
            return "";
        }

        String data = String.format("%s, %d, %s %s", root.data.getTitle(), root.data.getReleaseYear(),
                root.data.getDirectorName(), root.data.getDirectorSurname());

        return printAscendingByRelease(root.left) + "\n" + data + printAscendingByRelease(root.right);

    }

}