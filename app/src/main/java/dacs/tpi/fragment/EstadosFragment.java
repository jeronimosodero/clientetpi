package dacs.tpi.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONObject;
import org.jsoup.Jsoup;

import dacs.tpi.R;

/**
 * Created by Jerónimo Sodero on 04/07/2015.
 */
public class EstadosFragment extends Fragment{
    private static final String TAG = "EstadosFragment";
    private static final String URL_BASE = "http://192.168.1.8:8080/tpi/rest";
    private RecyclerView mRecycler;

    public static EstadosFragment newInstance(){
        return new EstadosFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecycler = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecycler.setLayoutManager(layoutManager);
        mRecycler.setHasFixedSize(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new RestCallTask().execute();
    }

    private class RestCallTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                String url = URL_BASE+"/clientes/1";
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

