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

    private ArrayList<clasecodigos> result;
    private funcionesBD bd;
    public static int cont = 0;
    public static boolean isVisible = true;
    public static Context updateContext;

    //TODO: Cuando se eliminan los usuarios sigue apareciendo el último eliminado.
    //TODO: Cuando no hay usuarios y se añade uno y luego se pasa a envases no sale información.

    //90008:8511
    //90006:4024
    //90009:5540
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
        ImageButton der = (ImageButton) MainActivity.user.getViewById(R.id.imageButtonDer);
        ImageButton izq = (ImageButton) MainActivity.user.getViewById(R.id.imageButtonIzq);

        if(0 >= result.size()){
            MainActivity.user.setVisibility(View.GONE);
        } else{
            if(1 == result.size()){
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
        setUserText(cont);
        if(0 == MainActivity.vecesEjecutado){
            Intent updateIntent = new Intent(MainActivity.action);
            updateContext.sendBroadcast(updateIntent);
        }
    }

    /**
     * Establece el text del código y el número del agricultor.
     * @param i índice del agricultor.
     */

    private void setUserText(int i){
        TextView numero = (TextView) MainActivity.user.getViewById(R.id.textViewNumero);
        TextView nombre = (TextView) MainActivity.user.getViewById(R.id.textViewNombre);
        numero.setText(result.get(i).codigo);
        if(!numero.getText().toString().equals(MainActivity.codigo)){
            MainActivity.codigo = result.get(i).codigo;
        }
        nombre.setText(result.get(i).nombre);
    }

}
