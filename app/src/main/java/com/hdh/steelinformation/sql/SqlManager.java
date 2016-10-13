package com.hdh.steelinformation.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.hdh.steelinformation.entitys.Shop;

/**
 * Created by huangdianhua on 2016/10/12 14:20.
 */
public class SqlManager {
    private SQLiteDatabase db;

    public SqlManager(Context context) {
        DatabaseHelper d = new DatabaseHelper(context);
        db = d.getWritableDatabase();
    }

    public void close() {
        if (db != null) {
            db.close();
        }
    }

    /**
     * 插入铁的数据
     *
     * @param shop
     * @return
     */
    public boolean insertSteel(Shop shop) {
        long value;
        ContentValues c = new ContentValues();
        c.put("name", shop.getName());
        c.put("area", shop.getArea());
        c.put("time", shop.getTime());
        c.put("stitle", shop.getStitle());
        c.put("sname", shop.getSname());
        c.put("ssize", shop.getSsize());
        c.put("splace", shop.getSplace());
        c.put("spricet", shop.getSpricet());
        c.put("spricey", shop.getSpricey());
        c.put("sevent", shop.getSevent());
        c.put("sremarks", shop.getSremarks());
        value = db.insert("steel", null, c);
        if (value == -1) {
            return false;
        }
        return true;
    }

    /**
     * 插入铜、不锈钢数据
     *
     * @param shop
     */
    public boolean insertCopper(Shop shop) {
        long value;
        ContentValues c = new ContentValues();
        c.put("name", shop.getName());
        c.put("area", shop.getArea());
        c.put("time", shop.getTime());
        c.put("stitle", shop.getStitle());
        c.put("sname", shop.getSname());
        c.put("ssize", shop.getSsize());
        c.put("splace", shop.getSplace());
        c.put("spriceb", shop.getSpriceb());
        c.put("sevent", shop.getSevent());
        c.put("sremarks", shop.getSremarks());
        value = db.insert("steel", null, c);
        if (value == -1) {
            return false;
        }
        return true;
    }

    /**
     * 插入铝数据
     *
     * @param shop
     */
    public boolean insertAluminum(Shop shop) {
        long value;
        ContentValues c = new ContentValues();
        c.put("name", shop.getName());
        c.put("area", shop.getArea());
        c.put("time", shop.getTime());
        c.put("stitle", shop.getStitle());
        c.put("sname", shop.getSname());
        c.put("ssize", shop.getSsize());
        c.put("splace", shop.getSplace());
        c.put("spriceb", shop.getSpriceb());
        c.put("sevent", shop.getSevent());
        c.put("sremarks", shop.getSremarks());
        c.put("sutil", shop.getSutil());
        value = db.insert("steel", null, c);
        if (value == -1) {
            return false;
        }
        return true;
    }

    public void getData() {
        Log.d("hdh", "=============================");
        Cursor cursor = db.rawQuery("select * from steel", null);
        Log.d("hdh", "=============================" + cursor.getCount());
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            Log.d("hdh", "=============================");
            Log.d("hdh", cursor.getString(0) + cursor.getString(1) + cursor.getString(2) + cursor.getString(3) + cursor.getString(4) + cursor.getString(5) + cursor.getString(6) + cursor.getString(7) +
                    cursor.getString(8) + cursor.getString(9) + cursor.getString(10) + cursor.getString(11) + cursor.getString(12));
        }
    }
}
