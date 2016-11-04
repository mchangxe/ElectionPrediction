package com.example.museum2015.electionprediction.JsonClasses;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.museum2015.electionprediction.JsonClasses.Models;

/**
 * Created by Museum2015 on 31/10/2016.
 */
public class Party implements Comparable<Party>,Parcelable {
    public String party;
    public String candidate;
    public String date;
    public Models models;

    @Override
    public int compareTo(Party party) {
        return this.models.compareTo(party.models);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.party);
        dest.writeString(this.candidate);
        dest.writeParcelable(this.models, flags);
    }

    public Party() {
    }

    protected Party(Parcel in) {
        this.party = in.readString();
        this.candidate = in.readString();
        this.models = in.readParcelable(Models.class.getClassLoader());
    }

    public static final Parcelable.Creator<Party> CREATOR = new Parcelable.Creator<Party>() {
        @Override
        public Party createFromParcel(Parcel source) {
            return new Party(source);
        }

        @Override
        public Party[] newArray(int size) {
            return new Party[size];
        }
    };
}
