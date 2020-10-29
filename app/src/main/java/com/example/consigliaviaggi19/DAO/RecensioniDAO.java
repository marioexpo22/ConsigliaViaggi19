package com.example.consigliaviaggi19.DAO;

import com.example.consigliaviaggi19.entity.Recensione;
import com.example.consigliaviaggi19.entity.Struttura;
import com.example.consigliaviaggi19.fragment.RecensisciStrutturaFragment;

import java.util.List;

public interface RecensioniDAO {
    String ottieniRecensioniStruttura(Struttura struttura, Float rating, String data);
    String aggiungiRecensione(Recensione recensione);
}
