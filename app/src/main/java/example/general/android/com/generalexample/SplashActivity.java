package example.general.android.com.generalexample;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import example.general.android.com.generalexample.modal.MainModal;
import example.general.android.com.generalexample.parser.GeneralParser;

/**
 * Created by jade on 12/3/16.
 */
public class SplashActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new AsyncTask<Void,Void,MainModal>(){

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
            protected MainModal doInBackground(Void... params) {
                InputStream inputStream = getResources().openRawResource(R.raw.f_two);
                try {
                    String jsonString = readFully(inputStream);
                    MainModal modal = GeneralParser.parseMainModal(jsonString);
                    return modal;
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(MainModal modal) {
                super.onPostExecute(modal);
                if(modal != null){
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    intent.putExtra("modal", modal);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(SplashActivity.this, "Section empty", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }
}
