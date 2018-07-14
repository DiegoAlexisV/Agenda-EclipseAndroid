package entidades;

import java.io.Serializable;

public class Fecha implements Serializable{
	private int idf;
	private int dia;
	private int mes;
	private int año;
	public Fecha(int idf, int dia, int mes, int año) {
		super();
		this.idf = idf;
		this.dia = dia;
		this.mes = mes;
		this.año = año;
	}
	public int getIdf() {
		return idf;
	}
	public void setIdf(int idf) {
		this.idf = idf;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getAño() {
		return año;
	}
	public void setAño(int año) {
		this.año = año;
	}
	
}
