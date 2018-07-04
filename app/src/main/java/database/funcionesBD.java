package database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

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
        if(mCursor.getCount()==0)
            db.execSQL("INSERT INTO user VALUES('"+codigo+"','"+nombre+"')");
    }
    //Dar codigos
    public String darusu(){
        Integer res=1;
        String idusu;
        Cursor mCursor=db.query("usuarios", new String[] { "iduser" }, "id=1", null, null,null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        idusu=mCursor.getString(0);
        mCursor.close();
        return idusu;
    }
}
