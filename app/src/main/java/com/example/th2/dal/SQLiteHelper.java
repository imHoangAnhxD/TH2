package com.example.th2.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.th2.model.Item;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="DATA.db";
    private static int DATABASE_VERSION=1;

    public SQLiteHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql= "CREATE TABLE items("+"id INTEGER PRIMARY KEY AUTOINCREMENT,"+"title TEXT,category TEXT,price TEXT,date TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
    // lay item theo thu tu ngay
    public List<Item>getAll(){
        List<Item> list=new ArrayList<>();
        SQLiteDatabase st=getReadableDatabase();
        String order="date DESC";
        Cursor rs=st.query("items",null,null,null,null,null,order);
        while (rs!=null && rs.moveToNext()){
            int id=rs.getInt(0);
            String title =rs.getString(1);
            String c =rs.getString(2);
            String p =rs.getString(3);
            String date =rs.getString(4);
            list.add(new Item(id,title,c,p,date));
        }
    return list;

    }
    // them item
    public  long addItem(Item i){
        ContentValues values=new ContentValues();
        values.put("title",i.getTitle());
        values.put("category",i.getCategory());
        values.put("price",i.getPrice());
        values.put("date",i.getDate());
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        return sqLiteDatabase.insert("items",null,values);

    }
    // Lay item theo ngay
    public List<Item> getByDate(String date){
        List<Item> list=new ArrayList<>();
        String whereClause="date like ?";
        String[] whereArgs={date};
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.query("items",null,whereClause,whereArgs,null,null,null);
        while (rs!=null && rs.moveToNext()){
            int id=rs.getInt(0);
            String title =rs.getString(1);
            String c =rs.getString(2);
            String p =rs.getString(3);

            list.add(new Item(id,title,c,p,date));
        }

        return list;
    }
//sua
    public int update(Item i){
        ContentValues values=new ContentValues();
        values.put("title",i.getTitle());
        values.put("category",i.getCategory());
        values.put("price",i.getPrice());
        values.put("date",i.getDate());
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        String whereClause="id like ?";
        String[] whereArgs={Integer.toString(i.getId())};
        return sqLiteDatabase.update("items",values,whereClause,whereArgs);

    }
    //xoa
    public int delete(int id){

        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        String whereClause="id like ?";
        String[] whereArgs={Integer.toString(id)};
        return sqLiteDatabase.delete("items",whereClause,whereArgs);

    }
    // tim bang key
    public List<Item> searchByTitle(String key){
        List<Item> list=new ArrayList<>();
        String whereClause="title like ?";
        String[] whereArgs={"%"+key+"%"};
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.query("items",null,whereClause,whereArgs,null,null,null);
        while (rs!=null && rs.moveToNext()){
            int id=rs.getInt(0);
            String title =rs.getString(1);
            String c =rs.getString(2);
            String p =rs.getString(3);
            String d=rs.getString(4);
            list.add(new Item(id,title,c,p,d));
        }

        return list;
    }
    public List<Item> searchByCategory(String category){
        List<Item> list=new ArrayList<>();
        String whereClause="category like ?";
        String[] whereArgs={category};
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.query("items",null,whereClause,whereArgs,null,null,null);
        while (rs!=null && rs.moveToNext()){
            int id=rs.getInt(0);
            String title =rs.getString(1);
            String c =rs.getString(2);
            String p =rs.getString(3);
            String d = rs.getString(4);
            list.add(new Item(id,title,c,p,d));
        }

        return list;
    }
    public List<Item> getByDateFromTo(String from,String to){
        List<Item> list=new ArrayList<>();
        String whereClause="date BETWEEN ? AND ?";
        String[] whereArgs={from.trim(),to.trim()};
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.query("items",null,whereClause,whereArgs,null,null,null);
        while (rs!=null && rs.moveToNext()){
            int id=rs.getInt(0);
            String title =rs.getString(1);
            String c =rs.getString(2);
            String p =rs.getString(3);
            String d=rs.getString(4);
            list.add(new Item(id,title,c,p,d));
        }

        return list;
    }
}
