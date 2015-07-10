package dacs.tpi.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dacs.tpi.R;
import dacs.tpi.activity.EstadosActivity;
import dacs.tpi.activity.MainActivity;
import dacs.tpi.activity.UnidadActivity;


public class MainFragment extends Fragment {
    private EditText mIp,mOrdenId,mUnidadId;
    private Button mSeguirOrden,mGuardarIp,mActualizarUnidad;
    private SharedPreferences mPrefs = null;
    public static final String SAVE_IP = "save_ip";



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPrefs = getActivity().getSharedPreferences(MainActivity.APP_NAME, MainActivity.MODE_PRIVATE);
        View v = inflater.inflate(R.layout.fragment_main,container,false);
        mOrdenId = (EditText)v.findViewById(R.id.id_editText);
        mSeguirOrden = (Button)v.findViewById(R.id.seguir_button);
        mSeguirOrden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = 0;
                try{
                    id = Integer.parseInt(mOrdenId.getText().toString());
                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getActivity(),"error",Toast.LENGTH_SHORT).show();
                }
                if(id!=0){
                    Intent i = new Intent(MainFragment.this.getActivity(),EstadosActivity.class);
                    i.putExtra(EstadosActivity.EXTRA_PAQUETE_ID,id);
                    startActivity(i);
                }


            }
        });

        mGuardarIp = (Button)v.findViewById(R.id.guardarIp);
        mIp = (EditText)v.findViewById(R.id.ip_editText);
        String ipActual = mPrefs.getString(SAVE_IP,"192....");
        mIp.setText(ipActual);
        mGuardarIp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ip = mIp.getText().toString();
                mPrefs.edit().putString(SAVE_IP, ip).commit();
                Toast.makeText(getActivity(), "Ip guardada", Toast.LENGTH_SHORT).show();
            }
        });

        mUnidadId = (EditText)v.findViewById(R.id.id_unidad);
        mActualizarUnidad = (Button)v.findViewById(R.id.unidad_button);
        mActualizarUnidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = 0;
                try{
                    id = Integer.parseInt(mUnidadId.getText().toString());
                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getActivity(),"error",Toast.LENGTH_SHORT).show();
                }
                if(id!=0){
                    Intent i = new Intent(MainFragment.this.getActivity(),UnidadActivity.class);
                    i.putExtra(UnidadActivity.EXTRA_UNIDAD_ID,id);
                    startActivity(i);
                }
            }
        });

        return v;
    }
}
