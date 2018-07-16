package com.example.agendax;

import java.util.ArrayList;


import entidades.Actividad;
import entidades.Fecha;

import android.os.Bundle;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.view.View;

public class MainActivity extends Activity implements android.view.View.OnClickListener{
	
	TextView hora;
	Button addsequih;
	int hr;
	int min;
	//vars para el spiner de fechas
	Spinner fechas;
	ArrayList<Fecha> listaFecha;// para obtener los datos de la base de datos
	ArrayList<String> listaSpinnerfechas; //los Strings q estaran en el spinner;
	//
	//vars para el spinner de actividad
	Spinner actividades;
	ArrayList<Actividad> listaActividad;
	ArrayList<String> listaSpinnerActividad;
	//
	
	ConexionSQLiteHelper conn;//para connectar con la base de datos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conn = new ConexionSQLiteHelper(this,"bdagenda",null,1);
        hora=(TextView)findViewById(R.id.hora);
        addsequih = (Button)findViewById(R.id.addSequih);
        addsequih.setOnClickListener(this);
        hora.setOnClickListener(this);
        fechas=(Spinner)findViewById(R.id.fechas);
        actividades=(Spinner)findViewById(R.id.actividades);
        
        consultarListaFechas();//procedimiento para consultar a la tabla FECHA
        //adapter para el Spinner de fechas
        ArrayAdapter<CharSequence> adaptadorSpinnerF = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listaSpinnerfechas);
        fechas.setAdapter(adaptadorSpinnerF);
        //
        consultarListaActividad();//procedimiento para consultar a la tabla ACTIVIDAD
        //Adapter para el Spinner de Actividades
        ArrayAdapter<CharSequence> adaptadorSpinnerA = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaSpinnerActividad);
        actividades.setAdapter(adaptadorSpinnerA);
    }
    //
    private void consultarListaActividad() {
    	SQLiteDatabase db = conn.getReadableDatabase();
    	Actividad actividad = null;
    	listaActividad = new ArrayList<Actividad>();
    	Cursor cursor = db.rawQuery("SELECT * FROM "+utilidades.TABLA_ACTIVIDAD, null);
    	while(cursor.moveToNext()){
    		actividad = new Actividad(cursor.getInt(0), cursor.getString(1));
    		
    		//logs para verlo desde consola
    		Log.i("idf", actividad.getIdAct()+"");
    		Log.i("descripcion",actividad.getDescripcion());
    		//
    		
    		listaActividad.add(actividad);
    	}
    	obtenerListaA();
	}
    private void obtenerListaA() {
    	listaSpinnerActividad = new ArrayList<String>();
    	listaSpinnerActividad.add("Seleccione Actividad");
    	Actividad a = new Actividad();
    	for (int i = 0; i < listaActividad.size(); i++) {
			a=listaActividad.get(i);
			listaSpinnerActividad.add(a.getIdAct()+""+a.getDescripcion());
		}
	}
	//
	private void consultarListaFechas() {
    	SQLiteDatabase db = conn.getReadableDatabase();//para leer la base de datos
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
    	obtenerListaF();
	}
	private void obtenerListaF() {
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
		switch(v.getId())
		{
			case(R.id.hora):
			{
				TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
					@Override
					public void onTimeSet(TimePicker view, int h, int m) {
						// TODO Auto-generated method stub
						hora.setText(h+":"+m);
						hr=h;
						min=m;
					}
				},hr, min,false);
				timePickerDialog.show();
			}break;
			case(R.id.addSequih):
			{
				Toast.makeText(this,hr+":"+min,Toast.LENGTH_SHORT).show();
			}
		}
	}
    
}
