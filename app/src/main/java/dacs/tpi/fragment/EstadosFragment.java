package dacs.tpi.fragment;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONObject;
import org.jsoup.Jsoup;


import dacs.tpi.R;
import dacs.tpi.activity.MainActivity;
import dacs.tpi.model.Estado;
import dacs.tpi.model.Orden;
public class EstadosFragment extends Fragment{
    private static final String TAG = "EstadosFragment";
    private static final String ARG_ORDEN_ID = "orden_id";
    private RecyclerView mRecycler;
    private OrdenAdapter mAdapter;
    private Orden mOrden;
    private SharedPreferences mPrefs = null;

    public static EstadosFragment newInstance(int ordenId){
        Bundle args = new Bundle();
        args.putInt(ARG_ORDEN_ID,ordenId);
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
        mPrefs = getActivity().getSharedPreferences(MainActivity.APP_NAME, MainActivity.MODE_PRIVATE);
        mRecycler = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecycler.setLayoutManager(layoutManager);
        mRecycler.setHasFixedSize(true);
        Bundle args = getArguments();
        if(args!=null){
            new RestCallTask().execute(args.getInt(ARG_ORDEN_ID));
        }

    }

    private class RestCallTask extends AsyncTask<Integer,Void,Void> {

        @Override
        protected Void doInBackground(Integer... integers) {
            int ordenId = integers[0];

            try {
                String ip = mPrefs.getString(MainFragment.SAVE_IP,"");
                String url ="http://"+ip+":8080/tpi/rest/orden/"+ordenId;
                String data = Jsoup.connect(url).ignoreContentType(true).execute().body();
                JSONObject json = new JSONObject(data);
                mOrden = new Orden(json);
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
           if(mOrden!=null){
               mAdapter = new OrdenAdapter(mOrden);
               mRecycler.setAdapter(mAdapter);
           }
        }
    }

       public class OrdenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private Orden orden;

        public OrdenAdapter(Orden orden) {
            this.orden = orden;
        }


        @Override
        public int getItemCount() {
            return orden.getEstado().size();
        }
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder vh, int i) {
            if(vh instanceof EstadoViewHolder){
                final Estado estado = orden.getEstado().get(i);

                EstadoViewHolder evh = (EstadoViewHolder) vh;

                evh.vSucursal.setText(estado.getSucursal().getDireccion().getCiudad());
                android.text.format.DateFormat df = new android.text.format.DateFormat();
                String fecha = df.format("dd/MM/yyyy hh:mm",estado.getFecha_hora()).toString();
                evh.vFechaHora.setText(fecha);

            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {


                View itemView = LayoutInflater.
                        from(viewGroup.getContext()).
                        inflate(R.layout.list_item_card_estado, viewGroup, false);
                return new EstadoViewHolder(itemView);

        }
    }

    public static class EstadoViewHolder extends RecyclerView.ViewHolder {
        protected TextView vFechaHora,vSucursal;

        public EstadoViewHolder(View v) {

            super(v);
            vFechaHora = (TextView)v.findViewById(R.id.fecha_hora_estado);
            vSucursal = (TextView)v.findViewById(R.id.sucursalTextView);

        }
    }
}

