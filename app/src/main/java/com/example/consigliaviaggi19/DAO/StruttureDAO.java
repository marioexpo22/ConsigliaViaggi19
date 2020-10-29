package com.example.consigliaviaggi19.DAO;

import com.example.consigliaviaggi19.entity.Struttura;

import java.util.List;

public interface StruttureDAO {
    String ottieniStruttureCheContengonoString(String nomeStruttura);
    String ottieniTutteStrutture();
}
