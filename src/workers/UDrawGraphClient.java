package workers;

import redblack.*;
import views.ExeLocator;

import javax.swing.JFileChooser;

import java.io.*;

public class UDrawGraphClient{
    private ExeLocator exeLocator;
    private PipeManager pipeManager;

    private OutputStream stdin;
    private InputStream stdout;

    private BufferedReader reader;
    private BufferedWriter writer;

    public UDrawGraphClient(){
        this.pipeManager = new PipeManager();
        this.exeLocator = new ExeLocator();
        int returnValue = exeLocator.showOpenDialog(null);
        handleReturnValue(returnValue);
    }

    private void handleReturnValue(int returnValue){
        if(returnValue == JFileChooser.APPROVE_OPTION){
            File exeFile = exeLocator.getSelectedFile();
            String exePath = exeFile.getAbsolutePath();
            pipeManager.init(exePath);
            open(exePath);
        }
    }

    private void open(String exePath){
        try{
            stdin = pipeManager.getOutputStream();   
            stdout = pipeManager.getInputStream();
            
            reader = new BufferedReader(new InputStreamReader(stdout));
            writer = new BufferedWriter(new OutputStreamWriter(stdin));
        }catch(Exception ex){
            ex.printStackTrace();
        }        
    }    

    public void newNode(Node node){
        String message = "graph(mixed_update([new_node(\"" + node.getKey() 
                            + "\",\"C\",[a(\"OBJECT\",\"" + node.getKey() + "\")])]))";
        sendMessage(message);
    }

    public void newLeftEdge(Node start, Node end){
        String message = "graph(mixed_update([new_edge(\"" + start.getKey() + ">"
                            + end.getKey() + "\",\"B\",[a(\"OBJECT\",\"links\")],\""
                            + start.getKey() + "\",\"" + end.getKey() + "\")]))";
        sendMessage(message);
    }

    public void newRightEdge(Node start, Node end){
        String message = "graph(mixed_update([new_edge(\"" + start.getKey() + ">"
                            + end.getKey() + "\",\"B\",[a(\"OBJECT\",\"rechts\")],\""
                            + start.getKey() + "\",\"" + end.getKey() + "\")]))";
        sendMessage(message);
    }

    public void newRedLeftEdge(Node start, Node end){
        String message = "graph(mixed_update([new_edge(\""
                            + start.getKey()
                            + ">"
                            + end.getKey()
                            + "\",\"B\",[a(\"EDGECOLOR\",\"red\"),a(\"OBJECT\",\"links\")],\""
                            + start.getKey() + "\",\"" + end.getKey() + "\")]))";
        sendMessage(message);
    }

    public void newRedRightEdge(Node start, Node end){
        String message = "graph(mixed_update([new_edge(\""
                            + start.getKey()
                            + ">"
                            + end.getKey()
                            + "\",\"B\",[a(\"EDGECOLOR\",\"red\"),a(\"OBJECT\",\"rechts\")],\""
                            + start.getKey() + "\",\"" + end.getKey() + "\")]))";
        sendMessage(message);
    }

    private void sendMessage(String message){
        try{
            writer.write(message + "\n");
            writer.flush();
            String tmp = reader.readLine();
            if(tmp.compareTo("ok") != 0){
                tmp = reader.readLine();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void close(){
        try{
            writer.close();
            reader.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
}