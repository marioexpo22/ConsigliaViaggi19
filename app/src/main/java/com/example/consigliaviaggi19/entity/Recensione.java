package com.example.consigliaviaggi19.entity;

public class Recensione {
    private final String titolo;
    private final float numeroStelle;
    private final String testo;
    private final String nomeNicknameRecensore;
    private final String data;

    public Recensione (String titolo, float numeroStelle, String testo, String nomeNicknameRecensore, String data){
        this.titolo = titolo;
        this.numeroStelle = numeroStelle;
        this.testo = testo;
        this.nomeNicknameRecensore = nomeNicknameRecensore;
        this.data = data;
    }

    public String getTitolo(){ return this.titolo; }
    public float getNumeroStelle(){ return this.numeroStelle; }
    public String getTesto(){ return this.testo; }
    public String getNomeNicknameRecensore(){ return this.nomeNicknameRecensore; }
    public String getData(){ return this.data; }

}
