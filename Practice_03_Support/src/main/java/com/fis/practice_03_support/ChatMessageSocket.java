/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fis.practice_03_support;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;
import javax.swing.JTextPane;

/**
 *
 * @author Dell
 */
public class ChatMessageSocket {
    private Socket socket;
    private JTextPane txtBoard;
    private PrintWriter out;
    private BufferedReader reader;

    public ChatMessageSocket(Socket socket, JTextPane txtBoard) throws IOException {
        this.socket = socket;
        this.txtBoard = txtBoard;
        this.out = new PrintWriter(socket.getOutputStream());
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        receive();
    }
        
    public void receive(){
        new Thread(){
            @Override
            public void run() {
               while(true){
                   try {
                       String line = reader.readLine();
                       if(line != null && !line.isEmpty()){
                           txtBoard.setText(txtBoard.getText() + "\n " + line);
                       }
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
               }
            }
            
        }.start();
    }
    public void send(String message){
        String current = txtBoard.getText();
        txtBoard.setText(current + "\n" + message);
        out.println(message);
        out.flush();
    }
    public void close(){
        try {
            out.close();
            reader.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
