package com.example.consigliaviaggi19.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.consigliaviaggi19.MainActivity;
import com.example.consigliaviaggi19.R;
import com.example.consigliaviaggi19.controller.AccediController;
import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedReader;

public class SchermataAccediFragment extends Fragment {

    public MainActivity mainActivity;
    public AccediController accediController;

    public TextInputEditText usernameTextField;
    public EditText passwordTextField;
    public Button bottoneEffettuaAccesso;
    public Button bottoneAnnullaAccesso;

    public static SchermataAccediFragment newInstance(MainActivity mainActivity){ return new SchermataAccediFragment(mainActivity); }

    private SchermataAccediFragment (MainActivity mainActivity){ this.mainActivity = mainActivity; }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.accedi_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        usernameTextField = getActivity().findViewById(R.id.usernameTextField);
        passwordTextField = getActivity().findViewById(R.id.passwordTextField);
        bottoneEffettuaAccesso = getActivity().findViewById(R.id.bottoneEffettuaAccesso);
        bottoneAnnullaAccesso = getActivity().findViewById(R.id.bottoneAnnullaAccesso);

        accediController = new AccediController(this);
        accediController.impostaBottoniSchermataAccedi();


    }
}
