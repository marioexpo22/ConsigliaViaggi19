package com.example.consigliaviaggi19.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.example.consigliaviaggi19.DAO.DAOFactory;
import com.example.consigliaviaggi19.DAO.RecensioniDAO;
import com.example.consigliaviaggi19.R;
import com.example.consigliaviaggi19.entity.Recensione;
import com.example.consigliaviaggi19.entity.Struttura;
import com.example.consigliaviaggi19.entity.Utente;
import com.example.consigliaviaggi19.fragment.RecensisciStrutturaFragment;
import com.example.consigliaviaggi19.fragment.SchermataStrutturaFragment;

import java.nio.channels.AsynchronousChannelGroup;

public class RecensisciController {

    private RecensisciStrutturaFragment recensisciStrutturaFragment;
    private Struttura struttura;
    private RecensioniDAO recensioniDAO;


    public RecensisciController(RecensisciStrutturaFragment recensisciStrutturaFragment, Struttura struttura){
        this.recensisciStrutturaFragment = recensisciStrutturaFragment;
        this.struttura = struttura;
    }

    public void impostaBottoniSchermataRecensisci(){
        recensisciStrutturaFragment.pubblicaRecensioneButton.setOnClickListener( (pubblicaLaRecensione) -> pubblicaRecensioneButton() );
        recensisciStrutturaFragment.annullaPubblicaRecensioneButton.setOnClickListener( (annullaLaPubblicazioneDellaRecensione) -> annullaPubblicaRecensioneButton() );

        recensisciStrutturaFragment.nomeCognome.setOnCheckedChangeListener((compoundButton, b) -> {
            if(recensisciStrutturaFragment.nickname.isChecked()) { recensisciStrutturaFragment.nickname.setChecked(false); }});

        recensisciStrutturaFragment.nickname.setOnCheckedChangeListener((compoundButton, b) -> {
            if(recensisciStrutturaFragment.nomeCognome.isChecked()) { recensisciStrutturaFragment.nomeCognome.setChecked(false); }});
    }

    private void pubblicaRecensioneButton(){
        int contatoreErrori = 0;
        Utente utente = Utente.getInstance();

        String titoloRecensioneTextField = null;
        String contenutoRecensione = null;
        String nomeCognomeNickname = null;

        float valutazioneLuogoPerRecensione = recensisciStrutturaFragment.valutazioneLuogoPerRecensione.getRating();

        if (recensisciStrutturaFragment.titoloRecensioneTextField.getText() != null && !recensisciStrutturaFragment.titoloRecensioneTextField.getText().toString().isEmpty()){
            titoloRecensioneTextField = recensisciStrutturaFragment.titoloRecensioneTextField.getText().toString();
        } else { contatoreErrori = contatoreErrori +1; }

        if (recensisciStrutturaFragment.contenutoRecensione.getText() != null && !recensisciStrutturaFragment.contenutoRecensione.getText().toString().isEmpty()){
            contenutoRecensione = recensisciStrutturaFragment.contenutoRecensione.getText().toString();
        } else { contatoreErrori = contatoreErrori +1; }

        if ( !(recensisciStrutturaFragment.nickname.isChecked() || recensisciStrutturaFragment.nomeCognome.isChecked()) ){
            contatoreErrori = contatoreErrori +1; }

        if (recensisciStrutturaFragment.nomeCognome.isChecked()){ nomeCognomeNickname = utente.getNome() + " " + utente.getCognome(); }
        else { nomeCognomeNickname = utente.getNickname(); }

        recensioniDAO = new DAOFactory(recensisciStrutturaFragment.getContext()).ottieniRecensioniDAO();


        if (contatoreErrori == 0){
            inserisciRecensioneAsync(new Recensione(struttura.idStruttura, utente.getId(),titoloRecensioneTextField,
                    valutazioneLuogoPerRecensione, contenutoRecensione, nomeCognomeNickname, null));
        } else { Toast.makeText(recensisciStrutturaFragment.getActivity(),"Sono presenti campi vuoti", Toast.LENGTH_SHORT).show(); }

    }

    private void inserisciRecensioneAsync(Recensione recensione){

        @SuppressLint("StaticFieldLeak")
        class InserisciRecensione extends AsyncTask<Void,Void,String>{
            private Context context;

            InserisciRecensione(Context context){ this.context = context; }

            @Override
            protected String doInBackground(Void... voids) { return recensioniDAO.aggiungiRecensione(recensione);}

            @Override
            protected void onPostExecute(String result) {
                if (result == null || result.length() == 0){
                    Toast.makeText(recensisciStrutturaFragment.getActivity(),"Errore", Toast.LENGTH_SHORT).show();
                } else{

                    Toast.makeText(recensisciStrutturaFragment.getActivity(),result, Toast.LENGTH_SHORT).show();
                    recensisciStrutturaFragment.mainActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, SchermataStrutturaFragment.newInstance(recensisciStrutturaFragment.mainActivity, struttura))
                            .commitNow();
                }
            }
        }
        InserisciRecensione inserisciRecensione = new InserisciRecensione(recensisciStrutturaFragment.getContext());
        inserisciRecensione.execute();
    }

    private void annullaPubblicaRecensioneButton(){
        recensisciStrutturaFragment.mainActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, SchermataStrutturaFragment.newInstance(recensisciStrutturaFragment.mainActivity, struttura))
                .commitNow();
    }
}
