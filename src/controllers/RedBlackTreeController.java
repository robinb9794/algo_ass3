package controllers;

import models.ViewModel;
import views.*;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BorderFactory;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RedBlackTreeController {
    private ViewModel viewModel;
	private InsertionWindow insertionWindow;
		
	public RedBlackTreeController() {
        this.insertionWindow = new RedBlackTreeInsertionWindow();
        this.viewModel = new ViewModel();
	}
	
	public void work() {
        insertionWindow.buildInteractionPanel();
        insertionWindow.insertionButton.addActionListener(getInsertionButtonListener());
        insertionWindow.packAndShow();
    }

    private ActionListener getInsertionButtonListener(){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String key = insertionWindow.insertionField.getText();
                JPanel infoRow = getNewInfoRow(key);
                insertionWindow.infoPanel.add(infoRow);
                insertionWindow.infoPanel.revalidate();
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
                insertionWindow.infoPanel.remove(infoRow);
                insertionWindow.infoPanel.revalidate();
                insertionWindow.infoPanel.repaint();
            }
        };
    }
}