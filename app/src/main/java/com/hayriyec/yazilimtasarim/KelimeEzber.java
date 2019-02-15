package com.hayriyec.yazilimtasarim;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hayriyec.yazilimtasarim.common.kelime;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

public class KelimeEzber extends AppCompatActivity {

    private TextToSpeech textToSpeech;
    private ImageView imgPlayWord;
    private ImageButton imgBtnTextToSpeech;
    private TextView txtPlayWord;
    private TextView txtCorrectAnswer;
    private TextView txtCümle;
    private TextView txtCümleAnlam;
    private Button btnİleri;
    private Button btnGeri;
    int basla=-1;
    int result,totalword,thisword=0;
    int index=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelime_ezber);

        Init();

        textToSpeech=new TextToSpeech(KelimeEzber.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status== TextToSpeech.SUCCESS)
                {
                    result=textToSpeech.setLanguage(Locale.UK);
                }else{
                    Toast.makeText(getApplicationContext(),"Feature not supported in your device",Toast.LENGTH_LONG).show();
                }
            }
        });

        imgBtnTextToSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TTS(v);
            }
        });

        btnİleri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index<totalword)
                {
                    if(index<9)
                    showWord(++index);
                    else
                    {showWord(0);}
                }

            }
        });
        btnGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( index<totalword)
                {
                    if(0<index)
                    showWord(--index);
                    else
                    {
                        showWord(9);
                    }
                }

            }
        });
    }
    public void TTS(View view) {
        switch (view.getId()) {
            case R.id.imgBtnTextToSpeech:
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(getApplicationContext(), "Feature not supported in your device", Toast.LENGTH_LONG).show();
                } else {
                    String text = txtPlayWord.getText().toString();
                    textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                }
                // textToSpeech.stop();
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(textToSpeech!=null)
        {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }

    private void showWord(int index) {

        if(index<totalword)
        {
            thisword++;

                txtPlayWord.setText( kelime.wordListt.get(index).getQuestion());

                imgPlayWord.setVisibility(View.INVISIBLE);
                txtPlayWord.setVisibility(View.VISIBLE);

                imgBtnTextToSpeech.setVisibility(View.VISIBLE);

                txtCorrectAnswer.setText(kelime.wordListt.get(index).getCorrectAnswer());
                txtCümle.setText(kelime.wordListt.get(index).getCümle());
                txtCümleAnlam.setText(kelime.wordListt.get(index).getCAnlam());

        }
        else
        {
            //If it is final question
            Intent intent=new Intent(this,OyunbaslaFragment.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        totalword=  kelime.wordListt.size();
        showWord(index);
    }

    private void Init() {

        btnİleri = (Button)findViewById(R.id.btnIleri);
        btnGeri = (Button)findViewById(R.id.btnGeri);
        imgPlayWord = (ImageView) findViewById(R.id.imgPlayWord);
        txtPlayWord = (TextView)findViewById(R.id.txtPlayWord);
        txtCorrectAnswer = (TextView)findViewById(R.id.txtCorrectAnswer);
        txtCümle = (TextView)findViewById(R.id.txtCümle);
        txtCümleAnlam = (TextView)findViewById(R.id.txtCümleAnlam);
        imgBtnTextToSpeech=(ImageButton)findViewById(R.id.imgBtnTextToSpeech);

    }
}
