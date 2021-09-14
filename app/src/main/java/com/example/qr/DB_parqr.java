package com.example.qr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

public class DB_parqr extends SQLiteOpenHelper {

    public DB_parqr(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "parqr.DB", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql1 = "CREATE TABLE PARKIR(HASIL STRING);";
        sqLiteDatabase.execSQL(sql1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql1 = "DROP TABLE IF EXISTS PARKIR;";
        sqLiteDatabase.execSQL(sql1);
        onCreate(sqLiteDatabase);
    }

    public void insert_qr(String hasil){
        ContentValues contentValues = new ContentValues();
        contentValues.put("HASIL", hasil);
        this.getWritableDatabase().insertOrThrow("PARKIR","",contentValues);
    }

    public void list_qr(TextView textView) {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM PARKIR", null);
        textView.setText("");
        while (cursor.moveToNext()){
            textView.append(cursor.getString(0) + "\n" + "\n");
        }
    }

}
