package com.example.consigliaviaggi19.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.consigliaviaggi19.R;
import com.example.consigliaviaggi19.entity.Struttura;

public class RecensioniStrutturaFragment extends Fragment {
    private final Struttura struttura;

    public static RecensioniStrutturaFragment newInstance(Struttura struttura){ return new RecensioniStrutturaFragment(struttura); }

    public RecensioniStrutturaFragment(Struttura struttura){ this.struttura = struttura; }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recensioni_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
