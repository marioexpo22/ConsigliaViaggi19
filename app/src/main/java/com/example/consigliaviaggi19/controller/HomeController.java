package com.example.consigliaviaggi19.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.consigliaviaggi19.MainActivity;
import com.example.consigliaviaggi19.R;
import com.example.consigliaviaggi19.entity.Struttura;
import com.example.consigliaviaggi19.entity.Utente;
import com.example.consigliaviaggi19.fragment.SchermataAccediFragment;
import com.example.consigliaviaggi19.fragment.SchermataHomeFragment;
import com.example.consigliaviaggi19.fragment.SchermataRicercaFragment;
import com.example.consigliaviaggi19.ui.MainAdapter;
import com.example.consigliaviaggi19.ui.MainModel;
import com.example.consigliaviaggi19.ui.Recycler;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class HomeController {

    SchermataHomeFragment schermataHomeFragment;
    Utente utente = Utente.getInstance();

    public HomeController(SchermataHomeFragment schermataHomeFragment){
        this.schermataHomeFragment = schermataHomeFragment;
    }

    @SuppressLint("SetTextI18n")
    public void impostaBottoniSchermataHome(){

        schermataHomeFragment.schermataRicercaButtonDaHome.setOnClickListener( (spostatiSuSchermataRicerca) -> cercaButtonPremuto());

        if(utente.getNome() == null){
            schermataHomeFragment.bottoneAccedi.setOnClickListener( (spostatiSuSchermataAccedi) -> accediButtonPremutoDaNonLoggato());
        } else if (utente.getNome() != null) {
            schermataHomeFragment.bottoneAccedi.setText("Esci");
            schermataHomeFragment.bottoneAccedi.setOnClickListener( (effettuaLogoutDaUtente) -> accediButtonPremutoDaLoggato());
        }

    }

    public void cercaButtonPremuto(){
        schermataHomeFragment.mainActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, SchermataRicercaFragment.newInstance(schermataHomeFragment.mainActivity))
                .commitNow();
    }

    public void accediButtonPremutoDaNonLoggato(){
        schermataHomeFragment.mainActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, SchermataAccediFragment.newInstance(schermataHomeFragment.mainActivity))
                .commitNow();
    }

    public void accediButtonPremutoDaLoggato(){
        utente.setNickname(null);
        utente.setNome(null);
        utente.setCognome(null);
        utente.setEmail(null);

        schermataHomeFragment.bottoneAccedi.setOnClickListener( (spostatiSuSchermataAccedi) -> accediButtonPremutoDaNonLoggato());
        schermataHomeFragment.bottoneAccedi.setText("Accedi");
        Toast.makeText(schermataHomeFragment.getActivity(),"Logout effettuato", Toast.LENGTH_SHORT).show();
    }

    public void createRecyclerView(RecyclerView recyclerView, String type, MainActivity mainActivity, SchermataHomeFragment schermataHomeFragment){
        Recycler recycler = new Recycler(mainActivity, recyclerView, this, schermataHomeFragment);
        recycler.execute(type);
    }
}
