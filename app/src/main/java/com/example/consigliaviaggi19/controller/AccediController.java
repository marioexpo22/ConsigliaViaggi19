package com.example.consigliaviaggi19.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.example.consigliaviaggi19.DAO.DAOFactory;
import com.example.consigliaviaggi19.DAO.UtenteDAO;
import com.example.consigliaviaggi19.R;
import com.example.consigliaviaggi19.entity.Utente;
import com.example.consigliaviaggi19.fragment.SchermataAccediFragment;
import com.example.consigliaviaggi19.fragment.SchermataHomeFragment;

import java.util.ArrayList;

public class AccediController {

    SchermataAccediFragment schermataAccediFragment;
    Utente utente;
    public UtenteDAO utenteDAO = new DAOFactory().ottieniUtenteDAO();

    private String email;
    private String password;

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
        this.email = null;
        this.password = null;
        int contatoreErrori = controlloCampiVuoti();;

        if (contatoreErrori == 0){ ottieniUtenteAsync(email, password); }
        else { Toast.makeText(schermataAccediFragment.getActivity(),"Sono presenti campi vuoti", Toast.LENGTH_SHORT).show(); }
    }

    public void ottieniUtenteAsync(String email, String password){
        utente = Utente.getInstance();

        @SuppressLint("StaticFieldLeak")
        class CaricaUtente extends AsyncTask<Void,Void,String>{
            private Context context;

            CaricaUtente(Context context){ this.context = context; }

            @Override
            protected String doInBackground(Void... voids) {
                return utenteDAO.ottieniUtenteValido(email, password);
            }

            @Override
            protected void onPostExecute(String result) {
                if (result == null || result.length() == 0){
                    Toast.makeText(schermataAccediFragment.getActivity(),"Email o password errati", Toast.LENGTH_SHORT).show();
                } else{ /* GESTIONE RISULTATO */
                    result = result.replace("[{\"id\":\"", "");
                    result = result.replace("\",\"nickname", "");
                    result = result.replace("\",\"nome", "");
                    result = result.replace("\",\"cognome", "");
                    result = result.replace("\",\"email", "");
                    result = result.replace("{\"", "");
                    result = result.replace("[", "");
                    result = result.replace("\"},", "");
                    result = result.replace("\"}", "");
                    result = result.replace("]", "");
                    result = result.replace("\\", "");
                    String[] res = result.split("\":\"");

                    utente.setId(Integer.parseInt(res[0]));
                    utente.setNickname(res[1]);
                    utente.setNome(res[2]);
                    utente.setCognome(res[3]);
                    utente.setEmail(res[4]);

                    Toast.makeText(schermataAccediFragment.getActivity(),"Accesso effettuato", Toast.LENGTH_SHORT).show();
                    schermataAccediFragment.mainActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, SchermataHomeFragment.newInstance(schermataAccediFragment.mainActivity))
                            .commitNow();
                }
            }
        }
        CaricaUtente caricaUtente = new CaricaUtente(schermataAccediFragment.getContext());
        caricaUtente.execute();

    }

    public int controlloCampiVuoti(){

        int contatoreErrori = 0;

        if(schermataAccediFragment.usernameTextField.getText() != null && !schermataAccediFragment.usernameTextField.getText().toString().isEmpty()){
            email = schermataAccediFragment.usernameTextField.getText().toString();
        } else { contatoreErrori = contatoreErrori +1; }

        if(schermataAccediFragment.passwordTextField.getText() != null && !schermataAccediFragment.passwordTextField.getText().toString().isEmpty()){
            password = schermataAccediFragment.passwordTextField.getText().toString();
        } else { contatoreErrori = contatoreErrori +1; }

        return contatoreErrori;
    }

}
