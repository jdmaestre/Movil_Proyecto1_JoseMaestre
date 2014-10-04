package com.uninorte.jdmaestre.calculadoradenotas;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
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
public class HomeFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View V = inflater.inflate(R.layout.fragment_home, container, false);
        return V;
    }

    @Override
    public void onResume() {
        super.onResume();
        final DbManager manager = new DbManager(getActivity());
        manager.Opendb();


        TextView NombreUsuario = (TextView)getView().findViewById(R.id.H_NombreUsuario_TextView);
        TextView PromAcu = (TextView)getView().findViewById(R.id.H_PromAcu_TextView);
        final EditText PromAcuDeseado = (EditText)getView().findViewById(R.id.H_PromAcuDeseado_EditText);

        NombreUsuario.setText(manager.getNombreUsuario());
        PromAcu.setText(String.valueOf(manager.getPromedioAcumulado()));
        PromAcuDeseado.setText(String.valueOf(manager.getPromedioDeseado()));

        getView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(PromAcuDeseado.isFocused()){
                    if(motionEvent.getY() >= 72){
                        //Will only enter this if the EditText already has focus
                        //And if a touch event happens outside of the EditText
                        //Which in my case is at the top of my layout
                        //and 72 pixels long
                        PromAcuDeseado.clearFocus();
                        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                }

                return false;
            }
        });

        PromAcuDeseado.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if((keyEvent != null && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER))|| (i == EditorInfo.IME_ACTION_DONE)){

                    PromAcuDeseado.clearFocus();
                    InputMethodManager  imm = (InputMethodManager) getActivity().getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(PromAcuDeseado.getApplicationWindowToken(), 0);
                }

                return false;


            }
        });


        PromAcuDeseado.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {

                    if (PromAcuDeseado.getText().toString().matches("")) {
                        PromAcuDeseado.setText(String.valueOf(manager.getPromedioDeseado()));
                    } else {
                        if (Double.parseDouble(PromAcuDeseado.getText().toString()) >= 0 && Double.parseDouble(PromAcuDeseado.getText().toString()) <= 5) {
                            manager.Opendb();
                            manager.ModificiarPromAcuDeseado(Double.parseDouble(PromAcuDeseado.getText().toString()));

                        } else {
                            Toast.makeText(getActivity(), "Ingrese un valor valido en el Promedio deseado", Toast.LENGTH_SHORT).show();
                            PromAcuDeseado.setText(String.valueOf(manager.getPromedioDeseado()));
                        }

                    }

                }
            }
        });



        manager.Closedb();

    }



}


