package com.example.consigliaviaggi19.DAO;

import com.example.consigliaviaggi19.entity.Utente;

public interface UtenteDAO {
    Boolean verificaEmail(String email);
    Boolean verificaPassword(String password);
    String ottieniUtenteValido(String email, String password);
}
