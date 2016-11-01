package com.example.museum2015.electionprediction;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Museum2015 on 31/10/2016.
 */
public class PredictionFragmentTest {

    public static final String URL = "http://projects.fivethirtyeight.com/2016-election-forecast/summary.json";

    @Test
    public void testingAsyncAndJsonParsing() throws Exception {
        PredictionFragment.Listener mockedListener = new PredictionFragment.Listener() {
            @Override
            public void onLoaded(StateElectionInfo[] electionPredictions) {
                return;
            }

            @Override
            public void onError() {
                return;
            }
        };

        PredictionFragment test = new PredictionFragment(mockedListener);
        String result = test.getPredictionInfoFromJson(URL);

        assertTrue(result.isEmpty()!=true);
    }

}