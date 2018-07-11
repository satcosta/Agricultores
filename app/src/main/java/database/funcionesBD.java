package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

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
    //Borrar codigos
    public void del_codigos(String codigo){
        db.execSQL("DELETE FROM user WHERE codigo='"+codigo+"'");
    }
    //Intentos de recuperar contraseña
    public Integer darintentos(){
        Integer res;
        Cursor mCursor=db.query("intentos", new String[] { "total" }, "id=1", null, null,null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        res=mCursor.getInt(0);
        mCursor.close();
        return res;
    }
    //Actualizar intentos
    public void sum_intento(){
        db.execSQL("UPDATE intentos SET total=1 WHERE id=1");
    }

}
