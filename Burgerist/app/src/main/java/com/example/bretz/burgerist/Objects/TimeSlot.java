package com.example.bretz.burgerist.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Brandon on 02-Dec-17.
 */

public class TimeSlot implements Parcelable {
    //Properties
    private String id;
    private String timeFrame;

    //Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setTimeFrame(String timeFrame) {
        this.timeFrame = timeFrame;
    }

    //Getters
    public String getId() {
        return id;
    }

    public String getTimeFrame() {
        return timeFrame;
    }

    //Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(timeFrame);
    }

    protected TimeSlot(Parcel in) {
        id = in.readString();
        timeFrame = in.readString();
    }

    public static final Creator<TimeSlot> CREATOR = new Creator<TimeSlot>() {
        @Override
        public TimeSlot createFromParcel(Parcel in) {
            return new TimeSlot(in);
        }

        @Override
        public TimeSlot[] newArray(int size) {
            return new TimeSlot[size];
        }
    };
}
