package dacs.tpi.fragment;

import android.content.Intent;
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

/**
 * Created by Jerónimo Sodero on 04/07/2015.
 */
public class MainFragment extends Fragment {
    private EditText mEditText;
    private Button mButton;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main,container,false);
        mEditText = (EditText)v.findViewById(R.id.id_editText);
        mButton = (Button)v.findViewById(R.id.seguir_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = 0;
                try{
                    id = Integer.parseInt(mEditText.getText().toString());
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
        return v;
    }
}
