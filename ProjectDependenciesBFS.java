import java.util.ArrayList;

public class ProjectDependenciesBFS {
    public enum State{BLANK,COMPLETE,PARTIAL};
    static class Node{
        private State status = State.BLANK;
        private String name;
        private ArrayList<Node> children = new ArrayList<Node>();

        Node(String name){this.name = name;}
        State getState(){return this.status;}
        String getName(){return this.name;}
        ArrayList<Node> getChildren(){return this.children;}
        void updateState(State s){status = s;}
        void clearChildren(){children.clear();}

        void addChildren(Node n){
            children.add(n);
        }

    }

    public static Node getProject(ArrayList<Node> projects,String name){
        for (Node project : projects) {
            if(project.getName() == name)return project;
        }
        return null;
    }

    public static String buildOrder(ArrayList<Node> projects, String[][] dependencies){
        StringBuilder result = new StringBuilder();
        for (String[] pair : dependencies) {
            getProject(projects, pair[0]).addChildren(getProject(projects, pair[1]));
        }
         for (Node project : projects) {
             if(project.getState() == State.BLANK){
                 if(!doDFS(result, project)){
                     return "No build order";
                 }
             }
         }

         return result.reverse().toString();
    }

    public static boolean doDFS(StringBuilder buildOrder, Node project){
        if(project.getState() == State.PARTIAL)return false;
        
        if(project.getState() == State.BLANK){
            project.updateState(State.PARTIAL);
            for (Node child : project.getChildren()) {
                if(!doDFS(buildOrder, child))return false;
            }

            project.updateState(State.COMPLETE);
            buildOrder.append(project.getName());
        }
        return true;
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

        System.out.println(buildOrder(nodes, dependencies));        

        System.out.println("Let's introduce a cycle now");

        String[][] newDependencies = {{"a","d"}, {"f","b"}, {"b","d"}, {"f", "a"}, {"d","c"}, {"e","f"}, {"b","e"}};
        for (Node project : nodes) {
            project.updateState(State.BLANK);
            project.clearChildren();
        }

        System.out.println(buildOrder(nodes, newDependencies));     
    }
}
