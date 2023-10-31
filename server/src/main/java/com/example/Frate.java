package com.example;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.ServerError;
import java.util.Random;


public class Frate extends Thread{
    Socket s;
    ServerSocket ss;

    public Frate(Socket s, ServerSocket ss){
        this.s = s;
        this.ss = ss;
    }


    public void run(){
        Random rand = new Random();

        int n = rand.nextInt(101);
            int tent = 0;
            int nRic = 0;
            System.out.println(n);

            System.out.println("in client si è ocnnesso");
            
            try{
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                DataOutputStream out = new DataOutputStream(s.getOutputStream());

                do {

                    nRic = Integer.parseInt(in.readLine());
                    tent++;
                    if (nRic > n) {
                        out.writeBytes("1\n");
                    } else if (nRic < n) {
                        out.writeBytes("2\n");
                    } else {
                        out.writeBytes("3\n");
                        System.out.println("il client ha indovinato\n");
                    }

                } while (nRic != n);

                out.writeBytes(Integer.toString(tent) + "\n");
                
                s.close();

            } catch (Exception e) {
                System.out.println("problem");
            }
    }
}
