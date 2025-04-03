import java.util.ArrayList;

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
    public static int getHeight (Node node){
        if (node == null) return -1; // covers the base case where the recursion reaches leaf node of if the root is not established

        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    public static int getBalance(Node node){
        if (node == null) return 0; // at leaf node it is assumed to be balanced node

        return getHeight(node.right) - getHeight(node.left);
    }

    public Node rotateLeft(){
        // there are two important nodes: pivotNode which will become the new parent, and the rootNode which will become one of the child of the pivotNode
        // for left rotation, the pivotNode is the right child of the rootNode, and the rootNode will become the left child of the pivotNode

        Node rootNode = this;

        Node pivotNode = rootNode.right;

        // we will be doing a strict pointer manipulation to do rotation

        // Step 1: Move the left subtree of pivotNode to rootNode’s right child.
        // Since pivotNode will become the new root, its left subtree needs to be assigned to the right of rootNode so we do not lose reference to it.
        rootNode.right = pivotNode.left;

        // Step 2: reassign the rootNode as the left child of the pivotNode
        pivotNode.left = rootNode;

        // Step 3: calculate the new height of the key nodes
        rootNode.height = 1 + Math.max(getHeight(rootNode.left), getHeight(rootNode.right));
        pivotNode.height = 1 + Math.max(getHeight(pivotNode.left), getHeight(pivotNode.right));

        // Step 4: return the pivotNode that now substitute the position of the rootNode
        return pivotNode;
    }

    public Node rotateRight(){
        // pointer manipulation
        Node pivotNode = this.left;
        this.left = pivotNode.right;
        pivotNode.right = this;

        // recalculate the height
        this.height = 1 + Math.max(getHeight(this.left), getHeight(this.right));
        pivotNode.height = 1 + Math.max(getHeight(pivotNode.left), getHeight(pivotNode.right));

        // return the pivotNode with rootNode as the right child
        return pivotNode;
    }

    public static Node addNode(Node node, int value){
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

    public static ArrayList<Node> traversePre(Node node, ArrayList<Node> arrayList){
        // preorder will add node for each movement from the root to a left leaf
        if (node == null) return arrayList;

        // I know this is redundant I just need a more explicit expression
        if (node != null) {
            arrayList.add(node); // for each node visited add the node to the arrayList
            traversePre(node.left, arrayList);
            traversePre(node.right, arrayList);
        }

        return arrayList; // return modified arrayList
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
