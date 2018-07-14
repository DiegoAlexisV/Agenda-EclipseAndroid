package com.example.agendax;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

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
		}
		case (R.id.adifecha): {
			String textadifecha;
			textadifecha = tadifecha.getText().toString();
		}
		case (R.id.eliAct): {
			String texteliact;
			texteliact = teliact.getText().toString();
		}
		case (R.id.elifecha): {
			String textelifecha;
			textelifecha = telifecha.getText().toString();
		}
		case (R.id.sehizo): {
			
		}
		case(R.id.sequierehacer):{
			pantsequih= new Intent(this,MainActivity.class);
		}
		}
	}

}
