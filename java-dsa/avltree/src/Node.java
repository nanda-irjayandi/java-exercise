public class Node {
    int value;
    Node left, right;

    Node(int value){
        this.value = value;
        left = right = null;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
