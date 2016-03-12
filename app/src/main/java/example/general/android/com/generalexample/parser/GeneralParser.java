package example.general.android.com.generalexample.parser;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import example.general.android.com.generalexample.modal.Item;
import example.general.android.com.generalexample.modal.MainModal;
import example.general.android.com.generalexample.modal.Section;

/**
 * Created by jade on 12/3/16.
 */
public class GeneralParser {

    public static MainModal parseMainModal(String s){
        MainModal modal = new MainModal();
        try {
            Log.d("anurag", " array :");
            JSONArray array = new JSONArray(s);
            Log.d("anurag", " array :" + array);
            if (array != null && array.length() > 0) {
                ArrayList<Section> mItems = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    mItems.add(parseSection(array.getJSONObject(i)));
                }
                modal.setmSections(mItems);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return modal;
    }

    public static Section parseSection(JSONObject object) {

        Section section = new Section();
        try {
            section.setmLabel(object.optString(Keys.LABEL));
            section.setmImageUrl(object.optString(Keys.IMAGE));
            section.setmTemplate(object.optString(Keys.TEMPLATE));

            JSONArray itemsArray = object.optJSONArray(Keys.ITEMS);
            if (itemsArray != null && itemsArray.length() > 0) {
                ArrayList<Item> mItems = new ArrayList<>();
                for (int i = 0; i < itemsArray.length(); i++) {
                    mItems.add(parseItem(itemsArray.getJSONObject(i)));
                }
                section.setmItems(mItems);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return section;

    }

    private static Item parseItem(JSONObject object) {
        Item item = new Item();
        item.setmLabel(object.optString(Keys.LABEL));
        item.setmImageUrl(object.optString(Keys.IMAGE));
        item.setWebUrl(object.optString(Keys.WEB_URL));
        return item;
    }
}
