package controllers;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import views.SuperInsertionWindow;
import workers.UDrawGraphClient;

public abstract class SuperTreeController {
    protected UDrawGraphClient graphClient;

    public SuperTreeController(){
      this.graphClient = new UDrawGraphClient();
    }

    public abstract void work();

    protected void showInfoDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "Success!", JOptionPane.INFORMATION_MESSAGE);
	}

    protected void showErrorDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "Error!", JOptionPane.ERROR_MESSAGE);
    }
}