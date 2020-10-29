package com.example.consigliaviaggi19.controller;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.example.consigliaviaggi19.DAO.DAOFactory;
import com.example.consigliaviaggi19.DAO.StruttureDAO;
import com.example.consigliaviaggi19.R;
import com.example.consigliaviaggi19.entity.Struttura;
import com.example.consigliaviaggi19.fragment.SchermataHomeFragment;
import com.example.consigliaviaggi19.fragment.SchermataRicercaFragment;
import com.example.consigliaviaggi19.fragment.SchermataStrutturaFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class MappaController extends AppCompatActivity implements GoogleMap.OnMarkerClickListener {

    private SchermataRicercaFragment schermataRicercaFragment;
    private static List<Marker> listaMarker;
    private static Set<Struttura> elencoStrutture = new HashSet<>();

    private Struttura strutturaCorrente = null;
    private boolean strutturaTrovata = false;

    private StruttureDAO struttureDAO;

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

    public void caricaStruttureSuMappaAsync(){
        struttureDAO = new DAOFactory(schermataRicercaFragment.getContext()).ottieniStruttureDAO();

        @SuppressLint("StaticFieldLeak")
        class CaricaStrutture extends AsyncTask<Void,Void,String> {
            private Context context;

            CaricaStrutture(Context context){ this.context = context; }

            @Override
            protected String doInBackground(Void... voids) {
                return struttureDAO.ottieniTutteStrutture();
            }

            @Override
            protected void onPostExecute(String result) {
                if (result == null || result.length() == 0){
                    System.out.println("NESSUNA STRUTTURA");
                } else{
                    result = result.replace("[{\"id\":\"", "");
                    result = result.replace("id", "");
                    result = result.replace("\",\"nomeStruttura", "");
                    result = result.replace("\",\"descrizione", "");
                    result = result.replace("\",\"emailStruttura", "");
                    result = result.replace("\",\"numeroDiTelefono", "");
                    result = result.replace("\",\"indirizzo", "");
                    result = result.replace("\",\"immagineStruttura", "");
                    result = result.replace("\",\"latitudine", "");
                    result = result.replace("\",\"longitudine", "");
                    result = result.replace("\",\"tipoStruttura", "");
                    result = result.replace("\",\"sitoWeb", "");
                    result = result.replace("\",\"stelle", "");
                    result = result.replace("{\"", "");
                    result = result.replace("[", "");
                    result = result.replace("\"},", "");
                    result = result.replace("\"}", "");
                    result = result.replace("]", "");
                    result = result.replace("\\", "");
                    String[] res = result.split("\":\"");

                    int i = 0; int id; float stelle; double latitudine, longitudine;
                    while (i < res.length) {
                        id = Integer.parseInt(res[i]);
                        stelle = Float.parseFloat(res[i+11]);
                        latitudine = Double.parseDouble(res[i + 7]);
                        longitudine = Double.parseDouble(res[i + 8]);
                        elencoStrutture.add(new Struttura(id, res[i+1], res[i+2], res[i+3],
                                res[i+4], res[i+5], res[i+6], latitudine, longitudine,
                                res[i+9], res[i+10], stelle));
                        i += 12;
                    }

                    listaMarker = new ArrayList<>();

                    for (Struttura struttura : elencoStrutture){
                        MarkerOptions markerOptions = creaMarkerPerStruttura(struttura);
                        Marker marker = schermataRicercaFragment.getMappa().addMarker(markerOptions);
                        listaMarker.add(marker);
                    }
                }
            }
        }
        CaricaStrutture caricaStrutture = new CaricaStrutture(schermataRicercaFragment.getContext());
        caricaStrutture.execute();
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
        LoadImage loadImage = new LoadImage(schermataRicercaFragment.immagineStrutturaPreview);
        loadImage.execute(strutturaCorrente.immagineStruttura);

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

    private class LoadImage extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public LoadImage(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String url = strings[0];
            Bitmap bitmap = null;
            try {
                InputStream inputStream = new URL(url).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    }

    public Set<Struttura> getElencoStrutture() { return elencoStrutture; }
    public List<Marker> getListaMarker() { return listaMarker; }
}
