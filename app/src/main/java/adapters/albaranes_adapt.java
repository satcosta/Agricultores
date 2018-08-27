package adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cesar.agricultores.R;
import com.example.cesar.agricultores.albaranes;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;


public class albaranes_adapt extends BaseAdapter { //Ana

    private ArrayList<HashMap<String, String>> data;
    private Context context;

    public albaranes_adapt(Context context1, ArrayList<HashMap<String, String>> d){
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
            vi = layoutInflater.inflate(R.layout.albaranes_adapter, null);
        }

        //funcionesBD bd = new funcionesBD(this.context);



        TextView numAlbaran = vi.findViewById(R.id.tvNumAlbaran);
        TextView fecha = vi.findViewById(R.id.tvFechaAlbaran);
        TextView partida = vi.findViewById(R.id.tvPartida);
        TextView nombreHortaliza = vi.findViewById(R.id.tvNombreHortaliza);
        TextView kilos = vi.findViewById(R.id.tvKilosNum);
        TextView precio = vi.findViewById(R.id.tvPrecioNum);
        TextView importe = vi.findViewById(R.id.tvImporteNum);
        ImageView sello = vi.findViewById(R.id.imageView_sello);

        HashMap<String, String> song;
        song = data.get(position);

        numAlbaran.setText(song.get(albaranes.KEY_ALBARAN));
        fecha.setText(formatFecha(song.get(albaranes.KEY_FECHA)));
        String textoPartida = "Partida: " + song.get(albaranes.KEY_PARTIDA);
        partida.setText(textoPartida);
        nombreHortaliza.setText(song.get(albaranes.KEY_GENERO));
        String kilosTexto = String.valueOf(Math.round(Double.parseDouble(song.get(albaranes.KEY_KILOS))));
        kilos.setText(kilosTexto);

        if(!"0".equals(song.get(albaranes.KEY_PRECIO))){
            DecimalFormat df = new DecimalFormat("0.00");
            String precioTexto = df.format(Double.parseDouble(song.get(albaranes.KEY_PRECIO))) + " €";
            precio.setText(precioTexto);
            double numeroImporte = (Double.valueOf(song.get(albaranes.KEY_KILOS)) * Double.valueOf(song.get(albaranes.KEY_PRECIO)));
            String valorImporte = df.format(numeroImporte)+ " €";
            importe.setText(valorImporte);
            if("0".equals(song.get(albaranes.KEY_TIPO))){
                sello.setImageResource(R.drawable.sellovendido);
            }
            if("1".equals(song.get(albaranes.KEY_TIPO))){
                sello.setImageResource(R.drawable.sellopagado);
            }
        }


        return vi;
    }

    /**
     * Clase que pasa del formato de fecha aaaa-mm-dd a dd/mm/aaaa.
     * @param fecha
     *      Fecha de la noticia.
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
