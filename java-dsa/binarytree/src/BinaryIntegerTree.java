import java.util.ArrayList;

public class BinaryIntegerTree {

    public static void main(String[] args) {
        BinaryIntegerTree bt = new BinaryIntegerTree();
        int[] integer = {50, 49, 48, 47, 46, 51, 55, 54, 53};

        for (int integ : integer) {
            ArrayList<BinaryIntegerNode> arrayList = new ArrayList<>();
            bt.add(integ);
//            bt.traversePreOrder(bt.root, arrayList);
            arrayList.addAll(bt.traverseTree(bt, Traversal.PREORDER));
            System.out.println(arrayList);
            System.out.println(" ");
            System.out.println("Height: " + bt.getHeight(bt.root));
            System.out.println("Balance: " + bt.getBalance(bt.root));
        }

        bt.root = bt.rotateLeft(bt.root);
        ArrayList<BinaryIntegerNode> arrayList = bt.traverseTree(bt, Traversal.PREORDER);
        System.out.println(arrayList);

        bt.root = bt.rotateRight(bt.root);
        arrayList = bt.traverseTree(bt, Traversal.PREORDER);
        System.out.println(arrayList);
    }
    BinaryIntegerNode root;

    public void add(int value){
        root = addRecursive(root, value);
    }

    private BinaryIntegerNode addRecursive(BinaryIntegerNode current, int value){
        if (current == null) return new BinaryIntegerNode(value); // base case for the recursion which adds the value to the null leaf node

        if (value < current.value){
            current.left = addRecursive(current.left, value);
        } else if (value > current.value){
            current.right = addRecursive(current.right, value);
        } else {
            return current; // as this form of tree does not allow duplicate nodes we simply return the tree as is
        }

        return current;
    }

    public int getHeight(BinaryIntegerNode node){
//        if (node == null) return 0; // this result in start index of 1 rather than 0
        if (node == null) return -1; // if the tree is empty we can consider the tree to have invalid height, and also a tree with a leaf will start at 0

        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    public int getBalance(BinaryIntegerNode node){
        if (node == null) return 0; // we assume the balance is neutral if the node is null

        return getHeight(node.left) - getHeight(node.right);
    }

    public BinaryIntegerNode rotateLeft(BinaryIntegerNode rootNode){
        if (rootNode == null || rootNode.right == null) return rootNode;

        BinaryIntegerNode pivotNode = rootNode.right; // in this way the memory address is saved before being removed by the JVM garbage collector
        rootNode.right = pivotNode.left; // it does not matter if the pivotNode left children is null or not
        pivotNode.left = rootNode; // as the left node of the pivot is saved, we can now assign the root node into the left child of the pivot

        return pivotNode;
    }

    public BinaryIntegerNode rotateRight(BinaryIntegerNode rootNode){
        if (rootNode == null || rootNode.left == null) return rootNode;

        BinaryIntegerNode pivotNode = rootNode.left;
        rootNode.left = pivotNode.right;
        pivotNode.right = rootNode;

        return pivotNode;
    }
    public ArrayList<BinaryIntegerNode> traverseTree(BinaryIntegerTree tree, Traversal traversal){
        ArrayList<BinaryIntegerNode> collector = new ArrayList<>();
        switch (traversal){
            case PREORDER -> traversePreOrder(tree.root, collector);
            case INORDER -> traverseInOrder(tree.root, collector);
            case POSTORDER -> traversePostOrder(tree.root, collector);
            default -> throw new IllegalStateException("Unexpected value: " + traversal);
        }
        return collector;
    }

    public void traverseInOrder(BinaryIntegerNode node, ArrayList<BinaryIntegerNode> arrayList){
        if (node != null){
            traverseInOrder(node.left, arrayList);
//            System.out.print(" " + node.value);
            arrayList.add(node);
            traverseInOrder(node.right, arrayList);
        }
    }

    public void traversePreOrder(BinaryIntegerNode node, ArrayList<BinaryIntegerNode> arrayList){
        if (node != null){
//            System.out.print(" " + node.value);
            arrayList.add(node);
            traversePreOrder(node.left, arrayList);
            traversePreOrder(node.right, arrayList);
        }
    }

    public void traversePostOrder(BinaryIntegerNode node, ArrayList<BinaryIntegerNode> arrayList){
        if (node != null){
//            System.out.print(" " + node.value);
            traversePreOrder(node.left, arrayList);
            traversePreOrder(node.right, arrayList);
            arrayList.add(node);
        }
    }
}
