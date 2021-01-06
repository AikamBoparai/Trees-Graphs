import java.util.LinkedList;
import java.util.ArrayList;
class BSTSequence{

    static class Node{
        int data;
        Node left = null;
        Node right = null;

        Node(int d){data = d;}

    }

    static void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second, LinkedList<Integer> prefix, ArrayList<LinkedList<Integer>> results){
        
        if(first.isEmpty() || second.isEmpty()){
            LinkedList<Integer> result = (LinkedList<Integer>)prefix.clone();
            result.addAll(first);
            result.addAll(second);
            results.add(result);
            return;
        }

        int headF = first.removeFirst();
        prefix.addLast(headF);
        weaveLists(first, second, prefix, results);
        prefix.removeLast();
        first.addFirst(headF);

        int headS = second.removeFirst();
        prefix.addLast(headS);
        weaveLists(first, second, prefix, results);
        prefix.removeLast();
        second.addFirst(headS);
    }

    static ArrayList<LinkedList<Integer>> allSequences(Node head){
        ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();

        if(head == null){
            result.add(new LinkedList<Integer>());
            return result;
        }

        LinkedList<Integer> prefix = new LinkedList<Integer>();
        prefix.add(head.data);

        ArrayList<LinkedList<Integer>> left = allSequences(head.left);
        ArrayList<LinkedList<Integer>> right = allSequences(head.right);

        for (LinkedList<Integer> seqL : left) {
            for (LinkedList<Integer> seqR : right) {
                ArrayList<LinkedList<Integer>> weaved = new ArrayList<LinkedList<Integer>>();
                weaveLists(seqL, seqR, prefix, weaved);
                result.addAll(weaved);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Node n1 = new Node(7);
        Node n2 = new Node(4);
        Node n3 = new Node(11);
        Node n4 = new Node(2);
        Node n5 = new Node(5);
        Node n6 = new Node(9);
        Node n7 = new Node(10);

        n1.left = n2;
        n1.right = n3;

        n2.right = n5;

        n3.left = n6;

        System.out.println(allSequences(n1).toString());

        System.out.println(allSequences(n1).toString());
    }
}