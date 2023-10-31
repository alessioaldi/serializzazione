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
            Scanner scan = new Scanner(System.in);

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            int r = 0;
            
            System.out.println("Inserici il tuo nickname:");
            out.writeBytes(scan.nextLine() + "\n");

            do {
                
                out.writeBytes(scan.nextLine()+ "\n");

            } while (true);

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
