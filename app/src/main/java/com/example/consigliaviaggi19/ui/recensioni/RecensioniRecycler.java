package com.example.consigliaviaggi19.ui.recensioni;

import android.os.AsyncTask;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.consigliaviaggi19.MainActivity;
import com.example.consigliaviaggi19.controller.RecensioniController;
import com.example.consigliaviaggi19.entity.Recensione;
import com.example.consigliaviaggi19.entity.Struttura;
import com.example.consigliaviaggi19.ui.MainAdapter;
import com.example.consigliaviaggi19.ui.MainModel;


import java.util.ArrayList;

public class RecensioniRecycler extends AsyncTask<String,Void,String> {

    MainActivity mainActivity;
    RecyclerView recensioniRecyclerView;
    Struttura struttura;
    RecensioniController recensioniController;

    public RecensioniRecycler(MainActivity mainActivity, RecyclerView recensioniRecyclerView, Struttura struttura, RecensioniController recensioniController){
        this.mainActivity = mainActivity;
        this.recensioniRecyclerView = recensioniRecyclerView;
        this.struttura = struttura;
        this.recensioniController = recensioniController;

    }

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPreExecute(){
    }

    @Override
    protected void onPostExecute(String result) {

        ArrayList<Recensione> listaRecensioni;
        RecensioniMainAdapter recensioniMainAdapter;


        listaRecensioni = new ArrayList<>();
        int i;

        for(i = 0; i < 6; i++){
            Recensione recensione = new Recensione("Titolo " + i, 3,
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ",
                    "Ciro", "24/08/2020");
            listaRecensioni.add(recensione);
            System.out.println("Inserito item" + i + "\n");
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity, LinearLayoutManager.VERTICAL, false);
        recensioniRecyclerView.setLayoutManager(linearLayoutManager);
        recensioniRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //Initialize MainAdapter
        recensioniMainAdapter = new RecensioniMainAdapter(mainActivity, listaRecensioni);
        //Set MainAdapter to RecyclerView
        recensioniRecyclerView.setAdapter(recensioniMainAdapter);
    }


}
