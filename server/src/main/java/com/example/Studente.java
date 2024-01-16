package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
public class Studente {
    String nome;
    String cognome;
    String classe;

    public Studente(String nome, String cognome, String classe){
        this.nome = nome;
        this.cognome = cognome;
        this.classe = classe;
    }
}
