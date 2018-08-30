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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import adapters.codigos;
import database.funciones;
import database.funcionesBD;

/**
 * Created by Cesar on 09/05/2018.
 */

public class cuentas extends Fragment implements AsyncResponse{

    private static final String TAG = "cuentas";

    Context context;
    private webphp php;
    public static final String KEY_CODIGO = "codigo";
    public static final String KEY_NOMBRE = "nombre";
    FloatingActionButton btn_add;
    private ListView list;
    static ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
    codigos listAdapter;
    TableLayout nuevo;
    EditText codi;
    EditText pass;
    Button btn_guardar;
    Button btn_recuperar;
    TextView msg;
    private funcionesBD bd;
    private funciones fn;
    //******************************
    private UpdateUser updateUser;
    //******************************
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View vi=inflater.inflate(R.layout.cuentas, container, false);
        Log.d(TAG, "onCreateView: -------------------------------> código vale " + MainActivity.codigo);
        list= (ListView) vi.findViewById(R.id.listc);
        btn_add = (FloatingActionButton) vi.findViewById(R.id.btn_add);
        nuevo =(TableLayout) vi.findViewById(R.id.empty);
        codi=(EditText) vi.findViewById(R.id.codigo) ;
        pass=(EditText) vi.findViewById(R.id.pass) ;
        btn_guardar=(Button) vi.findViewById(R.id.btn_guardar);
        btn_recuperar=(Button) vi.findViewById(R.id.btn_recuperar);
        msg=(TextView) vi.findViewById(R.id.msg);
        nuevo.setVisibility(View.GONE);
        songsList.clear();
        bd= new funcionesBD(getContext());
        //bd.open();
        //ArrayList<clasecodigos> result =bd.darcodigos();
        //bd.close();
        //************************************
        updateUser = new UpdateUser(bd);
        Log.d(TAG, "onCreateView: -------------------------------> código vale " + MainActivity.codigo);
        updateUser.update();
        //************************************

        fn = new funciones(getContext());

        UpdateUserList(updateUser, songsList);
        Log.d(TAG, "onCreateView: -------------------------------> " + songsList.toString());
        Log.d(TAG, "onCreateView: -------------------------------> código vale " + MainActivity.codigo);
        //Log.d(TAG, "onCreateView: -------------------------------> cont vale " + updateUser.getCont());
        Log.d(TAG, "onCreateView: -------------------------------> cont vale " + UpdateUser.cont);

        /*for(int a=0;a<updateUser.getResult().size();a++){
            clasecodigos codi=updateUser.getResult().get(a);
            HashMap<String, String> map = new HashMap<String, String>();
            map.put(KEY_CODIGO, codi.codigo);
            map.put(KEY_NOMBRE, codi.nombre);
            songsList.add(map);
        }*/
        listAdapter = new codigos(getContext(), songsList);
        list.setAdapter(listAdapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i("TAG", "Hacer algo");
                nuevo.setVisibility(View.VISIBLE);
                codi.setText("");
                pass.setText("");
            }
        });
        btn_guardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getContext().getSystemService(context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                //progDailog = ProgressDialog.show(getContext(), "", "Cargando...", true);
                php=new webphp(getContext());
                php.delegate = cuentas.this;
                if(!"".equals(codi.getText().toString()) && !"".equals(pass.getText().toString())){
                    php.execute(php.miIp  + "/agricultores/compruebaagri.php",codi.getText().toString(),pass.getText().toString(), fn.clave());

                } else {
                    Toast.makeText(getContext(), "Por favor, rellene los campos para continuar.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_recuperar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(codi.getText().toString().equals("")){
                    Toast.makeText(getContext(),R.string.recuperar2, Toast.LENGTH_SHORT).show();
                }else {
                    bd.open();
                    int intentos=bd.darintentos();
                    bd.close();
                    if(intentos==0) {
                        php = new webphp(getContext());
                        php.delegate2 = cuentas.this;
                        php.execute(php.miIp  + "/agricultores/altaagricultor.php", codi.getText().toString(),fn.clave());
                        bd.open();
                        bd.sum_intento(1);
                        bd.close();
                    }else
                        msg.setText(getResources().getString(R.string.msg5));
                }
            }
        });
        return vi;

    }
    public void processFinish(String output) {
        Log.i("RESULTADO", output);

        String[] veri=output.toString().split(":");
        if(veri.length>0) {
            if (!veri[0].equals("ERROR")) {
                bd.open();
                bd.acodigo(codi.getText().toString(), veri[1]);
                HashMap<String, String> map = new HashMap<String, String>();
                // adding each child node to HashMap key => value
                map.put(KEY_CODIGO, codi.getText().toString());
                map.put(KEY_NOMBRE, veri[1]);
                songsList.add(map);
                Log.d(TAG, "processFinish: -----------------------------------> " + songsList.toString());
                listAdapter = new codigos(getContext(), songsList);
                list.setAdapter(listAdapter);
                nuevo.setVisibility(View.GONE);
                bd.sum_intento(0);
                bd.close();
                //****************************
                updateUser.update();
                //****************************
            } else {
                // Mesaje: Codigo o contraseña no valida
                msg.setText(getResources().getString(R.string.msg3));
            }
        } else {
            // Error de conexion
            msg.setText(getResources().getString(R.string.msg4));
        }

    }
    public void processFinish2(String output) {
        Log.i("RESULTADO",output+"<-");
        msg.setText(getResources().getString(R.string.msg1)+" "+output.substring(0,2)+
                "xxxxx"+output.substring(7,9)+" "+getResources().getString(R.string.msg2));
    }

   private static void UpdateUserList(UpdateUser updateUser, ArrayList<HashMap<String, String>> songsList){
       for(int a=0;a<updateUser.getResult().size();a++){
           clasecodigos codi=updateUser.getResult().get(a);
           HashMap<String, String> map = new HashMap<String, String>();
           map.put(KEY_CODIGO, codi.codigo);
           map.put(KEY_NOMBRE, codi.nombre);
           songsList.add(map);
       }
   }

    /**
     * Método que elimina un elemento de la lista al eliminar un código.
     * @param song
     */
   public static void EliminarItemList(HashMap<String, String> song){
        songsList.remove(song);
   }
}
