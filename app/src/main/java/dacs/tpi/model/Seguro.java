package dacs.tpi.model;



public class Seguro {


	private String mNombre;

	private Float mPorcentajeCubierto;

	private Float mPrecio;

	private int mDuracion;
	
	// Getters/Setters --------------------------------------------------------
	public String getNombre() {
		return mNombre;
	}
	public void setNombre(String nombre) {
		mNombre = nombre;
	}
	public Float getPorcentajeCubierto() {
		return mPorcentajeCubierto;
	}
	public void setPorcentajeCubierto(Float porcentajeCubierto) {
		mPorcentajeCubierto = porcentajeCubierto;
	}
	public Float getPrecio() {
		return mPrecio;
	}
	public void setPrecio(Float precio) {
		mPrecio = precio;
	}
	public int getDuracion() {
		return mDuracion;
	}
	public void setDuracion(int duracion) {
		mDuracion = duracion;
	}

	
}
