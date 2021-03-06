import java.util.HashSet;
import java.util.ArrayList;

public class ProjectDependencies {
    static class Graph{
        ArrayList<Node> projects = new ArrayList<Node>();
        
        Graph(ArrayList<Node> nodes){
            projects = nodes;
        }

        Node getNode(String name){
            for (Node node : projects) {
                if(node.getName() == name){
                    return node;
                }
            }
            return null;
        }

        void CompleteProject(Node project){
            project.complete();
            for (Node node : projects) {
                if(!node.isCompleted() && node.hasDependent(project.getName())){
                    node.decrementDependencies();
                }
            }
        }
        
    }
    
    static class Node{
        private String name;
        private HashSet<String> dependentProjects = new HashSet<String>();
        private int dependencies = 0;
        private boolean completed = false;
        Node(String n){
            name = n;
        }
        String getName(){return name;}
        int getDependencies(){return dependencies;}
        boolean isCompleted(){return completed;}
        void decrementDependencies(){dependencies--;}
        void complete(){completed = true;}
        void addDependentProject(Node n){
            dependentProjects.add(n.getName());
            dependencies++;
        }
        boolean hasDependent(String name){
            return dependentProjects.contains(name);
        }

    }

    public static String getBuildOrder(ArrayList<Node> nodes, String[][] dependencies){
        Graph graph = new Graph(nodes);
        StringBuilder result = new StringBuilder();

        for (String[] pair : dependencies) {
            graph.getNode(pair[1]).addDependentProject(graph.getNode(pair[0]));
        }
        
        while(result.length() < nodes.size()){
            boolean cycle = true;
            for (Node p : graph.projects) {
                if(p.dependencies == 0 && !p.isCompleted()){
                    cycle = false;
                    result.append(p.getName());
                    graph.CompleteProject(p);
                }
            }

            if(cycle)return "No valid path found";
        }

        return result.toString();
    }

    public static void main(String[] args) {

        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");
        Node f = new Node("f");

        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(a);
        nodes.add(b);
        nodes.add(c);
        nodes.add(d);
        nodes.add(e);
        nodes.add(f);

        String[][] dependencies = {{"a","d"}, {"f","b"}, {"b","d"}, {"f", "a"}, {"d","c"}};

        System.out.println(getBuildOrder(nodes, dependencies));        

        System.out.println("Let's introduce a cycle now");

        String[][] newDependencies = {{"a","d"}, {"f","b"}, {"b","d"}, {"f", "a"}, {"d","c"}, {"e","f"}, {"b","e"}};

        System.out.println(getBuildOrder(nodes, newDependencies));     
    }
}
