package com.areaonline;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.areaonline.user.modal.Pop_Cat_Response;
import com.areaonline.user.modal.Sub_Cat_Response;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {
    public MyDbHandler( Context context) {
        super(context, Params.DATABASE_NAME, null,Params.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String pop_category = "CREATE TABLE " + Params.TABLE_CATEGORY + "("
                + Params.CatName + " TEXT PRIMARY KEY ,"  +Params.Category_img
                + " TEXT, " + Params.Countcat + " TEXT "+ ")";
        String sub_category = "CREATE TABLE " + Params.TABLE_SUBCATEGORY + "("
                + Params.SubCatName + " TEXT PRIMARY KEY ,"  +Params.Catlogo
                + " TEXT, " + Params.Countsubcat + " TEXT "+ ")";
//        String card_img = "CREATE TABLE " + Params.TABLE_CARDIMG + "("
//                + Params.NO + " TEXT PRIMARY KEY ," + Params.INTRO_CARDPIC +  " BLOB )";
        db.execSQL(pop_category);
        db.execSQL(sub_category);
//        db.execSQL(card_img);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS "+ Params.TABLE_CATEGORY);
    db.execSQL("DROP TABLE IF EXISTS "+ Params.TABLE_SUBCATEGORY);
//    db.execSQL("DROP TABLE IF EXISTS "+ Params.TABLE_CARDIMG);
    onCreate(db);
    }

    public void addCategory(Pop_Cat_Response.Cat contact){
        SQLiteDatabase dbvvv = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Params.Category_img, contact.getCategoryImg());
        values.put(Params.CatName, contact.getCatName());
        values.put(Params.Countcat, contact.getCountcat());
        dbvvv.insert(Params.TABLE_CATEGORY, null, values);
        Log.d("dbharry", "addCategory Successfully inserted");
        dbvvv.close();
    }

    public void addSubCategory(Sub_Cat_Response.Cat contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Params.Catlogo, contact.getCatLogo());
        values.put(Params.SubCatName, contact.getSubCatName());
        values.put(Params.Countsubcat, contact.getCountsubcat());
   Log.d("dbharry", "addSubCategory Successfully inserted");

        db.insert(Params.TABLE_SUBCATEGORY, null, values);
        db.close();
    }

//    public void addCardImage(String number,byte[] Profileimg ){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(Params.NO, number);
//        values.put(Params.INTRO_CARDPIC, Profileimg);
//
//        db.insert(Params.TABLE_CARDIMG, null, values);
////        Log.d("dbharry", "Successfully inserted");
//        db.close();
//    }

    public List<Pop_Cat_Response.Cat> getPopCategory(){
        List<Pop_Cat_Response.Cat> contactList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Generate the query to read from the database
        String select = "SELECT * FROM " + Params.TABLE_CATEGORY;
        Cursor cursor = db.rawQuery(select, null);

        //Loop through now
        if(cursor.moveToFirst()){
            do{
                Pop_Cat_Response.Cat contact = new Pop_Cat_Response.Cat();
                contact.setCatName((cursor.getString(0)));
                contact.setCategoryImg(cursor.getString(1));
                contact.setCountcat(cursor.getString(2));
                contactList.add(contact);
            }while(cursor.moveToNext());
        }
        return contactList;
    }

    public List<Sub_Cat_Response.Cat> getSubCategory(){
        List<Sub_Cat_Response.Cat> contactList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Generate the query to read from the database
        String select = "SELECT * FROM " + Params.TABLE_SUBCATEGORY;
        Cursor cursor = db.rawQuery(select, null);

        //Loop through now
        if(cursor.moveToFirst()){
            do{
                Sub_Cat_Response.Cat contact = new Sub_Cat_Response.Cat();
                contact.setSubCatName((cursor.getString(0)));
                contact.setCatLogo(cursor.getString(1));
                contact.setCountsubcat(cursor.getString(2));
                contactList.add(contact);
            }while(cursor.moveToNext());
        }
        return contactList;
    }

    public int updateCategory(Pop_Cat_Response.Cat contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.CatName, contact.getCatName());
        values.put(Params.Category_img, contact.getCategoryImg());
        values.put(Params.Countcat, contact.getCountcat());

        //Lets update now
        return db.update(Params.TABLE_CATEGORY, values, Params.CatName + "=?",
                new String[]{String.valueOf(contact.getCatName())});
    }

    public int updateSubCategory(Sub_Cat_Response.Cat contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.SubCatName, contact.getSubCatName());
        values.put(Params.Catlogo, contact.getCatLogo());
        values.put(Params.Countsubcat, contact.getCountsubcat());

        //Lets update now
        return db.update(Params.TABLE_SUBCATEGORY, values, Params.SubCatName + "=?",
                new String[]{String.valueOf(contact.getSubCatName())});
    }

}
