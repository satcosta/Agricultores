package com.example.cesar.agricultores;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Cesar on 09/05/2018.
 */

public class configuracion extends Fragment implements AsyncResponse{
    Context context;
    private webphp php;
    public static final String KEY_CODIGO = "codigo";
    public static final String KEY_NOMBRE = "nombre";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View vi=inflater.inflate(R.layout.cuentas, container, false);
        php=new webphp();
        php.delegate = configuracion.this;
        php.execute("http://192.168.0.163/login.php","1","1234","123456789");

        return vi;
    }
    public void processFinish(String output) {
        Log.i("RESULTADO", output);
    }

}
