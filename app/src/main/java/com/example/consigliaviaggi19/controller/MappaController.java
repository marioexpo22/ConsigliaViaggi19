package com.example.consigliaviaggi19.controller;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.example.consigliaviaggi19.R;
import com.example.consigliaviaggi19.entity.Struttura;
import com.example.consigliaviaggi19.fragment.SchermataRicercaFragment;
import com.example.consigliaviaggi19.fragment.SchermataStrutturaFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.*;

import java.util.*;

public class MappaController extends AppCompatActivity implements GoogleMap.OnMarkerClickListener {

    private SchermataRicercaFragment schermataRicercaFragment;
    private static List<MarkerOptions> listaMarker;
    private static Set<Struttura> elencoStrutture = new HashSet<>();

    private Struttura strutturaCorrente = null;
    private boolean strutturaTrovata = false;

    public MappaController(SchermataRicercaFragment schermataRicercaFragment) {
        this.schermataRicercaFragment = schermataRicercaFragment;
    }

    public void impostaBottoni() {
        schermataRicercaFragment.miaPosizioneButton.setOnClickListener( (trovaPosizione) -> miaPosizioneButtonPremuto());
        schermataRicercaFragment.chiudiPreviewButton.setOnClickListener( (chiudiPreview) -> chiudiPreviewButtonPremuto());
        schermataRicercaFragment.visualizzaStrutturaPreviewButton.setOnClickListener( (visualizzaStruttura) -> visualizzaStrutturaPreviewButtonPremuto());
    }

    public void miaPosizioneButtonPremuto() { abilitaGpsSeCondizioniValide(); }

    @SuppressLint("MissingPermission")
    private void abilitaFunzioniGPS() {
        GoogleMap mappa = schermataRicercaFragment.getMappa();
        if(!controlloPermessiGPS()){ return; }

        mappa.setMyLocationEnabled(true);
        schermataRicercaFragment.getFusedLocationClient()
                .getLastLocation()
                .addOnSuccessListener
                        (location -> {
                            if (location != null){
                                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                                zoomSuPosizione(latLng, 17f, 3000, mappa);
                            }});
    }

    public Boolean controlloPermessiGPS(){
        String permessoGPS = Manifest.permission.ACCESS_FINE_LOCATION;
        int result = ContextCompat.checkSelfPermission
                (Objects.requireNonNull(schermataRicercaFragment.getContext()),permessoGPS);
        /** CONTROLLA SE IL PERMESSO E' STATO DATO O MENO, -1 DENIED 0 GRANTED **/
        return result == PackageManager.PERMISSION_GRANTED;
    }

    public Boolean controlloGpsAttivo(){
        LocationManager gpsAttivo = (LocationManager) schermataRicercaFragment.getContext()
                .getSystemService(Context.LOCATION_SERVICE);
        return gpsAttivo.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    @SuppressLint("MissingPermission")
    public void abilitaGpsSeCondizioniValide(){
        if(!controlloPermessiGPS()){ schermataRicercaFragment.mostraPopUpRichiestaPermessi(); }
        else if (!controlloGpsAttivo()){
            schermataRicercaFragment.getMappa().setMyLocationEnabled(false);
            schermataRicercaFragment.mostraPopUpRichiestaAttivazioneGPS();
        }
        else { abilitaFunzioniGPS(); }
    }

    public void zoomSuPosizione(LatLng latLng, Float zoom, int milliSecondiTransizione, GoogleMap mappa){
        CameraPosition posizioneCamera = CameraPosition.builder().target(latLng).zoom(zoom).build();
        mappa.animateCamera(CameraUpdateFactory.newCameraPosition(posizioneCamera), milliSecondiTransizione, null);
    }

    public void caricaStruttureSuMappa(){

        /** L'OTTENIMENTO DELLE STRUTTURE VA CAMBIATO **/
        /*elencoStrutture.add(new Struttura("Test1","null","null","null","null",40.914124, 14.147411,"Hotel","null",4.0f));
        elencoStrutture.add(new Struttura("Test2","null","null","null","null",40.914556, 14.147611,"Ristorante","null", 4.0f));
        elencoStrutture.add(new Struttura("Test3","null","null","null","null",40.914174, 14.148805,"Attrazione","null", 4.0f));
        elencoStrutture.add(new Struttura("Test4","null","null","null","null",40.914618, 14.148424,"Hotel","null",5.0f));
        elencoStrutture.add(new Struttura("Test5","null","null","null","null",40.913287, 14.147921,"Ristorante","null",3.0f));
        elencoStrutture.add(new Struttura("Test6","null","null","null","null",40.913040, 14.147403,"Attrazione","null",5.0f));
*/
        listaMarker = new ArrayList<>();

        for (Struttura struttura : elencoStrutture){
            MarkerOptions marker = creaMarkerPerStruttura(struttura);
            listaMarker.add(marker);
            schermataRicercaFragment.getMappa().addMarker(marker);
        }

    }

    public MarkerOptions creaMarkerPerStruttura(Struttura struttura){
        MarkerOptions marker = new MarkerOptions();

        BitmapDescriptor iconaHotel = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE);
        BitmapDescriptor iconaRistorante = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW);
        BitmapDescriptor iconaAttrazione = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED);

        marker.position(new LatLng(struttura.latitudine, struttura.longitudine))
                .title(struttura.nomeStruttura)
                .snippet(struttura.tipoStruttura);

        if(struttura.tipoStruttura.equals("Hotel")) { marker.icon(iconaHotel); }
        else if(struttura.tipoStruttura.equals("Ristorante")) { marker.icon(iconaRistorante); }
        else { marker.icon(iconaAttrazione); }

        return marker;
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        LatLng posizione = marker.getPosition(); //ELEMENTO CHIAVE

        schermataRicercaFragment.previewStruttura.setVisibility(View.VISIBLE);
        schermataRicercaFragment.immagineStrutturaPreview.setVisibility(View.VISIBLE);
        schermataRicercaFragment.nomeStrutturaPreview.setVisibility(View.VISIBLE);
        schermataRicercaFragment.tipoStrutturaPreview.setVisibility(View.VISIBLE);
        schermataRicercaFragment.valutazioneLuoghiPreview.setVisibility(View.VISIBLE);
        schermataRicercaFragment.visualizzaStrutturaPreviewButton.setVisibility(View.VISIBLE);
        schermataRicercaFragment.chiudiPreviewButton.setVisibility(View.VISIBLE);

        for (Struttura struttura : elencoStrutture){
            if (struttura.latitudine == posizione.latitude && struttura.longitudine == posizione.longitude){
                this.strutturaTrovata = true;
                strutturaCorrente = struttura;
                break;
            } else { this.strutturaTrovata = false; }
        }
        schermataRicercaFragment.nomeStrutturaPreview.setText(strutturaCorrente.nomeStruttura);
        schermataRicercaFragment.tipoStrutturaPreview.setText(strutturaCorrente.tipoStruttura);
        schermataRicercaFragment.valutazioneLuoghiPreview.setRating(strutturaCorrente.numeroStelle);

        return false;
    }

    public void chiudiPreviewButtonPremuto(){
        schermataRicercaFragment.previewStruttura.setVisibility(View.GONE);
        schermataRicercaFragment.immagineStrutturaPreview.setVisibility(View.GONE);
        schermataRicercaFragment.nomeStrutturaPreview.setVisibility(View.GONE);
        schermataRicercaFragment.tipoStrutturaPreview.setVisibility(View.GONE);
        schermataRicercaFragment.valutazioneLuoghiPreview.setVisibility(View.GONE);
        schermataRicercaFragment.visualizzaStrutturaPreviewButton.setVisibility(View.GONE);
        schermataRicercaFragment.chiudiPreviewButton.setVisibility(View.GONE);
    }

    public void visualizzaStrutturaPreviewButtonPremuto(){
        if (strutturaTrovata){
            schermataRicercaFragment.mainActivity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SchermataStrutturaFragment.newInstance(schermataRicercaFragment.mainActivity, strutturaCorrente))
                    .commitNow();
        }
    }
}
