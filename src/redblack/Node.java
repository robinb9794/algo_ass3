package redblack;

public class Node {
	private String key;
	Node left = null;
	Node right = null;
	boolean isRed;
	
	public Node(String key) {
		this.key = key;
	}
	
	public String getKey() {
		return this.key;
	}
	
	public void setKey(String key) {
		this.key = key;
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
		this.isRed = true;
		this.left.setIsRed(false);
		this.right.setIsRed(false);
	}
}
