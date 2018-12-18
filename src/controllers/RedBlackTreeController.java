package controllers;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

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
        insertionWindow.testButton.addActionListener(getTestButtonListener());
        insertionWindow.packAndShow();
    }

    private ActionListener getInsertionButtonListener(){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String key = insertionWindow.insertionField.getText();
                if(!key.isEmpty()){
                    redBlackTree.insert(key);              
                    resetAndPrintTree();
                    improveTree();
                    updateGUI(key);
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
                    reader.close();
                    resetAndPrintTree();
                    improveTree();
                }catch(Exception ex){
                    ex.printStackTrace();
                }                
            }
        };
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
                redBlackTree.delete(key);
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
    }

    private void improveTree(){
        graphClient.improve();
    }
}