package adapters;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.cesar.agricultores.MainActivity;
import com.example.cesar.agricultores.R;
import com.example.cesar.agricultores.UpdateUser;
import com.example.cesar.agricultores.cuentas;

import java.util.ArrayList;
import java.util.HashMap;

import database.funcionesBD;

public class codigos extends BaseAdapter {

    private Fragment activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    Context context;
    private funcionesBD bd;

    public codigos(Context context1, ArrayList<HashMap<String, String>> d) {
        this.context = context1;
        this.data = d;

    }
    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null){
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            vi = mInflater.inflate(R.layout.cuentas_adapter, null);
        }
        // vi = inflater.inflate(R.layout.noticias_datos, null);
        final View vi2=vi;
        bd=new funcionesBD(this.context);
        final TextView codigo = (TextView) vi.findViewById(R.id.codigo); // title
        TextView nombre = (TextView) vi.findViewById(R.id.nombre);
        Button btn_del= (Button) vi.findViewById(R.id.btn_del);
        final HashMap<String, String> song = data.get(position);
        //song = data.get(position);
        codigo.setText(song.get(cuentas.KEY_CODIGO));
        nombre.setText(song.get(cuentas.KEY_NOMBRE));
        btn_del.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle(R.string.c_del1)
                        .setMessage(R.string.c_del2)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //vi2.setVisibility(View.GONE);
                                bd.open();
                                bd.del_codigos(codigo.getText().toString());
                                bd.close();
                                //******************************
                                cuentas.EliminarItemList(song);
                                UpdateUser updateUser = new UpdateUser(bd);
                                UpdateUser.cont = 0;
                                updateUser.update();
                                if(0 == MainActivity.vecesEjecutado){
                                    Intent updateIntent = new Intent(MainActivity.action);
                                    context.sendBroadcast(updateIntent);
                                }
                                notifyDataSetChanged();
                                //*******************************

                            }

                        })
                        .setNegativeButton("No", null)
                        .show();

            }
        });
        vi=vi2;

        return vi;
    }
}
