package com.hdh.steelinformation.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by huangdianhua on 2016/10/12 14:02.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private final static String NAME = "steelinformation";
    private final static int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table steel(" +
                "name varchar(10)," +//种类（铁，铜，不锈钢等）
                "area varchar(10)," +//区域
                "time varchar(10)," +//时间
                "stitle text," +//标题
                "sname text," +//品名
                "ssize text," +//规格
                "splace text," +//交易地
                "spricet text," +//今天价
                "spricey text," +//昨天价
                "spriceb text," +//价格区间
                "sutil text," +//单位
                "sevent text," +//涨跌情况
                "sremarks text)");//备注

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
