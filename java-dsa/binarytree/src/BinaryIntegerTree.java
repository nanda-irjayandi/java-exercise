public class BinaryIntegerTree {

    public static void main(String[] args) {
        BinaryIntegerTree bt = new BinaryIntegerTree();
        bt.add(50);
        bt.traverseInOrder(bt.root);
        bt.add(7);
        bt.traverseInOrder(bt.root);
        bt.add(11);
        bt.traverseInOrder(bt.root);
        bt.add(3);
        bt.traverseInOrder(bt.root);
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
