package com.example.cesar.agricultores;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import adapters.pizarras_adapt;
import database.funciones;
import database.funcionesBD;

public class pizarra extends Fragment implements AsyncResponse, View.OnClickListener { // Ana

    private ListView lista;
    //private ProgressDialog progDialog = null;
    String codi;
    private Global glo = Global.getInstance();
    public static final String KEY_NOMBRE = "nombre";
    public static final String KEY_CORTE_1 = "corte1";
    public static final String KEY_CORTE_2 = "corte2";
    public static final String KEY_CORTE_3 = "corte3";
    public static final String KEY_CORTE_4 = "corte4";
    public static final String KEY_CORTE_5 = "corte5";
    public static final String KEY_CORTE_6 = "corte6";
    ArrayList<HashMap<String, String>> songsLists = new ArrayList<>();
    pizarras_adapt listAdapter;
    Context cntx;

    //********************
    public static String downloadUrlPizarra = "https://hcostadealmeria.com/pizarra/pizarraalhondiga.php";

    public static Button buttonAlhondiga, buttonCehorpa, buttonTomate;

    public static int colorAlhondiga, colorCehorpa, colorTomate;
    //***********************

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View vi=inflater.inflate(R.layout.pizarras, container, false);

        //********************
        buttonAlhondiga = vi.findViewById(R.id.buttonAlhondiga);
        buttonCehorpa = vi.findViewById(R.id.buttonCehorpa);
        buttonTomate = vi.findViewById(R.id.buttonTomate);

        buttonAlhondiga.setOnClickListener(this);
        buttonCehorpa.setOnClickListener(this);
        buttonTomate.setOnClickListener(this);

        colorAlhondiga = ((ColorDrawable) buttonAlhondiga.getBackground()).getColor();
        colorCehorpa = ((ColorDrawable) buttonCehorpa.getBackground()).getColor();
        colorTomate = ((ColorDrawable) buttonTomate.getBackground()).getColor();

        //********************

        lista = vi.findViewById(R.id.listeP);
        Log.i("CONTROL2", "onCreateView: pizarras refrecadas.");

        //********************************
        /*
        fn = new funciones(getContext());
        fbd = new funcionesBD(getContext());

        fbd.open();
        codi = fbd.dar_codigop();
        fbd.close();
        Log.i("ERROR02",this.getContext() + "<--");

        if(this.getContext() == null){
            cntx = glo.da_context();
        } else {
            cntx = this.getContext();
            glo.pon_context(this.getContext());
        }

        Log.i("ERROR2", cntx + "<--");
        if(cntx != null){
            php = new webphp(getContext());
            php.delegate = pizarra.this;
            Log.i("RESULT COD2", codi + "<--");
            php.execute(downloadUrlPizarra);
        }
        //**********************************
        */
        //**********************************
        DownloadData(downloadUrlPizarra);
        return vi;
    }

    @Override
    public void processFinish(String output) {
        songsLists.clear();
        Log.i("RESULTADO2", output + "<--");
        String [] filas = output.split("#");
        for(int i = 1; i < filas.length; i++){
            String[] res = filas[i].split(";");
            HashMap<String, String> map = new HashMap<>();
            map.put(KEY_NOMBRE, res[0]);
            map.put(KEY_CORTE_1, res[1]);
            map.put(KEY_CORTE_2, res[2]);
            map.put(KEY_CORTE_3, res[3]);
            map.put(KEY_CORTE_4, res[4]);
            map.put(KEY_CORTE_5, res[5]);
            map.put(KEY_CORTE_6, res[6]);
            songsLists.add(map);
        }
        listAdapter = new pizarras_adapt(this.getContext(), songsLists);
        lista.setAdapter(listAdapter);

    }

    @Override
    public void processFinish2(String output) {

    }
    //***********************
    @Override
    public void onClick(View v) {
        int colorIdWhite = Color.parseColor("#FAFAFA");
        switch (v.getId()){
            case R.id.buttonAlhondiga:
                downloadUrlPizarra = "https://hcostadealmeria.com/pizarra/pizarraalhondiga.php";
                if(colorIdWhite == ((ColorDrawable) buttonAlhondiga.getBackground()).getColor()){
                    buttonAlhondiga.setBackgroundColor(Color.parseColor("#9FA8DA"));
                    colorAlhondiga = ((ColorDrawable) buttonAlhondiga.getBackground()).getColor();
                }
                if(colorIdWhite != ((ColorDrawable)buttonCehorpa.getBackground()).getColor()){
                    buttonCehorpa.setBackgroundColor(colorIdWhite);
                    colorCehorpa = ((ColorDrawable) buttonCehorpa.getBackground()).getColor();
                }
                if(colorIdWhite != ((ColorDrawable)buttonTomate.getBackground()).getColor()){
                    buttonTomate.setBackgroundColor(colorIdWhite);
                    colorTomate = ((ColorDrawable) buttonTomate.getBackground()).getColor();
                }
                break;
            case R.id.buttonCehorpa:
                downloadUrlPizarra = "https://hcostadealmeria.com/pizarra/pizarracehorpa.php";
                if(colorIdWhite != ((ColorDrawable) buttonAlhondiga.getBackground()).getColor()){
                    buttonAlhondiga.setBackgroundColor(colorIdWhite);
                    colorAlhondiga = ((ColorDrawable) buttonAlhondiga.getBackground()).getColor();
                }
                if(colorIdWhite == ((ColorDrawable)buttonCehorpa.getBackground()).getColor()){
                    buttonCehorpa.setBackgroundColor(Color.parseColor("#9FA8DA"));
                    colorCehorpa = ((ColorDrawable) buttonCehorpa.getBackground()).getColor();
                }
                if(colorIdWhite != ((ColorDrawable)buttonTomate.getBackground()).getColor()){
                    buttonTomate.setBackgroundColor(colorIdWhite);
                    colorTomate = ((ColorDrawable) buttonTomate.getBackground()).getColor();
                }
                break;
            case R.id.buttonTomate:
                downloadUrlPizarra = "https://hcostadealmeria.com/pizarra/pizarratomate.php";
                if(colorIdWhite != ((ColorDrawable) buttonAlhondiga.getBackground()).getColor()){
                    buttonAlhondiga.setBackgroundColor(colorIdWhite);
                    colorAlhondiga = ((ColorDrawable) buttonAlhondiga.getBackground()).getColor();
                }
                if(colorIdWhite != ((ColorDrawable)buttonCehorpa.getBackground()).getColor()){
                    buttonCehorpa.setBackgroundColor(colorIdWhite);
                    colorCehorpa = ((ColorDrawable) buttonCehorpa.getBackground()).getColor();
                }
                if(colorIdWhite == ((ColorDrawable)buttonTomate.getBackground()).getColor()){
                    buttonTomate.setBackgroundColor(Color.parseColor("#9FA8DA"));
                    colorTomate = ((ColorDrawable) buttonTomate.getBackground()).getColor();
                }
                break;
            default:
        }
        DownloadData(downloadUrlPizarra);
    }

    /**
     * Clase que se baja el contenido de internet.
     * @param downloadUrlPizarra
     *      Url del que se va a bajar la información.
     */

    private void DownloadData(String downloadUrlPizarra){
        funciones fn = new funciones(getContext());
        funcionesBD fbd = new funcionesBD(getContext());

        webphp php;

        fbd.open();
        codi = fbd.dar_codigop();
        fbd.close();
        Log.i("ERROR02",this.getContext() + "<--");

        if(this.getContext() == null){
            cntx = glo.da_context();
        } else {
            cntx = this.getContext();
            glo.pon_context(this.getContext());
        }

        Log.i("ERROR2", cntx + "<--");
        if(cntx != null){
            php = new webphp(cntx);
            php.delegate = pizarra.this;
            Log.i("RESULT COD2", codi + "<--");
            php.execute(downloadUrlPizarra, codi, fn.clave());
        }
    }
    //************************
}
