import java.util.ArrayList;
public class MinimalTree {
    public static BinaryNode createBST(int[] nums, int size){
        return createBSTHelper(nums,0,size-1);
    }

    public static BinaryNode createBSTHelper(int[] nums, int left, int right){
        if(left > right)return null;
        int middle = (left+right)/2;
        BinaryNode node = new BinaryNode(nums[middle]);
        node.left = createBSTHelper(nums, left, middle-1);
        node.right = createBSTHelper(nums, middle+1, right);
        return node;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        BinaryNode root = createBST(nums, 15);

        for (ArrayList<BinaryNode> arrayList : DepthLinkedLists.createDLLBFS(root)) {
            StringBuilder level = new StringBuilder();
            level.append('[');
            for (BinaryNode node : arrayList) {
                level.append(node.data);
                level.append(',');
            }
            level.append(']');
            System.out.println(level);
        }
    }

}
