package com.example.consigliaviaggi19.controller;

import android.annotation.SuppressLint;
import android.widget.SeekBar;
import android.widget.Toast;
import com.example.consigliaviaggi19.R;
import com.example.consigliaviaggi19.fragment.SchermataHomeFragment;
import com.example.consigliaviaggi19.fragment.SchermataRicercaFragment;
import com.example.consigliaviaggi19.fragment.SchermataStrutturaFragment;

import java.util.Map;

public class RicercaController {
    private SchermataRicercaFragment schermataRicercaFragment;

    public RicercaController(SchermataRicercaFragment schermataRicercaFragment) {
        this.schermataRicercaFragment = schermataRicercaFragment;
    }

    public void impostaSeekBar(){
        schermataRicercaFragment.seekBar.setMin(1);
        schermataRicercaFragment.seekBar.setMax(10);
        schermataRicercaFragment.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressoAggiornato = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progresso, boolean fromUser) {
                progressoAggiornato = progresso;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                /*Toast.makeText(schermataRicercaFragment.getActivity(), "Distanza : " + progressoAggiornato,
                        Toast.LENGTH_SHORT).show();*/
                schermataRicercaFragment.distanzaProgresso.setText("Distanza : " + progressoAggiornato);
            }
        });
    }

    public void impostaBottoni(){
        schermataRicercaFragment.cercaButton.setOnClickListener( (cerca) -> cercaPremuto() );
        schermataRicercaFragment.indietroButton.setOnClickListener( (back) -> indietroPremuto() );
    }


    public void cercaPremuto() {
        if(schermataRicercaFragment.getMappa() == null){
            Toast.makeText(schermataRicercaFragment.getActivity(),"La mappa non è pronta", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(schermataRicercaFragment.getActivity(),"La mappa è carica", Toast.LENGTH_SHORT).show();
        }

        String nomeStruttura = schermataRicercaFragment.nomeStrutturaTextField.getEditableText().toString();
        String luogo = schermataRicercaFragment.luogoTextField.getEditableText().toString();
        float numeroStelle = schermataRicercaFragment.ratingBar.getRating();
        int progresso = schermataRicercaFragment.seekBar.getProgress();

        MappaController mappaController = schermataRicercaFragment.getMappaController();
    }

    public void indietroPremuto(){
        schermataRicercaFragment.mainActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, SchermataHomeFragment.newInstance(schermataRicercaFragment.mainActivity))
                .commitNow();
    }


}
