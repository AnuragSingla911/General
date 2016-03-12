package example.general.android.com.generalexample.modal;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jade on 12/3/16.
 */
public class Item implements Parcelable {
    private String mLabel;

    public String getmLabel() {
        return mLabel;
    }

    public void setmLabel(String mLabel) {
        this.mLabel = mLabel;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    private String mImageUrl;
    private String webUrl;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(mLabel);
        dest.writeString(mImageUrl);
        dest.writeString(webUrl);

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Item createFromParcel(Parcel in) {
            Item item=new Item();
            item.setmLabel(in.readString());
            item.setmImageUrl(in.readString());
            item.setWebUrl(in.readString());
            return item;
        }

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
