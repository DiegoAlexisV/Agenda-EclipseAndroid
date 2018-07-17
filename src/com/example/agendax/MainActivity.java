package com.example.agendax;

import java.util.ArrayList;


import entidades.Actividad;
import entidades.Fecha;
import entidades.SeQuiereHacer;

import android.os.Bundle;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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
	//vars para almacenar los datos que se seleccione del Spinner
	Fecha addfecha;
	Actividad addActividad;
	//
	//vars para el Listview de la consulta de SEQUIEREHACER
	ListView listaSeQuiH;
	ArrayList<SeQuiereHacer> listaSqh;
	ArrayList<Fecha> listaSqhFecha;
	ArrayList<Actividad> listaSqhActividad;
	ArrayList<String> listaListviewSQH;
	ArrayAdapter adaptadorListviewsqh;
	//
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
        listaSeQuiH=(ListView)findViewById(R.id.listaSeQuiH);
      
        consultarListaSeQuiH();
        consultarListaFechas();//procedimiento para consultar a la tabla FECHA
        //adapter para el Spinner de fechas
        ArrayAdapter<CharSequence> adaptadorSpinnerF = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listaSpinnerfechas);
        fechas.setAdapter(adaptadorSpinnerF);
        //
        consultarListaActividad();//procedimiento para consultar a la tabla ACTIVIDAD
        //Adapter para el Spinner de Actividades
        ArrayAdapter<CharSequence> adaptadorSpinnerA = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaSpinnerActividad);
        actividades.setAdapter(adaptadorSpinnerA);
      //Adapter para el ListView de SeQuiereHacer Consulta SQHaf
        adaptadorListviewsqh = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaListviewSQH);
        listaSeQuiH.setAdapter(adaptadorListviewsqh);
        //para seleccionar los elementos del spinner
        fechas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View v,
					int position, long id) {
				if(position>=1)
				{
					addfecha=(Fecha)listaFecha.get(position-1);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        actividades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View v,
					int position, long id) {
				if(position>=1)
				{
					addActividad=(Actividad)listaActividad.get(position-1);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        
        
        
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
			listaSpinnerActividad.add(a.getIdAct()+"-"+a.getDescripcion());
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
	
	// este es el onClick de todos los views que estan con el setOnclickListener
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
			case(R.id.addSequih)://  para llenar la tabla SEQUIEREHACER y consultar a la bd llenar el list view
			{
				adicionarSeQuiereHacer();
				consultarListaSeQuiH();
			}
		}
	}
	private void consultarListaSeQuiH() {
		SQLiteDatabase db= conn.getReadableDatabase();
		Actividad sqhActividad=null;
		Fecha sqhFecha=null;
		SeQuiereHacer sqh=null;
		listaSqhActividad=new ArrayList<Actividad>();
		listaSqhFecha= new ArrayList<Fecha>();
		listaSqh = new ArrayList<SeQuiereHacer>();
		Cursor cursor=db.rawQuery("SELECT s."+utilidades.CAMPO_IDF+",s."+utilidades.CAMPO_IDACT+",s."+utilidades.CAMPO_HORA2+",s."+utilidades.CAMPO_MINUTO2+",a."+utilidades.CAMPO_DESCRIPCION+",f."+utilidades.CAMPO_DIA+",f."+utilidades.CAMPO_MES+",f."+utilidades.CAMPO_ANO+
				" FROM "+utilidades.TABLA_SEQUIEREHACER+" s,"+utilidades.TABLA_ACTIVIDAD+" a,"+utilidades.TABLA_FECHA+" f" +
				" WHERE s."+utilidades.CAMPO_IDF+" = "+"f."+utilidades.CAMPO_IDF+
				" AND s."+utilidades.CAMPO_IDACT+" = "+"a."+utilidades.CAMPO_IDACT, null);
		while(cursor.moveToNext())
		{
			sqh = new SeQuiereHacer(cursor.getInt(1), cursor.getInt(0), cursor.getInt(2), cursor.getInt(3));
			sqhActividad = new Actividad(cursor.getInt(1), cursor.getString(4));
			sqhFecha = new Fecha(cursor.getInt(0), cursor.getInt(5), cursor.getInt(6), cursor.getInt(7));
			listaSqh.add(sqh);
			listaSqhActividad.add(sqhActividad);
			listaSqhFecha.add(sqhFecha);
			
		
		}
		obtenetlistaSQHaf();
		adaptadorListviewsqh = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaListviewSQH);
		listaSeQuiH.setAdapter(adaptadorListviewsqh);
	}
	//para el array de Strings
	private void obtenetlistaSQHaf() {
		listaListviewSQH = new ArrayList<String>();
		for (int i = 0; i < listaSqh.size(); i++) {
			listaListviewSQH.add(listaSqhFecha.get(i).getIdf()+"-"+listaSqhFecha.get(i).getDia()+"/"+listaSqhFecha.get(i).getMes()+"/"+listaSqhFecha.get(i).getAno()+"\n" +
								listaSqh.get(i).getHora()+":"+listaSqh.get(i).getMinuto()+"\n" +
								listaSqhActividad.get(i).getIdAct()+"-"+listaSqhActividad.get(i).getDescripcion());
		}
	}
	//
	private void adicionarSeQuiereHacer() {
		SQLiteDatabase db = conn.getWritableDatabase();
		String insert ="INSERT INTO " +utilidades.TABLA_SEQUIEREHACER+"(" +
														utilidades.CAMPO_IDF+"," +
														utilidades.CAMPO_IDACT+"," +
														utilidades.CAMPO_HORA2+"," +
														utilidades.CAMPO_MINUTO2+") " +
									"VALUES " +
									"("+addfecha.getIdf()+"," +
										addActividad.getIdAct()+"," +
										hr+"," +
										min+")";
		db.execSQL(insert);
		db.close();
		Toast.makeText(this, "se adiciono correctamente", Toast.LENGTH_SHORT).show();
	}
}
