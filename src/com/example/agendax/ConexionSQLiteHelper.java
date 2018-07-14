package com.example.agendax;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionSQLiteHelper extends SQLiteOpenHelper{// hereda de la clase de SQLite
	
	
	
	

	public ConexionSQLiteHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		// ejecutamos sentencias sql
		db.execSQL(utilidades.CREAR_TABLA_ACTIVIDAD);
		db.execSQL(utilidades.CREAR_TABLA_FECHA);
		db.execSQL(utilidades.CREAR_TABLA_SEHACE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int vantigua, int vnueva) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXIST "+utilidades.TABLA_ACTIVIDAD+"");
		db.execSQL("DROP TABLE IF EXIST "+utilidades.TABLA_FECHA+"");
		db.execSQL("DROP TABLE IF EXIST "+utilidades.TABLA_SEHACE+"");
		db.execSQL("DROP TABLE IF EXIST "+utilidades.TABLA_SEQUIEREHACER+"");
		onCreate(db);
		// sirve para controlar las verciones de las tablas de la base de datos
	}

}
