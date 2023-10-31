package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 3000);
            Scanner scan = new Scanner(System.in);

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            int r = 0;

            do {
                out.writeBytes(scan.nextLine()+ "\n");

                r = Integer.parseInt(in.readLine());

                System.out.println("Il server ha risporso: " + r);
            } while (r != 3);

            System.out.println("numero indovinato in " + Integer.parseInt(in.readLine()) + " tentativi");
            s.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
