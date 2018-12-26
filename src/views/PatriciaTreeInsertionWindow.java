package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class PatriciaTreeInsertionWindow extends SuperInsertionWindow {

    public PatriciaTreeInsertionWindow(){
        super("Assignment 3 - Patricia tree - Robin Beyer");
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 400));
        setResizable(false);
    }

    @Override
    public void buildInteractionPanel(){
        interactionPanel = new JPanel();
        interactionPanel.setLayout(new BorderLayout());
        interactionPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        insertionPanel = new JPanel();
        insertionPanel.setLayout(new FlowLayout());
        insertionPanel.setBorder(BorderFactory.createRaisedBevelBorder());

        insertionField = new JTextField();
        insertionField.setPreferredSize(new Dimension(150, 30));
        insertionPanel.add(insertionField);

        insertionButton = new JButton("Insert");
        insertionButton.setPreferredSize(new Dimension(100, 30));
        insertionPanel.add(insertionButton);        

        interactionPanel.add(BorderLayout.NORTH, insertionPanel);

        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(infoPanel);

        interactionPanel.add(BorderLayout.CENTER, scrollPane);

        extraActionsPanel = new JPanel(new GridLayout(2, 1));

        searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(250, 30));
        extraActionsPanel.add(searchButton);

        testButton = new JButton("Insert testdata");
        testButton.setPreferredSize(new Dimension(25, 30));
        extraActionsPanel.add(testButton);

        interactionPanel.add(BorderLayout.SOUTH, extraActionsPanel);

        add(BorderLayout.CENTER, interactionPanel);
    }
}