package com.st18apps.testapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.Callback {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerAdapter adapter;
    private List<Card> cards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cards = new ArrayList<>();

        XmlPullParser parser = getResources().getXml(R.xml.colors_for_list);

        try {
            while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType() == XmlPullParser.START_TAG
                        && parser.getName().equals("color")) {
                    cards.add(new Card(parser.getAttributeValue(1), Color.parseColor(parser.getAttributeValue(1))));
                }
                parser.next();
            }
        } catch (Throwable t) {
            Toast.makeText(this,
                    getString(R.string.toast_text) + t.toString(), Toast.LENGTH_LONG)
                    .show();
        }

        recyclerView = findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerAdapter(cards, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

    }

    @Override
    public void onItemClick(int position) {
        cards.get(position).toggle();
        adapter.notifyItemChanged(position);
    }
}
