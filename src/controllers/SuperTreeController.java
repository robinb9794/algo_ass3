package controllers;

import javax.swing.JOptionPane;

import views.InsertionWindow;
import workers.UDrawGraphClient;

public abstract class SuperTreeController {
    protected InsertionWindow insertionWindow;
    protected UDrawGraphClient graphClient;

    protected abstract void work();

    protected void showInfoDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "Success!", JOptionPane.INFORMATION_MESSAGE);
	}

    protected void showErrorDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "Error!", JOptionPane.ERROR_MESSAGE);
	}
}