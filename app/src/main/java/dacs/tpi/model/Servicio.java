package dacs.tpi.model;




public class Servicio{

	private String mNombre;

	private String mDescripcion;

	private Float mCoeficienteCosto;

	private Seguro mSeguro;
	
	// Getters/Setters --------------------------------------------------------
	
	public String getNombre() {
		return mNombre;
	}
	
	public void setNombre(String nombre) {
		mNombre = nombre;
	}
	
	public String getDescripcion() {
		return mDescripcion;
	}
	
	public void setDescripcion(String descripcion) {
		mDescripcion = descripcion;
	}
	
	public Float getCoeficienteCosto() {
		return mCoeficienteCosto;
	}
	
	public void setCoeficienteCosto(Float coeficienteCosto) {
		mCoeficienteCosto = coeficienteCosto;
	}
	
	public Seguro getSeguro() {
		return mSeguro;
	}
	
	public void setSeguro(Seguro seguro) {
		mSeguro = seguro;
	}
}
