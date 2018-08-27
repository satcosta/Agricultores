package com.example.cesar.agricultores;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import database.funcionesBD;

/**
 * Clase que actualiza los usuarios.
 * @author
 * @version 24/5/2018
 */

public class UpdateUser implements View.OnClickListener{ // Ana

    private static final String TAG = "UpdateUser";

    private ArrayList<clasecodigos> result;
    private funcionesBD bd;
    private int cont;
    private static boolean visible = true;

    //90008:8511

    //TODO: Hacer que no salga user al borrar un usuario.
    //TODO: Cuando solo hay un usuario y se le da la vuelta a la pantalla vuelve a apareceer el user.

    /**
     * Al constructor solo se le pasan las funciones.
     * @param bd Funciones para acceder a la base de datos.
     */

    public UpdateUser(funcionesBD bd){
        this.bd = bd;
        this.result = new ArrayList<>();
        cont = 0;
    }

    /**
     * Getter del contador.
     * @return cont
     *      Es como un índice para para pasar de un agricultor a otro con las flechas.
     */

    public int getCont() {
        return cont;
    }

    /**
     * Setter del contador.
     * @param cont
     *      El índice dele agricultor.
     */

    public void setCont(int cont) {
        this.cont = cont;
    }

    /**
     * Getter de visible.
     * @return visible
     */

    public static boolean isVisible() {
        return visible;
    }

    /**
     * Setter de visible.
     * @param visible
     *      Variable que determina si el usuario se ve o no.
     */

    public void setVisible(boolean visible) {
        this.visible = visible;
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
                Log.d(TAG, "update: visible vale --------------------------------------------- > " + visible);
                if(visible){
                    MainActivity.user.setVisibility(View.VISIBLE);

                } else{
                    MainActivity.user.setVisibility(View.GONE);
                    visible = true;
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
    }

    /**
     * Establece el text del código y el número del agricultor.
     * @param i índice del agricultor.
     */

    private void setUserText(int i){
        TextView numero = (TextView) MainActivity.user.getViewById(R.id.textViewNumero);
        TextView nombre = (TextView) MainActivity.user.getViewById(R.id.textViewNombre);
        numero.setText(result.get(i).codigo);
        nombre.setText(result.get(i).nombre);
    }

}
