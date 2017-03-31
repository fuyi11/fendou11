package com.fuxia.w.view12;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fuxia.w.R;

import static com.fuxia.w.R.color.view;

/**
 * Created by fuyi on 2017/2/27.
 */
public class MyDataBaseActivity extends AppCompatActivity implements View.OnClickListener{

    private MySQLiteOpenHelper dbHelper;
    private static final String TAG = "Steven";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_database);
//        dbHelper = new MySQLiteOpenHelper(MyDataBaseActivity.this,"BookStore.db", null, 5);
        dbHelper = new MySQLiteOpenHelper(MyDataBaseActivity.this,"fujialiang.db", null, 1);/*
	 *  name 数据库的名字
	 *  factory  to use for creating cursor objects 目的为了创建一个cursor对象  ResultSet
	 *  version 数据库的版本  必须从1 开始
	 */


        Button mCreate = (Button) findViewById(R.id.btn_create_database);
        Button mInsert = (Button) findViewById(R.id.btn_insert_data);
        Button mDelete = (Button) findViewById(R.id.btn_delete_data);
        Button mUpdate = (Button) findViewById(R.id.btn_update_data);
        Button mUuery = (Button) findViewById(R.id.btn_query_data);

        mCreate.setOnClickListener(this);
        mInsert.setOnClickListener(this);
        mDelete.setOnClickListener(this);
        mUpdate.setOnClickListener(this);
        mUuery.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            // 1.创建并打开数据库fujialiang.db,回调MySQLiteOpenHelper类中的onCreate()方法创建表
            case R.id.btn_create_database:
                dbHelper.getWritableDatabase();
                break;

            // 2.向表中插入数据: insert
            case R.id.btn_insert_data:
                SQLiteDatabase db = dbHelper.getWritableDatabase(); //获取返回的SQLiteDatabase对象
                ContentValues contentValues = new ContentValues();

                //组装数据
                contentValues.put("name","第一行代码");
                contentValues.put("author","伏毅");
                contentValues.put("pages", 552);
                contentValues.put("price",79.0);
                db.insert("Book", null, contentValues); //向表Book中插入一条数据
                contentValues.clear();

                //组装数据
                contentValues.put("name","第二行代码");
                contentValues.put("author","王言");
                contentValues.put("pages", 662);
                contentValues.put("price",89.0);
                db.insert("Book", null, contentValues); //向表Book中插入第二条数据
                Toast.makeText(this, "成功插入数据", Toast.LENGTH_SHORT).show();
                break;

            // 3.修改数据: update
            case R.id.btn_update_data:
                SQLiteDatabase db1 = dbHelper.getWritableDatabase();
                ContentValues contentValues1 = new ContentValues();
                contentValues1.put("name", "第三行代码");
                contentValues1.put("author", "程怡雪");
                db1.update("Book", contentValues1,"name = ?",new String[]{"第二行代码"});
                db1.update("Book", contentValues1,"author = ?",new String[]{"王言"});
                Toast.makeText(this, "成功修改数据", Toast.LENGTH_SHORT).show();
                break;

            // 4.删除数据：delete
            case R.id.btn_delete_data:
                SQLiteDatabase db2 = dbHelper.getWritableDatabase();
                db2.delete("Book", "pages > ?", new String[]{"600"});
//                db2.delete("Book", "name > ?", new String[]{"600"});//全部删除
                Toast.makeText(this, "成功删除一条数据", Toast.LENGTH_SHORT).show();
                break;

            // 5.查询数据: query
            case R.id.btn_query_data:
                SQLiteDatabase db3 = dbHelper.getWritableDatabase();
                Cursor cursor = db3.query("Book", null, null, null, null, null, null);
                //查询表中所有数据
                if(cursor.moveToFirst()){
                    //遍历Cursor对象，取出数据
                    do{
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));

                        Log.d(TAG,"Book name: " + name);
                        Log.d(TAG,"Book author: " + author);
                        Log.d(TAG,"Book pages: " + pages);
                        Log.d(TAG,"Book price: " + price);
                    }while(cursor.moveToNext());
                    cursor.close();
                }
                Toast.makeText(this, "查询出表中全部数据", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
