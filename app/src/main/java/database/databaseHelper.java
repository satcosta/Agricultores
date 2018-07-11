package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "applicationdata";
    private static final int DATABASE_VERSION = 1;
    String sqlCreate1 = "CREATE TABLE user (id INTEGER PRIMARY KEY AUTOINCREMENT,codigo INTEGER, nombre VARCHAR(40))";
    String sqlCreate2 = "CREATE TABLE intentos (id INTEGER PRIMARY KEY,total INTEGER)";
    public databaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate1);
        db.execSQL(sqlCreate2);

        ContentValues values = new ContentValues();
        values.put("id", 1);
        values.put("total", 0);
        db.insert("intentos", null, values);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {

    }
}
