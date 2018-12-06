package models;

import redblack.RedBlackTree;

public class ViewModel{
    private RedBlackTree redBlackTree;

    public ViewModel(){
        this.redBlackTree = new RedBlackTree<>();
    }

    public RedBlackTree getRedBlackTree(){
        return this.redBlackTree;
    }
}