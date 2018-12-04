package algo_ass3.views;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SelectionWindow extends JFrame{
	public JButton redBlackTreeButton = new JButton("Red-black tree");
	public JButton patriciaTreeButton = new JButton("Patricia tree");
	public JButton binaryDecisionDiagramButton = new JButton("Binary decision diagram");
	
	private RedBlackTreeInsertionWindow redBlackTreeInsertionWindow;
	
	public SelectionWindow() {
		setTitle("Choose wisely...");
		setPreferredSize(new Dimension(300, 150));
		setResizable(false);
		setLayout(new GridLayout(3, 1));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		redBlackTreeButton.setPreferredSize(new Dimension(100, 50));
		patriciaTreeButton.setPreferredSize(new Dimension(100, 50));
		binaryDecisionDiagramButton.setPreferredSize(new Dimension(100, 50));
		
		add(redBlackTreeButton);
		add(patriciaTreeButton);
		add(binaryDecisionDiagramButton);
	}	
}