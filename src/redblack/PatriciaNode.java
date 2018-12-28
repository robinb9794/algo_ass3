package redblack;

public class PatriciaNode extends Node{
    private PatriciaNode left;
    private PatriciaNode right;

    public PatriciaNode(String key){
        super(key);
    }

    public PatriciaNode(String key, int bitPosition){
        this(key, bitPosition, null);
    }

    public PatriciaNode(String key, int bitPosition, PatriciaNode succ){
        super(key, bitPosition);
        boolean isLeft = PatriciaTree.left(key, bitPosition);
        left = isLeft ? this : succ;
        right = isLeft ? succ : this;
    }

    public PatriciaNode getLeft(){
        return this.left;
    }

    public void setLeft(PatriciaNode left){
        this.left = left;
    }

    public PatriciaNode getRight(){
        return this.right;
    }

    public void setRight(PatriciaNode right){
        this.right = right;
    }

    public int getBitPosition(){
        return this.dataItem.getBitPosition();
    }

    public void setBitPosition(int bitPosition){
        this.dataItem.setBitPosition(bitPosition);
    }

    @Override
    public String getVisualizationKey(){
        return this.dataItem.getKey() + " | " + dataItem.getBitPosition();
    }
}