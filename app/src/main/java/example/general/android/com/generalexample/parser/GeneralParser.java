package example.general.android.com.generalexample.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import example.general.android.com.generalexample.modal.Item;
import example.general.android.com.generalexample.modal.Section;

/**
 * Created by jade on 12/3/16.
 */
public class GeneralParser {

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
