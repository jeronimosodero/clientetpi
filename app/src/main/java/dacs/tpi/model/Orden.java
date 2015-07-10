package dacs.tpi.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;



public class Orden {

	private Date mFecha;

	private Float mMonto;

	private Servicio mServicio;

	private String mPagado;

	private Set<Paquete> mPaquetes;

	private Sucursal mDestino;

	private Sucursal mOrigen;


	private List<Estado> mEstado;

    public Orden(JSONObject json){
        try {
            mEstado = new ArrayList<>();
            mOrigen = new Sucursal(json.getJSONObject("origen"));
            mDestino = new Sucursal(json.getJSONObject("destino"));
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

	public Servicio getServicio() {
		return mServicio;
	}

	public void setServicio(Servicio servicio) {
		mServicio = servicio;
	}

	public String getPagado() {
		return mPagado;
	}

	public void setPagado(String pagado) {
		mPagado = pagado;
	}

	public Sucursal getDestino() {
		return mDestino;
	}

	public void setDestino(Sucursal destino) {
		mDestino = destino;
	}

	public Sucursal getOrigen() {
		return mOrigen;
	}

	public void setOrigen(Sucursal origen) {
		mOrigen = origen;
	}

	public Date getFecha() {
		return mFecha;
	}

	public void setFecha(Date fecha) {
		mFecha = fecha;
	}

	public Float getMonto() {
		return mMonto;
	}

	public void setMonto(Float monto) {
		mMonto = monto;
	}


	public Set<Paquete> getPaquetes() {
		return mPaquetes;
	}

	public void setPaquetes(Set<Paquete> paquetes) {
		mPaquetes = paquetes;
	}

	public List<Estado> getEstado() {
		return mEstado;
	}

	public void setEstado(List<Estado> estado) {
		mEstado = estado;
	}

}
