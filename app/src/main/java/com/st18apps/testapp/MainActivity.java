package com.st18apps.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Card> cards = new ArrayList<>();

        Card green = new Card("Green", getResources().getColor(R.color.green));
        Card red = new Card("Red", getResources().getColor(R.color.red));
        Card yellow = new Card("Yellow", getResources().getColor(R.color.yellow));
        Card blue = new Card("Blue", getResources().getColor(R.color.blue));

        cards.add(green);
        cards.add(red);
        cards.add(yellow);
        cards.add(blue);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerAdapter adapter = new RecyclerAdapter(cards);
        recyclerView.setAdapter(adapter);

    }
}
