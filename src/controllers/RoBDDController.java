package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import BoolExprParser.BoolExprParser;
import BoolExprParser.BoolExprScanner;
import BoolExprParser.Node;
import redblack.RoBDD;
import redblack.RoBDD.Function;
import views.RoBDDInsertionWindow;

public class RoBDDController extends SuperTreeController {
    private RoBDD robdd;
    private Map<Integer, Boolean> foundValues;

    public RoBDDController() {
        this.insertionWindow = new RoBDDInsertionWindow();
        this.robdd = new RoBDD();
        this.foundValues = new HashMap<Integer, Boolean>();
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
                    Function f = iter((Node) symbol.value);
                    printRoBDD(f);
                    graphClient.improve();
                    insertionWindow.insertionField.setEnabled(false);
                    insertionWindow.insertionButton.setEnabled(false);
                }catch(Exception ex){
                    showErrorDialog(ex.getMessage());
                }                
            }
        };
    }

    private Function iter(Node node){
        if(node != null){
            switch(node.type()){
                case VAR:
                    return robdd.generateVar(node.name());
                case NOT:
                    return robdd.not(iter(node.left()));
                case AND:
                    return robdd.and(iter(node.left()), iter(node.right()));
                case OR:
                    return robdd.or(iter(node.left()), iter(node.right()));
                case IMPLIES:
                    return robdd.implication(iter(node.left()), iter(node.right()));
                case EQUIV:
                    return robdd.equivalence(iter(node.left()), iter(node.right()));
            }
        }
        return new Function(true);
    }

    private void printRoBDD(Function f){
        if(!foundValues.containsKey(f.hashCode())){
            foundValues.put(f.hashCode(), true);

            String key = Integer.toString(f.hashCode());
            String value = f.getName();
            graphClient.newNode(key, value);

            Function thenFunction = f.getThen(f.getVar());
            if(thenFunction != null){
                String thenKey = Integer.toString(thenFunction.hashCode());
                printRoBDD(thenFunction);
                graphClient.newRightEdge(key, thenKey);
            }

            Function otherwiseFunction = f.getOtherwise(f.getVar());
            if(otherwiseFunction != null){
                String otherwiseKey = Integer.toString(otherwiseFunction.hashCode());
                printRoBDD(otherwiseFunction);
                graphClient.newLeftEdge(key, otherwiseKey);
            }
        }
    }
}