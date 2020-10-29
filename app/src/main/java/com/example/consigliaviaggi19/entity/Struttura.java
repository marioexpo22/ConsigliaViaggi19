package com.example.consigliaviaggi19.entity;

public class Struttura {
    public int idStruttura;
    public String nomeStruttura;
    public String descrizione;
    public String emailStruttura;
    public String numeroDiTelefono;
    public String indirizzo;
    public String immagineStruttura;
    public double longitudine;
    public double latitudine;
    public String tipoStruttura;
    public String sitoWeb;
    public float numeroStelle;

    public Struttura(int idStruttura, String nomeStruttura, String descrizione, String emailStruttura, String numeroDiTelefono,
                     String indirizzo, String immagineStruttura, double latitudine, double longitudine,  String tipoStruttura, String sitoWeb, float numeroStelle){
        this.idStruttura = idStruttura;
        this.nomeStruttura = nomeStruttura;
        this.descrizione = descrizione;
        this.emailStruttura = emailStruttura;
        this.numeroDiTelefono = numeroDiTelefono;
        this.indirizzo = indirizzo;
        this.immagineStruttura = immagineStruttura;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.tipoStruttura = tipoStruttura;
        this.sitoWeb = sitoWeb;
        this.numeroStelle = numeroStelle;
    }
}
