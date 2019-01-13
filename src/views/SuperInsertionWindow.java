package views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public abstract class SuperInsertionWindow extends JFrame {
    public JPanel interactionPanel;

    public JPanel insertionPanel;
    public JTextField insertionField;
    public JButton insertionButton;

    public JPanel infoPanel;
    public JScrollPane scrollPane;

    public JPanel extraActionsPanel;
    public JButton topDownButton;      

    public SuperInsertionWindow(String title){
        super(title);
    }

    public abstract void buildInteractionPanel();
    
    public void packAndShow(){
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
