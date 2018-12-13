package redblack;

import java.math.BigInteger;

public class RedBlackTree {
    private Node root;
	
	public Node getRoot() {
		return this.root;
    }
	
	public Node search(String key) {
        Node tmp = root;
        String binaryKeyToInsert = new BigInteger(key.getBytes()).toString(2);
		for(int i = 0; tmp != null; i++){
            if(tmp.getKey().equals(key))
                return tmp;
            int bit = (Integer.valueOf(binaryKeyToInsert, 2) & (1 << i));
            tmp = bit != 0 ? tmp.getRight() : tmp.getLeft();
        }
		return null;
	}	
	
	public void insert(String key) {
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
            j = (i >= 8 && keyBytes.length > 1 )? (j-1) : j;
            left = (keyBytes[j] & (1 << i)) == 0 ? true : false;
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
                root = node;
                root.setIsRed(false);
            }else if(left || nodes[kind] != null)
                nodes[kind+1].setLeft(node);
            else
                nodes[kind+1].setRight(node);
            nodes[kind] = node;
        }
        
        public void rotate(int kind) {
            Node dad = nodes[kind];
            Node son = nodes[kind-1];
            son.setIsRed(dad.isRed());
            dad.setIsRed(son.isRed());			
            if(dad.getLeft() == son) {
                dad.setLeft(son.getRight());
                son.setRight(dad);
            }else {
                dad.setRight(son.getLeft());
                son.setLeft(dad);
            }
            set(son, kind, dad.getLeft() == son);
        }
        
        public void split(int i) {
            Node dad = nodes[NodeFamily.DAD];
            if(dad != null && dad.isRed()) {
                byte[] dadBytes = nodes[NodeFamily.DAD].getKey().getBytes();
                byte[] sonBytes = nodes[NodeFamily.NODE].getKey().getBytes();
                int j = (dadBytes.length > 1) ? (i%8) : 0;
                int k = (sonBytes.length > 1) ? (i%8) : 0;
                boolean isDifferentOrientation = ((dadBytes[j] & (1 << (i-2))) == 1) != ((sonBytes[k] & (1 << (i-1))) == 1);
                if(isDifferentOrientation)
                    rotate(NodeFamily.DAD);
                rotate(NodeFamily.GRAND_DAD);
            }
        }
    }
}