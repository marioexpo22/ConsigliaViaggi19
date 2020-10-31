package com.example.consigliaviaggi19.DAO;

import android.content.Context;
import com.example.consigliaviaggi19.DAO.implementazioni.RecensioniDAOImplementazione;
import com.example.consigliaviaggi19.DAO.implementazioni.StruttureDAOImplementazione;
import com.example.consigliaviaggi19.DAO.implementazioni.UtenteDAOImplementazione;

public final class DAOFactory {
    Context context;

    public DAOFactory(){ }

    public UtenteDAO ottieniUtenteDAO(){
        return new UtenteDAOImplementazione();
    }

    public RecensioniDAO ottieniRecensioniDAO(){
        return new RecensioniDAOImplementazione();
    }

    public StruttureDAO ottieniStruttureDAO(){
        return new StruttureDAOImplementazione(); //Da passare il contesto
        //Aggiungere un eccezione
    }
}
