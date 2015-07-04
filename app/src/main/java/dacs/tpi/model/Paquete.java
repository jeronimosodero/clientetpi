package dacs.tpi.model;

import java.util.List;


public class Paquete {


	private String mContenido;

	private float mTamaño;

	private float mPeso;

	private List<Estado> mEstado;

	//private Orden orden;
	
	public String getContenido() {
		return mContenido;
	}
	public void setContenido(String contenido) {
		mContenido = contenido;
	}
	public float getTamaño() {
		return mTamaño;
	}
	public void setTamaño(float tamaño) {
		mTamaño = tamaño;
	}
	public float getPeso() {
		return mPeso;
	}
	public void setPeso(float peso) {
		mPeso = peso;
	}
	
	public List<Estado> getEstado() {
		return mEstado;
	}
	public void setEstado(List<Estado> estado) {
		mEstado = estado;
	}
	

	
	
}
