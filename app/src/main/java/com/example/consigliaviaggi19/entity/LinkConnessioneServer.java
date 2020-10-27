package com.example.consigliaviaggi19.entity;

public final class LinkConnessioneServer {
    private final String link;
    private  LinkConnessioneServer(String link){ this.link = link; }
    private static final LinkConnessioneServer LINK_CONNESSIONE_SERVER = new LinkConnessioneServer("http://15.161.130.205/php/");

    public static LinkConnessioneServer getInstance() {
        return LINK_CONNESSIONE_SERVER;
    }


    public String getLink(){ return this.link; }
}
