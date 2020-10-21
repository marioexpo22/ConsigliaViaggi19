package com.example.consigliaviaggi19.ui;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.example.consigliaviaggi19.MainActivity;
import com.example.consigliaviaggi19.entity.Struttura;
import com.example.consigliaviaggi19.fragment.InformazioniStrutturaFragment;
import com.example.consigliaviaggi19.fragment.PosizioneStrutturaFragment;
import com.example.consigliaviaggi19.fragment.RecensioniStrutturaFragment;
import com.example.consigliaviaggi19.fragment.SchermataStrutturaFragment;

public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    private Context mContext;
    private Struttura strutturaDaCaricare;
    public MainActivity mainActivity;

    public SectionsPagerAdapter(Context schermataStrutturaFragment, @NonNull FragmentManager fm, Struttura strutturaDaCaricare, MainActivity mainActivity) {
        super(fm);
        mContext = schermataStrutturaFragment;
        this.strutturaDaCaricare = strutturaDaCaricare;
        this.mainActivity = mainActivity;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new InformazioniStrutturaFragment(strutturaDaCaricare);
                break;
            case 1:
                fragment = new PosizioneStrutturaFragment(strutturaDaCaricare);
                break;
            case 2:
                fragment = new RecensioniStrutturaFragment(strutturaDaCaricare, mainActivity);
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Informazioni";
            case 1:
                return "Posizione";
            case 2:
                return "Recensioni";
        }
        return null;
    }

}
