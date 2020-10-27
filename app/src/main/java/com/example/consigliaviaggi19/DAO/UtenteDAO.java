package com.example.consigliaviaggi19.DAO;

import com.example.consigliaviaggi19.entity.Utente;

public interface UtenteDAO {
    String ottieniUtenteValido(String email, String password);
}
