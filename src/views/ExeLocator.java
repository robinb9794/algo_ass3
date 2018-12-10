package views;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ExeLocator extends JFileChooser{
    public ExeLocator(){
        setDialogTitle("Please select the uDrawGraph.exe");
        setFileFilter(new FileNameExtensionFilter("exe", "exe"));
        setMultiSelectionEnabled(false);
    }
}