public class BinaryIntegerNode {
    public static void main(String[] args) {
        BinaryIntegerNode test1 = new BinaryIntegerNode(4);
        System.out.println(test1);

    }
    int value;
    BinaryIntegerNode left;
    BinaryIntegerNode right;

    /**
     * Constructor to create the first node without child
     * @param value
     */
    BinaryIntegerNode(int value){
        this.value = value;
        left = null;
        right = null;
    }

}
