package com.example.freq_max;

public class Atleta {

    private String nome;
    private int idade;
    private int fcm;

    public Atleta(String nome, int idade, int fcm) {
        this.nome = nome;
        this.idade = idade;
        this.fcm = fcm;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public int getFcm() {
        return fcm;
    }

    @Override
    public String toString() {
        return nome + " - FCM: " + fcm;
    }
}
