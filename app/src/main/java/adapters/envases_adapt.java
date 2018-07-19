package adapters;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cesar.agricultores.R;
import com.example.cesar.agricultores.envases;

import java.util.ArrayList;
import java.util.HashMap;

import database.funcionesBD;

public class envases_adapt extends BaseAdapter {
    private Fragment activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    Context context;
    private funcionesBD bd;
    public envases_adapt(Context context1, ArrayList<HashMap<String, String>> d) {
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
            vi = mInflater.inflate(R.layout.envases_adapter, null);
        }
        // vi = inflater.inflate(R.layout.noticias_datos, null);
        bd=new funcionesBD(this.context);
        final TextView codigo = (TextView) vi.findViewById(R.id.codigo); // title
        TextView nombre = (TextView) vi.findViewById(R.id.envase);
        TextView saldo= (TextView) vi.findViewById(R.id.saldo);
        HashMap<String, String> song = new HashMap<String, String>();
        song = data.get(position);
        codigo.setText(song.get(envases.KEY_CODIGO));
        nombre.setText(song.get(envases.KEY_ENVASE));
        saldo.setText(song.get(envases.KEY_SALDO));
        return vi;
    }
}
