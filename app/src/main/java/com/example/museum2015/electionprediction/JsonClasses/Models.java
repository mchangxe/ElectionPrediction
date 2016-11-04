package com.example.museum2015.electionprediction.JsonClasses;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Museum2015 on 31/10/2016.
 */
public class Models implements Comparable<Models>,Parcelable {
    public PlusSecond plus;
    public NowSecond Now;
    public PollsSecond polls;

    @Override
    public int compareTo(Models models) {
        return this.plus.compareTo(models.plus);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.plus, flags);
    }

    public Models() {
    }

    protected Models(Parcel in) {
        this.plus = in.readParcelable(PlusSecond.class.getClassLoader());
    }

    public static final Parcelable.Creator<Models> CREATOR = new Parcelable.Creator<Models>() {
        @Override
        public Models createFromParcel(Parcel source) {
            return new Models(source);
        }

        @Override
        public Models[] newArray(int size) {
            return new Models[size];
        }
    };
}
