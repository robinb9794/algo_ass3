package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.SelectionWindow;

public class SelectionController {
	private SelectionWindow selectionWindow;
	
	private SuperTreeController redBlackTreeController;
    private SuperTreeController patriciaTreeController;
    private SuperTreeController robddController;
	
	public SelectionController() {
		selectionWindow = new SelectionWindow();
	}
	
	public void work() {
		addActionListenerToButtons();
		packAndShow();
	}
	
	private void addActionListenerToButtons() {
		selectionWindow.redBlackTreeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                selectionWindow.dispose();
				redBlackTreeController = new RedBlackTreeController();
				redBlackTreeController.work();
			}
		});
		
		selectionWindow.patriciaTreeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectionWindow.dispose();
				patriciaTreeController = new PatriciaTreeController();
				patriciaTreeController.work();
			}
		});
		
		selectionWindow.binaryDecisionDiagramButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                try{
                    selectionWindow.dispose();
                    robddController = new RoBDDController();
                    robddController.work();
                }catch(Exception ex){
                    System.out.println(ex.toString());
                }
                
			}
		});
	}
	
	private void packAndShow() {
		selectionWindow.pack();
		selectionWindow.setLocationRelativeTo(null);
		selectionWindow.setVisible(true);
	}
}
