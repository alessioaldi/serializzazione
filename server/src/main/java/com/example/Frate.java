package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Frate extends Thread {
    Socket s;
    ArrayList<Frate> users;
    DataOutputStream out;
    String n;

    public Frate(Socket s, String studenti) {
        this.s = s;
        n=studenti;
        try {
            this.out = new DataOutputStream(s.getOutputStream());
        } catch (Exception e) {
            System.out.println("Errore nel creare il Thread Frate");
        }
    }

    public DataOutputStream getFOutputStream() {
        return this.out;
    }

    public Socket getSocket() {
        return this.s;
    }


    public void run() {

        System.out.println("in client si è connesso");

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            out.writeBytes(n+"\n");
            

        } catch (Exception e) {
            System.out.println("problema");
        }
    }
}
