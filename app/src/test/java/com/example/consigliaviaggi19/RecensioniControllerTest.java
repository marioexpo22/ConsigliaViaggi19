package com.example.consigliaviaggi19;

import com.example.consigliaviaggi19.controller.RecensioniController;
import com.example.consigliaviaggi19.entity.Utente;
import org.junit.Before;
import org.junit.Test;

public class RecensioniControllerTest {

    private RecensioniController recensioniController;

    @Before
    public void istanziaAccediController(){
        recensioniController = new RecensioniController();
    }

    @Test
    public void bottoneScriviUnaRecensionePremutoDaLoggato(){
        Utente.getInstance().setNome("Mario");
        Boolean esito = recensioniController.utenteLoggato();
        assert (esito);
    }

    @Test
    public void bottoneScriviUnaRecensionePremutoDaNonLoggato(){
        Utente.getInstance().setNome(null);
        Boolean esito = recensioniController.utenteLoggato();
        assert (!esito);
    }
}
