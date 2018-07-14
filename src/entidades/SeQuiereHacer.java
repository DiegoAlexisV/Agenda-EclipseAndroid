package entidades;

import java.io.Serializable;

public class SeQuiereHacer implements Serializable{
	private int idAct;
	private int idf;
	private int hora;
	private int minuto;
	public SeQuiereHacer(int idAct, int idf, int hora, int minuto) {

		this.idAct = idAct;
		this.idf = idf;
		this.hora = hora;
		this.minuto = minuto;
	}
	public int getIdAct() {
		return idAct;
	}
	public void setIdAct(int idAct) {
		this.idAct = idAct;
	}
	public int getIdf() {
		return idf;
	}
	public void setIdf(int idf) {
		this.idf = idf;
	}
	public int getHora() {
		return hora;
	}
	public void setHora(int hora) {
		this.hora = hora;
	}
	public int getMinuto() {
		return minuto;
	}
	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}
	
	
}
