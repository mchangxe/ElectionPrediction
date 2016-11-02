package com.example.museum2015.electionprediction;

import android.os.AsyncTask;

import android.util.Log;

import com.example.museum2015.electionprediction.JsonClasses.StateElectionInfo;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by Museum2015 on 30/10/2016.
 */

public class PredictionFragment extends AsyncTask<String, Void, StateElectionInfo[]> {

    //private member variable to hold the listener
    private Listener mListener;

    /*
     * Default constructor with one parameter to instantiate the mListener variable
     */
    public PredictionFragment(Listener listener) {

        mListener = listener;
    }

    /*
     * Extensibility: utilizes a custom interface with two methods. If need to add more functions
     * for the controller to manipulate the data, can create more functions here.
     */
    public interface Listener {

        /*
         * Where the controller manipulates the data if the json Parsing was sucessful
         */
        void onLoaded(StateElectionInfo[] electionPredictions);

        /*
         * The controller will tell the view to throw a toast if the json parsing wasn't successful
         * and there is no output created from json parsing
         */
        void onError();
    }

    /*
     * Asynctask will call this function after it executes the json parsing. The controller will
     * decide what function to call depending on what the output of the json parsing is
     */
    @Override
    protected void onPostExecute(StateElectionInfo[] info) {

        if (info != null) {

            mListener.onLoaded(info);

        } else {

            mListener.onError();
        }
    }

    /*
     * The actual function that copies an online json file to a string.
     * @param jsonURL the url that the code will obtain information from
     * @return the json file in string form
     */
    public String getPredictionInfoFromJson(String jsonURL) throws IOException {
        URL url = new URL(jsonURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();

        //For some reason, the app runs smoothly without the GZIP line, however the test will not
        //run smoothly without the same line.
        //new GZIPInputStream(conn.getInputStream()
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        StringBuilder predictions = new StringBuilder();

        while ((line = in.readLine()) != null) {
            predictions.append(line);
        }

        in.close();
        return predictions.toString();
    }

    /*
     * The Async function that will call getPredictionInfoFromJson to parse json
     * @param strings takes a string from the .execute() function as the json url
     * @return the java objects obtained from json
     */
    @Override
    protected StateElectionInfo[] doInBackground(String... strings) {
        try {
            String stringResponse = getPredictionInfoFromJson(strings[0]);
            Log.d("test", stringResponse);
            Gson gson = new Gson();

            return gson.fromJson(stringResponse, StateElectionInfo[].class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

}
