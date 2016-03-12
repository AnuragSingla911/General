package example.general.android.com.generalexample;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import example.general.android.com.generalexample.modal.Section;
import example.general.android.com.generalexample.parser.GeneralParser;

/**
 * Created by jade on 12/3/16.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        new AsyncTask<Void,Void,Section>(){

            public String readFully(InputStream inputStream)
                    throws IOException {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length = 0;
                while ((length = inputStream.read(buffer)) != -1) {
                    baos.write(buffer, 0, length);
                }
                return new String(baos.toByteArray(), "UTF-8");
            }



            @Override
            protected Section doInBackground(Void... params) {
                InputStream inputStream = getResources().openRawResource(R.raw.f_one);
                try {
                    String jsonString = readFully(inputStream);
                    JSONObject object = new JSONObject(jsonString);
                    Section section = GeneralParser.parseSection(object);
                    return section;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Section section) {
                super.onPostExecute(section);
                if(section != null){
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    intent.putExtra("section", section);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(SplashActivity.this, "Section empty", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }
}
