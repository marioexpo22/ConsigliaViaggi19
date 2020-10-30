package com.example.consigliaviaggi19.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.consigliaviaggi19.R;
import com.example.consigliaviaggi19.entity.Struttura;
import com.example.consigliaviaggi19.fragment.SchermataHomeFragment;
import com.example.consigliaviaggi19.fragment.SchermataStrutturaFragment;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    ArrayList<MainModel> mainModels;
    Context context;
    RecyclerView recyclerView;
    private SchermataHomeFragment schermataHomeFragment;
    private static Struttura[] elencoStrutture;

    private View.OnClickListener myOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int position = recyclerView.getChildLayoutPosition(view);
            String item = mainModels.get(position).getStructName();


            for(Struttura struttura : elencoStrutture){
                if(struttura.nomeStruttura.equals(item))
                    schermataHomeFragment.mainActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, SchermataStrutturaFragment.newInstance(schermataHomeFragment.mainActivity, struttura))
                            .commitNow();
            }
        }
    };

    public MainAdapter(Context ctx, ArrayList<MainModel> models, RecyclerView recyclerView, SchermataHomeFragment schermataHomeFragment, Struttura[] strutture){
        context = ctx;
        mainModels = models;
        this.recyclerView = recyclerView;
        this.schermataHomeFragment = schermataHomeFragment;
        elencoStrutture = strutture;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Create View
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        view.setOnClickListener(myOnClickListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        //Set Logo to ImageView
        LoadImage loadImage = new LoadImage(holder.imageView);
        loadImage.execute(mainModels.get(position).structLogo);
        //Set Name to TextView
        holder.textView.setText(mainModels.get(position).getStructName());
    }

    @Override
    public int getItemCount() {
        return mainModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //Initialize variable ImageView e TextView of row_item
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Assign variable of row_item
            imageView = itemView.findViewById(R.id.imageView1);
            textView = itemView.findViewById(R.id.textView1);
        }
    }

    private class LoadImage extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;
        public LoadImage(ImageView imageView){
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
            imageView.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 300, 300, false));
        }
    }
}
