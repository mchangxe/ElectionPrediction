package com.example.museum2015.electionprediction.JsonClasses;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.*;

/**
 * Created by Museum2015 on 31/10/2016.
 */
public class PlusSecond implements Comparable<PlusSecond>,Parcelable {
    public Double winprob;
    public double forecast;
    public double hi;
    public double lo;

    @Override
    public int compareTo(PlusSecond plusSecond) {
        return this.winprob.compareTo(plusSecond.winprob);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.winprob);
    }

    public PlusSecond() {
    }

    protected PlusSecond(Parcel in) {
        this.winprob = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<PlusSecond> CREATOR = new Parcelable.Creator<PlusSecond>() {
        @Override
        public PlusSecond createFromParcel(Parcel source) {
            return new PlusSecond(source);
        }

        @Override
        public PlusSecond[] newArray(int size) {
            return new PlusSecond[size];
        }
    };
}
