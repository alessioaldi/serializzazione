package com.example;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {

        Random rand = new Random();
        try {
            ServerSocket server = new ServerSocket(3000);
            
            while(true){
                Socket s = server.accept();
                System.out.println("Server avviato");
                
                Frate f = new Frate(s, server);
                f.start();
            }
            
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
