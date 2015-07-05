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
import android.widget.TextView;

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
    private PaqueteAdapter mAdapter;
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
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
           if(mPaquete!=null){
               mAdapter = new PaqueteAdapter(mPaquete);
               mRecycler.setAdapter(mAdapter);
           }
        }
    }

       public class PaqueteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private Paquete paquete;
        private static final int TYPE_HEADER = 0;
        private static final int TYPE_ITEM = 1;

        public PaqueteAdapter(Paquete paquete) {
            this.paquete = paquete;
        }

        public void setPaquete(Paquete paquete){
            this.paquete = paquete;
        }

        @Override
        public int getItemCount() {
            return paquete.getEstado().size()+1;
        }

        @Override
        public int getItemViewType(int position) {
            if(position==0){
                return TYPE_HEADER;
            }
            return TYPE_ITEM;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder vh, int i) {
            if(vh instanceof EstadoViewHolder){
                final Estado estado = paquete.getEstado().get(i -1);

                EstadoViewHolder evh = (EstadoViewHolder) vh;

                evh.vSucursal.setText(estado.getSucursal().getDireccion().getCiudad());
                android.text.format.DateFormat df = new android.text.format.DateFormat();
                String fecha = df.format("dd/MM/yyyy hh:mm",estado.getFecha_hora()).toString();
                evh.vFechaHora.setText(fecha);

            } else {
                HeaderViewHolder hvh = (HeaderViewHolder) vh;
                hvh.vContenido.setText(paquete.getContenido());
            }


        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

            if(i==0){
                View itemView = LayoutInflater.
                        from(viewGroup.getContext()).
                        inflate(R.layout.list_item_card_paquete, viewGroup, false);
                return new HeaderViewHolder(itemView);
            } else {
                View itemView = LayoutInflater.
                        from(viewGroup.getContext()).
                        inflate(R.layout.list_item_card_estado, viewGroup, false);
                return new EstadoViewHolder(itemView);
            }
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

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView vContenido;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            vContenido = (TextView)itemView.findViewById(R.id.contenido_textView);
        }
    }

}

