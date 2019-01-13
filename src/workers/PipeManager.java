package workers;

import java.io.*;

import java.lang.System;

public class PipeManager{
    private Process process;

    public void init(String exePath){
        try{
            String[] commands = new String[3];
            String os = System.getProperty("os.name");
            if(isUnixOS(os)){
                commands = getUnixCommands(exePath);
                process = Runtime.getRuntime().exec(commands, null, null);
            }else
                process =  new ProcessBuilder(exePath, "pipe", "").start();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }

    private boolean isUnixOS(String os){
        return (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0 || os.indexOf("aix") > 0 );
    }

    private String[] getUnixCommands(String exePath){
        String[] commands = new String[3];
        commands[0] = "sh";
        commands[1] = "-c";
        commands[2] = exePath + " -pipe";
        return commands;
    }

    public OutputStream getOutputStream(){
        return process.getOutputStream();
    }

    public InputStream getInputStream(){
        return process.getInputStream();
    }
}