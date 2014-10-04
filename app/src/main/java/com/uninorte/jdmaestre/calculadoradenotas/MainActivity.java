package com.uninorte.jdmaestre.calculadoradenotas;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {




    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */

    private NavigationDrawerFragment mNavigationDrawerFragment;
    public static String[] rowsDrawerLayout = new String[6];//{"null","null","null","null","null","null"};
    public static int aux_num_materias;
    static int materia_id;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DbManager manager = new DbManager(this);
        // Verifica si el usuario ya creo la BD
        int aux_first_open = 0;


        try{
            manager.Opendb();
            aux_first_open = manager.BdExisteCheck();
            manager.Closedb();
           // Toast.makeText(getApplicationContext(), String.valueOf(aux_first_open) , Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){}

        if (aux_first_open == 0){
            Intent intent = new Intent(this, DatosUsuario.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();



        }
        else{

        }
        //-----------------------------

        aux_num_materias = 0;

        manager.Opendb();
        Cursor cursor = manager.AuxTitulosDrawerLayout();


       aux_num_materias = manager.getNumMaterias();
        if(aux_num_materias != -1) {

            Toast.makeText(getApplicationContext(), String.valueOf(aux_num_materias), Toast.LENGTH_SHORT).show();


            cursor.moveToFirst();
            int n = 0;
            while (cursor.isAfterLast() == false) {

                rowsDrawerLayout[n] = cursor.getString(0);
                cursor.moveToNext();
                n = n + 1;

            }

            manager.Closedb();

            setContentView(R.layout.activity_main);


            mNavigationDrawerFragment = (NavigationDrawerFragment)
                    getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
            mTitle = getTitle();

            // Set up the drawer.
            mNavigationDrawerFragment.setUp(
                    R.id.navigation_drawer,
                    (DrawerLayout) findViewById(R.id.drawer_layout));
        }
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment = null;
        switch (position){
            case 0:
                fragmentManager.popBackStack();
                fragment = new HomeFragment();
                break;
            case 1:
                fragmentManager.popBackStack();
                materia_id = 1;
                fragment = new Materia1Fragment();

                break;
            case 2:
                fragmentManager.popBackStack();
                materia_id = 2;
                fragment = new Materia1Fragment();

                break;
            case 3:
                fragmentManager.popBackStack();
                materia_id = 3;
                fragment = new Materia1Fragment();

                break;
            case 4:
                fragmentManager.popBackStack();
                materia_id = 4;
                fragment = new Materia1Fragment();
                break;
            case 5:
                fragmentManager.popBackStack();
                materia_id = 5;
                fragment = new Materia1Fragment();
                break;
            case 6:
                fragmentManager.popBackStack();
                materia_id = 6;
                fragment = new Materia1Fragment();
                break;

        }


        // update the main content by replacing fragments
        if(position!=0){
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment).addToBackStack("home")
                    .commit();

        }else{
            if(position==0 && fragmentManager.getBackStackEntryCount()>0){
                for(int i =0; i<fragmentManager.getBackStackEntryCount(); i++){
                    fragmentManager.popBackStack();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, fragment)
                            .commit();
                }
            }else{
                fragmentManager.beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();
            }

        }

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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
