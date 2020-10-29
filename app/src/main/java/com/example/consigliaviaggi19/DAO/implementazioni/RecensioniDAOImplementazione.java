package com.example.consigliaviaggi19.DAO.implementazioni;

import com.example.consigliaviaggi19.DAO.RecensioniDAO;
import com.example.consigliaviaggi19.entity.LinkConnessioneServer;
import com.example.consigliaviaggi19.entity.Recensione;
import com.example.consigliaviaggi19.entity.Struttura;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class RecensioniDAOImplementazione implements RecensioniDAO {

    @Override
    public String ottieniRecensioniStruttura(Struttura struttura, Float rating, String data) {

        String link = LinkConnessioneServer.getInstance().getLink() + "filtroRecensioni.php";

        try{
            URL url = new URL(link);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            if(httpURLConnection == null){ return null; }

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            String post_data = URLEncoder.encode("idStruttura", "UTF-8")+"="
                    +URLEncoder.encode(Integer.toString(struttura.idStruttura), "UTF-8")
                    +"&"+URLEncoder.encode("rating", "UTF-8")+"="
                    +URLEncoder.encode(Float.toString(rating),"UTF-8")
                    +"&"+URLEncoder.encode("dateval", "UTF-8")+"="
                    +URLEncoder.encode(data, "UTF-8");

            System.out.println(post_data);

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();

            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String result="";
            String line;

            while((line = bufferedReader.readLine())!= null) { result += line; }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            System.out.println("RISULTATO == " + result);
            return result;
        } catch (IOException e) { e.printStackTrace(); }

        return null;
    }

    @Override
    public String aggiungiRecensione(Recensione recensione) {

        String link = LinkConnessioneServer.getInstance().getLink() + "caricaRecensione.php";

        try{
            URL url = new URL(link);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            if(httpURLConnection == null){ return null; }

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            String post_data = URLEncoder.encode("titolo", "UTF-8")+"="
                    +URLEncoder.encode(recensione.getTitolo(), "UTF-8")
                    +"&"+URLEncoder.encode("descrizione", "UTF-8")+"="
                    +URLEncoder.encode(recensione.getTesto(),"UTF-8")
                    +"&"+URLEncoder.encode("rec-rating", "UTF-8")+"="
                    +URLEncoder.encode(Float.toString(recensione.getNumeroStelle()), "UTF-8")
                    +"&"+URLEncoder.encode("nome-utente", "UTF-8")+"="
                    +URLEncoder.encode(recensione.getNomeNicknameRecensore(), "UTF-8")
                    +"&"+URLEncoder.encode("idStruttura", "UTF-8")+"="
                    +URLEncoder.encode(Integer.toString(recensione.getIdStruttura()), "UTF-8")
                    +"&"+URLEncoder.encode("idUtente", "UTF-8")+"="
                    +URLEncoder.encode(Integer.toString(recensione.getIdUtente()), "UTF-8")
                    +"&"+URLEncoder.encode("bottone-form", "UTF-8")+"="
                    +URLEncoder.encode("Pubblica recensione", "UTF-8");

            System.out.println(post_data);

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();

            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String result="";
            String line;

            while((line = bufferedReader.readLine())!= null) { result += line; }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            System.out.println("RISULTATO == " + result);
            return result;
        } catch (IOException e) { e.printStackTrace(); }

        return null;
    }
}

