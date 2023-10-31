package com.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {

        ArrayList<Frate> listautenti = new ArrayList<Frate>();
        ServerSocket server = null;
        try {

            server = new ServerSocket(3000);

            while (true) {
                Socket s = server.accept();
                System.out.println("Server avviato");
                Frate f = new Frate(s, listautenti);
                listautenti.add(f);
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
