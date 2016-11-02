package com.example.museum2015.electionprediction;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.museum2015.electionprediction.JsonClasses.StateElectionInfo;

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

    /*
     * onCreate methods sets the mListView variable to the actual listView element and executes
     * the JSON parsing Asynctask.
     */
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
     * Converts the objects from json to the model and calls the loadListView method to
     * populate the list items.
     * @param electionPredictions the variable that holds the java objects obtained from json parsing,
     *          it will be empty if the json parsing didn't happen correctly. If it is empty, onError()
     *          will be called and if it is not empty, onLoaded will be called.
     * @return void
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
     * @return void
     */
    @Override
    public void onError() {

        Toast.makeText(this, "Error !", Toast.LENGTH_SHORT).show();
    }

    /*
     * When clicks on a list item, show a toast with the state name. JUST FOR FUN
     * @return void
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(this, mpredictionInfo.get(i).get(KEY_STATE),Toast.LENGTH_LONG).show();
    }

    /*
     * MVA, using an adapter to update the list view instead of the AsyncTask
     * return void
     */
    private void loadListView() {

        ListAdapter adapter = new SimpleAdapter(MainActivity.this, mpredictionInfo, R.layout.list_item,
                new String[] { KEY_STATE, KEY_CANDIDATE, KEY_PROBABILITY},
                new int[] { R.id.state,R.id.candidate, R.id.probability});

        mListView.setAdapter(adapter);

    }

}
