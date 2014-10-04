package com.uninorte.jdmaestre.calculadoradenotas;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Jose on 23/09/2014.
 */
public class SettingsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        return rootView;
    }


    @Override
    public void onResume() {

        super.onResume();
        Button RetirarButton = (Button)getActivity().findViewById(R.id.RetirarMateriasButton);
        Button ModificarButton =(Button)getActivity().findViewById(R.id.ModificarMateriasButton);
        Button ReiniciarApp = (Button)getActivity().findViewById(R.id.ReiniciarAppButton);

        RetirarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new RetirarFragment();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, fragment).addToBackStack("settings")
                        .commit();
            }
        });

        ModificarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Esta funcion no se encuentra disponible en la version actual de la aplicacion",Toast.LENGTH_LONG).show();
            }
        });

        ReiniciarApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked
                                try {
                                    // clearing app data
                                    Runtime runtime = Runtime.getRuntime();
                                    runtime.exec("pm clear com.uninorte.jdmaestre.calculadoradenotas");

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                Toast.makeText(getActivity(),"Si",Toast.LENGTH_LONG).show();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                Toast.makeText(getActivity(),"No",Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("¿Estas seguro?, Esto eliminara todos los datos y la aplicacion de cerrara.").setPositiveButton("Si", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();

            }
        });
}}
