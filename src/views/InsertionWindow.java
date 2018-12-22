package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class InsertionWindow extends JFrame {
    public JPanel interactionPanel;

    public JPanel insertionPanel;
    public JTextField insertionField;
    public JButton insertionButton;

    public JPanel infoPanel;
    public JScrollPane scrollPane;

    public JPanel extraActionsPanel;
    public JButton topDownButton;   
    public JButton searchButton;
    public JButton testButton;
    
    public InsertionWindow(String title){
        super(title);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 400));
        setResizable(false);
    }

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

        extraActionsPanel = new JPanel(new GridLayout(3, 1));

        topDownButton = new JButton("-> Top-Down-2-3-4");
        topDownButton.setPreferredSize(new Dimension(250, 30));
        extraActionsPanel.add(topDownButton);

        searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(250, 30));
        extraActionsPanel.add(searchButton);

        testButton = new JButton("Insert testdata");
        testButton.setPreferredSize(new Dimension(25, 30));
        extraActionsPanel.add(testButton);

        interactionPanel.add(BorderLayout.SOUTH, extraActionsPanel);

        add(BorderLayout.CENTER, interactionPanel);
    }

    public void packAndShow(){
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
