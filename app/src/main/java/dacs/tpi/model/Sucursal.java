package dacs.tpi.model;



public class Sucursal{

	private String mTelefono;

	private String mEmail;

	private Direccion mDireccion;
	
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
