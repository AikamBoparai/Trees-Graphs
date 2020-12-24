public class checkBST {
    public static boolean validateBST(BinaryNode root) {
        if(root == null)return false;
        return vBSTHelper(root,null,null);
    }

    public static boolean vBSTHelper(BinaryNode root, Integer min, Integer max) {
        if(root == null)return true;

        if((min != null && root.data <= min ) || (max != null && root.data > max)){
            return false;
        }

        return vBSTHelper(root.left, min, root.data) && vBSTHelper(root.right, root.data, max);
    }

    public static void main(String[] args) {
        int[] values = {1,2,3,4,5,6,7};
        BinaryNode root = MinimalTree.createBST(values, 7);
        System.out.println(validateBST(root));

        //set 3 to be 5 to break the property
        values[2] = 5;

        root = MinimalTree.createBST(values, 7);
        System.out.println(validateBST(root));
    }
}
