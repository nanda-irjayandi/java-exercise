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
        // base case when recursion reaches leaf node
        if (current == null) {
            return new BinaryIntegerNode(value);
        }

        if (value < current.value){
            current.left = addRecursive(current.left, value);
        } else if (value > current.value){
            current.right = addRecursive(current.right, value);
        } else {
            return current;
        }

        return current;
    }

    public void traverseInOrder(BinaryIntegerNode node){
        if (node != null){
            System.out.println(" " + node.value);
            traverseInOrder(node.left);
            traverseInOrder(node.right);
        }
    }
}
