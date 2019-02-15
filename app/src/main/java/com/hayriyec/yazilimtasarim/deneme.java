package com.hayriyec.yazilimtasarim;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Locale;

public  class deneme extends AppCompatActivity  {

    private TextView tv;
    private EditText et;
    private Button button;
    private Button KelimeListe;

    String dilCifti="tr-en";
    String arananKelime;
    String yandexKey="trnsl.1.1.20170826T124332Z.c7f36074597a666f.f831f314a08423422cd841afca69af8e4a869564";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deneme);
        tv=(TextView)findViewById(R.id.tv);
        et=(EditText)findViewById(R.id.et);
        button=(Button)findViewById(R.id.btn);
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) { //Burada Buttona tıklandığında çalıştırılacak kodlar yer alıyor.
                arananKelime=et.getText().toString();
                String query = null;
                try {
                    query = URLEncoder.encode(arananKelime, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String urlString="https://translate.yandex.net/api/v1.5/tr.json/translate?key=" + yandexKey
                        + "&text=" + query + "&lang=" + dilCifti;
                new deneme.TranslatorBackgroundTask().execute(urlString);
                KelimeListe=(Button)findViewById(R.id.ListeEkle);
                KelimeListe.setVisibility(View.VISIBLE);

            }
        });

    }

    class TranslatorBackgroundTask extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... params) {
            String urlString=params[0];
            StringBuilder jsonString=new StringBuilder();
            try {
                URL yandexUrl=new URL(urlString);
                HttpURLConnection httpURLConnection= (HttpURLConnection) yandexUrl.openConnection();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line=bufferedReader.readLine())!=null){
                    jsonString.append(line);
                }
                inputStream.close();
                bufferedReader.close();
                httpURLConnection.disconnect();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonString.toString();
        }
        @Override
        protected void onPostExecute(String json) {
            JsonObject jsonObject=new JsonParser().parse(json).getAsJsonObject();
            String sonuc=jsonObject.get("text").getAsString();
            tv.setText(sonuc);
            Log.i("sonuc",sonuc);
        }




    }
}
