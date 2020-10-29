package com.example.consigliaviaggi19.entity;

public class Recensione {
    private final Integer idStruttura;
    private final Integer idUtente;
    private final String titolo;
    private final float numeroStelle;
    private final String testo;
    private final String nomeNicknameRecensore;
    private final String data;

    public Recensione (Integer idStruttura, Integer idUtente, String titolo, float numeroStelle, String testo, String nomeNicknameRecensore, String data){
        this.idStruttura = idStruttura;
        this.idUtente = idUtente;
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
    public Integer getIdStruttura(){ return this.idStruttura; }
    public Integer getIdUtente(){ return this.idUtente; }
    public String getData(){ return this.data; }
}
