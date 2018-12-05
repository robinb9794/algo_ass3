package algo_ass3.controllers;

import algo_ass3.redblack.*;
import algo_ass3.views.*;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RedBlackTreeController {
	private InsertionWindow insertionWindow;
	
    private RedBlackTree redBlackTree;
    
    private int infoRows;
	
	public RedBlackTreeController() {
		this.insertionWindow = new RedBlackTreeInsertionWindow();
        this.redBlackTree = new RedBlackTree();
        this.infoRows = 0;
	}
	
	public void work() {
        buildInsertionPanel();
        packAndShow();
    }

    private void buildInsertionPanel(){
        insertionWindow.insertionPanel = new JPanel();
        insertionWindow.insertionPanel.setLayout(new FlowLayout());

        insertionWindow.insertionField = new JTextField();
        insertionWindow.insertionField.setPreferredSize(new Dimension(100, 30));
        insertionWindow.insertionPanel.add(insertionWindow.insertionField);

        insertionWindow.insertionButton = new JButton("Insert");
        insertionWindow.insertionButton.setPreferredSize(new Dimension(100, 30));
        insertionWindow.insertionButton.addActionListener(getInsertionButtonListener());
        insertionWindow.insertionPanel.add(insertionWindow.insertionButton);

        insertionWindow.infoPanel = new JPanel();
        insertionWindow.infoGrid = new GridLayout(1, 1);
        insertionWindow.infoPanel.setLayout(insertionWindow.infoGrid);

        insertionWindow.add(BorderLayout.NORTH, insertionWindow.insertionPanel);
        insertionWindow.add(BorderLayout.CENTER, insertionWindow.infoPanel);
    }

    private ActionListener getInsertionButtonListener(){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String key = insertionWindow.insertionField.getText();
                insertionWindow.infoGrid.setRows(++infoRows);
                insertionWindow.infoPanel.add(getNewInfo(key));
                insertionWindow.infoPanel.validate();
                insertionWindow.validate();
            }
        };
    }

    private JPanel getNewInfo(String key){
        JPanel info = new JPanel();
        info.setLayout(new FlowLayout());
        info.add(new JLabel(key));
        info.add(new JButton("Delete"));
        return info;
    }

    private void packAndShow(){
        insertionWindow.pack();
        insertionWindow.setLocationRelativeTo(null);
        insertionWindow.setVisible(true);
    }
}