public class CheckBalanced {
    public static boolean checkBalance(BinaryNode root){
        int left = cbHelper(root.left,0);
        int right = cbHelper(root.right, 0);

        return Math.abs(left - right) <= 1;
    }
    public static int cbHelper(BinaryNode n, int depth){
        if(n == null)return depth;
        depth++;
        int left = cbHelper(n.left, depth);
        int right = cbHelper(n.right, depth);
        return left > right ? left : right;
    }

    public static void main(String[] args) {
        BinaryNode root = new BinaryNode(0);
        root.left = new BinaryNode(1);
        root.right = new BinaryNode(2);
        System.out.println(checkBalance(root));

        root.right.left = new BinaryNode(3);
        root.right.right = new BinaryNode(4);
        System.out.println(checkBalance(root));

        root.right.right.right = new BinaryNode(5);
        System.out.println(checkBalance(root));
        root.left.left = new BinaryNode(6);
        System.out.println(checkBalance(root));
    }
}
