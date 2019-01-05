package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.StringReader;

import BoolExprParser.BoolExprParser;
import BoolExprParser.BoolExprScanner;
import redblack.Node;
import redblack.RoBDD;
import redblack.RoBDD.Function;
import views.RoBDDInsertionWindow;

public class RoBDDController extends SuperTreeController {
    private RoBDD robdd;

    public RoBDDController() {
        this.insertionWindow = new RoBDDInsertionWindow();
        this.robdd = new RoBDD();
    }

    @Override
    public void work(){
        insertionWindow.buildInteractionPanel();
        insertionWindow.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                insertionWindow.dispose();
                graphClient.close();
            }
        });
        insertionWindow.insertionButton.addActionListener(getInsertionButtonListener());
        insertionWindow.packAndShow();
    }

    private ActionListener getInsertionButtonListener(){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    String value = insertionWindow.insertionField.getText();
                    BoolExprParser parser = new BoolExprParser(new BoolExprScanner(new StringReader(value)));
                    java_cup.runtime.Symbol symbol = parser.parse();
                    print((Node) symbol.value);
                }catch(Exception ex){
                    showErrorDialog(ex.getMessage());
                }                
            }
        };
    }

    private void print(Node node){

    }

    private Function iter(Node node){
        if(node != null){
            
        }
        return new Function(true);
    }

}