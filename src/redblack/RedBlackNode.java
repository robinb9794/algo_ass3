package redblack;

public class RedBlackNode extends Node{
	private RedBlackNode left;
    private RedBlackNode right;
    private boolean isRed;    

    public RedBlackNode(String key) {
        super(key);
        this.isRed = true;
	}

	public RedBlackNode(String key, String data){
		super(key, data);
		this.isRed = true;
    }
    
    public RedBlackNode getLeft() {
		return this.left;
	}
    
	public void setLeft(RedBlackNode left) {
		this.left = left;
	}
    
	public RedBlackNode getRight() {
		return this.right;
	}
    
	public void setRight(RedBlackNode right) {
		this.right = right;
	}

    public boolean isRed() {
		return this.isRed;
	}
	
	public void setIsRed(boolean isRed) {
		this.isRed = isRed;
    }
    
    public boolean is2Node(){
        return !isRed && (left == null || !left.isRed()) && (right == null || !right.isRed());
    }
	
	public boolean is4Node() {
		return left != null && left.isRed() && right != null && right.isRed();
	}
	
	public void convert4Node() {
        System.out.println("Converting 4-Node _" + dataItem.getKey() + "_");
		this.isRed = true;
		this.left.setIsRed(false);
		this.right.setIsRed(false);
    }
    
    @Override
    public String getVisualizationKey(){
        return this.dataItem.getKey();
    }
}