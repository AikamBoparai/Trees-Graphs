import java.util.HashMap;

public class PathsWithSum {
    static class Node{
        int d;
        Node left = null;
        Node right = null;

        Node(int data){d = data;}
    }

    static int totalPathsWithSum(Node root, int targetSum){
        if(root == null){
            return 0;
        }

        int pathsRoot = countPathsWithSum(root, 0, targetSum);

        int pathsRight = totalPathsWithSum(root.right, targetSum);
        int pathsLeft = totalPathsWithSum(root.left, targetSum);

        return pathsRoot + pathsRight + pathsLeft;
        
    }

    static int countPathsWithSum(Node root,int currentTotal ,int targetSum){
        if(root == null)return 0;

        currentTotal += root.d;

        int totalPaths = 0;
        if(currentTotal == targetSum){
            totalPaths++;
        }

        int pathsFromLeft = countPathsWithSum(root.left, currentTotal, targetSum);
        int pathsFromRight = countPathsWithSum(root.right, currentTotal, targetSum);

        return totalPaths + pathsFromLeft + pathsFromRight;

    }

    static int countPathWithRunningSum(Node root, int targetSum){
        return countPathWithRunningSum(root, targetSum, 0, new HashMap<Integer,Integer>());
    }

    static int countPathWithRunningSum(Node root, int targetSum, int runningSum, HashMap<Integer,Integer> pathLookUp){
        if(root == null)return 0;
        runningSum += root.d;
        
        int total = pathLookUp.getOrDefault(runningSum - targetSum, 0);
        if(runningSum == targetSum)total++;
        
        incrementHashMap(pathLookUp, runningSum, 1);
        total += countPathWithRunningSum(root.left, targetSum, runningSum, pathLookUp);
        total += countPathWithRunningSum(root.right, targetSum,runningSum,pathLookUp);
        incrementHashMap(pathLookUp,runningSum, -1);

        return total;
    }

    static void incrementHashMap(HashMap<Integer,Integer> table, int key, int value){
        int bucket = table.getOrDefault(key, 0) + value;
        if(bucket == 0){
            //just remove to save space
            table.remove(key);
        }
        else{
            table.put(key, bucket);
        }
    }
    public static void main(String[] args) {
        Node root = new Node(10);
        root.right = new Node(-3);
        root.right.right = new Node(11);

        Node n5 = new Node(5);
        root.left = n5;

        n5.right = new Node(2);
        n5.right.right = new Node(1);

        n5.left = new Node(3);
        
        n5.left.right = new Node(-2);
        n5.left.left = new Node(3);

        System.out.println(totalPathsWithSum(root, 8));
        System.out.println(countPathWithRunningSum(root, 8));

        
    }
}
