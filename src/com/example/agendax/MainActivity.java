package com.example.agendax;

import java.util.ArrayList;


import entidades.Fecha;

import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.view.View;

public class MainActivity extends Activity implements android.view.View.OnClickListener{
	
	Spinner fechas;
	ArrayList<Fecha> listaFecha;// para obtener los datos de la base de datos
	ArrayList<String> listaSpinnerfechas; //los Strings q estaran en el spinner;
	ConexionSQLiteHelper conn;//para connectar con la base de datos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conn = new ConexionSQLiteHelper(this,"bdagenda",null,1);
        fechas=(Spinner)findViewById(R.id.fechas);
        
        consultarListaFechas();
        
        ArrayAdapter<CharSequence> adaptadorSpinner = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listaSpinnerfechas);
        fechas.setAdapter(adaptadorSpinner);
    }
    private void consultarListaFechas() {
    	SQLiteDatabase db = conn.getReadableDatabase();
    	Fecha fecha=null;
    	listaFecha = new ArrayList<Fecha>();
    	Cursor cursor = db.rawQuery("SELECT * FROM "+utilidades.TABLA_FECHA, null);
    	while(cursor.moveToNext())
    	{
    		fecha = new Fecha();
    		fecha.setIdf(cursor.getInt(0));
    		fecha.setDia(cursor.getInt(1));
    		fecha.setMes(cursor.getInt(2));
    		fecha.setAno(cursor.getInt(3));
    		
    		//log para revisar los datos en consola
    		Log.i("idf", fecha.getIdf()+"");
    		Log.i("dia", fecha.getDia()+"");
    		Log.i("mes", fecha.getMes()+"");
    		Log.i("año", fecha.getAno()+"");
    		//
    		listaFecha.add(fecha);
    	}
    	obtenerLista();
	}
	private void obtenerLista() {
		listaSpinnerfechas = new ArrayList<String>();
		listaSpinnerfechas.add("Seleccione fecha");
		for (int i = 0; i < listaFecha.size(); i++) {
			listaSpinnerfechas.add(listaFecha.get(i).getIdf()+"."+listaFecha.get(i).getDia()+"/"+listaFecha.get(i).getMes()+"/"+listaFecha.get(i).getAno());
		}
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	@Override
	public void onClick(View v) {
		
	}
    
}
