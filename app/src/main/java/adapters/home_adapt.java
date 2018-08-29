package adapters;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cesar.agricultores.R;
import com.example.cesar.agricultores.home;

import java.util.ArrayList;
import java.util.HashMap;

import database.funcionesBD;

public class home_adapt extends BaseAdapter { // Ana

    //private static final String TAG = "home_adapt";

    private ArrayList<HashMap<String, String>> data;
    private Context context;

    public home_adapt(Context context1, ArrayList<HashMap<String, String>> d){
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
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            vi = layoutInflater.inflate(R.layout.home_adapter, null);
        }
        //funcionesBD bd = new funcionesBD(this.context);

        TextView fecha = vi.findViewById(R.id.textViewFecha);
        TextView contenido = vi.findViewById(R.id.textViewContenido);
        TextView titulo = vi.findViewById(R.id.textViewTitulo);
        ImageView imagen = vi.findViewById(R.id.imageView);

        HashMap<String, String> song;
        song = data.get(position);
        fecha.setText(formatFecha(song.get(home.KEY_FECHA)));
        contenido.setText(song.get(home.KEY_MENSAJE));

        switch(song.get(home.KEY_TIPO)){
            case "1":
                titulo.setText(R.string.home_title_1);
                imagen.setImageResource(R.drawable.chat_2);
                break;
            case "2":
                titulo.setText(R.string.home_title_2);
                imagen.setImageResource(R.drawable.paper_plane);
                break;
            case "3":
                titulo.setText(R.string.home_title_3);
                imagen.setImageResource(R.drawable.euro_coin);
                break;
            default:
                titulo.setText(R.string.home_title_default);
                imagen.setImageResource(R.drawable.checklist);
                break;
        }

        return vi;
    }

    /**
     * Clase que pasa del formato de fecha aaaammdd a dd/mm/aa.
     * @param fecha
     *      Fecha de la publicación.
     * @return fecha formateada
     */

    private String formatFecha(String fecha){
        String anio = fecha.substring(0, 4);
        String mes = fecha.substring(5, 7);
        String dia = fecha.substring(8);
        fecha = dia + "/" + mes + "/" + anio;
        return fecha;
    }

}
