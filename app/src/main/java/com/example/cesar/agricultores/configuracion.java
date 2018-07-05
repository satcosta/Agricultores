package com.example.cesar.agricultores;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.HashMap;

import adapters.codigos;

/**
 * Created by Cesar on 09/05/2018.
 */

public class configuracion extends Fragment implements AsyncResponse{
    Context context;
    private webphp php;
    public static final String KEY_CODIGO = "codigo";
    public static final String KEY_NOMBRE = "nombre";
    FloatingActionButton btn_add;
    private ListView list;
    ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
    codigos listAdapter;
    TableLayout nuevo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View vi=inflater.inflate(R.layout.cuentas, container, false);
        list= (ListView) vi.findViewById(R.id.listc);
        btn_add = (FloatingActionButton) vi.findViewById(R.id.btn_add);
        nuevo =(TableLayout) vi.findViewById(R.id.empty);
        nuevo.setVisibility(View.GONE);
        songsList.clear();
        btn_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i("TAG", "Hacer algo");
                nuevo.setVisibility(View.VISIBLE);
                php=new webphp();
                php.delegate = configuracion.this;
                php.execute("http://192.168.0.163/login.php","1","1234","123456789");
            }
        });


        return vi;

    }
    public void processFinish(String output) {
        Log.i("RESULTADO", output);
        HashMap<String, String> map = new HashMap<String, String>();
        // adding each child node to HashMap key => value
        map.put(KEY_CODIGO, "1");
        map.put(KEY_NOMBRE, output);
        songsList.add(map);
        listAdapter=new codigos(getContext(), songsList);
        list.setAdapter(listAdapter);
        nuevo.setVisibility(View.GONE);
    }

}
