package com.uninorte.jdmaestre.calculadoradenotas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Jose on 23/09/2014.
 */
public class RetirarFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_retirar, container, false);

        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();

        final DbManager manager = new DbManager(getActivity());
        manager.Opendb();
        int aux_materias = manager.getNumMaterias();

        Button RetirarMat1 = (Button)getView().findViewById(R.id.RetirarMat1_Button);  RetirarMat1.setText("Retirar " +manager.getNombreMateria(1));
        Button RetirarMat2 = (Button)getView().findViewById(R.id.RetirarMat2_Button);
        Button RetirarMat3 = (Button)getView().findViewById(R.id.RetirarMat3_Button);
        Button RetirarMat4 = (Button)getView().findViewById(R.id.RetirarMat4_Button);
        Button RetirarMat5 = (Button)getView().findViewById(R.id.RetirarMat5_Button);
        Button RetirarMat6 = (Button)getView().findViewById(R.id.RetirarMat6_Button);

        for (int n = 6; n> aux_materias; n--){
            switch (n){
                case 2:
                    RetirarMat2.setVisibility(View.INVISIBLE);
                    break;
                case 3:
                    RetirarMat3.setVisibility(View.INVISIBLE);
                    break;
                case 4:
                    RetirarMat4.setVisibility(View.INVISIBLE);
                    break;
                case 5:
                    RetirarMat5.setVisibility(View.INVISIBLE);
                    break;
                case 6:
                    RetirarMat6.setVisibility(View.INVISIBLE);
                    break;


            }

        }

        for (int n = 1; n<=aux_materias ; n++){
            switch (n){
                case 2:
                    RetirarMat2.setText("Retirar " +manager.getNombreMateria(2));
                    break;
                case 3:
                    RetirarMat3.setText("Retirar " +manager.getNombreMateria(3));
                    break;
                case 4:
                     RetirarMat4.setText("Retirar " +manager.getNombreMateria(4));
                    break;
                case 5:
                   RetirarMat5.setText("Retirar " +manager.getNombreMateria(5));
                    break;
                case 6:
                    RetirarMat6.setText("Retirar " +manager.getNombreMateria(6));
                    break;


            }

        }

        manager.Closedb();
        RetirarMat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                manager.Opendb();
                manager.RetirarMateria(1, manager.getNumMaterias());
                manager.Closedb();

                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
                getActivity().finish();

            }

        } );
        RetirarMat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                manager.Opendb();
                manager.RetirarMateria(2, manager.getNumMaterias());
                manager.Closedb();

                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
                getActivity().finish();

            }

        } );
        RetirarMat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                manager.Opendb();
                manager.RetirarMateria(3, manager.getNumMaterias());
                manager.Closedb();

                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
                getActivity().finish();

            }

        } );
        RetirarMat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                manager.Opendb();
                manager.RetirarMateria(4, manager.getNumMaterias());
                manager.Closedb();

                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
                getActivity().finish();

            }

        } );
        RetirarMat5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                manager.Opendb();
                manager.RetirarMateria(5, manager.getNumMaterias());
                manager.Closedb();

                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
                getActivity().finish();

            }

        } );
        RetirarMat6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                manager.Opendb();
                manager.RetirarMateria(6, manager.getNumMaterias());
                manager.Closedb();

                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
                getActivity().finish();

            }

        } );






    }
}
