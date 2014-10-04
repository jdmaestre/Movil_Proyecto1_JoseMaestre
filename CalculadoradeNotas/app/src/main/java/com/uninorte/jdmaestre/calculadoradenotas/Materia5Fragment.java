package com.uninorte.jdmaestre.calculadoradenotas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Jose on 08/09/2014.
 */
public class Materia5Fragment extends Fragment {




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
        final int aux_Materia_id = 5;
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
            NotaNecesaria_TextView.setText(String.valueOf(Notaintera[0]));
        }
        else
        {
            if (manager.ParcialesVacios(aux_Materia_id, aux_cortes)){
                NotaNecesaria_TextView.setText(String.valueOf(NotaNecesaria[0]));
            }
            else
            {
                NotaNecesariaConCortes[0] = MateriasManager.CalcularNotaConCortes(aux_Materia_id, aux_cortes, NotaNecesaria[0], manager,getActivity());
                NotaNecesaria_TextView.setText(String.valueOf(NotaNecesariaConCortes[0]));
            }
        }
        manager.Closedb();




        Boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MateriasManager.ActualizarNota(aux_Materia_id, aux_numMaterias, aux_cortes, NotaNecesaria, NotaDeseo, Notaintera, NotaNecesariaConCortes, NotaNecesaria_TextView, manager, getActivity());
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
