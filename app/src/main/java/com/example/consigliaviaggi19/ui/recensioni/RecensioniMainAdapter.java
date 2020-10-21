package com.example.consigliaviaggi19.ui.recensioni;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.consigliaviaggi19.MainActivity;
import com.example.consigliaviaggi19.R;
import com.example.consigliaviaggi19.entity.Recensione;

import java.util.ArrayList;

public class RecensioniMainAdapter extends RecyclerView.Adapter<RecensioniMainAdapter.ViewHolder> {

    private MainActivity mainActivity;
    private ArrayList<Recensione> listaRecensioni;

    public RecensioniMainAdapter(MainActivity mainActivity, ArrayList<Recensione> listaRecensioni) {
        this.mainActivity = mainActivity;
        this.listaRecensioni = listaRecensioni;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Create View
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recensioni_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titoloDellaRecensione.setText(listaRecensioni.get(position).getTitolo());
        holder.valutazioneRecensione.setRating(listaRecensioni.get(position).getNumeroStelle());
        holder.testoRecensione.setText(listaRecensioni.get(position).getTesto());
        holder.nomeNicknameRecensore.setText(listaRecensioni.get(position).getNomeNicknameRecensore());
        holder.dataRecensione.setText(listaRecensioni.get(position).getData());
    }

    @Override
    public int getItemCount() {
        return listaRecensioni.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //Initialize variable ImageView e TextView of row_item
        private final TextView titoloDellaRecensione;
        private final RatingBar valutazioneRecensione;
        private final TextView testoRecensione;
        private final TextView nomeNicknameRecensore;
        private final TextView dataRecensione;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Assign variable of recensioni_item
            titoloDellaRecensione = itemView.findViewById(R.id.titoloDellaRecensione);
            valutazioneRecensione = itemView.findViewById(R.id.valutazioneRecensione);
            testoRecensione = itemView.findViewById(R.id.testoRecensione);
            nomeNicknameRecensore = itemView.findViewById(R.id.nomeNicknameRecensore);
            dataRecensione = itemView.findViewById(R.id.dataRecensione);
        }
    }

}


