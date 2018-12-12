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
		while(!nodeHandler.isNull()) {
			if(nodeHandler.getNode(NodeFamily.NODE).is4Node()) {
				nodeHandler.getNode(NodeFamily.NODE).convert4Node();
				nodeHandler.split(i);
            }
            /*Node tmpNode = nodeHandler.getNode(NodeFamily.NODE);
            BigInteger tmpBinaryCode = new BigInteger(tmpNode.getKey().getBytes());*/
            left = (keyBytes[keyBytes.length-1-i] & (1 << i)) == 0 ? true : false;
            nodeHandler.down(left);
            i++;
        }
		nodeHandler.set(new Node(key), left);
        nodeHandler.split(i);
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
        
        public void set(Node node, boolean left) {
            if(nodes[NodeFamily.DAD] == null) {
                root = node;
                root.setIsRed(false);
                System.out.println("Root is now: " + node.getKey());
            }else if(left)
                nodes[NodeFamily.DAD].setLeft(node);
            else
                nodes[NodeFamily.DAD].setRight(node);
            nodes[NodeFamily.NODE] = node;
        }
        
        public void rotate(int i) {
            System.out.print("Rotating");
            Node dad = nodes[i];
            Node son = nodes[i-1];
            boolean sonIsRed = son.isRed();
            son.setIsRed(dad.isRed());
            dad.setIsRed(sonIsRed);			
            if(dad.getLeft() == son) {
                System.out.println(" clockwise");
                dad.setLeft(son.getRight());
                son.setRight(dad);
            }else {
                System.out.println(" counter clockwise");
                dad.setRight(son.getLeft());
                son.setLeft(dad);
            }
            set(son, dad.getLeft() == son);
        }
        
        public void split(int i) {
            Node dad = nodes[NodeFamily.DAD];
            if(dad != null && dad.isRed()) {
                System.out.println("Splitting");
                byte[] grandDadBytes = nodes[NodeFamily.GRAND_DAD].getKey().getBytes();
                byte[] dadBytes = nodes[NodeFamily.DAD].getKey().getBytes();
                boolean shouldRotateAtDad = ((grandDadBytes[i%8] & (1 << (i-2))) == 1) != ((dadBytes[i%8] & (1 << (i-1))) == 1);
                if(shouldRotateAtDad)
                    rotate(NodeFamily.DAD);
                rotate(NodeFamily.GRAND_DAD);
            }
        }
    }
}