public class Successor {
    static class Node{
        Node parent = null;
        Node left = null;
        Node right = null;
        int data;

        Node(int data){
            this.data = data;
        }

        void addChild(Node n){
            if(left == null){
                left = n;
                left.parent = this;
            }
            else if(right == null){
                right = n;
                right.parent = this;
            }
            else{
                System.out.println("Node already has left and right children");
            }
        }
    }

    static Node getInOrderSuccessor(Node root){
        if(root == null){
            return null;
        }

        //check if node has a right subtree, if it does get leftmost child
        if(root.right != null){
            Node n = root.right;
            while(n.left != null){
                n = n.left;
            }

            return n;
        }

        //if it doesnt have a right subtree I want to get the node where the current node is a left child of it
        else{
            Node n = root;
            Node p = n.parent;

            while(p.left != n && p != null){
                n = n.parent;
                p = p.parent;
            }

            return p;
        }
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);

        n1.addChild(n2);
        n1.addChild(n3);

        n2.addChild(n4);
        n2.addChild(n5);

        n3.addChild(n6);
        n3.addChild(n7);

        System.out.println(getInOrderSuccessor(n1).data);
        System.out.println(getInOrderSuccessor(n5).data);
    }
}
