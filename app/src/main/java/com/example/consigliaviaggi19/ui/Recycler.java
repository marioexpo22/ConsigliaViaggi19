package com.example.consigliaviaggi19.ui;

import android.content.Context;
import android.os.AsyncTask;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.consigliaviaggi19.controller.HomeController;
import com.example.consigliaviaggi19.entity.Struttura;
import com.example.consigliaviaggi19.fragment.SchermataHomeFragment;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Recycler extends AsyncTask<String,Void,String> {

    Context context;
    RecyclerView recyclerView;
    HomeController homeController;

    public Recycler(Context ctx, RecyclerView recyclerView, HomeController homeController){
        context = ctx;
        this.recyclerView = recyclerView;
        this.homeController = homeController;
    }

    @Override
    protected String doInBackground(String... strings) {
        String type = strings[0];
        String link = "http://93.151.249.125/phpCV19/php/homeConsigliati.php";

        try{
            URL url = new URL(link);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            if(httpURLConnection == null){ return null; }

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("tipoStruttura", "UTF-8")+"="+URLEncoder.encode(type, "UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String result="";
            String line;
            while((line = bufferedReader.readLine())!= null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute(){
    }

    @Override
    protected void onPostExecute(String result) {
        if(result == null){
            return;
        }
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
        result = result.replace("{\"", "");
        result = result.replace("[", "");
        result = result.replace("\"},", "");
        result = result.replace("\"}", "");
        result = result.replace("]", "");
        result = result.replace("\\", "");
        String[] res = result.split("\":\"");

        Struttura[] strutture = new Struttura[res.length/12];

        ArrayList<MainModel> mainModels;
        MainAdapter mainAdapter;

        int j = 0;
        int i = 0;
        int id;
        float stelle;
        double latitudine, longitudine;
        while (i < res.length) {
            id = Integer.parseInt(res[i]);
            stelle = Float.parseFloat(res[i+11]);
            latitudine = Double.parseDouble(res[i + 7]);
            longitudine = Double.parseDouble(res[i + 8]);
            strutture[j] = new Struttura(id, res[i+1], res[i+2], res[i+3],
                    res[i+4], res[i+5], res[i+6], latitudine, longitudine,
                    res[i+9], res[i+10], stelle);
            j++;
            i += 12;
        }

        //Inizializziamo array
        mainModels = new ArrayList<>();
        for(i = 0; i < res.length/12; i++){
            MainModel model = new MainModel(strutture[i]);
            mainModels.add(model);
        }

        //Design horizontal layout
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //Initialize MainAdapter
        mainAdapter = new MainAdapter(context, mainModels);
        //Set MainAdapter to RecyclerView
        recyclerView.setAdapter(mainAdapter);
    }



}
