package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {

        ServerSocket server = null;
        try {

            server = new ServerSocket(3000);
            ArrayList <Studente> studenti= new ArrayList<Studente>();
            Studente s1= new Studente("culo", "cane", "Siu5");
            Studente s2= new Studente("spugni", "meucci", "sxbg");
            studenti.add(s2);
            studenti.add(s1);
            Classe c= new Classe(studenti);

            while (true) {
                Socket s = server.accept();
                System.out.println("Server avviato");
                Frate f = new Frate(s, c);
                f.start();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            server.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.exit(1);
    }
}
