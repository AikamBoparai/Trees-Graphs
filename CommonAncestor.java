import java.util.ArrayList;
class CommonAncestor{
    //Nodes for a binary tree
    //Node with parent links
    static class Node{
        int name;
        Node parent = null;
        Node left = null;
        Node right = null;

        Node(int name) {
            this.name = name;
        }

        void addChild(Node n) {
            if(this.left == null)
            {
                this.left = n;
                n.parent = this;
            }
            else if(this.right == null){
                this.right = n;
                n.parent = this;
            }
            else{
                System.out.println("2 children already exist");
                return;
            }
        }

        int getName(){return name;}

        int getDepth(){
            Node n = this;
            int depth = 0;
            while(n != null){
                depth++;
                n = n.parent;
            }
            return depth;
        }
    }

    static Node commonAncestor(Node n1, Node n2){
        //every node in a binary tree must have an ancestor
        if(n1 == n2)return n1;

        int depth1 = n1.getDepth();
        int depth2 = n2.getDepth();
        int difference = Math.abs(depth1 - depth2);

        Node deeper = depth1 >= depth2 ? n1 : n2;

        for(int i = 0;i < difference; i++){
            deeper = deeper.parent;
        }

        n1 = depth1 >= depth2 ? deeper : n1;
        n2 = depth1 >= depth2 ? n2 : deeper;

        while(n1 != n2){
            n1 = n1.parent;
            n2 = n2.parent;
        }

        return n1;
        
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
        

        Node ancestor = commonAncestor(nodes.get(3), nodes.get(4));
        System.out.println(ancestor.name);
    }
}