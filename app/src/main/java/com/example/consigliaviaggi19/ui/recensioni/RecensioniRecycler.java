package com.example.consigliaviaggi19.ui.recensioni;

import android.os.AsyncTask;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.consigliaviaggi19.DAO.DAOFactory;
import com.example.consigliaviaggi19.DAO.RecensioniDAO;
import com.example.consigliaviaggi19.MainActivity;
import com.example.consigliaviaggi19.controller.RecensioniController;
import com.example.consigliaviaggi19.entity.Recensione;
import com.example.consigliaviaggi19.entity.Struttura;

import java.util.ArrayList;

public class RecensioniRecycler extends AsyncTask<String,Void,String> {

    MainActivity mainActivity;
    RecyclerView recensioniRecyclerView;
    Struttura struttura;
    RecensioniController recensioniController;
    RecensioniDAO recensioniDAO;

    public RecensioniRecycler(MainActivity mainActivity, RecyclerView recensioniRecyclerView, Struttura struttura, RecensioniController recensioniController){
        this.mainActivity = mainActivity;
        this.recensioniRecyclerView = recensioniRecyclerView;
        this.struttura = struttura;
        this.recensioniController = recensioniController;
    }

    @Override
    protected String doInBackground(String... strings) {
        recensioniDAO = new DAOFactory(recensioniController.getRecensioniStrutturaFragment().getContext()).ottieniRecensioniDAO();
        return recensioniDAO.ottieniRecensioniStruttura(struttura,
                recensioniController.getRecensioniStrutturaFragment().filtroStelle.getRating(),
                recensioniController.getRecensioniStrutturaFragment().filtroData.getSelectedItem().toString());
    }

    @Override
    protected void onPostExecute(String result) {
        ArrayList<Recensione> listaRecensioni = new ArrayList<>();
        RecensioniMainAdapter recensioniMainAdapter;

        if(result == null || result.isEmpty()){
        } else {
            result = result.replace("[{\"titolo\":\"", "");
            result = result.replace("\",\"descrizione", "");
            result = result.replace("\",\"nomeVisualizzato", "");
            result = result.replace("\",\"rating", "");
            result = result.replace("\",\"data", "");
            result = result.replace("{\"", "");
            result = result.replace("[", "");
            result = result.replace("\"},", "");
            result = result.replace("\"}", "");
            result = result.replace("]", "");
            result = result.replace("\\", "");
            String[] res = result.split("\":\"");

            int i = 0;
            while (i < res.length) {
                listaRecensioni.add(new Recensione(null,null, res[i], Float.parseFloat(res[i+3]), res[i+1], res[i+2], res[i+4]));
                i += 5;
            }
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity, LinearLayoutManager.VERTICAL, false);
        recensioniRecyclerView.setLayoutManager(linearLayoutManager);
        recensioniRecyclerView.setItemAnimator(new DefaultItemAnimator());

        recensioniMainAdapter = new RecensioniMainAdapter(mainActivity, listaRecensioni);
        recensioniRecyclerView.setAdapter(recensioniMainAdapter);
    }


}
