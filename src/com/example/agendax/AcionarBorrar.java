package com.example.agendax;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class AcionarBorrar extends Activity implements android.view.View.OnClickListener{
	
	Button sequihacer;
	Button sehizo;
	Button adiact;
	Button adifecha;
	Button eliact;
	Button elifecha;
	
	EditText tadiact;
	EditText tadifecha;
	EditText teliact;
	EditText telifecha;
	Intent pantsequih;
	int dia,mes,anio;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acionar_borrar);
		sequihacer = (Button)findViewById(R.id.sequierehacer);
        sequihacer.setOnClickListener(this);
        adiact = (Button)findViewById(R.id.adiAct);
        adiact.setOnClickListener(this);
        adifecha = (Button)findViewById(R.id.adifecha);
        adifecha.setOnClickListener(this);
        eliact = (Button)findViewById(R.id.eliAct);
        eliact.setOnClickListener(this);
        elifecha = (Button)findViewById(R.id.elifecha);
        elifecha.setOnClickListener(this);
        sehizo = (Button)findViewById(R.id.sehizo);
        sehizo.setOnClickListener(this);
        
        tadiact = (EditText)findViewById(R.id.textAdiAct);
    	tadifecha = (EditText)findViewById(R.id.textadifecha);
    	tadifecha.setOnClickListener(this);
    	teliact = (EditText)findViewById(R.id.texteliAct);
    	telifecha = (EditText)findViewById(R.id.textelifecha);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.acionar_borrar, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case (R.id.adiAct): {
			String textadiact;
			textadiact = tadiact.getText().toString();
			adicionarActividadSql(textadiact);
		}break;
		case (R.id.adifecha): {
			/*String textadifecha;
			textadifecha = tadifecha.getText().toString();*/
			
			/*Toast t = new Toast(this);
			t.makeText(this, dia+"/"+mes+"/"+anio, Toast.LENGTH_SHORT).show();*/
			
			adicionarFecha(dia,mes,anio);
			
		}break;
		case (R.id.eliAct): {
			String texteliact;
			texteliact = teliact.getText().toString();
		}break;
		case (R.id.elifecha): {
			String textelifecha;
			textelifecha = telifecha.getText().toString();
		}break;
		case (R.id.sehizo): {
			
		}break;
		case(R.id.sequierehacer):{
			pantsequih= new Intent(this,MainActivity.class);
			startActivity(pantsequih);
		}break;
		case(R.id.textadifecha):
		{
			final Calendar c =Calendar.getInstance();
		
			
			DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
				
				@Override
				public void onDateSet(DatePicker view, int y, int m, int d) {
					// TODO Auto-generated method stub
					tadifecha.setText(d+"/"+(m+1)+"/"+y);
					dia=d;
					mes=m+1;
					anio=y;
				}
			}, dia, mes, anio);
			datePickerDialog.show();
			
			/*Toast t = new Toast(this);
			t.makeText(this, dia+"/"+mes+"/"+anio, Toast.LENGTH_SHORT).show();*/
			
			
		}break;
		default:{
			Toast t = new Toast(this);
			t.makeText(this, "no se adiciono funcion", Toast.LENGTH_SHORT).show();
		}
		}
	}

	/*private void adicionarFecha(int dia2, int mes2, int anio2)
	{
		ConexionSQLiteHelper conn= new ConexionSQLiteHelper(this,"bdagenda", null, 1);//enlaza con la BD
		SQLiteDatabase db =  conn.getWritableDatabase();//para editar
		
		String insert = 
				"INSERT INTO "+utilidades.TABLA_ACTIVIDAD+
				"("+utilidades.CAMPO_DIA+"," +
				""+utilidades.CAMPO_MES+"," +
				""+utilidades.CAMPO_ANO+") " +
				"VALUES ("+dia2+","+mes2+","+anio2+")";
				db.execSQL(insert);
				db.close();
	}*/
	private void adicionarFecha(int dia2, int mes2, int anio2) { // insertar datos sin sentencias sql
		ConexionSQLiteHelper conn= new ConexionSQLiteHelper(this,"bdagenda", null, 1);//enlaza con la BD
		SQLiteDatabase db = conn.getWritableDatabase();//para editar
		ContentValues values = new ContentValues();
		
		values.put(utilidades.CAMPO_DIA,dia2);
		values.put(utilidades.CAMPO_MES,mes2);
		values.put(utilidades.CAMPO_ANO,anio2);
		Long i=db.insert(utilidades.TABLA_FECHA,utilidades.CAMPO_IDF,values);
		Toast.makeText(this," "+i, Toast.LENGTH_LONG).show();
		db.close();
	}//falta desarrollar

	private void adicionarActividadSql(String taact) {// adicionar a la base de datos con sentencias sql
		ConexionSQLiteHelper conn= new ConexionSQLiteHelper(this,"bdagenda", null, 1);//enlaza con la BD
		SQLiteDatabase db = conn.getWritableDatabase();//para editar
		// insert into usuario (id,nombre,telefono) values (123,'cristian','498798');
		String insert = 
						"INSERT INTO "+utilidades.TABLA_ACTIVIDAD+
						"("+utilidades.CAMPO_DESCRIPCION+") " +
						"VALUES ("+taact+")";
		db.execSQL(insert);
		db.close();
	}

}
