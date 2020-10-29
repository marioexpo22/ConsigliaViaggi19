package com.example.consigliaviaggi19.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.consigliaviaggi19.MainActivity;
import com.example.consigliaviaggi19.R;
import com.example.consigliaviaggi19.controller.RecensioniController;
import com.example.consigliaviaggi19.entity.Struttura;
import com.example.consigliaviaggi19.ui.recensioni.RecensioniRecycler;

public class RecensioniStrutturaFragment extends Fragment {
    public final Struttura struttura;
    public RecensioniController recensioniController;
    public RecyclerView RecensioniRecyclerView;
    public MainActivity mainActivity;
    public SchermataStrutturaFragment schermataStrutturaFragment;

    public RatingBar filtroStelle;
    public Spinner filtroData;

    public Button scriviUnaRecensioneButton;

    public static RecensioniStrutturaFragment newInstance(Struttura struttura, MainActivity mainActivity, SchermataStrutturaFragment schermataStrutturaFragment)
    {
        return new RecensioniStrutturaFragment(struttura, mainActivity, schermataStrutturaFragment);
    }

    public RecensioniStrutturaFragment(Struttura struttura, MainActivity mainActivity, SchermataStrutturaFragment schermataStrutturaFragment){
        this.struttura = struttura;
        this.mainActivity = mainActivity;
        this.schermataStrutturaFragment = schermataStrutturaFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        recensioniController = new RecensioniController(this);
        return inflater.inflate(R.layout.recensioni_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecensioniRecyclerView = getActivity().findViewById(R.id.recyclerViewRecensioni);
        filtroStelle = getActivity().findViewById(R.id.filtroValutazioneLuoghi);
        filtroData = getActivity().findViewById(R.id.spinner);
        scriviUnaRecensioneButton = getActivity().findViewById(R.id.scriviUnaRecensioneButton);

        recensioniController.creaRecensioniRecyclerView(RecensioniRecyclerView, struttura, mainActivity);

        recensioniController.impostaBottoniSchermataRecensioni();

    }
}
