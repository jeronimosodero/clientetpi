package dacs.tpi.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Ruta {
	private static final long serialVersionUID = 1L;
	


	public Ruta() {
		super();
	}

	private String mNombre;

	private List<Sucursal> mSucursales;

	public Ruta(JSONObject json) {

		try {
            mNombre = json.getString("nombre");

			mSucursales = new ArrayList<>();
			JSONArray sucursales = json.getJSONArray("sucursales");
			for(int i = 0;i<sucursales.length();i++){
				JSONObject sucursal = sucursales.getJSONObject(i);
				Sucursal sucursal1 = new Sucursal(sucursal);
				mSucursales.add(sucursal1);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public String getNombre() {
		return mNombre;
	}

	public void setNombre(String nombre) {
		mNombre = nombre;
	}

	public List<Sucursal> getSucursales() {
		return mSucursales;
	}

	public void setSucursales(List<Sucursal> sucursales) {
		mSucursales = sucursales;
	}

	public Ruta(String mNombre, List<Sucursal> mSucursales) {
		super();
		this.mNombre = mNombre;
		this.mSucursales = mSucursales;
	}

	@Override
	public String toString() {
		return "Ruta [mNombre=" + mNombre + ", mSucursales=" + mSucursales
				+ "]";
	}

}
