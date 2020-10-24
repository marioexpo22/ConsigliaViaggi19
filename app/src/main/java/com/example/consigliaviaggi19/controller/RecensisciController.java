package com.example.consigliaviaggi19.controller;

import android.widget.Toast;
import com.example.consigliaviaggi19.R;
import com.example.consigliaviaggi19.entity.Struttura;
import com.example.consigliaviaggi19.fragment.RecensisciStrutturaFragment;
import com.example.consigliaviaggi19.fragment.SchermataStrutturaFragment;

public class RecensisciController {

    private RecensisciStrutturaFragment recensisciStrutturaFragment;
    private Struttura struttura;


    public RecensisciController(RecensisciStrutturaFragment recensisciStrutturaFragment, Struttura struttura){
        this.recensisciStrutturaFragment = recensisciStrutturaFragment;
        this.struttura = struttura;
    }

    public void impostaBottoniSchermataRecensisci(){
        recensisciStrutturaFragment.pubblicaRecensioneButton.setOnClickListener( (pubblicaLaRecensione) -> pubblicaRecensioneButton() );
        recensisciStrutturaFragment.annullaPubblicaRecensioneButton.setOnClickListener( (annullaLaPubblicazioneDellaRecensione) -> annullaPubblicaRecensioneButton() );
    }

    private void pubblicaRecensioneButton(){
        int contatoreErrori = 0;
        float valutazioneLuogoPerRecensione = recensisciStrutturaFragment.valutazioneLuogoPerRecensione.getRating();

        if (recensisciStrutturaFragment.titoloRecensioneTextField.getText() != null && !recensisciStrutturaFragment.titoloRecensioneTextField.getText().toString().isEmpty()){
            String titoloRecensioneTextField = recensisciStrutturaFragment.titoloRecensioneTextField.getText().toString();
        } else { contatoreErrori = contatoreErrori +1; }

        if (recensisciStrutturaFragment.contenutoRecensione.getText() != null && !recensisciStrutturaFragment.contenutoRecensione.getText().toString().isEmpty()){
            String titoloRecensioneTextField = recensisciStrutturaFragment.contenutoRecensione.getText().toString();
        } else { contatoreErrori = contatoreErrori +1; }

        System.out.println("errori:" + contatoreErrori);

        if (contatoreErrori == 0){

            /**INSERT QUERY**/

            Toast.makeText(recensisciStrutturaFragment.getActivity(),"Recensione pubblicata con successo", Toast.LENGTH_SHORT).show();
            recensisciStrutturaFragment.mainActivity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SchermataStrutturaFragment.newInstance(recensisciStrutturaFragment.mainActivity, struttura))
                    .commitNow();
        } else { Toast.makeText(recensisciStrutturaFragment.getActivity(),"Sono presenti campi vuoti", Toast.LENGTH_SHORT).show(); }





        }

    private void annullaPubblicaRecensioneButton(){

    }

}
