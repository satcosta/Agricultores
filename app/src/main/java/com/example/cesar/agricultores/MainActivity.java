package com.example.cesar.agricultores;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.provider.SyncStateContract;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;


import database.funcionesBD;

public class MainActivity extends AppCompatActivity {

    //private ViewPager mViewPager;

    //*****************************
    private static final String TAG = "MainActivity";
    public static int vecesEjecutado = 0;

    public static final String STATE_DOWNLOAD_URL = "downloadUrlPizarra";
    public static final String STATE_COLOR_ALHONDIGA = "colorAlhondiga";
    public static final String STATE_COLOR_CEHORPA = "colorCehorpa";
    public static final String STATE_COLOR_TOMATE = "colorTomate";
    public static final String STATE_CONT = "cont";
    public static final String STATE_UPVISIBLE = "upVisible";
    public static final String STATE_CODI = "codi";

    private funcionesBD bd;
    private UpdateUser updateUser;
    public static ConstraintLayout user;
    public static String codigo;
    public static  String action = "dbhome.update";

    //*****************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Inicio");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.getTabAt(0).setIcon(R.drawable.home);
        tabLayout.getTabAt(1).setIcon(R.drawable.pizarra);
        tabLayout.getTabAt(2).setIcon(R.drawable.albaranes);
        tabLayout.getTabAt(3).setIcon(R.drawable.saldos);
        tabLayout.getTabAt(4).setIcon(R.drawable.envases);
        tabLayout.getTabAt(5).setIcon(R.drawable.configuracion);
        //cambiar tamaño texto
        /*
        Typeface font = Typeface.createFromAsset(this.getAssets(), "fonts/CaviarDreams.ttf");
        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(font);
                    ((TextView) tabViewChild).setTextSize(6);
                    ((TextView) tabViewChild).setAllCaps(false);
                }
            }
        }
        */
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PageAdapter adapter = new PageAdapter (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                //Log.i("CONTROL",tab.getPosition()+"<-tab position");
                switch (tab.getPosition()){
                    case 0: getSupportActionBar().setTitle("Inicio");
                        //****************************
                        user.setVisibility(View.VISIBLE);
                        UpdateUser.isVisible = true;
                        action = "dbhome.update";
                        //****************************
                        break;
                    case 1: getSupportActionBar().setTitle("Pizarra");
                        //****************************
                        user.setVisibility(View.GONE);
                        UpdateUser.isVisible = false;
                        //****************************
                        break;
                    case 2:getSupportActionBar().setTitle("Albaranes");
                        //****************************
                        user.setVisibility(View.VISIBLE);
                        UpdateUser.isVisible = true;
                        action = "dbalbaranes.update";
                        //****************************
                        break;
                    case 3:getSupportActionBar().setTitle("Estadísticas");
                        //****************************
                        user.setVisibility(View.VISIBLE);
                        UpdateUser.isVisible = true;
                        //****************************
                        break;
                    case 4:getSupportActionBar().setTitle("Envases");
                        //****************************
                        user.setVisibility(View.VISIBLE);
                        UpdateUser.isVisible = true;
                        action = "dbenvases.update";
                        //****************************
                        break;
                    case 5:getSupportActionBar().setTitle("Cuentas");
                        //****************************
                        user.setVisibility(View.GONE);
                        UpdateUser.isVisible = false;
                        //****************************
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //viewPager.setOffscreenPageLimit(1);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
                //Not used
            }

            @Override
            public void onPageSelected(int position) {
                Log.i("CONTROL",position+"<-onpageselected");
            /*
                Fragment f = adapter.getItem(position);
                f.onResume();
            */

            }

            @Override
            public void onPageScrollStateChanged(int i) {
                //Not used
            }
        });
        //******************************

        /*bd = new funcionesBD(this);
        bd.open();
        result = bd.darcodigos();
        bd.close();

        user = findViewById(R.id.user);
        //90008: 9578.

        ImageButton der = findViewById(R.id.imageButtonDer);
        ImageButton izq = findViewById(R.id.imageButtonIzq);

        if(result.size() <= 0){
            user.setVisibility(View.GONE);
        } else{
            if(result.size() == 1){
                user.setVisibility(View.VISIBLE);
                der.setVisibility(View.INVISIBLE);
                izq.setVisibility(View.INVISIBLE);
            } else{
                der.setVisibility(View.VISIBLE);
                izq.setVisibility(View.VISIBLE);
            }
        }



        der.setOnClickListener(this);
        izq.setOnClickListener(this);
        if(result.size() != 0){
            setUserText(0);
        }*/
        //******************************

        UpdateUser.updateContext = MainActivity.this.getApplicationContext();
        user = findViewById(R.id.user);
        bd = new funcionesBD(this);
        updateUser = new UpdateUser(bd);
        if(null != savedInstanceState){
            //updateUser.setCont(savedInstanceState.getInt(STATE_CONT));
            UpdateUser.cont = savedInstanceState.getInt(STATE_CONT);
            //Log.d(TAG, "onCreate: -----------------------------------> cont restaurado vale " + updateUser.getCont());
            Log.d(TAG, "onCreate: -----------------------------------> cont restaurado vale " + UpdateUser.cont);
            codigo = savedInstanceState.getString(STATE_CODI);
            Log.d(TAG, "onCreate: -----------------------------------> el código restaurado vale " + codigo);
        }
        updateUser.update();
        Log.d(TAG, "onCreate: -----------------------------------> el código restaurado después de updateUser.update() vale " + codigo);
    }

    //************************
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pizarra.downloadUrlPizarra = savedInstanceState.getString(STATE_DOWNLOAD_URL);
        pizarra.colorAlhondiga = savedInstanceState.getInt(STATE_COLOR_ALHONDIGA);
        pizarra.colorCehorpa = savedInstanceState.getInt(STATE_COLOR_CEHORPA);
        pizarra.colorTomate = savedInstanceState.getInt(STATE_COLOR_TOMATE);
        UpdateUser.isVisible = savedInstanceState.getBoolean(STATE_UPVISIBLE);
        pizarra.buttonAlhondiga.setBackgroundColor(pizarra.colorAlhondiga);
        pizarra.buttonCehorpa.setBackgroundColor(pizarra.colorCehorpa);
        pizarra.buttonTomate.setBackgroundColor(pizarra.colorTomate);
        //setUserText(cont);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(STATE_DOWNLOAD_URL, pizarra.downloadUrlPizarra);
        outState.putInt(STATE_COLOR_ALHONDIGA, pizarra.colorAlhondiga);
        outState.putInt(STATE_COLOR_CEHORPA, pizarra.colorCehorpa);
        outState.putInt(STATE_COLOR_TOMATE, pizarra.colorTomate);
        //outState.putInt(STATE_CONT, updateUser.getCont());
        outState.putInt(STATE_CONT, UpdateUser.cont);
        Log.d(TAG, "onSaveInstanceState: ------------------------> cont vale " + UpdateUser.cont);
        outState.putBoolean(STATE_UPVISIBLE, UpdateUser.isVisible);
        outState.putString(STATE_CODI, codigo);
        Log.d(TAG, "onSaveInstanceState: ------------------------------> el código guardado es " + codigo);
        super.onSaveInstanceState(outState);
    }

    /*@Override
    public void onClick(View v) {
        ImageButton button = (ImageButton) v;
        if(R.id.imageButtonDer == button.getId()){
            if(result.size() - 1 != cont){
                cont++;
            } else{
                cont = 0;
            }
        } else{
            if (button.getId() == R.id.imageButtonIzq) {
                if(0 == cont){
                    cont = result.size() - 1;
                } else {
                   cont--;
                }
            }
        }
        setUserText(cont);

    }



    private void setUserText(int i){
        TextView numero = findViewById(R.id.textViewNumero);
        TextView nombre = findViewById(R.id.textViewNombre);
        numero.setText(result.get(i).codigo);
        nombre.setText(result.get(i).nombre);
    }*/


    //************************

}
