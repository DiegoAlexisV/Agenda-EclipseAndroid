package entidades;

import java.io.Serializable;

public class Fecha implements Serializable{
	private int idf;
	private int dia;
	private int mes;
	private int a�o;
	public Fecha(int idf, int dia, int mes, int a�o) {
		super();
		this.idf = idf;
		this.dia = dia;
		this.mes = mes;
		this.a�o = a�o;
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
	public int getA�o() {
		return a�o;
	}
	public void setA�o(int a�o) {
		this.a�o = a�o;
	}
	
}
