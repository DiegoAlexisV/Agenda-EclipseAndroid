package entidades;

public class Actividad {
	private int idAct;
	private String descripcion;
	public Actividad(int idAct, String descripcion) {
		this.idAct = idAct;
		this.descripcion = descripcion;
	}
	public int getIdAct() {
		return idAct;
	}
	public void setIdAct(int idAct) {
		this.idAct = idAct;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
