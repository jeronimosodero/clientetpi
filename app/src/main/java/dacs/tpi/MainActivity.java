package dacs.tpi;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONObject;
import org.jsoup.Jsoup;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = " MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new RestCallTask().execute();
    }

    private class RestCallTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = "http://192.168.1.75:8080/tpi/rest/clientes/1";
                String data = Jsoup.connect(url).ignoreContentType(true).execute().body();
                JSONObject json = new JSONObject(data);
                String email = json.getString("email");
                Log.d(TAG, "email: " + email);

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }
}
