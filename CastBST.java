//-----------------------------------------------------
// Title:       Movie Database with BST
// Author:      Erinç Polat
//              Çağla Su Keşan
// ID:          13715949830
//              ~~other id~~
// Section:     01
// Assignment:  4
// Description: This class defines the BST in order to hold Cast objects of the films.
//-----------------------------------------------------

public class CastBST {

    private Node root;

    class Node {
        // Description: This class defines a single node of the BST tree.
        // A node holds a Cast object as its data.
        Cast data;
        Node left = null;
        Node right = null;

        public Node(Cast data) {
            this.data = data;
        }
    }

    public CastBST() {
        this.root = null;
    }

    // calls the recursive add method
    public void add(Cast newCast) {

        this.root = this.add(this.root, newCast);
    }

    private Node add(Node root, Cast data) {
        // --------------------------------------------------------
        // Summary: Adds a new node with the given data according to the new cast
        // member's name.
        // Postcondition: Given node is added.
        // --------------------------------------------------------

        // leaf nodes or empty tree
        if (root == null) {
            root = new Node(data);
            return root;
        }

        // if the new actor's name is bigger than the current node's lexicographically,
        // add it to the right subtree.
        if (data.getFullname().compareTo(root.data.getFullname()) > 0) {
            root.right = add(root.right, data);
        }
        // otherwise, add it to the left subtree.
        else {
            root.left = add(root.left, data);
        }

        // initial root node can't change.
        return root;
    }

    // calls the initial find method
    public Cast findByName(String castName) {
        return this.findByName(root, castName);
    }

    private Cast findByName(Node root, String castName) {
        // --------------------------------------------------------
        // Summary: Finds the given cast's node recursively and return its data.
        // Precondition: Given cast member should exists.
        // Postcondition: Wanted node is returned if it exists. Otherwise, returned
        // null.
        // --------------------------------------------------------

        // if tree is empty or leafs
        if (root == null) {
            return null;
        }

        // Cast BST is built with respect to cast names.
        // Method uses this feature to search efficiently

        // right subtree
        if (castName.compareTo(root.data.getFullname()) > 0) {
            return findByName(root.right, castName);
        }

        // left subtree
        if (castName.compareTo(root.data.getFullname()) < 0) {
            return findByName(root.left, castName);
        }
        // equality
        else {
            return root.data;
        }
    }

    // calls the initial remove method
    public void remove(String castName) {
        this.root = remove(this.root, castName);
    }

    private Node remove(Node root, String castName) {
        // --------------------------------------------------------
        // Summary: Removes the given cast.
        // Precondition: Cast member should exist.
        // Postcondition: Given cast member is removed if it exists.
        // --------------------------------------------------------

        // if the tree is empty or leaf nodes
        if (root == null) {
            return null;
        }

        // Cast BST is built with respect to cast names.
        // Method uses this feature to search efficiently

        // right subtree
        if (castName.compareTo(root.data.getFullname()) > 0) {
            root.right = remove(root.right, castName);
        }

        // left subtree
        else if (castName.compareTo(root.data.getFullname()) < 0) {
            root.left = remove(root.left, castName);
        }

        // equality
        else {
            if (root.left == null) {
                return root.right;
            }

            if (root.right == null) {
                return root.left;
            }

            // two children node
            // find the smallest among the larger values
            root.data = findSuitableDescendant(root.right);

            // delete
            root.right = remove(root.right, root.data.getFullname());

        }

        return root;
    }

    private Cast findSuitableDescendant(Node root) {
        // Summary: Finds the next node that can be replaced with the
        // removed node.
        while (root.left != null) {
            root = root.left;
        }
        return root.data;
    }

    // calls the initial print method
    public String printCast() {
        return printCast(this.root);
    }

    private String printCast(Node root) {
        // --------------------------------------------------------
        // Summary: Prints the cast members in an ASCENDING form corresponding to their
        // names.
        // --------------------------------------------------------

        // empty tree or leaf nodes
        if (root == null) {
            return "";
        }

        String data = String.format("%s, %s", root.data.getFullname(), root.data.getRoleTitle());

        return printCast(root.left) + "\n" + data + printCast(root.right);

    }
}