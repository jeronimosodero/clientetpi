package dacs.tpi.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;


public class Unidad  {

	private String mDominio;

	private String mModelo;

	private Estado mEstado;

	private String mMarca;

	private Float mCapacidadPeso;

	private Float mCapacidadTamaño;

	private Date mFechaAdquisicion;

	private Viaje mViajeActual;

    private Long mId;

	// Constructor

	public Unidad() {
		super();
	}

	public Unidad(String mDominio, String mModelo, Estado mEstado,
			String mMarca, float i, float j, Date mFechaAdquisicion) {
		super();
		this.mDominio = mDominio;
		this.mModelo = mModelo;
		this.mEstado = mEstado;
		this.mMarca = mMarca;
		this.mCapacidadPeso = i;
		this.mCapacidadTamaño = j;
		this.mFechaAdquisicion = mFechaAdquisicion;

	}

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Unidad(JSONObject json){
        try {

            mViajeActual = new Viaje(json.getJSONObject("viajeActual"));

            mId = json.getLong("id");

        } catch (JSONException e) {
            e.printStackTrace();
        }
	}

	public Viaje getViajeActual() {
		return mViajeActual;
	}

	public void setViajeActual(Viaje viajeActual) {
		mViajeActual = viajeActual;
	}

	public String getDominio() {
		return mDominio;
	}

	public void setDominio(String dominio) {
		mDominio = dominio;
	}

	public String getModelo() {
		return mModelo;
	}

	public void setModelo(String modelo) {
		mModelo = modelo;
	}

	public String getMarca() {
		return mMarca;
	}

	public void setMarca(String marca) {
		mMarca = marca;
	}

	public Float getCapacidadPeso() {
		return mCapacidadPeso;
	}

	public void setCapacidadPeso(Float capacidadPeso) {
		mCapacidadPeso = capacidadPeso;
	}

	public Float getCapacidadTamaño() {
		return mCapacidadTamaño;
	}

	public void setCapacidadTamaño(Float capacidadTamaño) {
		mCapacidadTamaño = capacidadTamaño;
	}

	public Date getFechaAdquisicion() {
		return mFechaAdquisicion;
	}

	public void setFechaAdquisicion(Date fechaAdquisicion) {
		mFechaAdquisicion = fechaAdquisicion;
	}

}
