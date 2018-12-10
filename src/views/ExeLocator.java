package views;

import javax.swing.JFileChooser;

public class ExeLocator extends JFileChooser{
    public ExeLocator(){
        setDialogTitle("Please select the uDrawGraph.exe");
        setMultiSelectionEnabled(false);
    }
}