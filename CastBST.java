public class CastBST {
    // order by actor names

    private Node root;

    class Node {
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

    public void add(Cast newCast) {

        this.root = this.add(this.root, newCast);
    }

    private Node add(Node root, Cast data) {

        // leaf nodes or empty tree
        if (root == null) {
            root = new Node(data);
            return root;
        }
        // find the proper place recursively
        // traverse through the children according to a compare
        if (data.getFullname().compareTo(root.data.getFullname()) > 0) {
            root.right = add(root.right, data);
        } else {
            root.left = add(root.left, data);
        }

        return root;
    }

    public Cast findByName(String castName) {
        return this.findByName(root, castName);
    }

    private Cast findByName(Node root, String castName) {

        // if tree is empty
        if (root == null) {
            return null;
        }

        Cast foundLeft = null;
        Cast foundRight = null;

        if (root.data.getFullname().equals(castName)) {
            return root.data;
        }

        if (root.right != null) {
            foundRight = findByName(root.right, castName);
        }

        if (root.left != null) {
            foundLeft = findByName(root.left, castName);
        }

        if (foundLeft != null) {
            return foundLeft;
        } else if (foundRight != null) {
            return foundRight;
        } else {
            return null;
        }
    }

    public void remove(String castName) {
        this.root = remove(this.root, castName);
    }

    private Node remove(Node root, String castName) {
        // if the tree is empty
        if (root == null) {
            return null;
        }

        // if not the searched node
        if (!root.data.getFullname().equals(castName)) {
            root.right = remove(root.right, castName);
            root.left = remove(root.left, castName);
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
            root.right = remove(root.right, root.data.getFullname());

        }

        return root;
    }

    private Cast findSuccessor(Node root) {
        String smallest = root.data.getFullname();
        while (root.left != null) {
            smallest = root.left.data.getFullname();
            root = root.left;
        }
        return root.data;
    }

    public String printCast() {
        return printCast(this.root);
    }

    private String printCast(Node root) {
        if (this.root == null) {
            return null;
        }

        if (root == null) {
            return "";
        }

        String data = String.format("%s, %s", root.data.getFullname(), root.data.getRoleTitle());

        return printCast(root.left) + "\n" + data + printCast(root.right);

    }
}