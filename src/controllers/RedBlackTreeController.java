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

import redblack.Node;
import redblack.RedBlackTree;
import views.RedBlackTreeInsertionWindow;
import workers.UDrawGraphClient;

public class RedBlackTreeController extends SuperTreeController{
    private RedBlackTree redBlackTree;    

    public RedBlackTreeController() {
        this.insertionWindow = new RedBlackTreeInsertionWindow();
        this.redBlackTree = new RedBlackTree();
        this.graphClient = new UDrawGraphClient();
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
        insertionWindow.packAndShow();
    }

    private ActionListener getInsertionButtonListener(){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String key = insertionWindow.insertionField.getText();
                if(keyIsValid(key)){                   
                    redBlackTree.insert(key);
                    updateGUI(key);
                    resetAndPrintTree();
                }else
                    showErrorDialog("The key must have no less than 4 letters and no numbers.");                
            }
        };
    }

    private boolean keyIsValid(String key){
        return key.length() >= 4 && !key.matches(".*\\d+.*");
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
                insertionWindow.infoPanel.remove(infoRow);
                insertionWindow.infoPanel.revalidate();
                insertionWindow.infoPanel.repaint();
            }
        };
    }

    private void updateGUI(String key){
        JPanel infoRow = getNewInfoRow(key);
        insertionWindow.insertionField.setText("");
        insertionWindow.infoPanel.add(infoRow);
        insertionWindow.infoPanel.revalidate();
    }

    private void resetAndPrintTree(){
        graphClient.reset();
        print(redBlackTree.getRoot(), redBlackTree.getRoot());
    }

    private void print(Node node, Node dad){
        graphClient.newNode(node);
        if (node != redBlackTree.getRoot()) {
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
		if (node.getLeft() != null) {
			print(node.getLeft(), node);
		}
		if (node.getRight() != null) {
			print(node.getRight(), node);
		}
		if (node.getLeft() == null && node.getRight() == null)
			graphClient.improve();
    }
}