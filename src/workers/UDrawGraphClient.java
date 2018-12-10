package workers;

import redblack.*;
import views.ExeLocator;

import javax.swing.JFileChooser;

import java.io.*;

public class UDrawGraphClient{
    private ExeLocator exeLocator;
    private PipeManager pipeManager;

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
        }else
            System.exit(0);
    }

    private void open(String exePath){
        try{
            OutputStream stdin = pipeManager.getOutputStream();   
            InputStream stdout = pipeManager.getInputStream();
            
            reader = new BufferedReader(new InputStreamReader(stdout));
            writer = new BufferedWriter(new OutputStreamWriter(stdin));
        }catch(Exception ex){
            ex.printStackTrace();
        }        
    }    

    public void newNode(Node node){
        String message = String.format("graph(mixed_update([new_node(\"%1$s\",\"C\",[a(\"OBJECT\",\"%1$s\")])]))",
                            node.getKey());
        sendMessage(message);
    }

    public void newLeftEdge(Node start, Node end){
        String message = String.format("graph(mixed_update([new_edge(\"%1$s>%2$s\","
                            + "\"B\",[a(\"OBJECT\",\"links\")],\"%1$s\",\"%2$s\")]))",
                            start.getKey(), end.getKey());
        sendMessage(message);
    }

    public void newRightEdge(Node start, Node end){
        String message = String.format("graph(mixed_update([new_edge(\"%1$s>%1$s\","
                            + "\"B\",[a(\"OBJECT\",\"rechts\")],\"%1$s\",\"%2$s\")]))", 
                            start.getKey(), end.getKey());
        sendMessage(message);
    }

    public void newRedLeftEdge(Node start, Node end){
        String message = String.format("graph(mixed_update([new_edge(\"%1$s>%2$s\","
                            + "\"B\",[a(\"EDGECOLOR\",\"red\"),a(\"OBJECT\",\"links\")],\"%1$s\",\"%2$s\")]))",
                            start.getKey(), end.getKey());
        sendMessage(message);
    }

    public void newRedRightEdge(Node start, Node end){
        String message = String.format("graph(mixed_update([new_edge(\"%1$s>%2$s\","
                            + "\"B\",[a(\"EDGECOLOR\",\"red\"),a(\"OBJECT\",\"rechts\")],\"%1$s\",\"%2$s\")]))",
                            start.getKey(), end.getKey());
        sendMessage(message);
    }

    public void reset(){
        sendMessage("menu(file(new))");
    }

    public void improve(){
		sendMessage("menu(view(fit_scale_to_window))");
		sendMessage("menu(layout(improve_all))");
	}

    public void close(){
        try{
            sendMessage("menu(file(close))");
            writer.close();
            reader.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }        
    }

    private void sendMessage(String message){
        try{
            writer.write(message + "\n");
            writer.flush();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}