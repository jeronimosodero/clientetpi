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
import dacs.tpi.model.Estado;
import dacs.tpi.model.Paquete;

/**
 * Created by Jerónimo Sodero on 04/07/2015.
 */
public class EstadosFragment extends Fragment{
    private static final String TAG = "EstadosFragment";
    private static final String URL_BASE = "http://192.168.1.8:8080/tpi/rest";
    private static final String ARG_PAQUETE_ID = "paquete_id";
    private RecyclerView mRecycler;
    private Paquete mPaquete;

    public static EstadosFragment newInstance(int paqueteId){
        Bundle args = new Bundle();
        args.putInt(ARG_PAQUETE_ID,paqueteId);
        EstadosFragment ef = new EstadosFragment();
        ef.setArguments(args);
        return ef;
    }

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
        Bundle args = getArguments();
        if(args!=null){
            new RestCallTask().execute(args.getInt(ARG_PAQUETE_ID));
        }

    }

    private class RestCallTask extends AsyncTask<Integer,Void,Void> {

        @Override
        protected Void doInBackground(Integer... integers) {
            int paqueteId = integers[0];

            try {
                String url = URL_BASE+"/paquete/"+paqueteId;
                String data = Jsoup.connect(url).ignoreContentType(true).execute().body();
                JSONObject json = new JSONObject(data);
                mPaquete = new Paquete(json);
                for (Estado estado: mPaquete.getEstado()){
                    Log.d(TAG,"Latitud: "+estado.getLatitud());
                    Log.d(TAG,"fecha: "+estado.getFecha_hora());
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }
}

