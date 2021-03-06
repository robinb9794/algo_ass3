package controllers;

import javax.swing.JOptionPane;

import views.SuperInsertionWindow;
import workers.UDrawGraphClient;

public abstract class SuperTreeController {
    protected SuperInsertionWindow insertionWindow;
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