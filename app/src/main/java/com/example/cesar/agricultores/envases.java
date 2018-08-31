package com.example.cesar.agricultores;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import adapters.envases_adapt;
import database.funciones;
import database.funcionesBD;

/**
 * Created by Cesar on 09/05/2018.
 */

public class envases extends Fragment implements AsyncResponse{

    private static final String TAG = "envases";

    private ListView lista;
    private webphp php;
    private ProgressDialog progDailog=null;
    //private funcionesBD fbd;
    private funciones fn;
    //String codi;
    private Global glo=Global.getInstance();
    public static final String KEY_CODIGO = "codigo";
    public static final String KEY_ENVASE = "envase";
    public static final String KEY_SALDO = "saldo";
    ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
    envases_adapt listAdapter;
    Context cntx;
    private BroadcastReceiver mReciever;
    private Fragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View vi=inflater.inflate(R.layout.envases, container, false);
        lista=(ListView) vi.findViewById(R.id.liste);
        Log.i("CONTROL","envases refrescado");
        Log.d(TAG, "onCreateView: ------------------------------> envases refrescados.");

        fn=new funciones(getContext());
        //fbd= new funcionesBD(getContext());

        /*
        if(fn!=null)
            glo.pon_funciones(fn);
        glo.pon_funcionesbd(fbd);
        */

        /*fbd.open();
        codi=fbd.dar_codigop();
        fbd.close();
        Log.i("ERROR0", this.getContext() + "<--");*/

        //Revisar context
        if(this.getContext()==null)
            cntx=glo.da_context();
        else {
            cntx=this.getContext();
            glo.pon_context(this.getContext());
        }
        Log.i("ERROR", cntx + "<--");
        if(cntx!=null) {
            php = new webphp(cntx);
            php.delegate = envases.this;
            //Log.i("RESULT COD", codi + "<--");
            php.execute(php.miIp  + "/agricultores/saldocajasagri.php", MainActivity.codigo, fn.clave());
        }

        fragment = this;
        if(null == mReciever){
            mReciever = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    if (intent.getAction().contentEquals("dbenvases.update") && MainActivity.vecesEjecutado == 0){
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
        IntentFilter mDataUpdateFilter = new IntentFilter("dbenvases.update");
        getActivity().getApplicationContext().registerReceiver(mReciever, mDataUpdateFilter);

        return vi;
    }


    public void processFinish(String output) {
        songsList.clear();
        Log.i("RESULTADO",output+"<--");
        String[] filas=output.split("#");
        for(int a=0;a<filas.length-1;a++) {
            String[] res=filas[a].split(":");
            HashMap<String, String> map = new HashMap<String, String>();
            map.put(KEY_CODIGO, res[0]);
            map.put(KEY_ENVASE, res[1]);
            map.put(KEY_SALDO, res[2]);
            songsList.add(map);
        }
        listAdapter = new envases_adapt(this.getContext(), songsList);
        lista.setAdapter(listAdapter);

        MainActivity.vecesEjecutado = 0;
    }
    public void processFinish2(String output) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().getApplicationContext().unregisterReceiver(mReciever);
    }
}
