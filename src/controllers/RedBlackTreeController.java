package controllers;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interfaces.Tree;
import redblack.RedBlackNode;
import redblack.RedBlackTree;
import views.RedBlackTreeInsertionWindow;

public class RedBlackTreeController extends SuperTreeController {
    private Tree redBlackTree;

    public RedBlackTreeController() {
        this.insertionWindow = new RedBlackTreeInsertionWindow();
        this.redBlackTree = new RedBlackTree();
    }

    @Override
	public void work() {
        insertionWindow.buildInteractionPanel();
        insertionWindow.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                insertionWindow.dispose();
                graphClient.close();
            }
        });
        insertionWindow.insertionButton.addActionListener(getInsertionButtonListener());
        insertionWindow.topDownButton.addActionListener(getTopDownButtonListener());
        insertionWindow.packAndShow();
    }

    private ActionListener getInsertionButtonListener(){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String key = insertionWindow.insertionField.getText();
                if(!key.isEmpty()){
                    boolean alreadyInserted = redBlackTree.search(key);
                    if(!alreadyInserted){
                        redBlackTree.insert(key);    
                        printRedBlackTree();
                        updateGUI(key);
                    }else
                        showErrorDialog("Key already inserted.");
                }else
                     showErrorDialog("Key cannot be empty.");       
            }
        };
    }

    private void printRedBlackTree(){
        graphClient.reset();
        if(redBlackTree.getRoot() != null)
            printRedBlackTree((RedBlackNode) redBlackTree.getRoot(), (RedBlackNode) redBlackTree.getRoot());  
    }

    private void printRedBlackTree(RedBlackNode node, RedBlackNode dad){
        graphClient.newNode(node);
        if (node != dad) {
			if(dad.getLeft() != null && dad.getLeft() == node){
                if(node.isRed())
                    graphClient.newRedLeftEdge(dad, node);
                else
                    graphClient.newLeftEdge(dad, node);
            }else if(dad.getRight() != null && dad.getRight() == node){
                if(node.isRed())
                    graphClient.newRedRightEdge(dad, node);
                else
                    graphClient.newRightEdge(dad, node);
            }
        }
		if (node.getLeft() != null)
            printRedBlackTree(node.getLeft(), node);
		if (node.getRight() != null)
            printRedBlackTree(node.getRight(), node);
        if(node.getLeft() == null && node.getRight() == null)
            graphClient.improve();
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

    private ActionListener getTopDownButtonListener(){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(redBlackTree.getRoot() != null)
                    printTopDownTree();
                else
                    showErrorDialog("Nothing to convert.");
            }
        };
    }

    private void printTopDownTree(){
        graphClient.reset();
        printTopDownTree((RedBlackNode) redBlackTree.getRoot(), (RedBlackNode) redBlackTree.getRoot());
    }

    private void printTopDownTree(RedBlackNode node, RedBlackNode dad){
        String key = node.getKey();
        RedBlackNode topDownNode = new RedBlackNode(key);
        if((node != dad && !node.isRed()) || node == redBlackTree.getRoot()){
            if(node.getLeft() != null && node.getLeft().isRed())
                key = node.getLeft().getKey() + " " + key;
            if(node.getRight() != null && node.getRight().isRed())
                key = key + " " + node.getRight().getKey();
            topDownNode.setKey(key);
            graphClient.newNode(topDownNode);
            if(dad != node)
                graphClient.newAnythingEdge(dad, topDownNode);       
        }

        if(node.getLeft() != null && !node.isRed())
            printTopDownTree(node.getLeft(), topDownNode);
        else if(node.getLeft() != null && node.isRed())
            printTopDownTree(node.getLeft(), dad);

        if(node.getRight() != null && !node.isRed())
            printTopDownTree(node.getRight(), topDownNode);
        else if(node.getRight() != null && node.isRed())
            printTopDownTree(node.getRight(), dad);

        if(node.getLeft() == null && node.getRight() == null)
            graphClient.improve();
    }

    private ActionListener getSearchButtonListener(){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String key = insertionWindow.insertionField.getText();
                if(!key.isEmpty()){
                    boolean found = redBlackTree.search(key);    
                    if(found)
                        showInfoDialog("Key found.");
                    else
                        showErrorDialog("Key not found.");
                }else
                     showErrorDialog("Key cannot be empty.");       
            }
        };
    }

    private ActionListener getTestButtonListener(){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    File testFile = new File("./testdata.txt");
                    BufferedReader reader = new BufferedReader(new FileReader(testFile));
                    String key;
                    while((key = reader.readLine()) != null){
                        redBlackTree.insert(key);
                        updateGUI(key);
                    }
                    printRedBlackTree();
                    reader.close();
                }catch(Exception ex){
                    ex.printStackTrace();
                }                
            }
        };
    }     

    private ActionListener getDeletionButtonListener(JPanel infoRow, String key){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                redBlackTree.delete(key);
                insertionWindow.infoPanel.remove(infoRow);
                insertionWindow.infoPanel.revalidate();
                insertionWindow.infoPanel.repaint();
                printRedBlackTree();
            }
        };
    }
}