package controllers;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interfaces.Tree;
import redblack.PatriciaNode;
import redblack.PatriciaTree;
import views.PatriciaTreeInsertionWindow;

public class PatriciaTreeController extends SuperTreeController {
    private Tree patriciaTree;

    public PatriciaTreeController() {
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
        insertionWindow.insertionButton.addActionListener(getInsertionListener());
        insertionWindow.packAndShow();
    }

    private ActionListener getInsertionListener(){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String key = insertionWindow.insertionField.getText();
                if(!key.isEmpty()){
                    patriciaTree.insert(key);    
                    printPatriciaTree();
                    updateGUI(key);
                }else
                     showErrorDialog("Key cannot be empty.");   
            }
        };
    }

    private void updateGUI(String key){
        JPanel infoRow = getNewInfoRow(key);
        insertionWindow.insertionField.setText("");
        insertionWindow.infoPanel.add(infoRow);
        insertionWindow.infoPanel.revalidate();
    }

    private JPanel getNewInfoRow(String key){
        JPanel infoRow = new JPanel();
        infoRow.setBorder(BorderFactory.createLoweredBevelBorder());
        infoRow.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JLabel keyLabel = new JLabel(key);
        infoRow.add(keyLabel);

        JButton deletionButton = new JButton("Delete");
        deletionButton.addActionListener(getDeletionButtonListener(infoRow, key));
        infoRow.add(deletionButton);

        return infoRow;
    }   

    private ActionListener getDeletionButtonListener(JPanel infoRow, String key){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                patriciaTree.delete(key);
                insertionWindow.infoPanel.remove(infoRow);
                insertionWindow.infoPanel.revalidate();
                insertionWindow.infoPanel.repaint();
                printPatriciaTree();
            }
        };
    }

    private void printPatriciaTree(){
        graphClient.reset();
        printPatriciaTree((PatriciaNode) patriciaTree.getRoot(),(PatriciaNode)  patriciaTree.getRoot());
    }

    private void printPatriciaTree(PatriciaNode node, PatriciaNode dad){
        graphClient.newNode(node);

        if (node.getLeft() != null && node.getLeft() == node)
            graphClient.newLeftEdge(node, node);

        if (node.getRight() != null && node.getRight() == node)
            graphClient.newRightEdge(node, node);

        if(dad.getLeft() != null && dad.getLeft() == node && dad != node)
            graphClient.newLeftEdge(dad, node);

        if(dad.getRight() != null && dad.getRight() == node && dad != node)
            graphClient.newRightEdge(dad, node);

        if (node.getLeft() != null && node.getLeft().getBitPosition() < node.getBitPosition())
            graphClient.newLeftEdge(node, node.getLeft());

        if (node.getRight() != null && node.getRight().getBitPosition() < node.getRight().getBitPosition())
            graphClient.newRightEdge(node, node.getRight());

        if (node.getLeft() != null && node.getLeft().getBitPosition() > node.getBitPosition())
            printPatriciaTree(node.getLeft(), node);
            
        if (node.getRight() != null && node.getRight().getBitPosition() > node.getBitPosition())
            printPatriciaTree(node.getRight(), node);

        graphClient.improve();
    }
}