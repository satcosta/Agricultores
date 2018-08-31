package adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cesar.agricultores.R;
import com.example.cesar.agricultores.pizarra;

import java.util.ArrayList;
import java.util.HashMap;

import database.funcionesBD;


public class pizarras_adapt extends BaseAdapter { // Ana

    private ArrayList<HashMap<String, String>> data;
    private Context context;

    public pizarras_adapt(Context context1, ArrayList<HashMap<String, String>> d){
        this.context = context1;
        this.data = d;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if(convertView == null){
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            vi = mInflater.inflate(R.layout.pizarras_adapter, null);
        }
        //*************
        vi.setBackgroundColor(position % 2 == 0? Color.parseColor("#FAFAFA"):Color.parseColor("#E1F5FE"));
        //************
        final TextView nombre = vi.findViewById(R.id.nombreP);
        TextView corte1 = vi.findViewById(R.id.corte1);
        TextView corte2 = vi.findViewById(R.id.corte2);
        TextView corte3 = vi.findViewById(R.id.corte3);
        TextView corte4 = vi.findViewById(R.id.corte4);
        TextView corte5 = vi.findViewById(R.id.corte5);
        TextView corte6 = vi.findViewById(R.id.corte6);

        HashMap<String, String> song;
        song = data.get(position);
        nombre.setText(song.get(pizarra.KEY_NOMBRE));
        corte1.setText(song.get(pizarra.KEY_CORTE_1));
        corte2.setText(song.get(pizarra.KEY_CORTE_2));
        corte3.setText(song.get(pizarra.KEY_CORTE_3));
        corte4.setText(song.get(pizarra.KEY_CORTE_4));
        corte5.setText(song.get(pizarra.KEY_CORTE_5));
        corte6.setText(song.get(pizarra.KEY_CORTE_6));
        return vi;
    }
}
