package com.example.cesar.agricultores;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Cesar on 09/05/2018.
 */

public class envases extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View vi=inflater.inflate(R.layout.principal, container, false);
        //((MainActivity) getActivity()).setActionBarTitle("Envases");
        return vi;
    }

}
