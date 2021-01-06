public class checkSubTree {

    static class Node{
        int data;
        Node left = null;
        Node right = null;

        Node(int d){data = d;}
    }

    //let's traverse both trees using pre-order traversal and compare strings of each tree
    static boolean checkSubTreeStrings(Node t1, Node t2){
        //assuming t1 is bigger than t2
        StringBuilder t1S = new StringBuilder();
        StringBuilder t2S = new StringBuilder();

        preOrder(t1, t1S);
        preOrder(t2, t2S);

        return t1S.indexOf(t2S.toString()) != -1;
    }

    static void preOrder(Node head, StringBuilder result){
        if(head == null){
            result.append("X");
            return;
        }
        result.append(head.data);
        preOrder(head.left, result);
        preOrder(head.right, result);
    }
    public static void main(String[] args) {
        //Lets first create T1
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);
        Node n9 = new Node(9);

        n1.left = n2;
        n1.right = n3;

        n2.left = n4;
        n2.right = n5;

        n3.left = n6;
        n3.right = n7;

        n4.left = n8;

        n7.right = n9;

        //T2

        Node n10 = new Node(2);
        n10.left = new Node(4);
        n10.right = new Node(5);
        n10.left.left = new Node(8);

        System.out.println(checkSubTreeStrings(n1, n10));
    }
}
