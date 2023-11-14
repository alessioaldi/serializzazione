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

            Scanner scan = new Scanner(System.in);
            System.out.println("Inserisci l'indirizzo a cui ti vuoi connettere");
            String indirizzo = scan.nextLine();
            s = new Socket(indirizzo, 3000);

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            
            System.out.println("Inserici il tuo nickname:");
            out.writeBytes(scan.nextLine() + "\n");

            String messaggio = "";
            //starto il tubo di ascolto continuo
            Ascolto a = new Ascolto(s);
            a.start();

            do {
                //scrivo 
                messaggio = scan.nextLine();
                out.writeBytes(messaggio + "\n");
            } while (!messaggio.equals("/q") && !messaggio.equals("/Q"));

            s.close();
            System.out.println("COMUNICAZIONE TERMINATA");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            if(s != null)
            s.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
