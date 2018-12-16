package redblack;

public class RedBlackTree {
    private Node root;
	
	public Node getRoot() {
		return this.root;
    }
	
	public Node search(String key) {
        System.out.println(".............................\nSearching _" + key + "_");
        Node tmp = root;
        int i = 0;
		while(tmp != null){
            System.out.println("Checking tmp key _" + tmp.getKey() + "_");
            if(tmp.getKey().equals(key))
                return tmp;
            byte[] tmpBytes = tmp.getKey().getBytes();
            int j = tmpBytes.length - 1;
            j = (i >= 8 && tmpBytes.length > 1 )? (j-1) : j;
            System.out.println("Checking tmp bit at position " + i + ": " + (((tmpBytes[j] & (1 << i)) == 0) ? "0" : "1"));
            boolean left = (tmpBytes[j] & (1 << i)) == 0;
            System.out.println("Going " + (left ? "left" : "right"));
            tmp = left ? tmp.getLeft() : tmp.getRight();
        }
		return null;
	}	
	
	public void insert(String key) {
        System.out.println(".............................\nInserting _" + key + "_");
        NodeHandler nodeHandler = new NodeHandler(root);
        byte[] keyBytes = key.getBytes();
        boolean left = false;
        int i = 0;
        int j = keyBytes.length - 1;
		while(!nodeHandler.isNull()) {
			if(nodeHandler.getNode(NodeFamily.NODE).is4Node()) {
				nodeHandler.getNode(NodeFamily.NODE).convert4Node();
				nodeHandler.split(i);
            }
            j = (i >= 8 && keyBytes.length > 1) ? (j-1) : j;
            System.out.println("Checking key bit at position " + i + ": " + (((keyBytes[j] & (1 << i)) == 0) ? "0" : "1"));
            left = (keyBytes[j] & (1 << i)) == 0 ? true : false;
            System.out.println("Going down " + (left ? "left" : "right"));
            nodeHandler.down(left);
            i++;
        }
		nodeHandler.set(new Node(key), NodeFamily.NODE, left);
        nodeHandler.split(i);
        root.setIsRed(false);
    }	
    
    class NodeHandler {	
        private Node[] nodes = new Node[4];
        
        public NodeHandler(Node node) {
            nodes[NodeFamily.NODE] = node;
        }
        
        public void down(boolean left) {
            for(int i = nodes.length - 1; i > 0; i--)
                nodes[i] = nodes[i-1];
            nodes[NodeFamily.NODE] = left ? nodes[NodeFamily.DAD].getLeft() : nodes[NodeFamily.DAD].getRight();
        }
        
        public boolean isNull() {
            return nodes[NodeFamily.NODE] == null;
        }
        
        public Node getNode(int i) {
            return nodes[i];
        }
        
        public void set(Node node, int kind, boolean left) {
            if(nodes[kind+1] == null) {
                System.out.println("Root is now _" + node.getKey() + "_");
                root = node;
                root.setIsRed(false);
            }else if(nodes[kind] != null ? nodes[kind+1].getLeft() == nodes[kind] : left){
                System.out.println("Setting node _" + node.getKey() + "_ left from _" + nodes[kind+1].getKey() + "_");
                nodes[kind+1].setLeft(node);
            }else{
                System.out.println("Setting node _" + node.getKey() + "_ right from _" + nodes[kind+1].getKey() + "_");
                nodes[kind+1].setRight(node);
            }
            nodes[kind] = node;
        }
        
        public void rotate(int kind) {
            Node dad = nodes[kind];
            System.out.print("Rotating at dad _" + dad.getKey() + "_ ");
            Node son = nodes[kind-1];
            boolean sonIsRed = son.isRed();
            son.setIsRed(dad.isRed());
            dad.setIsRed(sonIsRed);			
            if(dad.getLeft() == son) {
                System.out.println("clockwise");
                dad.setLeft(son.getRight());
                son.setRight(dad);
            }else {
                System.out.println("counter clockwise");
                dad.setRight(son.getLeft());
                son.setLeft(dad);
            }
            set(son, kind, nodes[NodeFamily.GRAND_DAD].getLeft() == dad);
        }
        
        public void split(int i) {
            Node grandDad = nodes[NodeFamily.GRAND_DAD];
            Node dad = nodes[NodeFamily.DAD];
            Node son = nodes[NodeFamily.NODE];
            if(dad != null && dad.isRed()) {
                System.out.println("Splitting at dad _" + dad.getKey() + "_");
                byte[] dadBytes = dad.getKey().getBytes();
                byte[] sonBytes = son.getKey().getBytes();
                int j = (i >= 8 && dadBytes.length > 1) ? (i%8) : dadBytes.length - 1;
                int k = (i >= 8 && sonBytes.length > 1) ? (i%8) : sonBytes.length - 1;
                System.out.println("Dad _" + dad.getKey() + "_ is " + (((dadBytes[j] & (1 << (i-2))) == 0) ? "left" : "right") + " from GrandDad _" + grandDad.getKey() + "_");
                System.out.println("Son _" + son.getKey() + "_ is " + (((sonBytes[k] & (1 << (i-1))) == 0) ? "left" : "right") + " from Dad _" + dad.getKey() + "_");
                boolean isDifferentOrientation = ((dadBytes[j] & (1 << (i-2))) == 0) != ((sonBytes[k] & (1 << (i-1))) == 0);
                if(isDifferentOrientation)
                    rotate(NodeFamily.DAD);
                rotate(NodeFamily.GRAND_DAD);
            }
        }
    }
}