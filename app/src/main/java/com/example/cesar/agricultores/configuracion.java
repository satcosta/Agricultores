package com.example.cesar.agricultores;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Cesar on 09/05/2018.
 */

public class configuracion extends Fragment implements AsyncResponse{
    Context context;
    private webphp php;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View vi=inflater.inflate(R.layout.principal, container, false);
        php=new webphp();
        php.delegate = configuracion.this;
        //php.execute("http://hcostadealmeria.com/tecnicos/receta2.php?id=" + ids.get(tecnicos.getSelectedItemPosition()) + "&fecha=" + nfecha2);

        return vi;
    }
    public void processFinish(String output) {
    }

}
