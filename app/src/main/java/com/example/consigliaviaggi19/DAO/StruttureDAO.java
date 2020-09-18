package com.example.consigliaviaggi19.DAO;

import com.example.consigliaviaggi19.entity.Struttura;

import java.util.List;

public interface StruttureDAO {
    List<Struttura> ottieniStruttureCheContengonoString(String nomeStruttura);
    List<Struttura> ottieniTutteStrutture();
}
