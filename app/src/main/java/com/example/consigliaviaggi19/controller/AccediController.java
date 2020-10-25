package com.example.consigliaviaggi19.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import com.example.consigliaviaggi19.DAO.DAOFactory;
import com.example.consigliaviaggi19.DAO.UtenteDAO;
import com.example.consigliaviaggi19.R;
import com.example.consigliaviaggi19.entity.Utente;
import com.example.consigliaviaggi19.fragment.SchermataAccediFragment;
import com.example.consigliaviaggi19.fragment.SchermataHomeFragment;

public class AccediController {

    SchermataAccediFragment schermataAccediFragment;
    Utente utente;
    private UtenteDAO utenteDAO;

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
        String email;
        String password;

        email = schermataAccediFragment.usernameTextField.getText().toString();
        password = schermataAccediFragment.passwordTextField.getText().toString();

        utenteDAO = new DAOFactory(schermataAccediFragment.getContext()).ottieniUtenteDAO();
        ottieniUtenteAsync(email, password);

        schermataAccediFragment.mainActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, SchermataHomeFragment.newInstance(schermataAccediFragment.mainActivity))
                .commitNow();
    }

    private void ottieniUtenteAsync(String email, String password){
        utente = Utente.getInstance();

        @SuppressLint("StaticFieldLeak")
        class CaricaUtente extends AsyncTask<String,Void,String>{

            private Context context;

            CaricaUtente(Context context){ this.context = context; }

            @Override
            protected String doInBackground(String... strings) {
                return utenteDAO.ottieniUtenteValido(email, password);
            }

            @Override
            protected void onPostExecute(String s) {

            }
        }

        CaricaUtente caricaUtente = new CaricaUtente(schermataAccediFragment.getContext());
        caricaUtente.execute();

        utente.setNickname("Ciro97");
        utente.setNome("Ciro");
        utente.setCognome("Esposito");
        utente.setEmail("ciromail@hotmail.it");
    }

}
