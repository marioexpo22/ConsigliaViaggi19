package com.example.consigliaviaggi19.DAO;

import android.content.Context;
import com.example.consigliaviaggi19.DAO.implementazioni.StruttureDAOServer;

public class DAOFactory {
    Context context;

    public DAOFactory(Context context){ this.context = context; }

    /*public UtenteDAO ottieniUtenteDAO(){
        return
    }*/

    /*public RecensioniDAO ottieniRecensioniDAO(){

    }*/

    public StruttureDAO ottieniStruttureDAO(){
        return new StruttureDAOServer(); //Da passare il contesto
        //Aggiungere un eccezione
    }
}
