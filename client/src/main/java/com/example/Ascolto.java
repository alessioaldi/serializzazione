package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Ascolto extends Thread {
    public Socket s;
    
    public Ascolto(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
            while (true) {
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                DataOutputStream out = new DataOutputStream(s.getOutputStream());

                System.out.println(in.readLine());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}