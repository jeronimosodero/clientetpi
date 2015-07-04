package dacs.tpi.model;


import org.json.JSONException;
import org.json.JSONObject;

public class Direccion {


	private String mCalle;

	private int mAltura;

	private int mPiso;

	private int mDepartamento;

	private String mCiudad;

	private String mProvincia;

	private String mPais;

	public Direccion(JSONObject json) {

        try {
            mCalle = json.getString("calle");
            mPiso = json.getInt("piso");
            mDepartamento = json.getInt("departamento");
            mCiudad = json.getString("ciudad");
            mProvincia = json.getString("provincia");
            mPais = json.getString("pais");
        } catch (JSONException e) {
            e.printStackTrace();
        }

	}

	// Getters/Setters -------------------------------------------------------------
	
	public String getCalle() {
		return mCalle;
	}
	public void setCalle(String calle) {
		mCalle = calle;
	}
	public int getAltura() {
		return mAltura;
	}
	public void setAltura(int altura) {
		mAltura = altura;
	}
	public int getPiso() {
		return mPiso;
	}
	public void setPiso(int piso) {
		mPiso = piso;
	}
	public String getCiudad() {
		return mCiudad;
	}
	public void setCiudad(String ciudad) {
		mCiudad = ciudad;
	}
	public String getProvincia() {
		return mProvincia;
	}
	public void setProvincia(String provincia) {
		mProvincia = provincia;
	}
	public String getPais() {
		return mPais;
	}
	public void setPais(String pais) {
		mPais = pais;
	}
	public int getDepartamento() {
		return mDepartamento;
	}
	public void setDepartamento(int departamento) {
		mDepartamento = departamento;
	}

	}