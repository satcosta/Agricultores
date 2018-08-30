package com.example.cesar.agricultores;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import database.funcionesBD;

/**
 * Clase que actualiza los usuarios.
 * @author Ana
 * @version 24/5/2018
 */

public class UpdateUser implements View.OnClickListener{ // Ana

    private static final String TAG = "UpdateUser";

    private ArrayList<clasecodigos> result;
    private funcionesBD bd;
    public static int cont = 0;
    public static boolean isVisible = true;
    public static Context updateContext;

    //90008:8511
    //90006:4024
    /**
     * Al constructor solo se le pasan las funciones.
     * @param bd Funciones para acceder a la base de datos.
     */

    public UpdateUser(funcionesBD bd){
        this.bd = bd;
        this.result = new ArrayList<>();
    }

    /**
     * Getter del contador.
     * @return cont
     *      Es como un índice para pasar de un agricultor a otro con las flechas.
     */

    /*public int getCont() {
        return cont;
    }

    /**
     * Setter del contador.
     * @param cont
     *      El índice del agricultor.
     */

    /*public void setCont(int cont) {
        this.cont = cont;
    }

    /**
     * Getter de result
     * @return result
     *      Lista que contiene los usuarios.
     */

    public ArrayList<clasecodigos> getResult() {
        return result;
    }

    /**
     * Método que actualiza los usuarios.
     */

    public void update(){
        bd.open();
        result = bd.darcodigos();
        bd.close();
        //Log.d(TAG, "update: -------------------------------------> result.size() vale " + result.size());
        ImageButton der = (ImageButton) MainActivity.user.getViewById(R.id.imageButtonDer);
        ImageButton izq = (ImageButton) MainActivity.user.getViewById(R.id.imageButtonIzq);

        if(0 >= result.size()){
            MainActivity.user.setVisibility(View.GONE);
        } else{
            if(1 == result.size()){
                //Log.d(TAG, "update: visible vale --------------------------------------------- > " + visible);
                if(isVisible){
                    MainActivity.user.setVisibility(View.VISIBLE);
                } else {
                    MainActivity.user.setVisibility(View.GONE);
                }
                der.setVisibility(View.INVISIBLE);
                izq.setVisibility(View.INVISIBLE);
            } else{
                der.setVisibility(View.VISIBLE);
                izq.setVisibility(View.VISIBLE);
                der.setOnClickListener(this);
                izq.setOnClickListener(this);
            }
            setUserText(cont);
        }
    }

    @Override
    public void onClick(View v) {
        ImageButton button = (ImageButton) v;
        if(R.id.imageButtonDer == button.getId()){
            if(result.size() - 1 != cont){
                cont++;
            } else{
                cont = 0;
            }
        } else{
            if (button.getId() == R.id.imageButtonIzq) {
                if(0 == cont){
                    cont = result.size() - 1;
                } else {
                    cont--;
                }
            }
        }
        Log.d(TAG, "onClick: ----------------------------------> cont vale " + cont);
        setUserText(cont);
        if(MainActivity.vecesEjecutado == 0){
            Log.d(TAG, "onClick: -----------------------------> action vale " + MainActivity.action);
            Intent updateIntent = new Intent(MainActivity.action);
            updateContext.sendBroadcast(updateIntent);
            Log.d(TAG, "onClick: --------------------> instancia enviada.");
        }
    }

    /**
     * Establece el text del código y el número del agricultor.
     * @param i índice del agricultor.
     */

    private void setUserText(int i){
        TextView numero = (TextView) MainActivity.user.getViewById(R.id.textViewNumero);
        TextView nombre = (TextView) MainActivity.user.getViewById(R.id.textViewNombre);
        /*if(result.size() == i){
            Log.d(TAG, "setUserText: -----------------------------> result.size() == index");
            i = result.size() - 1;
        }*/
        numero.setText(result.get(i).codigo);
        //Log.d(TAG, "setUserText: codigo ---------------------------------> " + MainActivity.codigo + " ---- " + MainActivity.i++);
        if(!numero.getText().toString().equals(MainActivity.codigo)){
            MainActivity.codigo = result.get(i).codigo;
        }
        //Log.d(TAG, "setUserText: codigo ---------------------------------> " + MainActivity.codigo + " ---- " + MainActivity.i++);
        nombre.setText(result.get(i).nombre);
    }

}
