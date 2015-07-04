package dacs.tpi.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;




public class Estado{


	private Date mFecha_hora;

	private float mLatitud;

	private float mLongitud;

	private Sucursal mSucursal;


    public Estado(JSONObject json) {

		try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");

			String fecha = json.getString("fecha");
            fecha = fecha + "-"+json.getString("hora");

			mFecha_hora = formatter.parse(fecha);

            mLatitud = Float.valueOf(json.getLong("latitud"));
            mLongitud = Float.valueOf(json.getLong("longitud"));
            JSONObject sucursal = json.getJSONObject("sucursal");
            mSucursal = new Sucursal(sucursal);

		} catch (Exception e) {
			e.printStackTrace();
		}



	}


	public float getLatitud() {
		return mLatitud;
	}
	public void setLatitud(float latitud) {
		mLatitud = latitud;
	}
	public float getLongitud() {
		return mLongitud;
	}
	public void setLongitud(float longitud) {
		mLongitud = longitud;
	}
	public Sucursal getSucursal() {
		return mSucursal;
	}
	public void setSucursal(Sucursal sucursal) {
		mSucursal = sucursal;
	}

    public Date getFecha_hora() {
        return mFecha_hora;
    }

    public void setFecha_hora(Date fecha_hora) {
        mFecha_hora = fecha_hora;
    }
}
