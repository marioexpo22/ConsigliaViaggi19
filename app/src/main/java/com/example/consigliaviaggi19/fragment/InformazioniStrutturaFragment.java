package com.example.consigliaviaggi19.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.consigliaviaggi19.R;
import com.example.consigliaviaggi19.entity.Struttura;

public class InformazioniStrutturaFragment extends Fragment {
    private final Struttura struttura;

    private TextView nomeStrutturaInformazioni;
    private RatingBar valutazioneStrutturaInformazioni;
    private TextView descrizioneStrutturaInformazioni;


    //public static InformazioniStrutturaFragment newInstance(Struttura struttura){ return new InformazioniStrutturaFragment(struttura); }

    public InformazioniStrutturaFragment(Struttura struttura){ this.struttura = struttura; }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.informazioni_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nomeStrutturaInformazioni = getActivity().findViewById(R.id.nomeStrutturaInformazioni);
        valutazioneStrutturaInformazioni = getActivity().findViewById(R.id.valutazioneStrutturaInformazioni);
        descrizioneStrutturaInformazioni = getActivity().findViewById(R.id.descrizioneStrutturaInformazioni);
        caricaPaginaInformazioni();
    }

    public void caricaPaginaInformazioni(){
        nomeStrutturaInformazioni.setText(struttura.nomeStruttura);
        System.out.println(struttura.numeroStelle);
        valutazioneStrutturaInformazioni.setRating(struttura.numeroStelle);
        valutazioneStrutturaInformazioni.setIsIndicator(true);
        descrizioneStrutturaInformazioni.setText(struttura.descrizione);
    }
}
