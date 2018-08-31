package com.example.cesar.agricultores;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import adapters.configuracion_adapt;


public class configuracion extends Fragment { // Ana

    Context context;
    private Global glo = Global.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View vi = inflater.inflate(R.layout.configuracion, container, false);
        Log.i("CONTROL", "configuración");

        ListView listaConfig = vi.findViewById(R.id.listConfig);

        String[] nombresConfig = {"Códigos", "Notificaciones", "Informar de errores", "Tutorial", "Información", "Datos", "Estadísticas"};
        if(this.getContext() == null){
            context = glo.da_context();
        } else {
            context = this.getContext();
            glo.pon_context(this.getContext());
        }

        configuracion_adapt listAdapterConfig = new configuracion_adapt(context, nombresConfig);
        listaConfig.setAdapter(listAdapterConfig);

        return vi;
    }

}
