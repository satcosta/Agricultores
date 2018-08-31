package adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cesar.agricultores.R;
import com.example.cesar.agricultores.cuentas;

import java.util.ArrayList;
import java.util.Arrays;

public class configuracion_adapt extends BaseAdapter { // Ana

    private ArrayList<String> data;
    private Context context;


    public configuracion_adapt(Context context, String[] data){
        this.context = context;
        this.data = new ArrayList<>();
        this.data.addAll(Arrays.asList(data));
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            vi = layoutInflater.inflate(R.layout.configuracion_adapter, null);
        }

        String dir = data.get(position);
        TextView textView = vi.findViewById(R.id.textViewConfig);
        textView.setText(dir);

        ImageView imageView = vi.findViewById(R.id.imageViewConfig);
        TextView flechita = vi.findViewById(R.id.textViewConfigA);

        switch (dir){
            case "Códigos":
                imageView.setImageResource(R.drawable.settings_5_1);
                vi.setClickable(true);
                break;
            case "Notificaciones":
                imageView.setImageResource(R.drawable.smartphone_message_1);
                vi.setClickable(true);
                break;
            case "Informar de errores":
                imageView.setImageResource(R.drawable.nuclear_mushroom_1);
                vi.setClickable(true);
                break;
            case "Tutorial":
                imageView.setImageResource(R.drawable.student_3_1);
                vi.setClickable(true);
                break;
            case "Datos":
                //Log.d(TAG, "getView: Aquí ha entrado (Datos) ---------------------------> " + dir);
                imageView.setImageResource(R.drawable.analytics_1);
                vi.setClickable(true);
                break;
            case "Estadísticas":
                imageView.setImageResource(R.drawable.money_graph);
                vi.setClickable(true);
                break;
            default:
                flechita.setVisibility(View.GONE);
                textView.setTypeface(null, Typeface.BOLD);
                vi.setBackgroundColor(Color.parseColor("#f7f7f7"));
                textView.setTextSize(14);
                imageView.setVisibility(View.GONE);
                vi.setClickable(false);
        }


        vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (data.get(position)) {
                    case "Códigos":
                        AppCompatActivity activity = (AppCompatActivity) v.getContext();
                        Fragment fragment = new cuentas();
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutConfig, fragment).addToBackStack(null).commit();
                        break;
                    case "Notificaciones":
                        Toast.makeText(context, "Has pulsado notificaciones.", Toast.LENGTH_SHORT).show();
                        break;
                    case "Informar de errores":
                        Toast.makeText(context, "Has pulsado informar de errores.", Toast.LENGTH_SHORT).show();
                        break;
                    case "Tutorial":
                        Toast.makeText(context, "Has pulsado tutorial.", Toast.LENGTH_SHORT).show();
                        break;
                    case "Datos":
                        Toast.makeText(context, "Has pulsado datos.", Toast.LENGTH_SHORT).show();
                        break;
                    case "Estadísticas":
                        Toast.makeText(context, "Has pulsado estadísticas.", Toast.LENGTH_SHORT).show();
                        break;
                    default:

                }
            }
        });

        return vi;
    }

}
