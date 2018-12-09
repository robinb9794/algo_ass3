package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Color;

public class InsertionWindow extends JFrame{
    public JPanel interactionPanel;
    public JPanel insertionPanel;
    public JTextField insertionField;
    public JButton insertionButton;
    public JButton topDownButton;

    public JPanel infoPanel;
    public JScrollPane scrollPane;
    
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

        topDownButton = new JButton("-> Top-Down-2-3-4");
        topDownButton.setPreferredSize(new Dimension(250, 30));

        interactionPanel.add(BorderLayout.SOUTH, topDownButton);

        add(BorderLayout.CENTER, interactionPanel);
    }

    public void packAndShow(){
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
