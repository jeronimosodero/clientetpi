package dacs.tpi.model;


import org.json.JSONException;
import org.json.JSONObject;

public class Sucursal{

	private String mTelefono;

	private String mEmail;

	private Direccion mDireccion;

	private Long mId;

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Sucursal(JSONObject json) {
        try {
            mTelefono = json.getString("telefono");
            mEmail = json.getString("email");
			mId = json.getLong("id");
            JSONObject direccion = json.getJSONObject("direccion");
            mDireccion = new Direccion(direccion);
        } catch (JSONException e) {
            e.printStackTrace();
        }


	}

	// Getters/Setters --------------------------------------------------------
	public String getTelefono() {
		return mTelefono;
	}
	public void setTelefono(String telefono) {
		mTelefono = telefono;
	}
	public String getEmail() {
		return mEmail;
	}
	public void setEmail(String email) {
		mEmail = email;
	}
	public Direccion getDireccion() {
		return mDireccion;
	}
	public void setDireccion(Direccion direccion) {
		mDireccion = direccion;
	}

}
