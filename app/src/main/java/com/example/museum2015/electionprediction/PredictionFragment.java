package com.example.museum2015.electionprediction;

import android.net.sip.SipAudioCall;
import android.net.sip.SipSession;
import android.os.AsyncTask;
import android.util.JsonReader;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Museum2015 on 30/10/2016.
 */

public class PredictionFragment extends AsyncTask<String, Void, StateElectionInfo[]> {

    private Listener mListener;

    public PredictionFragment(Listener listener) {

        mListener = listener;
    }

    public interface Listener {

        void onLoaded(StateElectionInfo[] electionPredictions);

        void onError();
    }

    @Override
    protected void onPostExecute(StateElectionInfo[] info) {

        if (info != null) {

            mListener.onLoaded(info);

        } else {

            mListener.onError();
        }
    }

    public String getPredictionInfoFromJson(String jsonURL) throws IOException {
        URL url = new URL(jsonURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        StringBuilder predictions = new StringBuilder();

        while ((line = in.readLine()) != null) {
            predictions.append(line);
        }

        in.close();
        return predictions.toString();
    }

    @Override
    protected StateElectionInfo[] doInBackground(String... strings) {
        try {
            String stringResponse = getPredictionInfoFromJson(strings[0]);
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
