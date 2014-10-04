package com.uninorte.jdmaestre.calculadoradenotas;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jose on 08/09/2014.
 */
public class Materia1Fragment extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_materia1, container, false);

        return rootView;
    }


    @Override
    public void onResume() {

        super.onResume();
        final DbManager manager = new DbManager(getActivity());
        final int aux_Materia_id = MainActivity.materia_id;
        manager.Opendb();
        final int aux_cortes = manager.getNumCortes(aux_Materia_id);
        //final int aux_numMaterias = manager.getNumMaterias();
        manager.Closedb();




        // Hace visible solo los Cortes existentes en la materia
        final TextView C1_TextView =(TextView)getView().findViewById(R.id.M1C1_TextView); final EditText M1C1 = (EditText)getView().findViewById(R.id.M1C1_EditText);
        final TextView C2_TextView =(TextView)getView().findViewById(R.id.M1C2_TextView); final EditText M1C2 = (EditText)getView().findViewById(R.id.M1C2_EditText);
        final TextView C3_TextView =(TextView)getView().findViewById(R.id.M1C3_TextView); final EditText M1C3 = (EditText)getView().findViewById(R.id.M1C3_EditText);
        final TextView C4_TextView =(TextView)getView().findViewById(R.id.M1C4_TextView); final EditText M1C4 = (EditText)getView().findViewById(R.id.M1C4_EditText);
        final TextView C5_TextView =(TextView)getView().findViewById(R.id.M1C5_TextView); final EditText M1C5 = (EditText)getView().findViewById(R.id.M1C5_EditText);
        final TextView C6_TextView =(TextView)getView().findViewById(R.id.M1C6_TextView); final EditText M1C6 = (EditText)getView().findViewById(R.id.M1C6_EditText);
        final TextView NotaNecesaria_TextView = (TextView)getView().findViewById(R.id.M1_NotaNecesaria_TV);
        Button Boton = (Button)getView().findViewById(R.id.M1_Button);
        final EditText M1_NotaDeseo = (EditText)getView().findViewById(R.id.M1_Deseo);
        final EditText M1_NotaExpectativa = (EditText)getView().findViewById(R.id.M1_Expectativa);
        final TextView Titulo_TextView = (TextView)getView().findViewById(R.id.M1_Titulo_TextView);


        manager.Opendb();
        Titulo_TextView.setText(manager.getNombreMateria(aux_Materia_id));
        manager.Closedb();

        Boton.setVisibility(View.INVISIBLE);

        // Pasar focus a la pantalla cuando se haga click
        getView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(M1C1.isFocused() || M1C2.isFocused() || M1C3.isFocused() || M1C4.isFocused() || M1C5.isFocused() || M1C6.isFocused()
                        || M1_NotaDeseo.isFocused() || M1_NotaExpectativa.isFocused()){
                    if(motionEvent.getY() >= 72){
                        //Will only enter this if the EditText already has focus
                        //And if a touch event happens outside of the EditText
                        //Which in my case is at the top of my layout
                        //and 72 pixels long
                        M1C1.clearFocus();
                        M1C2.clearFocus();
                        M1C3.clearFocus();
                        M1C4.clearFocus();
                        M1C5.clearFocus();
                        M1C6.clearFocus();
                        M1_NotaDeseo.clearFocus();
                        M1_NotaExpectativa.clearFocus();
                        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                }
                //Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        M1_NotaDeseo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if((keyEvent != null && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER))|| (i == EditorInfo.IME_ACTION_DONE)){

                    M1_NotaDeseo.clearFocus();
                    InputMethodManager  imm = (InputMethodManager) getActivity().getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(M1_NotaExpectativa.getApplicationWindowToken(), 0);
                }

                return false;


            }
        });
        M1_NotaExpectativa.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if((keyEvent != null && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (i == EditorInfo.IME_ACTION_DONE)){

                    M1_NotaExpectativa.clearFocus();
                    InputMethodManager  imm = (InputMethodManager) getActivity().getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(M1_NotaExpectativa.getApplicationWindowToken(), 0);
                }

                return false;



            }
        });


        for (int n = 6; n>aux_cortes; n--){
            switch (n){
                case 2:
                    C2_TextView.setVisibility(View.INVISIBLE); M1C2.setVisibility(View.INVISIBLE);
                    break;
                case 3:
                    C3_TextView.setVisibility(View.INVISIBLE); M1C3.setVisibility(View.INVISIBLE);
                    break;
                case 4:
                    C4_TextView.setVisibility(View.INVISIBLE); M1C4.setVisibility(View.INVISIBLE);
                    break;
                case 5:
                    C5_TextView.setVisibility(View.INVISIBLE); M1C5.setVisibility(View.INVISIBLE);
                    break;
                case 6:
                    C6_TextView.setVisibility(View.INVISIBLE); M1C6.setVisibility(View.INVISIBLE);
                    break;


            }

        }


        MateriasManager.SetNotaMcCxEdiText(M1C1,DbManager.CN_PARCIAL_1,aux_Materia_id,manager);
        MateriasManager.SetNotaMcCxEdiText(M1C2,DbManager.CN_PARCIAL_2,aux_Materia_id,manager);
        MateriasManager.SetNotaMcCxEdiText(M1C3,DbManager.CN_PARCIAL_3,aux_Materia_id,manager);
        MateriasManager.SetNotaMcCxEdiText(M1C4,DbManager.CN_PARCIAL_4,aux_Materia_id,manager);
        MateriasManager.SetNotaMcCxEdiText(M1C5,DbManager.CN_PARCIAL_5,aux_Materia_id,manager);
        MateriasManager.SetNotaMcCxEdiText(M1C6,DbManager.CN_PARCIAL_6,aux_Materia_id,manager);
        MateriasManager.SetDeseoEdiText(M1_NotaDeseo,aux_Materia_id,manager);
        MateriasManager.SetExpectativaEdiText(M1_NotaExpectativa,aux_Materia_id,manager);
        //Asigna promedio necesario
        manager.Opendb();
        final int aux_numMaterias = manager.getNumMaterias();

        final double[] NotaNecesaria = new double[1];
        final double[] NotaNecesariaConCortes = new double[1];
        final double[] NotaDeseo = {manager.getNotaDeseo(aux_Materia_id)};
        final double[] Notaintera = new double[1];

        //NotaNecesaria = MateriasManager.CalcularNotaSinCortes(aux_numMaterias,manager);


        NotaNecesariaConCortes[0] = MateriasManager.CalcularNotaConCortes(aux_Materia_id, aux_cortes, NotaNecesaria[0], manager,getActivity());
        Notaintera[0] = MateriasManager.CalcularNotaInterna(aux_Materia_id, NotaDeseo[0],aux_cortes,manager);
        //Actualiza Nota Necesaria
        manager.Opendb();

        if(!manager.NotasExpectativasVacias(aux_numMaterias)){
            NotaNecesaria[0] = MateriasManager.CalculadoraNotaConExpectatvas(aux_numMaterias, manager);
            //Toast.makeText(getActivity(),"Con expectativas: " +String.valueOf(NotaNecesaria[0]) , Toast.LENGTH_SHORT).show();

        }
        else{
            NotaNecesaria[0] = MateriasManager.CalcularNotaSinCortes(aux_numMaterias, manager);
            //Toast.makeText(getActivity(), "Sin expectativas"+String.valueOf(NotaNecesaria[0]), Toast.LENGTH_SHORT).show();
        }
        manager.Opendb();
        if(manager.NotaDeseoCheck(aux_Materia_id) == true){
            NotaDeseo[0] = manager.getNotaDeseo(aux_Materia_id);
            Notaintera[0] = MateriasManager.CalcularNotaInterna(aux_Materia_id, NotaDeseo[0],aux_cortes,manager);
            NotaNecesaria_TextView.setText(String.format("%.2f",Notaintera[0]));
        }
        else
        {
            if (manager.ParcialesVacios(aux_Materia_id, aux_cortes)){
                NotaNecesaria_TextView.setText(String.format("%.2f",NotaNecesaria[0]));
            }
            else
            {
                NotaNecesariaConCortes[0] = MateriasManager.CalcularNotaConCortes(aux_Materia_id, aux_cortes, NotaNecesaria[0], manager,getActivity());
                NotaNecesaria_TextView.setText(String.format("%.2f",NotaNecesariaConCortes[0]));
            }
        }
        if(Double.parseDouble(NotaNecesaria_TextView.getText().toString()) > 5){

            NotaNecesaria_TextView.setText("Es imposible conseguir el objetivo buscando.");
            NotaNecesaria_TextView.setTextColor(Color.RED);

        }else{

            if(Double.parseDouble(NotaNecesaria_TextView.getText().toString()) < 0){

                NotaNecesaria_TextView.setText("Ya ha conseguido su objetivo en esta materia.");
                NotaNecesaria_TextView.setTextColor(Color.GREEN);
            }else{

                NotaNecesaria_TextView.setTextColor(Color.BLACK);
            }

        }
        manager.Closedb();




        Boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MateriasManager.ActualizarNota(aux_Materia_id,aux_numMaterias,aux_cortes,NotaNecesaria,NotaDeseo,Notaintera,NotaNecesariaConCortes,NotaNecesaria_TextView,manager,getActivity());
            }
        });


        //Configuracion Corte1 EditText
        M1C1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (!b){

                    MateriasManager.CorteEditTextControl(M1C1,DbManager.CN_PARCIAL_1,aux_Materia_id,manager,getActivity());
                    MateriasManager.ActualizarNota(aux_Materia_id,aux_numMaterias,aux_cortes,NotaNecesaria,NotaDeseo,Notaintera,NotaNecesariaConCortes,NotaNecesaria_TextView,manager,getActivity());
                }
            }
        });

        //Configuracion Corte2 EditText
        M1C2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (!b){

                    MateriasManager.CorteEditTextControl(M1C2,DbManager.CN_PARCIAL_2,aux_Materia_id,manager,getActivity());
                    MateriasManager.ActualizarNota(aux_Materia_id,aux_numMaterias,aux_cortes,NotaNecesaria,NotaDeseo,Notaintera,NotaNecesariaConCortes,NotaNecesaria_TextView,manager,getActivity());
                }
            }
        });

        //Configuracion Corte3 EditText
        M1C3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (!b){

                    MateriasManager.CorteEditTextControl(M1C3,DbManager.CN_PARCIAL_3,aux_Materia_id,manager,getActivity());
                    MateriasManager.ActualizarNota(aux_Materia_id,aux_numMaterias,aux_cortes,NotaNecesaria,NotaDeseo,Notaintera,NotaNecesariaConCortes,NotaNecesaria_TextView,manager,getActivity());
                }
            }
        });

        //Configuracion Corte4 EditText
        M1C4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (!b){

                    MateriasManager.CorteEditTextControl(M1C4,DbManager.CN_PARCIAL_4,aux_Materia_id,manager,getActivity());
                    MateriasManager.ActualizarNota(aux_Materia_id,aux_numMaterias,aux_cortes,NotaNecesaria,NotaDeseo,Notaintera,NotaNecesariaConCortes,NotaNecesaria_TextView,manager,getActivity());
                }
            }
        });

        //Configuracion Corte5 EditText
        M1C5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (!b){

                    MateriasManager.CorteEditTextControl(M1C5,DbManager.CN_PARCIAL_5,aux_Materia_id,manager,getActivity());
                    MateriasManager.ActualizarNota(aux_Materia_id,aux_numMaterias,aux_cortes,NotaNecesaria,NotaDeseo,Notaintera,NotaNecesariaConCortes,NotaNecesaria_TextView,manager,getActivity());
                }
            }
        });

        //Configuracion Corte6 EditText
        M1C6.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (!b){

                    MateriasManager.CorteEditTextControl(M1C6,DbManager.CN_PARCIAL_6,aux_Materia_id,manager,getActivity());
                    MateriasManager.ActualizarNota(aux_Materia_id,aux_numMaterias,aux_cortes,NotaNecesaria,NotaDeseo,Notaintera,NotaNecesariaConCortes,NotaNecesaria_TextView,manager,getActivity());
                }
            }
        });

        //Configuracion NotaExpectativa EdiText
        M1_NotaDeseo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b){

                    MateriasManager.DeseoEditTextControl(M1_NotaDeseo,aux_Materia_id,manager,getActivity());
                    MateriasManager.ActualizarNota(aux_Materia_id,aux_numMaterias,aux_cortes,NotaNecesaria,NotaDeseo,Notaintera,NotaNecesariaConCortes,NotaNecesaria_TextView,manager,getActivity());


                }
            }
        });

        M1_NotaExpectativa.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

               if(!b){

                   MateriasManager.ExpectativaEditTextControl(M1_NotaExpectativa,aux_Materia_id,manager,getActivity());
                   if (!M1_NotaExpectativa.getText().toString().equals("")){

                       M1C1.setVisibility(View.INVISIBLE); C1_TextView.setVisibility(View.INVISIBLE);
                       M1C2.setVisibility(View.INVISIBLE); C2_TextView.setVisibility(View.INVISIBLE);
                       M1C3.setVisibility(View.INVISIBLE); C3_TextView.setVisibility(View.INVISIBLE);
                       M1C4.setVisibility(View.INVISIBLE); C4_TextView.setVisibility(View.INVISIBLE);
                       M1C5.setVisibility(View.INVISIBLE); C5_TextView.setVisibility(View.INVISIBLE);
                       M1C6.setVisibility(View.INVISIBLE); C6_TextView.setVisibility(View.INVISIBLE);
                       M1_NotaDeseo.setVisibility(View.INVISIBLE);
                       NotaNecesaria_TextView.setText("La Expectativa bloquea las demas opciones de la materia");
                   }
                   else
                   {
                       for (int n = 1; n<=aux_cortes; n++){
                           switch (n){
                               case 1:
                                   C1_TextView.setVisibility(View.VISIBLE); M1C1.setVisibility(View.VISIBLE);
                                   break;
                               case 2:
                                   C2_TextView.setVisibility(View.VISIBLE); M1C2.setVisibility(View.VISIBLE);
                                   break;
                               case 3:
                                   C3_TextView.setVisibility(View.VISIBLE); M1C3.setVisibility(View.VISIBLE);
                                   break;
                               case 4:
                                   C4_TextView.setVisibility(View.VISIBLE); M1C4.setVisibility(View.VISIBLE);
                                   break;
                               case 5:
                                   C5_TextView.setVisibility(View.VISIBLE); M1C5.setVisibility(View.VISIBLE);
                                   break;
                               case 6:
                                   C6_TextView.setVisibility(View.VISIBLE); M1C6.setVisibility(View.VISIBLE);
                                   break;


                           }

                       }
                       M1_NotaDeseo.setVisibility(View.VISIBLE);
                       MateriasManager.ActualizarNota(aux_Materia_id,aux_numMaterias,aux_cortes,NotaNecesaria,NotaDeseo,Notaintera,NotaNecesariaConCortes,NotaNecesaria_TextView,manager,getActivity());
                   }

               }

            }
        });


        if (!M1_NotaExpectativa.getText().toString().equals("")){

            M1C1.setVisibility(View.INVISIBLE); C1_TextView.setVisibility(View.INVISIBLE);
            M1C2.setVisibility(View.INVISIBLE); C2_TextView.setVisibility(View.INVISIBLE);
            M1C3.setVisibility(View.INVISIBLE); C3_TextView.setVisibility(View.INVISIBLE);
            M1C4.setVisibility(View.INVISIBLE); C4_TextView.setVisibility(View.INVISIBLE);
            M1C5.setVisibility(View.INVISIBLE); C5_TextView.setVisibility(View.INVISIBLE);
            M1C6.setVisibility(View.INVISIBLE); C6_TextView.setVisibility(View.INVISIBLE);
            M1_NotaDeseo.setVisibility(View.INVISIBLE);
            NotaNecesaria_TextView.setText("La Expectativa bloquea las demas opciones de la materia");

        }

    }




}
