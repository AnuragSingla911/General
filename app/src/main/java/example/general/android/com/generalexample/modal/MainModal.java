package example.general.android.com.generalexample.modal;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by jade on 12/3/16.
 */
public class MainModal implements Parcelable{

    public ArrayList<Section> getmSections() {
        return mSections;
    }

    public void setmSections(ArrayList<Section> mSections) {
        this.mSections = mSections;
    }

    private ArrayList<Section> mSections;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mSections != null ? mSections.size() : 0);
        if(mSections != null && mSections.size() > 0){
            dest.writeTypedList(mSections);
        }
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public MainModal createFromParcel(Parcel in) {
            MainModal mainModal = new MainModal();
            int size = in.readInt();
            if(size > 0){
                ArrayList<Section> mItems = new ArrayList<>();
                in.readTypedList(mItems, Section.CREATOR);
                mainModal.setmSections(mItems);
            }
            return mainModal;
        }

        public Section[] newArray(int size) {
            return new Section[size];
        }
    };
}
