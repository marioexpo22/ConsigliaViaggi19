package com.example.consigliaviaggi19.DAO.implementazioni;

import com.example.consigliaviaggi19.DAO.StruttureDAO;
import com.example.consigliaviaggi19.entity.LinkConnessioneServer;
import com.example.consigliaviaggi19.entity.Struttura;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public class StruttureDAOImplementazione implements StruttureDAO {

    @Override
    public String ottieniStruttureCheContengonoString(String nomeStruttura) {
        return null;
    }

    @Override
    public String ottieniTutteStrutture() {

        String link = LinkConnessioneServer.getInstance().getLink() + "strutture.php";

        try{
            URL url = new URL(link);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            if(httpURLConnection == null){ return null; }

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
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
            return result;
        } catch (IOException e) { e.printStackTrace(); }

        return null;
    }

}
