package com.example.consigliaviaggi19.controller;

import androidx.recyclerview.widget.RecyclerView;
import com.example.consigliaviaggi19.MainActivity;
import com.example.consigliaviaggi19.R;
import com.example.consigliaviaggi19.entity.Struttura;
import com.example.consigliaviaggi19.fragment.RecensioniStrutturaFragment;
import com.example.consigliaviaggi19.fragment.RecensisciStrutturaFragment;
import com.example.consigliaviaggi19.fragment.SchermataHomeFragment;
import com.example.consigliaviaggi19.ui.Recycler;
import com.example.consigliaviaggi19.ui.recensioni.RecensioniRecycler;

public class RecensioniController {

    private RecensioniStrutturaFragment recensioniStrutturaFragment;

    public RecensioniController(RecensioniStrutturaFragment recensioniStrutturaFragment){
        this.recensioniStrutturaFragment = recensioniStrutturaFragment;
    }

    public void creaRecensioniRecyclerView(RecyclerView recensioniRecyclerView, Struttura struttura, MainActivity mainActivity){
        RecensioniRecycler recensioniRecycler = new RecensioniRecycler(mainActivity, recensioniRecyclerView, struttura, this);
        recensioniRecycler.execute();
    }

    public void impostaBottoniSchermataRecensioni(){
        recensioniStrutturaFragment.scriviUnaRecensioneButton.setOnClickListener( (spostatiSuScriviUnaRecensione) -> scriviUnaRecensioneButtonPremuto() );
    }

    public void scriviUnaRecensioneButtonPremuto(){
        recensioniStrutturaFragment.mainActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, RecensisciStrutturaFragment.newInstance(recensioniStrutturaFragment.struttura, recensioniStrutturaFragment.mainActivity, recensioniStrutturaFragment.schermataStrutturaFragment))
                .commitNow();
    }

}
