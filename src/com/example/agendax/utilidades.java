package com.example.agendax;

public class utilidades {
	
	public static final String TABLA_FECHA ="FECHA";
	public static final String CAMPO_IDF="idf";
	public static final String CAMPO_DIA="dia";
	public static final String CAMPO_MES="mes";
	public static final String CAMPO_ANO="ano";
	
	public static final String TABLA_ACTIVIDAD="ACTIVIDAD";
	public static final String CAMPO_IDACT = "idAct";
	public static final String CAMPO_DESCRIPCION="descripcion";
	
	public static final String TABLA_SEHACE = "SEHACE";
	public static final String CAMPO_HORA="hora";
	public static final String CAMPO_MINUTO="minuto";
	
	public static final String CREAR_TABLA_FECHA = 
			"CREATE TABLE "+TABLA_FECHA+"("+CAMPO_IDF+" INTEGER PRIMARY KEY AUTOINCREMENT," +
								""+CAMPO_DIA+" INTEGER," +
								""+CAMPO_MES+" INTEGER" +
								""+CAMPO_ANO+" INTEGER)";
	
	public static final String CREAR_TABLA_ACTIVIDAD = 
			"CREATE TABLE "+TABLA_ACTIVIDAD+"("+CAMPO_IDACT+" INTEGER PRIMARY KEY AUTOINCREMENT" +
									""+CAMPO_DESCRIPCION+" TEXT)";
	
	public static final String CREAR_TABLA_SEHACE = 
			"CREATE TABLE "+TABLA_SEHACE+"(idf INTEGER," +
								""+CAMPO_IDACT+" INTEGER" +
								""+CAMPO_HORA+" INTEGER" +
								""+CAMPO_MINUTO+" INTEGER)";
}
