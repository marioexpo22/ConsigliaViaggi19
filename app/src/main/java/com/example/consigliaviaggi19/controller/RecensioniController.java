package com.example.consigliaviaggi19.controller;

import android.widget.RatingBar;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.example.consigliaviaggi19.MainActivity;
import com.example.consigliaviaggi19.R;
import com.example.consigliaviaggi19.entity.Struttura;
import com.example.consigliaviaggi19.entity.Utente;
import com.example.consigliaviaggi19.fragment.RecensioniStrutturaFragment;
import com.example.consigliaviaggi19.fragment.RecensisciStrutturaFragment;
import com.example.consigliaviaggi19.ui.recensioni.RecensioniRecycler;

public class RecensioniController {

    private RecensioniStrutturaFragment recensioniStrutturaFragment;
    private RecyclerView recensioniRecyclerView;
    private Struttura struttura;
    private MainActivity mainActivity;

    public RecensioniController(){}

    public RecensioniController(RecensioniStrutturaFragment recensioniStrutturaFragment){
        this.recensioniStrutturaFragment = recensioniStrutturaFragment;
    }

    public void creaRecensioniRecyclerView(RecyclerView recensioniRecyclerView, Struttura struttura, MainActivity mainActivity){
        this.recensioniRecyclerView = recensioniRecyclerView;
        this.struttura = struttura;
        this.mainActivity = mainActivity;
        RecensioniRecycler recensioniRecycler = new RecensioniRecycler(mainActivity, recensioniRecyclerView, struttura, this);
        recensioniRecycler.execute();
    }

    public void impostaBottoniSchermataRecensioni(){
        recensioniStrutturaFragment.scriviUnaRecensioneButton.setOnClickListener( (spostatiSuScriviUnaRecensione) -> scriviUnaRecensioneButtonPremuto() );
        recensioniStrutturaFragment.filtroStelle.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                creaRecensioniRecyclerView(recensioniRecyclerView, struttura, mainActivity);
            }
        });
    }

    private void scriviUnaRecensioneButtonPremuto(){
        if(utenteLoggato()){
            recensioniStrutturaFragment.mainActivity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, RecensisciStrutturaFragment.newInstance(recensioniStrutturaFragment.struttura, recensioniStrutturaFragment.mainActivity, recensioniStrutturaFragment.schermataStrutturaFragment))
                    .commitNow();
        } else {
            Toast.makeText(recensioniStrutturaFragment.getActivity(),"Devi prima effettuare il login", Toast.LENGTH_SHORT).show();
        }

    }

    public Boolean utenteLoggato(){
        if (Utente.getInstance().getNome() != null) {
            return true;
        } else return false;
    }

    public RecensioniStrutturaFragment getRecensioniStrutturaFragment() { return recensioniStrutturaFragment; }
}
