package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RoBDDInsertionWindow extends SuperInsertionWindow {

    public RoBDDInsertionWindow(){
        super("Assignment 3 - Robin Beyer - RoBDD");
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 80));
        setResizable(false);
    }

    @Override
    public void buildInteractionPanel(){
        insertionPanel = new JPanel();
        insertionPanel.setLayout(new FlowLayout());

        insertionField = new JTextField();
        insertionField.setPreferredSize(new Dimension(200, 30));
        insertionPanel.add(insertionField);

        insertionButton = new JButton("-> RoBDD");
        insertionButton.setPreferredSize(new Dimension(100, 30));
        insertionPanel.add(insertionButton);

        add(BorderLayout.CENTER, insertionPanel);
    }
}