package com.uninorte.jdmaestre.calculadoradenotas;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.uninorte.jdmaestre.calculadoradenotas.R;

public class DatosUsuario extends ActionBarActivity {

    public static int aux_numMaterias = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_usuario);

        Button mComenzarButton = (Button) findViewById(R.id.ComenzarButton);
        final EditText NombreField = (EditText) findViewById(R.id.NombreField);
        final EditText CreditosField = (EditText) findViewById(R.id.creditosField);
        final EditText PromedioField = (EditText) findViewById(R.id.promedioField);
        final EditText nMateriasField = (EditText) findViewById(R.id.nMateriasField);
        final EditText PromedioAcuField = (EditText)findViewById(R.id.promedioAcuDeseadoField);

        final DbManager manager = new DbManager(this);

        mComenzarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int n = 0;
                String auxString = "";
                double auxDouble = 11;

                if (NombreField.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext(), "Ingrese un nombre", Toast.LENGTH_SHORT).show();
                }
                else{
                    n = n+1;
                }

                try{
                    auxDouble = Double.parseDouble(CreditosField.getText().toString());
                }
                catch(Exception e){}


                Toast.makeText(getApplicationContext(), String.valueOf(auxDouble), Toast.LENGTH_SHORT).show();

                if ( CreditosField.getText().toString().matches("")  ){
                    Toast.makeText(getApplicationContext(), "Ingrese el numero de creditos cursados", Toast.LENGTH_SHORT).show();
                }
                else{
                    n = n+1;
                }

                try{
                    auxDouble = Double.parseDouble(PromedioField.getText().toString());
                }
                catch(Exception e){}

                if ( PromedioField.getText().toString().matches("") ||  auxDouble > 5 || auxDouble < 0   ){
                    Toast.makeText(getApplicationContext(), "Ingrese un valor valido en el promedio", Toast.LENGTH_SHORT).show();
                }
                else{
                    n = n+1;
                }

                if ( PromedioAcuField.getText().toString().matches("") ||  auxDouble > 5 || auxDouble < 0   ){
                    Toast.makeText(getApplicationContext(), "Ingrese un valor valido en el promedio acumulado", Toast.LENGTH_SHORT).show();
                }
                else{
                    n = n+1;
                }

                try{
                    auxDouble = Double.parseDouble(nMateriasField.getText().toString());
                }
                catch(Exception e){}

                if ( nMateriasField.getText().toString().matches("")  ||  auxDouble > 6 || auxDouble < 1   ){
                    Toast.makeText(getApplicationContext(), "El numero de materias cusrsadas debe estar entre 1 y 6", Toast.LENGTH_SHORT).show();
                }
                else{
                    n = n+1;
                }


                if (n == 5){
                    Toast.makeText(getApplicationContext(), "guarda en BBDD", Toast.LENGTH_SHORT).show();

                    aux_numMaterias = Integer.parseInt(nMateriasField.getText().toString());
                    manager.Opendb();
                    manager.InsertarUsuario(NombreField.getText().toString() ,Integer.parseInt(nMateriasField.getText().toString()),
                            Double.parseDouble(PromedioField.getText().toString()), Double.parseDouble(PromedioAcuField.getText().toString())
                            ,Integer.parseInt(CreditosField.getText().toString()));
                    manager.Closedb();

                    Intent intent = new Intent(getApplicationContext(), DatosMaterias.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);



                }
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.datos_usuario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
