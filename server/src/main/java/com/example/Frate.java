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

    public Frate(Socket s, ArrayList<Frate> utenti) {
        this.s = s;
        this.users = utenti;
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

    // Manda un messaggio a tutti
    public void broadcast(String username, String messaggio) {
        try {
            for (int i = 0; i < users.size(); i++) {
                if (this.s.equals(users.get(i).getSocket()))
                    continue;
                Frate nuovo = users.get(i);
                nuovo.getFOutputStream().writeBytes(username + ": " + messaggio + "\n");
            }
        } catch (Exception e) {
            System.out.println("Problema nel Broadcast");
        }
    }

    // Trova il posto nell'array di un utente
    public int trovaUser(String name) {
        for (int i = 0; i < users.size(); i++) {
            String due = "/" + users.get(i).getName();
            if (due.equals(name)) {
                return i;
            }
        }
        return -1;
    }

    // controllo se username è già presente
    public String controlloUser(String username, BufferedReader in) {

        try {
            boolean controllo = false;
            do {
                controllo = false;
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getName().equals(username)) {
                        controllo = true;
                    }
                }
                if (controllo == true) {
                    out.writeBytes("Username gia' in utilizzo\n");
                    username = in.readLine();
                }
            } while (controllo == true);

        } catch (Exception e) {
            System.out.println("Errore nel controllo di doppio user");
        }
        System.out.println("NUOVO UTENTE - " + username);
        return username;
    }

    public void run() {

        System.out.println("in client si è connesso");

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            String username = controlloUser(in.readLine(), in);
            // metti nome al thread
            Thread.currentThread().setName(username);

            String messaggio = new String();

            while (true) {
                // se il client è solo non permette invio di messaggi
                if (users.size() < 1) {
                    continue;
                } else {
                    messaggio = in.readLine();
                    // controllo se l'utente vuole inviare messaggio privato
                    int num = trovaUser(messaggio);

                    // l'utente vuole mandare in privato
                    if (num != -1) {

                        messaggio = in.readLine();
                        try {
                            Frate nuovo = users.get(num);
                            // invio in privato
                            nuovo.getFOutputStream().writeBytes(username + " (messaggio privato): " + messaggio + "\n");

                        } catch (Exception e) {
                            System.out.println("Errore nel messaggio privato");
                        }

                        // mando in broadcast
                    } else {

                        // broadcast a tutti
                        if (!messaggio.equals("/q") && !messaggio.equals("/Q")) {

                            System.out.println(username + ": " + messaggio);
                            broadcast(username, messaggio);

                            // uscita
                        } else {
                            s.close();
                            broadcast("SERVER",
                                    "L'utente " + Thread.currentThread().getName() + " e' uscito dalla conversazione");
                            break;
                        }

                    }
                }
            }

        } catch (Exception e) {
            System.out.println("problema");
        }
    }
}
