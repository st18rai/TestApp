package com.st18apps.testapp;

import android.graphics.Color;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private Parcelable mListState;
    private String LIST_STATE_KEY = "list_state";
    private String TAG = "mytag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: ");

        List<Card> cards = new ArrayList<>();

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
                    "Ошибка при загрузке XML-документа: " + t.toString(), Toast.LENGTH_LONG)
                    .show();
        }

        recyclerView = findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerAdapter adapter = new RecyclerAdapter(cards);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume: ");

        if (mListState != null) {
            linearLayoutManager.onRestoreInstanceState(mListState);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        Log.d(TAG, "onSaveInstanceState: ");
        // Save list state
        mListState = linearLayoutManager.onSaveInstanceState();
        outState.putParcelable(LIST_STATE_KEY, mListState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.d(TAG, "onRestoreInstanceState: ");
        if (savedInstanceState != null)
            mListState = savedInstanceState.getParcelable(LIST_STATE_KEY);
    }
}
