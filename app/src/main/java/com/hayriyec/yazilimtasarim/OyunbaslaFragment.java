package com.hayriyec.yazilimtasarim;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hayriyec.yazilimtasarim.Model.Questions;
import com.hayriyec.yazilimtasarim.Model.Rank;
import com.hayriyec.yazilimtasarim.common.common;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hayriyec.yazilimtasarim.common.kelime;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class OyunbaslaFragment extends Fragment {

    View myFragment;

    Button btnPlay;
    Button btnChat;
    Button btnEzberle;
    Button btncevir;

    private DatabaseReference databaseReference;

    DatabaseReference mQuestionsDatabase;
    public static OyunbaslaFragment newInstance() {
        OyunbaslaFragment startPlayFragment = new OyunbaslaFragment();
        return startPlayFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mQuestionsDatabase = FirebaseDatabase.getInstance().getReference().child("Questions");

        databaseReference = FirebaseDatabase.getInstance().getReference("Questions");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_oyunbasla, container, false);

        loadQuestions();

        btnPlay = (Button) myFragment.findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), test.class);
                startActivity(intent);
            }
        });
        btnChat=(Button) myFragment.findViewById(R.id.btnChat);
        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), sohbetCategory.class);
                startActivity(intent);
            }
        });
       loadWord();
        btnEzberle=(Button) myFragment.findViewById(R.id.btnEzberle);
        btnEzberle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), KelimeEzber.class);
                startActivity(intent);
            }
        });
        btncevir=(Button) myFragment.findViewById(R.id.btnHikayeler);
        btncevir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), deneme.class);
                startActivity(intent);
            }
        });


        return myFragment;
    }

    private void loadQuestions() {
        if (common.questionList.size() > 0)
            common.questionList.clear();

        mQuestionsDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Questions ques = postSnapshot.getValue(Questions.class);
                    common.questionList.add(ques);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //Random List
        Collections.shuffle(common.questionList);
    }
    private  void loadWord()
    {
        if (kelime.wordListt.size() > 0)
            kelime.wordListt.clear();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Word question = postSnapshot.getValue(Word.class);
                    kelime.wordListt.add(question);
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Collections.shuffle(kelime.wordListt);

    }
}
