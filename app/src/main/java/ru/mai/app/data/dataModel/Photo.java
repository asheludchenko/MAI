package ru.mai.app.data.dataModel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by olegosipenko on 26.09.15.
 */
public class Photo implements Parcelable{
    private String original;

    public Photo() {
    }

    protected Photo(Parcel in) {
        original = in.readString();
    }

    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };

    public String getOriginal() {
        return original;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(original);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
