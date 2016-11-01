package com.example.museum2015.electionprediction;

import java.text.DecimalFormat;

/**
 * Created by Museum2015 on 30/10/2016.
 */

public class PollInfo {
    public String leader;
    public String model;
    public String party;
    public double probability;
    public String state;
    public String tie;

    public String toString(double probability){
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(probability);
    }
}
