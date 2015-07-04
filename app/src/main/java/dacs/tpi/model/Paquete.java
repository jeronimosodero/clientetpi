package dacs.tpi.model;

import android.annotation.TargetApi;
import android.os.Build;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class Paquete {


	private String mContenido;

	private float mTamaño;

	private float mPeso;

	private List<Estado> mEstado;

	@TargetApi(Build.VERSION_CODES.KITKAT)
	public Paquete(JSONObject json) {
		try {
			mContenido = json.getString("contenido");
			mTamaño = Float.valueOf(json.getLong("tamaño"));
			mPeso = Float.valueOf(json.getLong("peso"));
			mEstado = new ArrayList<>();
			JSONArray estados = json.getJSONArray("estado");
			for(int i = 0;i<estados.length();i++){
				JSONObject estado = estados.getJSONObject(i);
				Estado estado1 = new Estado(estado);
				mEstado.add(estado1);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	//private Orden orden;
	
	public String getContenido() {
		return mContenido;
	}
	public void setContenido(String contenido) {
		mContenido = contenido;
	}
	public float getTamaño() {
		return mTamaño;
	}
	public void setTamaño(float tamaño) {
		mTamaño = tamaño;
	}
	public float getPeso() {
		return mPeso;
	}
	public void setPeso(float peso) {
		mPeso = peso;
	}
	
	public List<Estado> getEstado() {
		return mEstado;
	}
	public void setEstado(List<Estado> estado) {
		mEstado = estado;
	}
	

	
	
}
