package com.example.cesar.agricultores;

/**
 * Created by Cesar on 10/05/2018.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

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

        switch (position) {
            case 0:
                principal tab1 = new principal();
                return tab1;
            case 1:
                albaranes tab2 = new albaranes();
                return tab2;
            case 2:
                saldos tab3 = new saldos();
                return tab3;
            case 3:
                envases tab4 = new envases();
                return tab4;
            case 4:
                configuracion tab5 = new configuracion();
                return tab5;
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