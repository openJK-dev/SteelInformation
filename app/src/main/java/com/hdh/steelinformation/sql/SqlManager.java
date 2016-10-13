package com.hdh.steelinformation.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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
    //public List<Shop>
}
