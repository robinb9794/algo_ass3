package algo_ass3.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;

public class InsertionWindow extends JFrame{
    public JPanel insertionPanel;
    public JTextField insertionField;
    public JButton insertionButton;

    public JPanel infoPanel;
    public GridLayout infoGrid;

    public InsertionWindow(String title){
        super(title);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(280, 80));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
