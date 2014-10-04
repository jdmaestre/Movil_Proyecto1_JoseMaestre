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

public class DatosMaterias extends ActionBarActivity {

    public static int aux_id = 1;

    DbManager manager = new DbManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_materias);

        final Button nSiguienteButton = (Button) findViewById(R.id.siguienteButton);
        final Button nSiguienteMateriaButton = (Button) findViewById(R.id.siguienteMateriaButton);
        final EditText nCorte1 = (EditText) findViewById(R.id.Corte1Field);
        final EditText nCorte2 = (EditText) findViewById(R.id.Corte2Field);
        final EditText nCorte3 = (EditText) findViewById(R.id.Corte3Field);
        final EditText nCorte4 = (EditText) findViewById(R.id.Corte4Field);
        final EditText nCorte5 = (EditText) findViewById(R.id.Corte5Field);
        final EditText nCorte6 = (EditText) findViewById(R.id.Corte6Field);
        final EditText nNumeroCortes = (EditText) findViewById(R.id.NumCortesField);
        final EditText nNombreMateria = (EditText) findViewById(R.id.NombreMateriaField);
        final EditText nNumeroCreditos = (EditText) findViewById(R.id.NumCreditosField);





        nSiguienteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int n = 0;


                if (nNombreMateria.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext(), "Ingrese un nombre", Toast.LENGTH_SHORT).show();
                }
                else{
                    n = n+1;
                }

                if (nNumeroCreditos.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext(), "Ingrese el numero de creditos", Toast.LENGTH_SHORT).show();
                }
                else{
                    n = n+1;
                }

                try {

                    int cortes = Integer.parseInt(String.valueOf(nNumeroCortes.getText()));
                    if (cortes > 0 && cortes < 7) {
                        for (int n1 = 1; n1 <= cortes; n1++) {
                            switch (n1) {
                                case 1:
                                    n1 = 1;
                                    nCorte1.setVisibility(view.VISIBLE);
                                    break;
                                case 2:
                                    n1 = 2;
                                    nCorte2.setVisibility(view.VISIBLE);
                                    break;
                                case 3:
                                    n1 = 3;
                                    nCorte3.setVisibility(view.VISIBLE);
                                    break;
                                case 4:
                                    n1 = 4;
                                    nCorte4.setVisibility(view.VISIBLE);
                                    break;
                                case 5:
                                    n1 = 5;
                                    nCorte5.setVisibility(view.VISIBLE);
                                    break;
                                case 6:
                                    n1 = 6;
                                    nCorte6.setVisibility(view.VISIBLE);
                                    break;
                            }

                        }
                        nSiguienteButton.setVisibility(view.INVISIBLE);
                        nSiguienteMateriaButton.setVisibility(view.VISIBLE);
                        nNumeroCortes.setVisibility(view.INVISIBLE);
                        nNombreMateria.setVisibility(view.INVISIBLE);
                        nNumeroCreditos.setVisibility(view.INVISIBLE);
                        n = n+1;
                    } else {
                        Toast.makeText(getApplicationContext(), "El numero de cortes debe estar entre 1 y 6", Toast.LENGTH_LONG).show();
                    }


                }catch (Exception e){Toast.makeText(getApplicationContext(), "Ingrese el numero de cortes", Toast.LENGTH_LONG).show();}

                if (n==3){
                    Toast.makeText(getApplicationContext(), "guarda en BBDD", Toast.LENGTH_SHORT).show();
                    manager.Opendb();
                    manager.CrearMateria(nNombreMateria.getText().toString(),Integer.parseInt(String.valueOf(nNumeroCortes.getText())),Integer.parseInt(nNumeroCreditos.getText().toString()));
                    manager.Closedb();

                }

            }
        });


        nSiguienteMateriaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int n = 0;

                String sCorte1 = nCorte1.getText().toString();
                String sCorte2 = nCorte2.getText().toString();
                String sCorte3 = nCorte3.getText().toString();
                String sCorte4 = nCorte4.getText().toString();
                String sCorte5 = nCorte5.getText().toString();
                String sCorte6 = nCorte6.getText().toString();
                int Corte1 = 0;
                int Corte2 = 0;
                int Corte3 = 0;
                int Corte4 = 0;
                int Corte5 = 0;
                int Corte6 = 0;


                int cortes = Integer.parseInt(String.valueOf(nNumeroCortes.getText()));





                for (int n1 = 1; n1 <= cortes; n1++) {
                    switch (n1) {
                        case 1:
                            n1 = 1;
                            if (sCorte1.matches("")){
                                Toast.makeText(getApplicationContext(), "Ingrese un valor valido en el corte 1", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                n = n+1;
                                Corte1 = Integer.parseInt(String.valueOf(sCorte1));
                            }
                            break;
                        case 2:
                            n1 = 2;
                            if (sCorte2.matches("")){
                                Toast.makeText(getApplicationContext(), "Ingrese un valor valido en el corte 2", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                n = n+1;
                                Corte2 = Integer.parseInt(String.valueOf(sCorte2));
                            }
                            break;
                        case 3:
                            n1 = 3;
                            if (sCorte3.matches("")){
                                Toast.makeText(getApplicationContext(), "Ingrese un valor valido en el corte 3", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                n = n+1;
                                Corte3 = Integer.parseInt(String.valueOf(sCorte3));
                            }
                            break;
                        case 4:
                            n1 = 4;
                            if (sCorte4.matches("")){
                                Toast.makeText(getApplicationContext(), "Ingrese un valor valido en el corte 4", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                n = n+1;
                                Corte4 = Integer.parseInt(String.valueOf(sCorte4));
                            }
                            break;
                        case 5:
                            n1 = 5;
                            if (sCorte5.matches("")){
                                Toast.makeText(getApplicationContext(), "Ingrese un valor valido en el corte 5", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                n = n+1;
                                Corte5 = Integer.parseInt(String.valueOf(sCorte5));
                            }
                            break;
                        case 6:
                            n1 = 6;
                            if (sCorte6.matches("")){
                                Toast.makeText(getApplicationContext(), "Ingrese un valor valido en el corte 6", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                n = n+1;
                                Corte6 = Integer.parseInt(String.valueOf(sCorte6));
                            }
                            break;
                    }}

                if (Corte1 + Corte2 + Corte3 + Corte4 + Corte5 + Corte6 == 100){
                    n = n+1;
                }
                else{
                    Toast.makeText(getApplicationContext(), "La suma de los cortes debe ser igual a 100", Toast.LENGTH_SHORT).show();
                }



                // Accion del boton luego de comprobar q los campos son validos
                if (n == cortes+1){
                    Toast.makeText(getApplicationContext(), "guarda en BBDD", Toast.LENGTH_SHORT).show();


                    for (int n1 = 1; n1 <= cortes; n1++) {
                        switch (n1) {
                            case 1:
                                n1 = 1;
                                manager.Opendb();
                                manager.ModificiarNotaPorcentage(aux_id,DbManager.CN_PARCIAL_1_PORCENTAGE,Corte1);
                                manager.Closedb();
                                 break;
                            case 2:
                                manager.Opendb();
                                manager.ModificiarNotaPorcentage(aux_id,DbManager.CN_PARCIAL_2_PORCENTAGE,Corte2);
                                manager.Closedb();
                                break;
                            case 3:
                                n1 = 3;
                                manager.Opendb();
                                manager.ModificiarNotaPorcentage(aux_id,DbManager.CN_PARCIAL_3_PORCENTAGE,Corte3);
                                manager.Closedb();
                                break;
                            case 4:
                                n1 = 4;
                                manager.Opendb();
                                manager.ModificiarNotaPorcentage(aux_id,DbManager.CN_PARCIAL_4_PORCENTAGE,Corte4);
                                manager.Closedb();
                                break;
                            case 5:
                                n1 = 5;
                                manager.Opendb();
                                manager.ModificiarNotaPorcentage(aux_id,DbManager.CN_PARCIAL_5_PORCENTAGE,Corte5);
                                manager.Closedb();
                                break;
                            case 6:
                                n1 = 6;
                                manager.Opendb();
                                manager.ModificiarNotaPorcentage(aux_id,DbManager.CN_PARCIAL_6_PORCENTAGE,Corte6);
                                manager.Closedb();
                                break;
                        }}


                    aux_id = aux_id + 1;

                    if (aux_id-1 == DatosUsuario.aux_numMaterias){
                        manager.Opendb();
                        manager.BdEdxiste();
                        manager.Closedb();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }

                    nSiguienteButton.setVisibility(view.VISIBLE);
                    nSiguienteMateriaButton.setVisibility(view.INVISIBLE);
                    nNumeroCortes.setVisibility(view.VISIBLE); nNumeroCortes.setText(null);
                    nNombreMateria.setVisibility(view.VISIBLE); nNombreMateria.setText(null);
                    nNumeroCreditos.setVisibility(view.VISIBLE); nNumeroCreditos.setText(null);
                    nCorte1.setVisibility(view.INVISIBLE); nCorte1.setText(null);
                    nCorte2.setVisibility(view.INVISIBLE); nCorte2.setText(null);
                    nCorte3.setVisibility(view.INVISIBLE); nCorte3.setText(null);
                    nCorte4.setVisibility(view.INVISIBLE); nCorte4.setText(null);
                    nCorte5.setVisibility(view.INVISIBLE); nCorte5.setText(null);
                    nCorte6.setVisibility(view.INVISIBLE); nCorte6.setText(null);

                }

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.datos_materias, menu);
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
