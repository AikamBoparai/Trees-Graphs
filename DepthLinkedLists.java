import java.util.ArrayList;
public class DepthLinkedLists {
    //Creates depth linked list using depth first search
    public static ArrayList<ArrayList<BinaryNode>> createDepthLinkedList(BinaryNode root){
        ArrayList<ArrayList<BinaryNode>> lists = new ArrayList<ArrayList<BinaryNode>>();
        createDLLHelper(lists, root, 0);
        return lists;
    }

    public static void createDLLHelper(ArrayList<ArrayList<BinaryNode>> lists, BinaryNode node, int depth){
        if(node == null)return;
        ArrayList<BinaryNode> list;
        if(depth == lists.size()){
             list = new ArrayList<BinaryNode>();
             lists.add(list);
        }
        else{
            list = lists.get(depth);
        }
        list.add(node);
        createDLLHelper(lists, node.left, depth+1);
        createDLLHelper(lists, node.right, depth+1);
    }

    //Create depth linkedLists using Breadth first search
    public static ArrayList<ArrayList<BinaryNode>> createDLLBFS(BinaryNode root){
        if(root == null)return null;
        ArrayList<ArrayList<BinaryNode>> lists = new ArrayList<ArrayList<BinaryNode>>();
        ArrayList<BinaryNode> parents = new ArrayList<BinaryNode>();
        parents.add(root);

        while(!parents.isEmpty()){
            lists.add(parents);
            ArrayList<BinaryNode> children = new ArrayList<BinaryNode>();
            for (BinaryNode parent : parents) {
                if(parent.left != null)children.add(parent.left);
                if(parent.right != null)children.add(parent.right);
            }
            parents = children;
        }

        return lists;

    }

    public static void main(String[] args) {
        BinaryNode root = new BinaryNode(0);
        BinaryNode left = new BinaryNode(1);
        BinaryNode right = new BinaryNode(2);

        root.left = left;
        root.right = right;

        left.left = new BinaryNode(3);
        left.right = new BinaryNode(4);

        right.left = new BinaryNode(5);
        right.right = new BinaryNode(6);

        ArrayList<ArrayList<BinaryNode>> lists = createDLLBFS(root);
        for (ArrayList<BinaryNode> arrayList : lists) {
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
