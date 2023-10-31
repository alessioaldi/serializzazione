package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class Frate extends Thread {
    Socket s;
    ArrayList<Frate> users;
    DataOutputStream out;
    String n;

    public Frate(Socket s, ArrayList<Frate> utenti, String n) {
        this.s = s;
        this.users = utenti;
        try {
            this.out = new DataOutputStream(s.getOutputStream());
        } catch (Exception e) {
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

            String username = in.readLine();
            System.out.println("NUOVO UTENTE - " + username);
            //metti nome al thread

            String messaggio = new String();

            while (true) {
                messaggio = in.readLine();

                System.out.println(messaggio);
                System.out.println(users.size());
                for (int i = 0; i < users.size(); i++) {
                    if (this.s.equals(users.get(i).getSocket()))
                        continue;
                    Frate nuovo = users.get(i);
                    nuovo.getFOutputStream().writeBytes(username + ": " + messaggio + "\n");
                }
            }

        } catch (Exception e) {
            System.out.println("problem");
        }
    }
}
