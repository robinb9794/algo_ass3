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
		while(tmp != null) {
			final int RES = tmp.getKey().compareTo(key);
			if(RES == 0)
				return tmp;
			tmp = RES < 0 ? tmp.getLeft() : tmp.getRight();
		}
		return null;
	}	
	
	public void insert(String key) {
        NodeHandler nodeHandler = new NodeHandler(root);
        String binaryKeyToInsert = new BigInteger(key.getBytes()).toString(2);
        String tmpKey = "";
		while(!nodeHandler.isNull()) {
			if(nodeHandler.getNode(NodeFamily.NODE).is4Node()) {
				nodeHandler.getNode(NodeFamily.NODE).convert4Node();
				nodeHandler.split();
            }
            tmpKey = new BigInteger(nodeHandler.getNode(NodeFamily.NODE).getKey()).toString(2);
			final int RES = key.compareTo(nodeHandler.getNode(NodeFamily.NODE).getKey());
			if(RES == 0)
				return;
			nodeHandler.down(RES < 0);
		}
		nodeHandler.set(new Node(key), NodeFamily.NODE);
		nodeHandler.split();
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
		
		public void set(Node node, int i) {
			if(nodes[i+1] == null) {
				System.out.println("Root is now " + node.getKey());
				root = node;
				root.setIsRed(false);
			}else if(nodes[i] != null ? nodes[i+1].getLeft() == nodes[i] : node.getKey().compareTo(nodes[i+1].getKey()) < 0)
				nodes[i+1].setLeft(node);
			else
				nodes[i+1].setRight(node);
			nodes[i] = node;
		}
		
		public void rotate(int i) {
			Node dad = nodes[i];
			Node son = nodes[i-1];
			boolean sonIsRed = son.isRed();
			son.setIsRed(dad.isRed());
			dad.setIsRed(sonIsRed);			
			if(dad.getLeft() == son) {
				dad.setLeft(son.getRight());
				son.setRight(dad);
			}else {
				dad.setRight(son.getLeft());
				son.setLeft(dad);
			}
			set(son, i);
		}
		
		public void split() {
			Node dad = nodes[NodeFamily.DAD];
			if(dad != null && dad.isRed()) {
				System.out.println("Splitting");
				if(nodes[NodeFamily.GRAND_DAD].getKey().compareTo(dad.getKey()) < 0 != dad.getKey().compareTo(nodes[NodeFamily.NODE].getKey()) < 0)
					rotate(NodeFamily.DAD);
				rotate(NodeFamily.GRAND_DAD);
			}
		}
	}
}