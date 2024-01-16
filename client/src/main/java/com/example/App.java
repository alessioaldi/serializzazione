package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Socket s = null;
        try {

            
            s = new Socket("localhost", 3000);

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            
            
            
            String messaggio = in.readLine();
            
            System.out.println(messaggio);

            s.close();
            System.out.println("COMUNICAZIONE TERMINATA");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
}
