package dacs.tpi.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;

import dacs.tpi.R;
import dacs.tpi.activity.MainActivity;
import dacs.tpi.model.Estado;
import dacs.tpi.model.Orden;
import dacs.tpi.model.Sucursal;
import dacs.tpi.model.Unidad;

/**
 * Created by Jerónimo Sodero on 10/07/2015.
 */
public class UnidadFragment extends Fragment {
    private static final String ARG_UNIDAD_ID = "unidad_id";
    private static final String TAG = "UnidadFragment";
    private SharedPreferences mPrefs = null;
    private Unidad mUnidad;
    private ListView mList;
    private Button mSiguiente;

    public static UnidadFragment newInstance(int unidadId){
        Bundle args = new Bundle();
        args.putInt(ARG_UNIDAD_ID, unidadId);
        UnidadFragment uf = new UnidadFragment();
        uf.setArguments(args);
        return uf;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_unidad, container, false);
    }

    @Override
    public void onViewCreated(View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        mPrefs = getActivity().getSharedPreferences(MainActivity.APP_NAME, MainActivity.MODE_PRIVATE);
        mList = (ListView)v.findViewById(R.id.listView);
        Bundle args = getArguments();
        mSiguiente = (Button)v.findViewById(R.id.siguienteButton);
        mSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SubirEstado().execute();
            }
        });
        new RestCallTask().execute(args.getInt(ARG_UNIDAD_ID));

    }

    private class RestCallTask extends AsyncTask<Integer,Void,Void> {

        @Override
        protected Void doInBackground(Integer... integers) {
            int unidadId = integers[0];

            try {
                String ip = mPrefs.getString(MainFragment.SAVE_IP,"");
                String url ="http://"+ip+":8080/tpi/rest/unidad/"+unidadId;
                String data = Jsoup.connect(url).ignoreContentType(true).execute().body();
                JSONObject json = new JSONObject(data);
                mUnidad = new Unidad(json);
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {



                ListAdapter customAdapter = new ListAdapter(UnidadFragment.this.getActivity(), android.R.layout.simple_list_item_1,mUnidad.getViajeActual().getRuta().getSucursales());
                mList.setAdapter(customAdapter);

            }

    }

    private class SubirEstado extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String ip = mPrefs.getString(MainFragment.SAVE_IP,"");
                String url ="http://"+ip+":8080/tpi/rest/viaje/actualizarViaje";

                List<Estado> estados = mUnidad.getViajeActual().getOrdenes().get(0).getEstado();
                Estado ultimoEstado = estados.get(estados.size()-1);
                Long idSucursalSiguiente = -1L;
                int sucursalActual = -1;

                for(int i=0;i<mUnidad.getViajeActual().getRuta().getSucursales().size();i++){
                    Sucursal sucursal = mUnidad.getViajeActual().getRuta().getSucursales().get(i);
                    if(sucursal.getDireccion().getCiudad().equals(ultimoEstado.getSucursal().getDireccion().getCiudad())){
                        sucursalActual = i;
                    }
                }



                if(sucursalActual+1>=mUnidad.getViajeActual().getRuta().getSucursales().size()){
                    idSucursalSiguiente = -1L;
                } else {
                    idSucursalSiguiente = mUnidad.getViajeActual().getRuta().getSucursales().get(sucursalActual+1).getId();
                }



                if(idSucursalSiguiente!=-1){
                    String data = Jsoup.connect(url).ignoreContentType(true).data("idViaje",String.valueOf(mUnidad.getViajeActual().getId()))
                            .data("idSucursal", String.valueOf(idSucursalSiguiente))
                            .post().body().text();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            new RestCallTask().execute(Integer.valueOf(String.valueOf(mUnidad.getId())));
        }
    }
    public class ListAdapter extends ArrayAdapter<Sucursal> {


        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public boolean isEnabled(int position) {
            return false;
        }

        public ListAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        public ListAdapter(Context context, int resource, List<Sucursal> items) {
            super(context, resource, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = convertView;

            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(android.R.layout.simple_list_item_1,null);

            }

            Sucursal s = getItem(position);

            if (s != null) {



                TextView tv = (TextView)v;
                tv.setText(s.getDireccion().getCiudad());
                tv.setBackgroundColor(Color.WHITE);

                List<Estado> estados = mUnidad.getViajeActual().getOrdenes().get(0).getEstado();
                try{
                    Estado ultimoEstado = estados.get(estados.size()-1);
                    if(ultimoEstado.getSucursal().getDireccion().getCiudad().equals(s.getDireccion().getCiudad())){
                        tv.setBackgroundColor(Color.GRAY);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            return v;
        }

    }




}
