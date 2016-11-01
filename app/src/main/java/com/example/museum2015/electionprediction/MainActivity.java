package com.example.museum2015.electionprediction;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PredictionFragment.Listener,
        AdapterView.OnItemClickListener{

    private ListView mListView;
    private List<HashMap<String, String>> mpredictionInfo = new ArrayList<>();

    public static final String URL = "http://projects.fivethirtyeight.com/2016-election-forecast/summary.json";

    private static final String KEY_STATE = "state";
    private static final String KEY_CANDIDATE = "candidate";
    private static final String KEY_PROBABILITY = "probability";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setOnItemClickListener((AdapterView.OnItemClickListener) this);
        new PredictionFragment(this).execute(URL);

        mListView.getRootView().setBackgroundColor(Color.parseColor("#CCDBE1"));
    }

    /*
     * Convert json objects to string and calls the loadListView method to populate the list items.
     */
    @Override
    public void onLoaded(StateElectionInfo[] electionPredictions) {

        for (StateElectionInfo stateCurrent : electionPredictions) {

            HashMap<String, String> map = new HashMap<>();

            map.put(KEY_STATE, stateCurrent.getFull(stateCurrent.state));
            map.put(KEY_CANDIDATE, stateCurrent.sentences.polls.leader);
            map.put(KEY_PROBABILITY,
                    (stateCurrent.sentences.polls.toString(stateCurrent.sentences.polls.probability)));

            mpredictionInfo.add(map);
        }

        loadListView();
    }

    /*
     * Show an error toast in case json parsing goes wrong
     */
    @Override
    public void onError() {

        Toast.makeText(this, "Error !", Toast.LENGTH_SHORT).show();
    }

    /*
     * When clicks on a list item, show a toast with the state name. JUST FOR FUN
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(this, mpredictionInfo.get(i).get(KEY_STATE),Toast.LENGTH_LONG).show();
    }

    /*
     * MVA, using an adapter to update the list view instead of the AsyncTask
     */
    private void loadListView() {

        ListAdapter adapter = new SimpleAdapter(MainActivity.this, mpredictionInfo, R.layout.list_item,
                new String[] { KEY_STATE, KEY_CANDIDATE, KEY_PROBABILITY},
                new int[] { R.id.state,R.id.candidate, R.id.probability});

        mListView.setAdapter(adapter);

    }

}
