package com.example.consigliaviaggi19.fragment;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.example.consigliaviaggi19.MainActivity;
import com.example.consigliaviaggi19.R;
import com.example.consigliaviaggi19.controller.MappaController;
import com.example.consigliaviaggi19.controller.RicercaController;
import com.example.consigliaviaggi19.entity.Struttura;
import com.example.consigliaviaggi19.ui.MainAdapter;
import com.example.consigliaviaggi19.ui.SectionsPagerAdapter;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class SchermataStrutturaFragment extends Fragment {

    private final Struttura strutturaDaCaricare;
    public MainActivity mainActivity;
    public int tornaAllaHome = 0;

    private SectionsPagerAdapter sectionsPagerAdapter;

    public static SchermataStrutturaFragment newInstance(MainActivity mainActivity, Struttura strutturaDaCaricare) {
        return new SchermataStrutturaFragment(mainActivity, strutturaDaCaricare);
    }

    public SchermataStrutturaFragment(MainActivity mainActivity, Struttura strutturaDaCaricare) {
        this.mainActivity = mainActivity;
        this.strutturaDaCaricare = strutturaDaCaricare;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        sectionsPagerAdapter = new SectionsPagerAdapter(this, mainActivity.getSupportFragmentManager(), strutturaDaCaricare, mainActivity);
        return inflater.inflate(R.layout.struttura_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ViewPager viewPager = getActivity().findViewById(R.id.viewPager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout sezioniStruttura = getActivity().findViewById(R.id.sezioniStruttura);
        sezioniStruttura.setupWithViewPager(viewPager);
        ImageButton ricercaStrutturaButton = getActivity().findViewById(R.id.ricercaStrutturaButton);
        ricercaStrutturaButton.setOnClickListener((indietro) -> ricercaStrutturaButtonPremuto());

        ImageView immagineStruttura = getActivity().findViewById(R.id.immagineStruttura);

        LoadImage loadImage = new LoadImage(immagineStruttura);
        loadImage.execute(strutturaDaCaricare.immagineStruttura);

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

    public void ricercaStrutturaButtonPremuto(){

        if(tornaAllaHome == 0){
            mainActivity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SchermataRicercaFragment.newInstance(mainActivity))
                    .commitNow();
        } else if (tornaAllaHome == 1){
            mainActivity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SchermataHomeFragment.newInstance(mainActivity))
                    .commitNow();
        }

    }
}



