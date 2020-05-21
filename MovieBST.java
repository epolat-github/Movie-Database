public class MovieBST {
    // order by release years of the movies
    // smaller => left subtree
    // greater or equal => rigth subtree

    private Node root;

    class Node {
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

    // add a new movie to the tree
    public void add(Movie newMovie) {

        this.root = this.add(this.root, newMovie);
    }

    private Node add(Node root, Movie data) {

        // leaf nodes or empty tree
        if (root == null) {
            root = new Node(data);
            return root;
        }

        // find the proper place recursively
        // traverse through the children according to a compare
        if (root.data.getReleaseYear() <= data.getReleaseYear()) {
            root.right = add(root.right, data);
        } else {
            root.left = add(root.left, data);
        }

        return root;
    }

    public Movie findByTitle(String movieTitle) {
        return this.findByTitle(root, movieTitle);
    }

    private Movie findByTitle(Node root, String movieTitle) {

        // if tree is empty
        if (root == null) {
            return null;
        }

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
        } else if (foundRight != null) {
            return foundRight;
        } else {
            return null;
        }
    }

    public void remove(String movieTitle) {
        this.root = remove(this.root, movieTitle);
    }

    private Node remove(Node root, String movieTitle) {

        // if the tree is empty
        if (root == null) {
            return null;
        }

        // if not the searched node
        if (!root.data.getTitle().equals(movieTitle)) {
            root.right = remove(root.right, movieTitle);
            root.left = remove(root.left, movieTitle);
        }

        // if equals
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

    private Movie findSuccessor(Node root) {
        int smallest = root.data.getReleaseYear();
        while (root.left != null) {
            smallest = root.left.data.getReleaseYear();
            root = root.left;
        }
        return root.data;
    }

    // descending
    public String findActorRoles(String castName) {
        return findActorRoles(this.root, castName);
    }

    private String findActorRoles(Node root, String castName) {

        if (root == null) {
            return "";
        }
        // System.out.println("girdi: " + root.data.getTitle() + " " +
        // root.data.getReleaseYear());
        Cast foundCast = root.data.findCast(castName);

        String output = "";

        if (foundCast != null) {

            output += String.format("%s, %s, %d\n", foundCast.getRoleTitle(), root.data.getTitle(),
                    root.data.getReleaseYear());
        }

        // System.out.println("geldi");
        return findActorRoles(root.right, castName) + output + findActorRoles(root.left, castName);

    }

    public String findDirectorMovies(String directorName) {
        return findDirectorMovies(this.root, directorName);
    }

    private String findDirectorMovies(Node root, String directorName) {
        if (root == null) {
            return "";
        }

        String output = "";

        if (root.data.getDirectorFullName().equals(directorName)) {
            Movie foundMovie = root.data;
            output += String.format("Movie %s, %d/%d/%d\n", foundMovie.getTitle(), foundMovie.getReleaseDay(),
                    foundMovie.getReleaseMonth(), foundMovie.getReleaseYear());
        }

        return findDirectorMovies(root.right, directorName) + output + findDirectorMovies(root.left, directorName);
    }

    public String findByYear(int releaseYear) {
        return this.findByYear(this.root, releaseYear);
    }

    private String findByYear(Node root, int releaseYear) {
        if (root == null) {
            return "";
        }

        String output = "";

        // if it is found
        // can be the same year in the right subtree too
        if (releaseYear == root.data.getReleaseYear()) {
            Movie foundMovie = root.data;
            output += String.format("Movie %s, %s, %d/%d\n", foundMovie.getTitle(), foundMovie.getDirectorFullName(),
                    foundMovie.getReleaseDay(), foundMovie.getReleaseMonth());
            return findByYear(root.right, releaseYear) + output;
        }

        if (releaseYear > root.data.getReleaseYear()) {
            return findByYear(root.right, releaseYear);
        }

        return findByYear(root.left, releaseYear);

    }

    // TODO: fill
    public String findByInterval(int intervalStart, int intervalEnd) {
        return this.findByInterval(this.root, intervalStart, intervalEnd);
    }

    private String findByInterval(Node root, int intervalStart, int intervalEnd) {
        if (root == null) {
            return "";
        }

        String output = "";

        int releaseYear = root.data.getReleaseYear();

        if (releaseYear >= intervalStart && releaseYear <= intervalEnd) {
            Movie foundMovie = root.data;
            output += String.format("Movie %s, %s, %d\n", foundMovie.getTitle(), foundMovie.getDirectorFullName(),
                    foundMovie.getReleaseYear());

        }

        return findByInterval(root.right, intervalStart, intervalEnd) + output
                + findByInterval(root.left, intervalStart, intervalEnd);

    }

    public String printAscendingByRelease() {

        return printAscendingByRelease(this.root);
    }

    private String printAscendingByRelease(Node root) {

        if (this.root == null) {
            return null;
        }

        if (root == null) {
            return "";
        }

        String data = String.format("%s, %d, %s %s", root.data.getTitle(), root.data.getReleaseYear(),
                root.data.getDirectorName(), root.data.getDirectorSurname());

        return printAscendingByRelease(root.left) + "\n" + data + printAscendingByRelease(root.right);

    }

}