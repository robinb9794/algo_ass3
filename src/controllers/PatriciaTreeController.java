package controllers;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import interfaces.Tree;
import redblack.PatriciaTree;
import views.PatriciaTreeInsertionWindow;
import views.SuperInsertionWindow;

public class PatriciaTreeController extends SuperTreeController{
    private SuperInsertionWindow insertionWindow;
    private Tree patriciaTree;

    public PatriciaTreeController(){
        this.insertionWindow = new PatriciaTreeInsertionWindow();
        this.patriciaTree = new PatriciaTree();
    }

    @Override
    public void work(){
        insertionWindow.buildInteractionPanel();
        insertionWindow.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                insertionWindow.dispose();
                graphClient.close();
            }
        });
        insertionWindow.packAndShow();
    }
}