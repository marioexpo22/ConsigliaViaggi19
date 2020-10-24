package com.example.consigliaviaggi19.DAO;

import com.example.consigliaviaggi19.entity.Recensione;
import com.example.consigliaviaggi19.entity.Struttura;
import com.example.consigliaviaggi19.fragment.RecensisciStrutturaFragment;

import java.util.List;

public interface RecensioniDAO {
    List<Recensione> ottieniRecensioniStruttura(Struttura struttura);
    Boolean aggiungiRecensione(Recensione recensione);
}
