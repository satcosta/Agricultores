package adapters;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cesar.agricultores.R;
import com.example.cesar.agricultores.configuracion;

import java.util.ArrayList;
import java.util.HashMap;

public class codigos extends BaseAdapter {
    private Fragment activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    Context context;
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
            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            vi = mInflater.inflate(R.layout.cuentas_adapter, null);
        }
        // vi = inflater.inflate(R.layout.noticias_datos, null);
        TextView codigo = (TextView) vi.findViewById(R.id.codigo); // title
        TextView nombre = (TextView) vi.findViewById(R.id.nombre);

        HashMap<String, String> song = new HashMap<String, String>();
        song = data.get(position);

        codigo.setText(song.get(configuracion.KEY_CODIGO));
        nombre.setText(song.get(configuracion.KEY_NOMBRE));
        /*
        Nomenvase.setTextColor(Color.WHITE);
        cantidad.setTextColor(Color.WHITE);
        cantidad.setText(song.get(envases.KEY_CANTIDAD));
        vi.setBackgroundColor(Color.parseColor("#315b8f"));
        cantidad.setTypeface(null, Typeface.BOLD);
        cantidad.setTextSize(16);*/
        return vi;
    }
}
