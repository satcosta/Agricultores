package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cesar.agricultores.clasecodigos;

import java.util.ArrayList;

public class funcionesBD {
    private Context context;
    private SQLiteDatabase db;
    private databaseHelper dbHelper;
    public funcionesBD(Context context) {
        this.context = context;
    }

    public funcionesBD open() throws SQLException {
        dbHelper = new databaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    //Añadir codigo
    public void acodigo(String codigo,String nombre){
        Cursor mCursor=db.query("user", new String[] { "codigo" }, "codigo='"+codigo+"'", null, null,null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        if(mCursor.getCount()==0) {
            ContentValues values = new ContentValues();
            values.put("codigo", codigo);
            values.put("nombre", nombre);
            values.put("predeterminado",1);//cambiar a 0
            db.insert("user", null, values);
        }
    }
    //Dar codigos
    public ArrayList<clasecodigos> darcodigos(){
        ArrayList<clasecodigos> result= new ArrayList<clasecodigos>();
        Cursor mCursor=db.query("user", new String[] { "codigo","nombre" }, null, null, null,null, null);
        if (mCursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                clasecodigos codi=new clasecodigos();
                codi.codigo=mCursor.getInt(0)+"";
                codi.nombre=mCursor.getString(1);
                result.add(codi);
            } while(mCursor.moveToNext());
        };
        mCursor.close();
        return result;
    }
    //Dar codigo predeterminado
    public String dar_codigop(){
        String res="0";
        Cursor mCursor=db.query("user", new String[] { "codigo" }, "predeterminado=1", null, null,null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
            if(mCursor.getCount()>0)
             res=mCursor.getString(0);
            }
        mCursor.close();
        return res;
    }
    //Borrar codigos
    public void del_codigos(String codigo){
        db.execSQL("DELETE FROM user WHERE codigo='"+codigo+"'");
    }
    //Intentos de recuperar contraseña
    public Integer darintentos(){
        Integer res=0;
        Cursor mCursor=db.query("intentos", new String[] { "total" }, "id=1", null, null,null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
            if(mCursor.getCount()>0) res=mCursor.getInt(0);
        }
        Log.d("PHP", "darintentos: res vale ---------------------------------> " + res);
        mCursor.close();
        return res;
    }

    /***
     * Actualiza intentos, numIntento, numero de intentos
     * @param numIntentos numero de intentos a grabar
     */
      public void sum_intento(int numIntentos){
        db.execSQL("UPDATE intentos SET total=" + String.valueOf(numIntentos) + " WHERE id=1");
    }


}
