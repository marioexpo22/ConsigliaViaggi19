package com.example.consigliaviaggi19.DAO.implementazioni;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import com.example.consigliaviaggi19.DAO.UtenteDAO;
import com.example.consigliaviaggi19.entity.LinkConnessioneServer;
import com.example.consigliaviaggi19.entity.Utente;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class UtenteDAOImplementazione implements UtenteDAO {
    @Override
    public String ottieniUtenteValido(String email, String password) {

        String link = LinkConnessioneServer.getInstance().getLink() + "login.php";

        try{
            URL url = new URL(link);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            if(httpURLConnection == null){ return null; }

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            String post_data = URLEncoder.encode("username", "UTF-8")+"="+URLEncoder.encode(email, "UTF-8")+"&"+URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
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
            return result;
        } catch (IOException e) { e.printStackTrace(); }

        return null;
    }
}

