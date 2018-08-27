package com.example.cesar.agricultores;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import adapters.home_adapt;
import database.funciones;
import database.funcionesBD;

public class home extends Fragment implements AsyncResponse{ // Es la clase principal. Ana

    //private static final String TAG = "home";

    private ListView lista;
    //private ProgressDialog progDialog = null;
    String codi;
    private Global glo = Global.getInstance();
    public static final String KEY_FECHA = "fecha";
    public static final String KEY_MENSAJE = "mensaje";
    public static final String KEY_TIPO = "tipo";
    ArrayList<HashMap<String, String>> songsLists = new ArrayList<>();
    home_adapt listAdapter;
    Context cntx;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View vi = inflater.inflate(R.layout.home, container, false);

        lista = vi.findViewById(R.id.listaH);
        Log.i("CONTROL2", "onCreateView: mensajes refrecados.");

        webphp php;

        //http://192.168.0.46/agricultores/consultamensajes.php
        //http://212.145.151.31:9090/agricultores/consultamensajes.php
        funciones fn = new funciones(getContext());
        funcionesBD fbd = new funcionesBD(getContext());

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
            php.delegate = home.this;
            Log.i("RESULT COD2", codi + "<--");
            php.execute(php.miIp + "/agricultores/consultamensajes.php", codi, fn.clave());

        }
        //MainActivity.user.setVisibility(View.VISIBLE);
        return vi;
    }

    @Override
    public void processFinish(String output) {
        songsLists.clear();
        Log.i("RESULTADO2 mensajes", output + "<--");
        String[] filas = output.split("#");
        for(int i = 0; i < filas.length - 1; i++){
            String[] res = filas[i].split(":");
            HashMap<String, String> map = new HashMap<>();
            map.put(KEY_FECHA, res[0].trim());
            map.put(KEY_MENSAJE, res[1]);
            map.put(KEY_TIPO, res[2]);
            songsLists.add(map);
        }

        listAdapter = new home_adapt(this.getContext(), songsLists);

        lista.setAdapter(listAdapter);


    }

    @Override
    public void processFinish2(String output) {
        
    }
}
