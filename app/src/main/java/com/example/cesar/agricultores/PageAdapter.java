package com.example.cesar.agricultores;

/**
 * Created by Cesar on 10/05/2018.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

/**
 * Created by Cesar on 10/05/2018.
 */

public class PageAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PageAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        //Log.i("CONTROL",position+"<-posicion");
        switch (position) {
            case 0:
                principal tab1 = new principal();
                return tab1;
            case 1:
                pizarra tab2 = new pizarra();
                return tab2;
            case 2:
                albaranes tab3 = new albaranes();
                return tab3;
            case 3:
                estadisticas tab4 = new estadisticas();
                return tab4;
            case 4:
                envases tab5 = new envases();
                return tab5;
            case 5:
                cuentas tab6 = new cuentas();
                return tab6;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
    @Override
    public int getItemPosition(Object object) {

        return POSITION_NONE;
    }
}
