package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Frate extends Thread {
    Socket s;
    ArrayList<Frate> users;
    DataOutputStream out;
    Classe classe;

    public Frate(Socket s, Classe c) {
        this.s = s;
        classe=c;
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

        XmlMapper xmlMapper = new XmlMapper();
        String stringa = new String();
        
        //serializzo
        try{
            stringa = xmlMapper.writeValueAsString(classe);
        } catch(Exception e){
            System.out.println("errore");
        }

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            out.writeBytes(stringa+"\n");
            

        } catch (Exception e) {
            System.out.println("problema");
        }
    }
}
