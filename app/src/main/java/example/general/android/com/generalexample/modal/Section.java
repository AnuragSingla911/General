package example.general.android.com.generalexample.modal;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by jade on 12/3/16.
 */
public class Section implements Parcelable{

    String mLabel;
    String mImageUrl;

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

    public String getmTemplate() {
        return mTemplate;
    }

    public void setmTemplate(String mTemplate) {
        this.mTemplate = mTemplate;
    }

    public ArrayList<Item> getmItems() {
        return mItems;
    }

    public void setmItems(ArrayList<Item> mItems) {
        this.mItems = mItems;
    }

    String mTemplate;

    ArrayList<Item> mItems;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mLabel);
        dest.writeString(mImageUrl);
        dest.writeString(mTemplate);
        dest.writeInt(mItems != null ? mItems.size()  : 0);
        if(mItems != null && mItems.size() > 0){
            dest.writeTypedList(mItems);
        }
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Section createFromParcel(Parcel in) {
            Section section = new Section();
            section.setmLabel(in.readString());
            section.setmImageUrl(in.readString());
            section.setmTemplate(in.readString());
            int size = in.readInt();
            if(size > 0){
                ArrayList<Item> mItems = new ArrayList<>();
                in.readTypedList(mItems, Item.CREATOR);
                section.setmItems(mItems);
            }
            return section;
        }

        public Section[] newArray(int size) {
            return new Section[size];
        }
    };
}
