import java.util.ArrayList;
public class CommonAncestor {
    static class Node{
        Node left;
        Node right;
        int data;
        Node(int data){this.data = data;}

        void addChild(Node n) {
            if(this.left == null)
            {
                this.left = n;
            }
            else if(this.right == null){
                this.right = n;
            }
            else{
                System.out.println("2 children already exist");
                return;
            }
        }
    }

    static Node LCA(Node root, Node n1, Node n2){
        if(root == null)return null;
        if(root == n1 || root == n2)return root;

        Node x = LCA(root.left, n1, n2);

        if(x!= null && x!= n1 && x != n2)return x;

        Node y = LCA(root.right,n1,n2);
        if(y != null && y != n1 && y != n2)return y;

        if(x != null && y != null)return root;
        else{
            return x == null ? y:x;
        }
    }

    public static void main(String[] args) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        for(int i = 0; i < 7; i++){
            nodes.add(new Node(i));
        }

        nodes.get(0).addChild(nodes.get(1));
        nodes.get(0).addChild(nodes.get(2));

        nodes.get(1).addChild(nodes.get(3));
        nodes.get(1).addChild(nodes.get(4));

        nodes.get(2).addChild(nodes.get(5));
        nodes.get(2).addChild(nodes.get(6));

        Node ancestor = LCA(nodes.get(0),nodes.get(3), nodes.get(4));
        System.out.println(ancestor.data);
    }
}
