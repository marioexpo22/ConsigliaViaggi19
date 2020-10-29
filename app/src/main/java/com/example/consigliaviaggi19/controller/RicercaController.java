package com.example.consigliaviaggi19.controller;

import android.annotation.SuppressLint;
import android.widget.SeekBar;
import android.widget.Toast;
import com.example.consigliaviaggi19.R;
import com.example.consigliaviaggi19.entity.Struttura;
import com.example.consigliaviaggi19.fragment.SchermataHomeFragment;
import com.example.consigliaviaggi19.fragment.SchermataRicercaFragment;
import com.example.consigliaviaggi19.fragment.SchermataStrutturaFragment;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RicercaController {
    private SchermataRicercaFragment schermataRicercaFragment;
    private MappaController mappaController;

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
            String nomeStruttura = schermataRicercaFragment.nomeStrutturaTextField.getEditableText().toString();
            String indirizzo = schermataRicercaFragment.luogoTextField.getEditableText().toString();
            float numeroStelle = schermataRicercaFragment.ratingBar.getRating();
            int distanza = schermataRicercaFragment.seekBar.getProgress();

            mappaController = schermataRicercaFragment.getMappaController();
            Set<Struttura> elencoStrutture = mappaController.getElencoStrutture();
            List<Marker> listaMarkers = mappaController.getListaMarker();

            for(Marker marker : listaMarkers){ marker.remove(); }
            listaMarkers.clear();

            if(nomeStruttura.isEmpty() && indirizzo.isEmpty() && numeroStelle == 0){
                for(Struttura struttura : elencoStrutture){
                    MarkerOptions markerOptions = mappaController.creaMarkerPerStruttura(struttura);
                    Marker marker = schermataRicercaFragment.getMappa().addMarker(markerOptions);
                    listaMarkers.add(marker);
                }
            } else {
                for(Struttura struttura : elencoStrutture){
                    if(struttura.nomeStruttura.contains(nomeStruttura) && struttura.indirizzo.contains(indirizzo)){
                        if(numeroStelle == 0){
                            MarkerOptions markerOptions = mappaController.creaMarkerPerStruttura(struttura);
                            Marker marker = schermataRicercaFragment.getMappa().addMarker(markerOptions);
                            listaMarkers.add(marker);
                        }
                        else if (numeroStelle != 0 && struttura.numeroStelle == numeroStelle){
                            MarkerOptions markerOptions = mappaController.creaMarkerPerStruttura(struttura);
                            Marker marker = schermataRicercaFragment.getMappa().addMarker(markerOptions);
                            listaMarkers.add(marker);
                        }
                    }
                }
            }
        }
    }

    public void indietroPremuto(){
        schermataRicercaFragment.mainActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, SchermataHomeFragment.newInstance(schermataRicercaFragment.mainActivity))
                .commitNow();
    }

    private void aggiungiMarker(Struttura struttura, List<Marker> listaMarkers){

    }
}
