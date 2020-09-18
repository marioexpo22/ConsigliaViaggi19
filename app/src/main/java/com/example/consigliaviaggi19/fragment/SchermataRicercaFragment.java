package com.example.consigliaviaggi19.fragment;


import android.Manifest;
import android.app.AlertDialog;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.consigliaviaggi19.MainActivity;
import com.example.consigliaviaggi19.R;
import com.example.consigliaviaggi19.controller.MappaController;
import com.example.consigliaviaggi19.controller.RicercaController;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.textfield.TextInputEditText;
import android.content.Intent;

import java.util.Objects;

public class SchermataRicercaFragment extends Fragment implements OnMapReadyCallback {

    private RicercaController ricercaController;
    private MappaController mappaController;
    private GoogleMap mappa;

    /** ELEMENTI GRAFICI **/
    public ImageButton cercaButton;
    public ImageButton indietroButton;
    public TextInputEditText nomeStrutturaTextField;
    public TextInputEditText luogoTextField;
    public RatingBar ratingBar;
    public TextView distanzaProgresso;
    public SeekBar seekBar;
    public ImageButton miaPosizioneButton;

    //EXTRA
    public View previewStruttura;
    public ImageView immagineStrutturaPreview;
    public TextView nomeStrutturaPreview;
    public TextView tipoStrutturaPreview;
    public RatingBar valutazioneLuoghiPreview;
    public Button visualizzaStrutturaPreviewButton;
    public ImageButton chiudiPreviewButton;

    private static final int PERMISSION_REQUEST_CODE = 1;

    private FusedLocationProviderClient fusedLocationClient;

    public MainActivity mainActivity;

    /** COSTRUTTORE DELLA CLASSE */

    public static SchermataRicercaFragment newInstance(MainActivity mainActivity) { return new SchermataRicercaFragment(mainActivity); }

    private SchermataRicercaFragment(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ricercaController = new RicercaController(this);
        mappaController = new MappaController(this);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(Objects.requireNonNull(this.getContext()));

        return inflater.inflate(R.layout.ricerca_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mappaGoogle);
        mapFragment.getMapAsync(this);
        /** BOTTONI RICERCA **/
        cercaButton = getActivity().findViewById(R.id.bottoneCerca);
        indietroButton = getActivity().findViewById(R.id.bottoneHome);
        nomeStrutturaTextField = getActivity().findViewById(R.id.strutturaIn);
        luogoTextField = getActivity().findViewById(R.id.luogoIn);
        ratingBar = getActivity().findViewById(R.id.valutazioneLuoghi);
        distanzaProgresso = getActivity().findViewById(R.id.distanzaProgresso);
        seekBar = getActivity().findViewById(R.id.distanza);

        previewStruttura = getActivity().findViewById(R.id.previewStruttura);
        immagineStrutturaPreview = getActivity().findViewById(R.id.immagineStrutturaPreview);
        nomeStrutturaPreview = getActivity().findViewById(R.id.nomeStrutturaPreview);
        tipoStrutturaPreview = getActivity().findViewById(R.id.tipoStrutturaPreview);
        valutazioneLuoghiPreview = getActivity().findViewById(R.id.valutazioneLuoghiPreview);
        valutazioneLuoghiPreview.setIsIndicator(true);
        visualizzaStrutturaPreviewButton = getActivity().findViewById(R.id.visualizzaStrutturaPreviewButton);
        chiudiPreviewButton = getActivity().findViewById(R.id.chiudiPreviewButton);

        /** BOTTONI MAPPA **/
        miaPosizioneButton = getActivity().findViewById(R.id.miaPosizione);

        mappaController.impostaBottoni();
        ricercaController.impostaBottoni();
        ricercaController.impostaSeekBar();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        setMappa(googleMap);

        mappaController.caricaStruttureSuMappa();
        googleMap.setOnMarkerClickListener(mappaController);
    }

    public void mostraPopUpRichiestaPermessi(){
        ActivityCompat.requestPermissions(getActivity()
                , new String[]{Manifest.permission.ACCESS_FINE_LOCATION}
                , PERMISSION_REQUEST_CODE);
    }

    public void mostraPopUpRichiestaAttivazioneGPS(){
        AlertDialog.Builder richiestaAttivazioneGPS = new AlertDialog.Builder(this.getContext());

        richiestaAttivazioneGPS.setTitle(R.string.popup_permessiGPS_titolo);
        richiestaAttivazioneGPS.setMessage("GPS disabilitato, desideri attivarlo?");
        richiestaAttivazioneGPS.setPositiveButton(R.string.popup_permessiGPS_messaggio, (dialogInterface, i) ->  {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent); });
        richiestaAttivazioneGPS.setNegativeButton(R.string.bottone_annulla,((dialogInterface, i2) -> dialogInterface.dismiss()));

        AlertDialog alert = richiestaAttivazioneGPS.create();
        alert.show();
    }

    public GoogleMap getMappa() {
        return mappa;
    }
    public void setMappa(GoogleMap mappa) {
        this.mappa = mappa;
    }
    public MappaController getMappaController(){ return mappaController; }
    public FusedLocationProviderClient getFusedLocationClient() { return fusedLocationClient; }

}