package redblack;

public class Node<K extends Comparable<K>, V> {
	private K key;
	private V value;
	Node left = null;
	Node right = null;
	boolean isRed;
	
	public Node(K key, V value) {
		this.value = value;
		this.key = key;
	}
	
	public K getKey() {
		return this.key;
	}
	
	public void setKey(K key) {
		this.key = key;
	}
	
	public V getValue() {
		return this.value;
	}
	
	public void setValue(V value) {
		this.value = value;
	}
	
	public Node getLeft() {
		return this.left;
	}
	
	public void setLeft(Node left) {
		this.left = left;
	}
	
	public Node getRight() {
		return this.right;
	}
	
	public void setRight(Node right) {
		this.right = right;
	}
	
	public boolean isRed() {
		return this.isRed;
	}
	
	public void setIsRed(boolean isRed) {
		this.isRed = isRed;
	}
	
	public boolean is4Node() {
		return left != null && left.isRed() && right != null && right.isRed();
	}
	
	public void convert4Node() {
		System.out.println("Converting 4-Node: " + key.toString());
		this.isRed = true;
		this.left.setIsRed(false);
		this.right.setIsRed(false);
	}
}
