package com.example.museum2015.electionprediction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        StateElectionInfo currState = extras.getParcelable("StateElectionInfo");

        TextView candidate1Name = (TextView)findViewById(R.id.candidate1);
        candidate1Name.setText(currState.getFirstCandidateString());
        TextView candidate1Prob = (TextView)findViewById(R.id.probability1);
        candidate1Prob.setText(currState.getFirstCandidateProbability().toString());
        TextView candidate1Par = (TextView)findViewById(R.id.party1);
        candidate1Par.setText(currState.getFirstCandidateParty());

        TextView candidate2Name = (TextView)findViewById(R.id.candidate2);
        candidate2Name.setText(currState.getSecondCandidateString());
        TextView candidate2Prob = (TextView)findViewById(R.id.probability2);
        candidate2Prob.setText(currState.getSecondCandidateProbability().toString());
        TextView candidate2Par = (TextView)findViewById(R.id.party2);
        candidate2Par.setText(currState.getSecondCandidateParty());

        TextView candidate3Name = (TextView)findViewById(R.id.candidate3);
        candidate3Name.setText(currState.getThirdCandidateString());
        TextView candidate3Prob = (TextView)findViewById(R.id.probability3);
        candidate3Prob.setText(currState.getThirdCandidateProbability().toString());
        TextView candidate3Par = (TextView)findViewById(R.id.party3);
        candidate3Par.setText(currState.getThirdCandidateParty());

        TextView stateText = (TextView)findViewById(R.id.stateText);
        stateText.setText(currState.getFull(currState.state));

    }
}
