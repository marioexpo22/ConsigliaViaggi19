package com.example.consigliaviaggi19.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.example.consigliaviaggi19.MainActivity;
import com.example.consigliaviaggi19.R;
import com.example.consigliaviaggi19.controller.MappaController;
import com.example.consigliaviaggi19.controller.RicercaController;
import com.example.consigliaviaggi19.entity.Struttura;
import com.example.consigliaviaggi19.ui.SectionsPagerAdapter;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.tabs.TabLayout;


public class SchermataStrutturaFragment extends Fragment {

    private final Struttura strutturaDaCaricare;
    public MainActivity mainActivity;

    private SectionsPagerAdapter sectionsPagerAdapter;

    public static SchermataStrutturaFragment newInstance(MainActivity mainActivity, Struttura strutturaDaCaricare)
        { return new SchermataStrutturaFragment(mainActivity, strutturaDaCaricare); }

    public SchermataStrutturaFragment(MainActivity mainActivity, Struttura strutturaDaCaricare) {
        this.mainActivity = mainActivity;
        this.strutturaDaCaricare = strutturaDaCaricare;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        sectionsPagerAdapter = new SectionsPagerAdapter(this, mainActivity.getSupportFragmentManager(), strutturaDaCaricare, mainActivity);
        return inflater.inflate(R.layout.struttura_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ViewPager viewPager = getActivity().findViewById(R.id.viewPager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout sezioniStruttura = getActivity().findViewById(R.id.sezioniStruttura);
        sezioniStruttura.setupWithViewPager(viewPager);
        ImageButton ricercaStrutturaButton = getActivity().findViewById(R.id.ricercaStrutturaButton);
        ricercaStrutturaButton.setOnClickListener( (indietro) -> ricercaStrutturaButtonPremuto() );
    }

    public void ricercaStrutturaButtonPremuto(){
        mainActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, SchermataRicercaFragment.newInstance(mainActivity))
                .commitNow();
    }
}
