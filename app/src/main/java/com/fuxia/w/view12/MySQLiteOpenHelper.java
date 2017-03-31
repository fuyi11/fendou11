package com.fuxia.w.view12;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by fuyi on 2017/2/27.
 */

public class MySQLiteOpenHelper  extends SQLiteOpenHelper {

    //定义一个创建表Book的SQLite语句
    private static final String CREATE_BOOK = "create table Book("
            + "id integer primary key autoincrement, "
            + "author text,"
            + "price real, "
            + "pages integer, "
            + "name text)";

    //定义一个创建表Category的SQLite语句
    private static final String CREATE_CATEGORY = "create table Category("
            + "id integer primary key autoincrement, "
            + "category_name text, "
            + "category_code integer)";

    private Context mContext;

    /*
    * 调用父类构造器
    *
    *   name 数据库的名字
    *  factory  to use for creating cursor objects 目的为了创建一个cursor对象  ResultSet
    *  version 数据库的版本  必须从1 开始
    */

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;     //获取Context实例
    }


    /**
     * 当数据库首次创建时执行该方法，一般将创建表等初始化操作放在该方法中执行.
     * 重写onCreate方法，调用execSQL方法创建表
     * */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
        Toast.makeText(mContext, "成功创建Database", Toast.LENGTH_SHORT).show();
    }


    //当打开数据库时传入的版本号与当前的版本号不同时会调用该方法
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists Book");        //如果存在表Book，则删除该表
        db.execSQL("drop table if exists Category");    //如果存在表Category，则删除该表
        onCreate(db);       //重新调用onCreate()，创建两张表
    }
}
