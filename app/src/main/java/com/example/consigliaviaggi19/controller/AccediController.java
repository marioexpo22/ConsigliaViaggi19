package com.example.consigliaviaggi19.controller;

import com.example.consigliaviaggi19.R;
import com.example.consigliaviaggi19.entity.Utente;
import com.example.consigliaviaggi19.fragment.SchermataAccediFragment;
import com.example.consigliaviaggi19.fragment.SchermataHomeFragment;

public class AccediController {

    SchermataAccediFragment schermataAccediFragment;
    Utente utente;

    public AccediController(SchermataAccediFragment schermataAccediFragment){ this.schermataAccediFragment = schermataAccediFragment; }

    public void impostaBottoniSchermataAccedi(){
        schermataAccediFragment.bottoneAnnullaAccesso.setOnClickListener( (spostatiSuSchermataHomeDaAccedi) -> bottoneAnnullaAccessoPremuto());
        schermataAccediFragment.bottoneEffettuaAccesso.setOnClickListener( (SpostatiSuSchermataHomeDaAccessoConAccesso) -> bottoneEffettuaAccessoPremuto());
    }

    public void bottoneAnnullaAccessoPremuto(){
        schermataAccediFragment.mainActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, SchermataHomeFragment.newInstance(schermataAccediFragment.mainActivity))
                .commitNow();
    }

    public void bottoneEffettuaAccessoPremuto(){
        utente = Utente.getInstance();
        String username;
        String password;

        username = schermataAccediFragment.usernameTextField.getText().toString();
        password = schermataAccediFragment.passwordTextField.getText().toString();

        /** GESTIRE RETRIEVE DATI **/

        /** UNA VOLTA PRESI, IMPOSTA TUTTI I DATI NELLA CLASSE UTENTE **/

        utente.setNickname("Ciro97");
        utente.setNome("Ciro");
        utente.setCognome("Esposito");
        utente.setEmail("ciromail@hotmail.it");

        schermataAccediFragment.mainActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, SchermataHomeFragment.newInstance(schermataAccediFragment.mainActivity))
                .commitNow();
    }

}
