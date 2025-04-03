public class Node {
    int value;
    Node left, right;
    int height;

    Node(int value){
        this.value = value;
        left = right = null;
    }

    /**
     *
     * @param node
     * @return
     * @see     <a href="https://how.dev/answers/how-to-find-the-height-of-a-binary-tree">...</a>
     */
    public int getHeight (Node node){
        if (node == null) return -1; // covers the base case where the recursion reaches leaf node of if the root is not established

        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    public int getBalance(Node node){
        if (node == null) return 0; // at leaf node it is assumed to be balanced node

        return getHeight(node.left) - getHeight(node.right);
    }

    public Node addNode(Node node, int value){
        if (node == null) return new Node(value);

        if (value < node.value) node.left =     addNode(node.left, value);
        if (value > node.value) node.right =    addNode(node.right, value);

        // this is unnecessary as above statement should have covered this case
        // but I do want it explicitly for my own education purpose
        else if (value == node.value) {
            return node;
        }
        return node;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
