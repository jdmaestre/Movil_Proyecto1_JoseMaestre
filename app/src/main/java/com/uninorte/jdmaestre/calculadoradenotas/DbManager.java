package com.uninorte.jdmaestre.calculadoradenotas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;

/**
 * Created by Jose on 09/09/2014.
 */
public class DbManager {

    //



    //Nombres de Tablas
    public static final String T_NAME_Users ="usuario";
    public static final String T_NAME_Materias = "materias";
    public static final String T_NAME_MateriasExp = "materiasExp";

    //Nombres de campos usador por todas las tablas

    public static final String CN_ID = "_id";
    public static final String CN_NAME = "name";

    //Nobres de campos usados por Usuario
    public static final String CN_NUMMATERIAS = "numMaterias";
    public static final String CN_PROMACU = "promAcumulado";
    public static final String CN_CREDCURSADOS = "credCursados";
    public static final String CN_PROMACU_DESEADO = "promAcuDeseado";
    public static final String CN_AUX_BD_EXISTENCIA = "auxBd";

    //Nobres de campos usados por Materias
    public static final String CN_CORTES = "cortes";
    public static final String CN_NUM_CREDITOS = "numCreditos";

    public static final String CN_PARCIAL_1 = "p1";
    public static final String CN_PARCIAL_2 = "p2";
    public static final String CN_PARCIAL_3 = "p3";
    public static final String CN_PARCIAL_4 = "p4";
    public static final String CN_PARCIAL_5 = "p5";
    public static final String CN_PARCIAL_6 = "p6";

    public static final String CN_PARCIAL_1_PORCENTAGE = "p1p";
    public static final String CN_PARCIAL_2_PORCENTAGE = "p2p";
    public static final String CN_PARCIAL_3_PORCENTAGE = "p3p";
    public static final String CN_PARCIAL_4_PORCENTAGE = "p4p";
    public static final String CN_PARCIAL_5_PORCENTAGE = "p5p";
    public static final String CN_PARCIAL_6_PORCENTAGE = "p6p";

    //Nobres de campos usados por Materias_exp (Expectativa)

    public static final String CN_EXP_ACUMULADO = "expAcumulado";
    public static final String CN_EXP_MATERIA = "expMateria";



    //Query Create Table usuarios

    public static final String CREATE_TABLE_Users = "create table " +T_NAME_Users+ " (" + CN_ID +
            " integer primary key autoincrement," + CN_NAME + " text," + CN_NUMMATERIAS + " int,"
            + CN_PROMACU + " double," + CN_CREDCURSADOS + " int," + CN_PROMACU_DESEADO + " double," + CN_AUX_BD_EXISTENCIA + " int);";

    //Query Create Table materias

    public static final String CREATE_TABLE_Materias = "create table " + T_NAME_Materias + " (" +
            CN_ID + " integer primary key autoincrement," + CN_NAME + " text," + CN_CORTES + " int," + CN_NUM_CREDITOS + " int,"+
            CN_PARCIAL_1 + " double," +
            CN_PARCIAL_2 + " double," +
            CN_PARCIAL_3 + " double," +
            CN_PARCIAL_4 + " double," +
            CN_PARCIAL_5 + " double," +
            CN_PARCIAL_6 + " double," +
            CN_PARCIAL_1_PORCENTAGE + " int," +
            CN_PARCIAL_2_PORCENTAGE + " int," +
            CN_PARCIAL_3_PORCENTAGE + " int," +
            CN_PARCIAL_4_PORCENTAGE + " int," +
            CN_PARCIAL_5_PORCENTAGE + " int," +
            CN_PARCIAL_6_PORCENTAGE + " int);";

    //Query Create Table materiasExp

    public static final String CREATE_TABLE_Materias_Exp = "create table " + T_NAME_MateriasExp + " (" +
            CN_ID + " integer primary key autoincrement," + CN_NAME + " text,"  + CN_EXP_MATERIA + " double,"
            + CN_EXP_ACUMULADO + " double);";

    private DbHelper helper;
    private SQLiteDatabase db;

    public DbManager(Context context){

        helper = new DbHelper(context);


    }

    public void Opendb (){
        db = helper.getWritableDatabase();
    }

    public void Closedb (){
        db.close();
    }



    public ContentValues genContentValuesUsuario(String nombre, int numMaterias, double promAcumulado,double promAcuDeseado , int credCursados) {

        ContentValues values = new ContentValues();
        values.put(CN_NAME, nombre);
        values.put(CN_NUMMATERIAS, numMaterias);
        values.put(CN_PROMACU, promAcumulado);
        values.put(CN_PROMACU_DESEADO, promAcuDeseado);
        values.put(CN_CREDCURSADOS, credCursados);
        values.put(CN_AUX_BD_EXISTENCIA,0);

        return values;
    }

    // Insertar Datos del usuario
    public void InsertarUsuario (String nombre, int numMaterias, double promAcumulado, double promAcuDeseado , int credCursados ){

        db.insert(T_NAME_Users, null, genContentValuesUsuario(nombre, numMaterias, promAcumulado,promAcuDeseado , credCursados));

    }

    public void BdEdxiste(){
        ContentValues values = new ContentValues();
        values.put(CN_AUX_BD_EXISTENCIA,1);
        db.update(T_NAME_Users,values,"_id=1", null);

    }

    public int BdExisteCheck(){

        String[] columna =new String[]{CN_AUX_BD_EXISTENCIA};
        Cursor cursor =  db.query(T_NAME_Users,columna,null,null,null,null,null);
        cursor.moveToFirst();

        return cursor.getInt(0);
    }

    public int getNumMaterias(){

        String[] columna =new String[]{CN_NUMMATERIAS};
        Cursor cursor =  db.query(T_NAME_Users,columna,null,null,null,null,null);
        cursor.moveToFirst();

        if(cursor.getCount() > 0) {
            int numMaterias = cursor.getInt(0);
            return numMaterias;
        }
        else {
            return -1;
        }




    }

    // Otener Nota, si el campo en null devulve -1.0
   public double getNota( String NumParcial, int id){

       Double Nota;
       String[] columna =new String[]{NumParcial};
       Cursor cursor =  db.query(T_NAME_Materias,columna,"_id="+String.valueOf(id),null,null,null,null);
       cursor.moveToFirst();

      // for(int n=1; n< id; n++){ cursor.moveToNext();}



       if (!cursor.isNull(0)){

            Nota = cursor.getDouble(0);
       }else{
           Nota = -1.0;
       }



       return Nota;
   }


    public int getNumCortes(int id){

        String[] columna =new String[]{CN_CORTES};
        Cursor cursor =  db.query(T_NAME_Materias,columna,"_id="+String.valueOf(id),null,null,null,null);
        cursor.moveToFirst();
        int numMaterias = cursor.getInt(0);
        return numMaterias;


    }

    public Cursor AuxTitulosDrawerLayout(){

        String[] columna = new String[]{CN_NAME};
        Cursor cursor = db.query(T_NAME_Materias,columna,null,null,null,null,null);


        return cursor;


    }

    //Crear Materia y Llnala tabla de expectativas

    public void CrearMateria (String Nombre , int Cortes, int Creditos){

        ContentValues values = new ContentValues();
        values.put(CN_NAME, Nombre);

        ContentValues values1 = new ContentValues();
        db.insert(T_NAME_MateriasExp, null, values);
        values1.put(CN_NAME, Nombre);
        values1.put(CN_CORTES, Cortes);
        values1.put(CN_NUM_CREDITOS, Creditos);
        //values.putNull(CN_PARCIAL_1); values.putNull(CN_PARCIAL_2); values.putNull(CN_PARCIAL_3);
        //values.putNull(CN_PARCIAL_4); values.putNull(CN_PARCIAL_5); values.putNull(CN_PARCIAL_6);
        //values.putNull(CN_PARCIAL_1_PORCENTAGE); values.putNull(CN_PARCIAL_2_PORCENTAGE); values.putNull(CN_PARCIAL_3_PORCENTAGE);
        //values.putNull(CN_PARCIAL_4_PORCENTAGE); values.putNull(CN_PARCIAL_5_PORCENTAGE); values.putNull(CN_PARCIAL_6_PORCENTAGE);
        db.insert(T_NAME_Materias, null , values1);

    }

    // Insertar % Cortes
    public void ModificiarNotaPorcentage (int id, String rowParcial, int Peso){

        ContentValues values = new ContentValues();
        values.put(rowParcial,Peso);
        db.update(T_NAME_Materias,values,"_id="+String.valueOf(id).toString(),null);


    }

    //Modificar Nota
    public void ModificiarNota (int id, String rowParcial, double Peso){

        ContentValues values = new ContentValues();

        if (Peso == -1){
            values.putNull(rowParcial);
            db.update(T_NAME_Materias,values,"_id="+String.valueOf(id).toString(),null);
        }
        else{
            values.put(rowParcial,Peso);
            db.update(T_NAME_Materias,values,"_id="+String.valueOf(id).toString(),null);
        }



    }

    //Modificar Nota
    public void ModificiarPromAcuDeseado (double PromAcuDeseado){

        ContentValues values = new ContentValues();
            values.put(CN_PROMACU_DESEADO,PromAcuDeseado);
            db.update(T_NAME_Users,values,null,null);
    }

    public void ModificiarDeseo (int id, double Expectativa){

        ContentValues values = new ContentValues();

        if ((Expectativa == -1)){
            values.putNull(CN_EXP_MATERIA);
            db.update(T_NAME_MateriasExp,values,"_id="+String.valueOf(id).toString(),null);
        }else
        {
            values.put(CN_EXP_MATERIA,Expectativa);
            db.update(T_NAME_MateriasExp,values,"_id="+String.valueOf(id).toString(),null);
        }



    }

    public void Modificiar_Expectativa (int id, double Expectativa){

        ContentValues values = new ContentValues();

        if ((Expectativa == -1)){
            values.putNull(CN_EXP_ACUMULADO);
            db.update(T_NAME_MateriasExp,values,"_id="+String.valueOf(id).toString(),null);
        }else
        {
            values.put(CN_EXP_ACUMULADO,Expectativa);
            db.update(T_NAME_MateriasExp,values,"_id="+String.valueOf(id).toString(),null);
        }



    }

    //Obtener Nombre de usuario
    public String getNombreUsuario(){
        String[] columna =new String[]{CN_NAME};
        Cursor cursor =  db.query(T_NAME_Users,columna,null,null,null,null,null);
        cursor.moveToFirst();
        String Nombre = cursor.getString(0);
        return Nombre;

    }

    //Obtener Creditos Totales
    public int getCreditosTotales(){
        String[] columna =new String[]{CN_CREDCURSADOS};
        Cursor cursor =  db.query(T_NAME_Users,columna,null,null,null,null,null);
        cursor.moveToFirst();
        int CreditosTotales = cursor.getInt(0);
        return CreditosTotales;

    }

    //Obtener Creditos en semestra actual
    public int getCreditosSemestreAct(int numMaterias){
        String[] columna =new String[]{CN_NUM_CREDITOS};
        Cursor cursor =  db.query(T_NAME_Materias,columna,null,null,null,null,null);
        cursor.moveToFirst();

        int CreditosTotales = 0;
        for(int n=1; n<=numMaterias;n++){
            CreditosTotales = CreditosTotales + cursor.getInt(0);
            cursor.moveToNext();
        }

        return CreditosTotales;
    }

    public int getNumeroCortes(int id){

        String[] columna =new String[]{CN_CORTES};
        Cursor cursor =  db.query(T_NAME_Materias,columna,null,null,null,null,null);
        cursor.moveToFirst();
        return  cursor.getInt(0);
    }

    //Obtener Valor del Corte
    public int getValorCorte(int id, String Corte){
        String[] columna =new String[]{Corte};
        Cursor cursor =  db.query(T_NAME_Materias,columna,"_id="+String.valueOf(id),null,null,null,null);
        cursor.moveToFirst();
        int CreditosTotales = cursor.getInt(0);
        return CreditosTotales;

    }

    //Verifica si hay notas de parciales
    public boolean ParcialesVacios(int id, int numParciales){

        Cursor cursor;
        boolean Vacios = true;
        String[] columna = new String[1];

        for(int n=1; n<=numParciales; n++){
            columna[0] = ("p"+String.valueOf(n));
            cursor =  db.query(T_NAME_Materias,columna,"_id="+String.valueOf(id),null,null,null,null);

            cursor.moveToFirst();
            if (!cursor.isNull(0)){
                Vacios = false;
            }
        }

        return Vacios;


    }

    public boolean NotasExpectativasVacias( int numMaterias){

        Cursor cursor;
        boolean Vacios = true;
        String[] columna = new String[]{CN_EXP_ACUMULADO};

        for(int n=1; n<=numMaterias; n++){
            cursor =  db.query(T_NAME_MateriasExp,columna,"_id="+String.valueOf(n),null,null,null,null);

            cursor.moveToFirst();
            if (!cursor.isNull(0)){
                Vacios = false;
            }
        }

        return Vacios;


    }

    //Verifica si la materia tiene nota Deseo
    public boolean NotaDeseoCheck(int id){

        Cursor cursor;
        boolean Vacios = true;
        String[] columna = new String[]{CN_EXP_MATERIA};

            cursor =  db.query(T_NAME_MateriasExp,columna,"_id="+String.valueOf(id),null,null,null,null);
            cursor.moveToFirst();
            if (cursor.isNull(0)){
                Vacios = false;
            }

        return Vacios;


    }

    //Verifica si la materia tiene nota Expectatica
    public boolean NotaExpectativaCheck(int id){

        Cursor cursor;
        boolean Vacios = true;
        String[] columna = new String[]{CN_EXP_ACUMULADO};

        cursor =  db.query(T_NAME_MateriasExp,columna,"_id="+String.valueOf(id),null,null,null,null);
        cursor.moveToFirst();
        if (cursor.isNull(0)){
            Vacios = false;
        }

        return Vacios;


    }

    //Obtener Creditos Materia
    public int getCreditosMateria(int id){
        String[] columna =new String[]{CN_NUM_CREDITOS};
        Cursor cursor =  db.query(T_NAME_Materias,columna,"_id="+String.valueOf(id),null,null,null,null);
        cursor.moveToFirst();
        int Creditos = cursor.getInt(0);
        return Creditos;

    }

    //Obtener Promedio Cumulado
    public double getPromedioAcumulado(){
        String[] columna =new String[]{CN_PROMACU};
        Cursor cursor =  db.query(T_NAME_Users,columna,null,null,null,null,null);
        cursor.moveToFirst();
        return  cursor.getDouble(0);


    }

    //Obtener Promedio Cumulado Desedo
    public double getPromedioDeseado(){
        String[] columna =new String[]{CN_PROMACU_DESEADO};
        Cursor cursor =  db.query(T_NAME_Users,columna,null,null,null,null,null);
        cursor.moveToFirst();
        double PromedioDeseado = cursor.getDouble(0);
        return PromedioDeseado;

    }


    public double getNotaDeseo(int id){
        double Nota;
        String[] columna =new String[]{CN_EXP_MATERIA};
        Cursor cursor =  db.query(T_NAME_MateriasExp,columna,"_id="+String.valueOf(id),null,null,null,null);
        cursor.moveToFirst();


        if (!cursor.isNull(0)){

            Nota = cursor.getDouble(0);
        }else{
            Nota = -1.0;
        }

        return Nota;

    }

    public double getNotaExpectativa(int id){
        double Nota;
        String[] columna =new String[]{CN_EXP_ACUMULADO};
        Cursor cursor =  db.query(T_NAME_MateriasExp,columna,"_id="+String.valueOf(id),null,null,null,null);
        cursor.moveToFirst();


        if (!cursor.isNull(0)){

            Nota = cursor.getDouble(0);
        }else{
            Nota = -1.0;
        }

        return Nota;

    }

    public String getNombreMateria(int id){
        String[] columna =new String[]{CN_NAME};
        Cursor cursor =  db.query(T_NAME_Materias,columna,"_id="+String.valueOf(id),null,null,null,null);
        cursor.moveToFirst();
        String PromedioDeseado = cursor.getString(0);
        return PromedioDeseado;

    }


    public void RetirarMateria(int id, int numMaterias) {

        db.delete(T_NAME_Materias,"_id=" + String.valueOf(id),null);
        db.delete(T_NAME_MateriasExp,"_id=" + String.valueOf(id),null);

        for(int n = id+1; n<=numMaterias; n++){

            String strSQL = "UPDATE "+ T_NAME_Materias +" SET _id = " + String.valueOf(n-1) + " WHERE _id = " + String.valueOf(n);
            db.execSQL(strSQL);
            strSQL = "UPDATE "+ T_NAME_MateriasExp +" SET _id = " + String.valueOf(n-1) + " WHERE _id = " + String.valueOf(n);
            db.execSQL(strSQL);
        }
        String strSQL = "UPDATE "+ T_NAME_Users +" SET " + CN_NUMMATERIAS + " = " + String.valueOf(numMaterias-1) + " WHERE _id = " + String.valueOf(1);
        db.execSQL(strSQL);

    }

}


