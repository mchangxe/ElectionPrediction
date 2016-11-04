package com.example.museum2015.electionprediction;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.test.espresso.core.deps.guava.util.concurrent.Service;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import javax.annotation.Nonnull;

/**
 * Created by Museum2015 on 3/11/2016.
 */

public class CustomArrayAdapter extends ArrayAdapter {


    public CustomArrayAdapter(Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, View v, ViewGroup parent){
        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.list_item, null);
        }

        StateElectionInfo currState = (StateElectionInfo) getItem(position);

        TextView candidateText = (TextView)v.findViewById(R.id.candidate);
        TextView statesText = (TextView)v.findViewById(R.id.state);
        TextView probabilityText = (TextView)v.findViewById(R.id.probability);

        candidateText.setText(currState.getFirstCandidateString());
        statesText.setText(currState.getFull(currState.state));
        probabilityText.setText(currState.getFirstCandidateProbability().toString());

        if (candidateText.getText().toString().equals("Trump")){
            v.setBackgroundColor(Color.BLUE);
        }else{
            v.setBackgroundColor(Color.WHITE);
        }

        return v;
    }
}
