package com.example.museum2015.electionprediction;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PredictionFragment.Listener{

    private ListView mListView;
    CustomArrayAdapter mAdapter;

    public static final String URL = "http://projects.fivethirtyeight.com/2016-election-forecast/summary.json";


    /*
     * onCreate methods sets the mListView variable to the actual listView element and executes
     * the JSON parsing Asynctask.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.list_view);
        mAdapter = new CustomArrayAdapter(this, R.layout.list_item);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                /*
                 * When clicks on a list item, show a toast with the state name. JUST FOR FUN
                 * @return void
                 */
                StateElectionInfo currState = (StateElectionInfo) mAdapter.getItem(position);
                Intent detailIntent = new Intent(adapterView.getContext(), DetailActivity.class);
                detailIntent.putExtra("StateElectionInfo", currState);
                startActivity(detailIntent);
            }
        });

        new PredictionFragment(this).execute(URL);

        mListView.getRootView().setBackgroundColor(Color.parseColor("#CCDBE1"));
    }

    /*
     * Converts the objects from json to the model and calls the loadListView method to
     * populate the list items.
     * @param electionPredictions the variable that holds the java objects obtained from json parsing,
     *          it will be empty if the json parsing didn't happen correctly. If it is empty, onError()
     *          will be called and if it is not empty, onLoaded will be called.
     * @return void
     */
    @Override
    public void onLoaded(StateElectionInfo[] electionPredictions) {
        mAdapter.addAll(electionPredictions);
    }

    /*
     * Show an error toast in case json parsing goes wrong
     * @return void
     */
    @Override
    public void onError() {

        Toast.makeText(this, "Error !", Toast.LENGTH_SHORT).show();
    }

}