package com.example.museum2015.electionprediction;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.museum2015.electionprediction.JsonClasses.*;

import com.example.museum2015.electionprediction.JsonClasses.Latest;
import com.example.museum2015.electionprediction.JsonClasses.Margin;
import com.example.museum2015.electionprediction.JsonClasses.Roi;
import com.example.museum2015.electionprediction.JsonClasses.Sentences;
import com.example.museum2015.electionprediction.JsonClasses.TippingPoint;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Museum2015 on 30/10/2016.
 */

public class StateElectionInfo implements Parcelable {

    public String state;
    public Margin margin;
    public TippingPoint tippingpoint;
    public Roi roi;
    public Latest latest;
    public Sentences sentences;
    public double latest_poll;

    public static final Map<String, String> STATE_MAP;
    static {
        STATE_MAP = new HashMap<String, String>();
        STATE_MAP.put("AL", "Alabama");
        STATE_MAP.put("AK", "Alaska");
        STATE_MAP.put("AB", "Alberta");
        STATE_MAP.put("AZ", "Arizona");
        STATE_MAP.put("AR", "Arkansas");
        STATE_MAP.put("BC", "British Columbia");
        STATE_MAP.put("CA", "California");
        STATE_MAP.put("CO", "Colorado");
        STATE_MAP.put("CT", "Connecticut");
        STATE_MAP.put("DE", "Delaware");
        STATE_MAP.put("DC", "District Of Columbia");
        STATE_MAP.put("FL", "Florida");
        STATE_MAP.put("GA", "Georgia");
        STATE_MAP.put("GU", "Guam");
        STATE_MAP.put("HI", "Hawaii");
        STATE_MAP.put("ID", "Idaho");
        STATE_MAP.put("IL", "Illinois");
        STATE_MAP.put("IN", "Indiana");
        STATE_MAP.put("IA", "Iowa");
        STATE_MAP.put("KS", "Kansas");
        STATE_MAP.put("KY", "Kentucky");
        STATE_MAP.put("LA", "Louisiana");
        STATE_MAP.put("ME", "Maine");
        STATE_MAP.put("MB", "Manitoba");
        STATE_MAP.put("MD", "Maryland");
        STATE_MAP.put("MA", "Massachusetts");
        STATE_MAP.put("MI", "Michigan");
        STATE_MAP.put("MN", "Minnesota");
        STATE_MAP.put("MS", "Mississippi");
        STATE_MAP.put("MO", "Missouri");
        STATE_MAP.put("MT", "Montana");
        STATE_MAP.put("NE", "Nebraska");
        STATE_MAP.put("NV", "Nevada");
        STATE_MAP.put("NB", "New Brunswick");
        STATE_MAP.put("NH", "New Hampshire");
        STATE_MAP.put("NJ", "New Jersey");
        STATE_MAP.put("NM", "New Mexico");
        STATE_MAP.put("NY", "New York");
        STATE_MAP.put("NF", "Newfoundland");
        STATE_MAP.put("NC", "North Carolina");
        STATE_MAP.put("ND", "North Dakota");
        STATE_MAP.put("NT", "Northwest Territories");
        STATE_MAP.put("NS", "Nova Scotia");
        STATE_MAP.put("NU", "Nunavut");
        STATE_MAP.put("OH", "Ohio");
        STATE_MAP.put("OK", "Oklahoma");
        STATE_MAP.put("ON", "Ontario");
        STATE_MAP.put("OR", "Oregon");
        STATE_MAP.put("PA", "Pennsylvania");
        STATE_MAP.put("PE", "Prince Edward Island");
        STATE_MAP.put("PR", "Puerto Rico");
        STATE_MAP.put("QC", "Quebec");
        STATE_MAP.put("RI", "Rhode Island");
        STATE_MAP.put("SK", "Saskatchewan");
        STATE_MAP.put("SC", "South Carolina");
        STATE_MAP.put("SD", "South Dakota");
        STATE_MAP.put("TN", "Tennessee");
        STATE_MAP.put("TX", "Texas");
        STATE_MAP.put("UT", "Utah");
        STATE_MAP.put("VT", "Vermont");
        STATE_MAP.put("VI", "Virgin Islands");
        STATE_MAP.put("VA", "Virginia");
        STATE_MAP.put("WA", "Washington");
        STATE_MAP.put("WV", "West Virginia");
        STATE_MAP.put("WI", "Wisconsin");
        STATE_MAP.put("WY", "Wyoming");
        STATE_MAP.put("YT", "Yukon Territory");
    }

    public String getFull(String state){
        return STATE_MAP.get(state);
    }

    public String getFirstCandidateString(){
        String candidate1 = latest.D.candidate;
        double candidate1Prob = latest.D.models.plus.winprob;

        String candidate2 = latest.R.candidate;
        double candidate2Prob = latest.R.models.plus.winprob;

        String candidate3 = latest.L.candidate;
        double candidate3Prob = latest.L.models.plus.winprob;

        if (candidate1Prob>candidate2Prob && candidate1Prob>candidate3Prob){
            return candidate1;
        }else if (candidate2Prob>candidate1Prob && candidate2Prob>candidate3Prob){
            return candidate2;
        }else{
            return candidate3;
        }
    }

    public Double getFirstCandidateProbability(){
        String candidate1 = latest.D.candidate;
        double candidate1Prob = latest.D.models.plus.winprob;

        String candidate2 = latest.R.candidate;
        double candidate2Prob = latest.R.models.plus.winprob;

        String candidate3 = latest.L.candidate;
        double candidate3Prob = latest.L.models.plus.winprob;

        if (candidate1Prob>candidate2Prob && candidate1Prob>candidate3Prob){
            return candidate1Prob;
        }else if (candidate2Prob>candidate1Prob && candidate2Prob>candidate3Prob){
            return candidate2Prob;
        }else{
            return candidate3Prob;
        }
    }

    public String getFirstCandidateParty(){

        double candidate1Prob = latest.D.models.plus.winprob;

        double candidate2Prob = latest.R.models.plus.winprob;

        double candidate3Prob = latest.L.models.plus.winprob;

        if (candidate1Prob>candidate2Prob && candidate1Prob>candidate3Prob){
            return "D";
        }else if (candidate2Prob>candidate1Prob && candidate2Prob>candidate3Prob){
            return "R";
        }else{
            return "L";
        }
    }

    public String getSecondCandidateString(){
        String candidate1 = latest.D.candidate;
        double candidate1Prob = latest.D.models.plus.winprob;

        String candidate2 = latest.R.candidate;
        double candidate2Prob = latest.R.models.plus.winprob;

        String candidate3 = latest.L.candidate;
        double candidate3Prob = latest.L.models.plus.winprob;

        if (candidate1Prob>candidate2Prob && candidate1Prob<candidate3Prob){
            return candidate1;
        }else if (candidate2Prob>candidate1Prob && candidate2Prob<candidate3Prob){
            return candidate2;
        }else{
            return candidate3;
        }
    }

    public Double getSecondCandidateProbability(){
        String candidate1 = latest.D.candidate;
        double candidate1Prob = latest.D.models.plus.winprob;

        String candidate2 = latest.R.candidate;
        double candidate2Prob = latest.R.models.plus.winprob;

        String candidate3 = latest.L.candidate;
        double candidate3Prob = latest.L.models.plus.winprob;

        if (candidate1Prob>candidate2Prob && candidate1Prob<candidate3Prob){
            return candidate1Prob;
        }else if (candidate2Prob>candidate1Prob && candidate2Prob<candidate3Prob){
            return candidate2Prob;
        }else{
            return candidate3Prob;
        }
    }

    public String getSecondCandidateParty(){

        double candidate1Prob = latest.D.models.plus.winprob;

        double candidate2Prob = latest.R.models.plus.winprob;

        double candidate3Prob = latest.L.models.plus.winprob;

        if (candidate1Prob>candidate2Prob && candidate1Prob<candidate3Prob){
            return "D";
        }else if (candidate2Prob>candidate1Prob && candidate2Prob<candidate3Prob){
            return "R";
        }else{
            return "L";
        }
    }

    public String getThirdCandidateString(){
        String candidate1 = latest.D.candidate;
        double candidate1Prob = latest.D.models.plus.winprob;

        String candidate2 = latest.R.candidate;
        double candidate2Prob = latest.R.models.plus.winprob;

        String candidate3 = latest.L.candidate;
        double candidate3Prob = latest.L.models.plus.winprob;

        if (candidate1Prob<candidate2Prob && candidate1Prob<candidate3Prob){
            return candidate1;
        }else if (candidate2Prob<candidate1Prob && candidate2Prob<candidate3Prob){
            return candidate2;
        }else{
            return candidate3;
        }
    }

    public Double getThirdCandidateProbability(){
        String candidate1 = latest.D.candidate;
        double candidate1Prob = latest.D.models.plus.winprob;

        String candidate2 = latest.R.candidate;
        double candidate2Prob = latest.R.models.plus.winprob;

        String candidate3 = latest.L.candidate;
        double candidate3Prob = latest.L.models.plus.winprob;

        if (candidate1Prob<candidate2Prob && candidate1Prob<candidate3Prob){
            return candidate1Prob;
        }else if (candidate2Prob<candidate1Prob && candidate2Prob<candidate3Prob){
            return candidate2Prob;
        }else{
            return candidate3Prob;
        }
    }

    public String getThirdCandidateParty(){

        double candidate1Prob = latest.D.models.plus.winprob;

        double candidate2Prob = latest.R.models.plus.winprob;

        double candidate3Prob = latest.L.models.plus.winprob;

        if (candidate1Prob<candidate2Prob && candidate1Prob<candidate3Prob){
            return "D";
        }else if (candidate2Prob<candidate1Prob && candidate2Prob<candidate3Prob){
            return "R";
        }else{
            return "L";
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.state);
        dest.writeParcelable(this.latest, flags);
    }

    public StateElectionInfo() {
    }

    protected StateElectionInfo(Parcel in) {
        this.state = in.readString();
        this.latest = in.readParcelable(Latest.class.getClassLoader());
    }

    public static final Parcelable.Creator<StateElectionInfo> CREATOR = new Parcelable.Creator<StateElectionInfo>() {
        @Override
        public StateElectionInfo createFromParcel(Parcel source) {
            return new StateElectionInfo(source);
        }

        @Override
        public StateElectionInfo[] newArray(int size) {
            return new StateElectionInfo[size];
        }
    };
}
