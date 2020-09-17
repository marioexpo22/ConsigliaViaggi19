package com.example.consigliaviaggi19.ui;

import com.example.consigliaviaggi19.entity.Struttura;

public class MainModel {
    String structLogo;
    String structName;

    public MainModel (Struttura struct){
        this.structLogo = struct.immagineStruttura;
        this.structName = struct.nomeStruttura;
    }

    public String getStructLogo() {
        return structLogo;
    }

    public String getStructName() {
        return structName;
    }
}
