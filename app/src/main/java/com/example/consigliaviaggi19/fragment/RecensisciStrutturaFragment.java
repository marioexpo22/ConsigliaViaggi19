package com.example.consigliaviaggi19.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.consigliaviaggi19.MainActivity;
import com.example.consigliaviaggi19.R;
import com.example.consigliaviaggi19.controller.RecensisciController;
import com.example.consigliaviaggi19.entity.Struttura;
import com.google.android.material.textfield.TextInputEditText;

public class RecensisciStrutturaFragment extends Fragment {
    public Struttura struttura;
    public MainActivity mainActivity;
    public RecensisciController recensisciController;

    public RatingBar valutazioneLuogoPerRecensione;
    public TextInputEditText titoloRecensioneTextField;
    public EditText contenutoRecensione;
    public Button pubblicaRecensioneButton;
    public Button annullaPubblicaRecensioneButton;

    public static RecensisciStrutturaFragment newInstance(Struttura struttura, MainActivity mainActivity){ return new RecensisciStrutturaFragment(struttura, mainActivity); }

    public RecensisciStrutturaFragment(Struttura struttura, MainActivity mainActivity){
        this.struttura = struttura;
        this.mainActivity = mainActivity;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        recensisciController = new RecensisciController(this);
        return inflater.inflate(R.layout.recensisci_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        valutazioneLuogoPerRecensione = getActivity().findViewById(R.id.valutazioneLuogoPerRecensione);
        titoloRecensioneTextField = getActivity().findViewById(R.id.titoloRecensioneTextField);
        contenutoRecensione = getActivity().findViewById(R.id.contenutoRecensione);
        pubblicaRecensioneButton = getActivity().findViewById(R.id.pubblicaRecensioneButton);
        annullaPubblicaRecensioneButton = getActivity().findViewById(R.id.annullaPubblicaRecensioneButton);
    }
}
