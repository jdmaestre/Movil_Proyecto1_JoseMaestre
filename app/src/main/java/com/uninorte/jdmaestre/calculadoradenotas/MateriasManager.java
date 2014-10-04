package com.uninorte.jdmaestre.calculadoradenotas;

import android.content.Context;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jose on 11/09/2014.
 */
public class MateriasManager {



    public static void CorteEditTextControl(EditText MxCx, String NumParcial, int id,DbManager manager , Context context){

        if (MxCx.getText().toString().matches("")){
            manager.Opendb();
            manager.ModificiarNota(id, NumParcial, -1);
            manager.Closedb();
        }
        else{
            if(Double.parseDouble(MxCx.getText().toString()) >= 0 && Double.parseDouble(MxCx.getText().toString()) <= 5){
                manager.Opendb();
                manager.ModificiarNota(id,NumParcial,Double.parseDouble(MxCx.getText().toString()));
                manager.Closedb();
            }
            else{
                Toast.makeText(context, "Ingrese un valor valido en el corte", Toast.LENGTH_SHORT).show();
                MxCx.setText(null);
            }

        }


    }

    public static void SetNotaMcCxEdiText(EditText MxCx, String NumParcial, int id, DbManager manager){

        manager.Opendb();
        Double Nota;
        Nota = manager.getNota( NumParcial, id);

        if (Nota != -1.0){
            MxCx.setText(String.valueOf(Nota));
        }

        manager.Closedb();

    }

    public static void SetDeseoEdiText(EditText MxDeseo,  int id, DbManager manager){

        manager.Opendb();
        Double Nota;
        Nota = manager.getNotaDeseo(id);

        if (Nota != -1.0){
            MxDeseo.setText(String.valueOf(Nota));
        }

        manager.Closedb();

    }

    public static void DeseoEditTextControl( EditText MxExpectativa,int id, DbManager manager, Context context){

        if (MxExpectativa.getText().toString().matches("")){
            manager.Opendb();
            manager.ModificiarDeseo(id,-1);
            manager.Closedb();
        }
        else{
            if(Double.parseDouble(MxExpectativa.getText().toString()) >= 0 && Double.parseDouble(MxExpectativa.getText().toString()) <= 5){
                manager.Opendb();
                manager.ModificiarDeseo(id,Double.parseDouble(MxExpectativa.getText().toString()));
                manager.Closedb();
            }
            else{
                Toast.makeText(context, "Ingrese un valor valido", Toast.LENGTH_SHORT).show();
                MxExpectativa.setText(null);
            }

        }
    }

    public static void ExpectativaEditTextControl( EditText MxExpectativa,int id, DbManager manager, Context context){

        if (MxExpectativa.getText().toString().matches("")){
            manager.Opendb();
            manager.Modificiar_Expectativa(id,-1);
            manager.Closedb();
        }
        else{
            if(Double.parseDouble(MxExpectativa.getText().toString()) >= 0 && Double.parseDouble(MxExpectativa.getText().toString()) <= 5){
                manager.Opendb();
                manager.Modificiar_Expectativa(id,Double.parseDouble(MxExpectativa.getText().toString()));
                manager.Closedb();
            }
            else{
                Toast.makeText(context, "Ingrese un valor valido", Toast.LENGTH_SHORT).show();
                MxExpectativa.setText(null);
            }

        }
    }

    public static void SetExpectativaEdiText(EditText MxDeseo,  int id, DbManager manager){

        manager.Opendb();
        Double Nota;
        Nota = manager.getNotaExpectativa(id);

        if (Nota != -1.0){
            MxDeseo.setText(String.valueOf(Nota));
        }

        manager.Closedb();

    }

    public static double CalcularNotaSinCortes(int numMaterias,DbManager manager){

        manager.Opendb();;
        int CreditosCursado =  manager.getCreditosTotales();
        int CreditosSemAct = manager.getCreditosSemestreAct(numMaterias);
        double PromDeseado = manager.getPromedioDeseado();
        double PromedioAcumulado = manager.getPromedioAcumulado();
        double NotaNecesaria;
        manager.Closedb();



        NotaNecesaria = ((PromDeseado*(CreditosSemAct + CreditosCursado))-(CreditosCursado * PromedioAcumulado))/CreditosSemAct;

        return NotaNecesaria;

    }

    public static double CalcularNotaConCortes(int id, int numCortes,double NotaNecesaria ,DbManager manager, Context context){
        manager.Opendb();;



        double Nota =1 ;
        double NotaMomentanea = 0;
        double ValCorte ;
        double ValCorteMomentaneo = 0;



        for (int n=1; n<=numCortes ;n++){
            Nota = manager.getNota("p"+String.valueOf(n),id );
            ValCorte = manager.getValorCorte(id,"p"+String.valueOf(n)+"p");
            if(Nota != -1){

                ValCorteMomentaneo = ValCorteMomentaneo + ValCorte;
                NotaMomentanea =  NotaMomentanea + Nota*(ValCorte/100);

            }

        }
        manager.Closedb();

        return ((100*(NotaNecesaria - NotaMomentanea))/( 100-ValCorteMomentaneo));

    }

    public static double CalcularNotaInterna(int id, double notaExpectativa, int numCortes, DbManager manager){


        manager.Opendb();;

        double Nota =1 ;
        double NotaMomentanea = 0;
        double ValCorte ;
        double ValCorteMomentaneo = 0;



        for (int n=1; n<=numCortes ;n++){
            Nota = manager.getNota("p"+String.valueOf(n),id );
            ValCorte = manager.getValorCorte(id,"p"+String.valueOf(n)+"p");
            if(Nota != -1){

                ValCorteMomentaneo = ValCorteMomentaneo + ValCorte;
                NotaMomentanea =  NotaMomentanea + Nota*(ValCorte/100);

            }

        }
        manager.Closedb();

        return ((100*(notaExpectativa - NotaMomentanea))/( 100-ValCorteMomentaneo));
    }

    public static double CalculadoraNotaConExpectatvas(int numMaterias,DbManager manager){

        manager.Opendb();
        double CreditosCursado =  manager.getCreditosTotales();
        double CreditosSemAct = manager.getCreditosSemestreAct(numMaterias);
        double PromDeseado = manager.getPromedioDeseado();
        double PromedioAcumulado = manager.getPromedioAcumulado();
        double NotaNecesaria;


        for (int n=1; n<=numMaterias;n++){
            double CreditosMateria = manager.getCreditosMateria(n);
            double NotaExpMat = manager.getNotaExpectativa(n);
            if(manager.NotaExpectativaCheck(n)){

                PromedioAcumulado = (PromedioAcumulado*CreditosCursado+NotaExpMat*CreditosMateria)/(CreditosCursado+CreditosMateria);
                CreditosCursado = CreditosCursado + CreditosMateria;
                CreditosSemAct = CreditosSemAct - CreditosMateria;

             //   Toast.makeText(context, String.valueOf(n)+" Prom: "+String.valueOf(PromedioAcumulado) , Toast.LENGTH_LONG).show();


            }

        }

        NotaNecesaria = ((PromDeseado*(CreditosSemAct + CreditosCursado))-(CreditosCursado * PromedioAcumulado))/CreditosSemAct;
       // Toast.makeText(context," NotNec: "+String.valueOf(NotaNecesaria), Toast.LENGTH_LONG).show();

        manager.Closedb();;
        return NotaNecesaria;

    }


    public static void ActualizarNota(int aux_Materia_id, int aux_numMaterias,int aux_cortes, double NotaNecesaria[],double NotaDeseo[], double Notaintera[],
                                        double NotaNecesariaConCortes[], TextView NotaNecesaria_TextView, DbManager manager, Context context){

        manager.Opendb();

        if(!manager.NotasExpectativasVacias(aux_numMaterias)){
            NotaNecesaria[0] = MateriasManager.CalculadoraNotaConExpectatvas(aux_numMaterias, manager);
           // Toast.makeText(context,"Con expectativas: " +String.valueOf(NotaNecesaria[0]) , Toast.LENGTH_SHORT).show();

        }
        else{
            NotaNecesaria[0] = MateriasManager.CalcularNotaSinCortes(aux_numMaterias, manager);
            //Toast.makeText(context, "Sin expectativas"+String.valueOf(NotaNecesaria[0]), Toast.LENGTH_SHORT).show();
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
                NotaNecesariaConCortes[0] = MateriasManager.CalcularNotaConCortes(aux_Materia_id, aux_cortes, NotaNecesaria[0], manager,context);
                NotaNecesaria_TextView.setText(String.format("%.2f",NotaNecesariaConCortes[0]));
            }
        }
        manager.Closedb();


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

    }



}


