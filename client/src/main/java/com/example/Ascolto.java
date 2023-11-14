package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Ascolto extends Thread {
    public Socket s;
    
    public Ascolto(Socket s) {
        this.s = s;
    }
//sto sempre in ascolto
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            
            //stampo tutto quello che mi arriva all'infinito
            while (true) {
                System.out.println(in.readLine());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}