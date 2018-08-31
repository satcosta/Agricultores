package com.example.cesar.agricultores;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import adapters.albaranes_adapt;
import database.funciones;

/**
 * Created by Cesar on 09/05/2018.
 */

public class albaranes extends Fragment implements AsyncResponse  { // Ana

    private static final String TAG = "albaranes";

    private ListView lista;
    //String codi;
    private Global glo = Global.getInstance();
    // private ProgressDialog progDialog = null;
    public static final String KEY_ALBARAN = "albaran";
    public static final String KEY_FECHA = "fecha";
    public static final String KEY_GENERO = "genero";
    public static final String KEY_PARTIDA = "partida";
    public static final String KEY_KILOS = "kilos";
    public static final String KEY_PRECIO = "precio";
    public static final String KEY_TIPO = "tipo";
    ArrayList<HashMap<String, String>> songsLists = new ArrayList<>();
    albaranes_adapt listAdapter;
    Context cntx;
    private BroadcastReceiver mReciever;
    private Fragment fragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View vi=inflater.inflate(R.layout.albaranes, container, false);
        Log.i("CONTROL", "albaranes");

        //******************************
        lista = vi.findViewById(R.id.listaA);
        Log.i("CONTROL2", "onCreateView: albaranes refrecados.");

        webphp php;

        funciones fn = new funciones(getContext());
        //funcionesBD fbd = new funcionesBD(getContext());

        /*fbd.open();
        codi = fbd.dar_codigop();
        fbd.close();
        Log.i("ERROR02",this.getContext() + "<--");*/

        if(this.getContext() == null){
            cntx = glo.da_context();
        } else {
            cntx = this.getContext();
            glo.pon_context(this.getContext());
        }
        Log.i("ERROR2", cntx + "<--");

        if(cntx != null){
            php = new webphp(cntx);
            php.delegate = albaranes.this;
            //Log.i("RESULT COD2", codi + "<--");
            php.execute(php.miIp + "/agricultores/consultaalbaranes.php", MainActivity.codigo, fn.clave());
        }

        //*****************************
        fragment = this;
        if(null == mReciever){
            mReciever = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    if (intent.getAction().contentEquals("dbalbaranes.update") && MainActivity.vecesEjecutado == 0){
                        ++MainActivity.vecesEjecutado;
                        //Refrescar el fragment.
                        try{
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.detach(fragment);
                            ft.attach(fragment);
                            ft.commit();
                        } catch (NullPointerException e){
                            Log.e(TAG, "onReceive: NullPointer exception.");
                            e.printStackTrace();
                        }
                    }
                }
            };
        }
        IntentFilter mDataUpdateFilter = new IntentFilter("dbalbaranes.update");
        getActivity().getApplicationContext().registerReceiver(mReciever, mDataUpdateFilter);

        return vi;
    }

    @Override
    public void processFinish(String output) {
        songsLists.clear();
        Log.i("RESULTADO2 albaranes", output + "<--");
        String[] filas = output.split("#");
        for(int i = 0; i < filas.length - 1; i++){
            String[] res = filas[i].split(":");
            HashMap<String, String> map = new HashMap<>();
            map.put(KEY_ALBARAN, res[0]);
            map.put(KEY_FECHA, res[1]);
            map.put(KEY_GENERO, res[2]);
            map.put(KEY_PARTIDA, res[3]);
            map.put(KEY_KILOS, res[4]);
            map.put(KEY_PRECIO, res[5]);
            map.put(KEY_TIPO, res[6]);
            songsLists.add(map);
        }

        listAdapter = new albaranes_adapt(this.getContext(), songsLists);

        lista.setAdapter(listAdapter);

        MainActivity.vecesEjecutado = 0;
    }

    @Override
    public void processFinish2(String output) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().getApplicationContext().unregisterReceiver(mReciever);
    }
}
