package com.example.consigliaviaggi19.entity;

import android.content.Intent;

public final class Utente {

    private Integer id;
    private String nickname;
    private String nome;
    private String cognome;
    private String email;

    private Utente(){
        this.id = null;
        this.nickname = null;
        this.nome = null;
        this.cognome = null;
        this.email = null;
    }

    private static final Utente UTENTE = new Utente();

    public static Utente getInstance() {
        return UTENTE;
    }

    public String getNickname() { return nickname; }

    public Integer getPassword() { return id; }

    public String getNome() { return nome; }

    public String getCognome() { return cognome; }

    public String getEmail() { return email; }


    public void setNickname(String username) { this.nickname = username; }

    public void setId(Integer id) { this.id = id; }

    public void setNome(String nome) { this.nome = nome; }

    public void setCognome(String cognome) { this.cognome = cognome; }

    public void setEmail(String email) { this.email = email; }
}
