public class BinaryIntegerTree {

    public static void main(String[] args) {
        BinaryIntegerTree bt = new BinaryIntegerTree();
        int[] integer = {50, 49, 48, 47, 46};

        for (int integ : integer) {
            bt.add(integ);
            bt.traversePreOrder(bt.root);
            System.out.println(" ");
        }

        System.out.println(bt.getHeight(bt.root));
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

    public void traverseInOrder(BinaryIntegerNode node){
        if (node != null){
            traverseInOrder(node.left);
            System.out.print(" " + node.value);
            traverseInOrder(node.right);
        }
    }

    public void traversePreOrder(BinaryIntegerNode node){
        if (node != null){
            System.out.print(" " + node.value);
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }
}
