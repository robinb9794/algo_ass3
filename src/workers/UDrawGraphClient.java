package workers;

import redblack.*;

import java.net.Socket;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UDrawGraphClient{
    private Socket socket;
    private OutputStream out;
    private BufferedReader in;

    public UDrawGraphClient(){
        try{
            Runtime.getRuntime().exec("sh -c /home/robin/repos/algo_ass3/lib/uDrawGraph-3.1/bin/uDrawGraph -server 2542");
            socket = new Socket("localhost", 2542);
            out = socket.getOutputStream();
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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
            out.write((message + "\n").getBytes());
            String tmp = in.readLine();
            if(tmp.compareTo("ok") != 0){
                tmp = in.readLine();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void close(){
        try{
            out.close();
            in.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
}