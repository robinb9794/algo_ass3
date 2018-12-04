package algo_ass3.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import algo_ass3.views.SelectionWindow;

public class SelectionController {
	private SelectionWindow selectionWindow;
	
	private RedBlackTreeController redBlackTreeController;
	
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
				redBlackTreeController = new RedBlackTreeController();
				redBlackTreeController.work();
			}
		});
		
		selectionWindow.patriciaTreeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		selectionWindow.binaryDecisionDiagramButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
	
	private void packAndShow() {
		selectionWindow.pack();
		selectionWindow.setLocationRelativeTo(null);
		selectionWindow.setVisible(true);
	}
}
