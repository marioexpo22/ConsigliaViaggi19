package com.example.consigliaviaggi19;

import com.example.consigliaviaggi19.controller.MappaController;
import org.junit.Before;
import org.junit.Test;

public class MappaControllerTest {

    private MappaController mappaController;

    @Before
    public void istanziaMappaController(){
        mappaController = new MappaController();
    }

    @Test (expected = IllegalArgumentException.class)
    public void correggiMediaMaggioreDiCinque(){
        mappaController.correggiMedia(6);
    }

    @Test
    public void correggiMediaMinoreZeroPuntoTre(){
        float mediaCorretta;
        mediaCorretta = mappaController.correggiMedia(1.25f);
        assert(mediaCorretta == 1);
    }

    @Test
    public void correggiMediaMinoreUgualeZeroPuntoSettantacinque(){
        float mediaCorretta;
        mediaCorretta = mappaController.correggiMedia(1.75f);
        assert(mediaCorretta == 1.5f);
    }

    @Test
    public void correggiMediaConValoreSuperioreAZeroPuntoSettantacinque(){
        float mediaCorretta;
        mediaCorretta = mappaController.correggiMedia(1.85f);
        assert(mediaCorretta == 2);
    }
}
