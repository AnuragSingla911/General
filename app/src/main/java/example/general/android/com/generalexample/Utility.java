package example.general.android.com.generalexample;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by jade on 13/3/16.
 */
public class Utility {

    public static void handleClick(String url,Context context){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(browserIntent);
    }
}
