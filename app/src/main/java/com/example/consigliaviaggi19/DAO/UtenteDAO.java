package com.example.consigliaviaggi19.DAO;

import com.example.consigliaviaggi19.entity.Utente;

public interface UtenteDAO {
    Boolean verificaEmail(String email);
    Boolean verificaPassword(String password);
    Utente ottieniUtenteValido(String email, String password);
}
