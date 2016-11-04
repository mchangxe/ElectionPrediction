package com.example.museum2015.electionprediction.JsonClasses;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Museum2015 on 31/10/2016.
 */
public class Latest implements Parcelable {
    public Party D;
    public Party R;
    public Party L;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.D, flags);
        dest.writeParcelable(this.R, flags);
        dest.writeParcelable(this.L, flags);
    }

    public Latest() {
    }

    protected Latest(Parcel in) {
        this.D = in.readParcelable(Party.class.getClassLoader());
        this.R = in.readParcelable(Party.class.getClassLoader());
        this.L = in.readParcelable(Party.class.getClassLoader());
    }

    public static final Parcelable.Creator<Latest> CREATOR = new Parcelable.Creator<Latest>() {
        @Override
        public Latest createFromParcel(Parcel source) {
            return new Latest(source);
        }

        @Override
        public Latest[] newArray(int size) {
            return new Latest[size];
        }
    };
}
