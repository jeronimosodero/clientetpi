package dacs.tpi.model;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Viaje {


	private Date mFechaPartida;

	private List<Orden> mOrdenes;

	private Ruta mRuta;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Viaje(JSONObject json) {
		try {
            mRuta = new Ruta(json.getJSONObject("ruta"));
            mOrdenes = new ArrayList<>();
            id = json.getLong("id");
            JSONArray ordenes = json.getJSONArray("ordenes");

            for(int i = 0;i<ordenes.length();i++){
                JSONObject orden = ordenes.getJSONObject(i);
                Orden orden1 = new Orden(orden);
                mOrdenes.add(orden1);
            }



		} catch (JSONException e) {
			e.printStackTrace();
		}
	}


	public Date getFechaPartida() {
		return mFechaPartida;
	}
	public void setFechaPartida(Date fechaPartida) {
		mFechaPartida = fechaPartida;
	}
	public List<Orden> getOrdenes() {
		return mOrdenes;
	}
	public void setOrdenes(List<Orden> ordenes) {
		mOrdenes = ordenes;
	}
	public Ruta getRuta() {
		return mRuta;
	}
	public void setRuta(Ruta ruta) {
		mRuta = ruta;
	}
}
