package com.example.consigliaviaggi19.entity;

public final class Utente {

    private String nickname;
    private String password;
    private String nome;
    private String cognome;
    private String email;

    private Utente(){
        this.nickname = null;
        this.password = null;
        this.nome = null;
        this.cognome = null;
        this.email = null;
    }

    private static final Utente UTENTE = new Utente();

    public static Utente getInstance() {
        return UTENTE;
    }

    public String getNickname() { return nickname; }

    public String getPassword() { return password; }

    public String getNome() { return nome; }

    public String getCognome() { return cognome; }

    public String getEmail() { return email; }


    public void setNickname(String username) { this.nickname = username; }

    public void setPassword(String password) { this.password = password; }

    public void setNome(String nome) { this.nome = nome; }

    public void setCognome(String cognome) { this.cognome = cognome; }

    public void setEmail(String email) { this.email = email; }
}
