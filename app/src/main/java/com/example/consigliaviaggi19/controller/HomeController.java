package com.example.consigliaviaggi19.controller;

import android.content.Context;
import android.os.AsyncTask;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.consigliaviaggi19.MainActivity;
import com.example.consigliaviaggi19.R;
import com.example.consigliaviaggi19.entity.Struttura;
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

    public HomeController(SchermataHomeFragment schermataHomeFragment){
        this.schermataHomeFragment = schermataHomeFragment;
    }

    public void impostaBottoniSchermataHome(){
        schermataHomeFragment.schermataRicercaButtonDaHome.setOnClickListener( (spostatiSuSchermataRicerca) -> cercaButtonPremuto());
    }

    public void cercaButtonPremuto(){
        schermataHomeFragment.mainActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, SchermataRicercaFragment.newInstance(schermataHomeFragment.mainActivity))
                .commitNow();
    }

    public void createRecyclerView(RecyclerView recyclerView, String type, MainActivity mainActivity){
        Recycler recycler = new Recycler(mainActivity, recyclerView, this);
        recycler.execute(type);
    }
}
