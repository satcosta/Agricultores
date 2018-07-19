package com.example.cesar.agricultores;

import android.app.ProgressDialog;
import android.content.Context;

import database.funciones;
import database.funcionesBD;

public class Global {
    private static Global instance;
    private funciones fn;
    private funcionesBD fbd;
    private ProgressDialog progDailog;
    private Global(){}
    private Context context;

    public Context da_context(){return this.context;}
    public funciones da_funciones(){
        return this.fn;
    }
    public funcionesBD da_funcionesbd(){
        return this.fbd;
    }
    public ProgressDialog da_dialog(){
        return this.progDailog;
    }
    public void pon_funciones(funciones fnn){
        this.fn=fnn;
    }
    public void pon_funcionesbd(funcionesBD fbdd){
        this.fbd=fbdd;
    }
    public void pon_dialog(ProgressDialog pro){this.progDailog=pro;}
    public void pon_context(Context ctx){this.context=ctx;}
    public static synchronized Global getInstance(){
        if(instance==null){
            instance=new Global();
        }
        return instance;
    }
}
