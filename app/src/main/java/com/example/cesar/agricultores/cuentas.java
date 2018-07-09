package com.example.cesar.agricultores;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.HashMap;

import adapters.codigos;
import database.funcionesBD;

/**
 * Created by Cesar on 09/05/2018.
 */

public class cuentas extends Fragment implements AsyncResponse{
    Context context;
    private webphp php;
    public static final String KEY_CODIGO = "codigo";
    public static final String KEY_NOMBRE = "nombre";
    FloatingActionButton btn_add;
    private ListView list;
    ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
    codigos listAdapter;
    TableLayout nuevo;
    EditText codi;
    EditText pass;
    Button btn_guardar;
    private ProgressDialog progDailog=null;
    private funcionesBD bd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View vi=inflater.inflate(R.layout.cuentas, container, false);
        list= (ListView) vi.findViewById(R.id.listc);
        btn_add = (FloatingActionButton) vi.findViewById(R.id.btn_add);
        nuevo =(TableLayout) vi.findViewById(R.id.empty);
        codi=(EditText) vi.findViewById(R.id.codigo) ;
        pass=(EditText) vi.findViewById(R.id.pass) ;
        btn_guardar=(Button) vi.findViewById(R.id.btn_guardar);
        nuevo.setVisibility(View.GONE);
        songsList.clear();
        bd= new funcionesBD(getContext());
        bd.open();
        ArrayList<clasecodigos> result =bd.darcodigos();
        bd.close();
        for(int a=0;a<result.size();a++){
            clasecodigos codi=result.get(a);
            HashMap<String, String> map = new HashMap<String, String>();
            map.put(KEY_CODIGO, codi.codigo);
            map.put(KEY_NOMBRE, codi.nombre);
            songsList.add(map);
            listAdapter = new codigos(getContext(), songsList);
            list.setAdapter(listAdapter);
        }

        btn_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i("TAG", "Hacer algo");
                nuevo.setVisibility(View.VISIBLE);
            }
        });
        btn_guardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getContext().getSystemService(context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                progDailog = ProgressDialog.show(getContext(), "", "Cargando...", true);
                php=new webphp();
                php.delegate = cuentas.this;
                php.execute("http://192.168.0.163/login.php",codi.getText().toString(),pass.getText().toString(),"123456789");
            }
        });
        return vi;

    }
    public void processFinish(String output) {
        Log.i("RESULTADO", output);
        if(!output.toString().equals("ERROR")) {
            String[] veri=output.toString().split(":");
            if(veri.length>0)
                if(veri[0].equals("OK")) {
                    progDailog.dismiss();
                    bd.open();
                    bd.acodigo(codi.getText().toString(),veri[1]);
                    bd.close();
                    HashMap<String, String> map = new HashMap<String, String>();
                    // adding each child node to HashMap key => value
                    map.put(KEY_CODIGO, codi.getText().toString());
                    map.put(KEY_NOMBRE, veri[1]);
                    songsList.add(map);
                    listAdapter = new codigos(getContext(), songsList);
                    list.setAdapter(listAdapter);
                    nuevo.setVisibility(View.GONE);
                }else
                    progDailog.dismiss();
        }else
            progDailog.dismiss();
    }

}
