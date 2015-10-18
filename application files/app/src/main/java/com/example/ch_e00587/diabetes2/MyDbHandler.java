package com.example.ch_e00587.diabetes2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {
    public static final int DB_version=1;
    public static final String DB_name="products.db";
    public static final String Table_products="products";
    public static final String column_id="id";
    public static final String column_name="column_name";
    public static final String dob="dob";
    public static final String cholestrol="cholestrol";
    public static final String bp="bp";
    public static final String hypo="hypo";
    public static final String glucose="glucose";
    public static final String column_name_user="column_name_user";
    public static final String image_name_user="image_name_user";
    private ArrayAdapter myAdapter;

    public MyDbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_name, factory, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query="create table if not exists "+Table_products+"("+column_id+" INTEGER PRIMARY KEY AUTOINCREMENT, "+column_name+" TEXT, "+dob+" TEXT, "+cholestrol+" INTEGER, "+bp+" INTEGER, "+hypo+" INTEGER, "+glucose+" INTEGER , "+column_name_user+" TEXT , "+image_name_user+" TEXT);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        Log.i("DIABETES1","On upgrade yet to do");
    }

    public void addProduct(Products product){
        Log.i("DIABETES1", "In add product start");
        ContentValues values=new ContentValues();
        Log.i("DIABETES1", "In add product started");
        values.put(column_name,product.get_name());
        values.put(dob,product.get_dob());
        values.put(cholestrol,product.get_cholestrol());
        values.put(bp,product.get_bp());
        values.put(hypo,product.get_hypo());
        values.put(glucose,product.get_glucose());
        values.put(column_name_user,product.get_column_name_user());
        values.put(image_name_user,product.get_image_name_user());

        SQLiteDatabase db=getWritableDatabase();
        db.insert(Table_products,null,values);
        db.close();
    }

    public void deleteProduct(String product){
        SQLiteDatabase db=getWritableDatabase();
        Log.i("DIABETES1", "In DbHandler for delete");
        //db.execSQL("delete from "+Table_products+" where "+column_name+"=\""+product+"\";");
        db.execSQL("delete from "+Table_products+";");
    }

/*    public String dbToString(){
        String dbName="";

        SQLiteDatabase db=getWritableDatabase();
        String query="select * from "+Table_products+" where 1";
        Cursor c=db.rawQuery(query,null);
        c.moveToFirst();

        //while (!c.isAfterLast()){
        while(c.moveToNext()){
            if(c.getString(c.getColumnIndex(column_name))!=null){
                dbName += c.getString(c.getColumnIndex(column_name));
                Log.i("DIABETES1", "In DbHandler after change");
            }
        }
        db.close();
        return dbName;
    }*/

    public List dbToString(){
//    String[] dbName=new String[6];
        //List<Products> dbName=new ArrayList<Products>();
        List<Object> dbName=new ArrayList();
        SQLiteDatabase db=getWritableDatabase();
        String query="select * from "+Table_products+" order by id desc limit 1";
        Log.i("DIABETES1", "In DbHandler db to string");
        Cursor c=db.rawQuery(query,null);
        c.moveToFirst();
        Log.i("DIABETES1", "Before while " + c.getColumnCount());

        c.moveToFirst();
        while (c.isAfterLast() == false) {
            //dbName1.add("n" + c.getString(1));
            Log.i("DIABETES1", "In DbHandler select product object if loop  name as "+(c.getString(c.getColumnIndex(column_name))));
            Products pd=new Products();
            pd.set_name(c.getString(c.getColumnIndex(column_name)));
            pd.set_dob(c.getString(c.getColumnIndex(dob)));
            pd.set_bp(c.getInt(c.getColumnIndex(bp)));
            pd.set_hypo(c.getInt(c.getColumnIndex(hypo)));
            pd.set_cholestrol(c.getInt(c.getColumnIndex(cholestrol)));
            pd.set_glucose(c.getInt(c.getColumnIndex(glucose)));
            pd.set_column_name_user(c.getString(c.getColumnIndex(column_name_user)));
            if(!(image_name_user.equals("abcd"))) {
                Log.i("DIABETES1", " in setting image "+image_name_user);
                pd.set_image_name_user(c.getString(c.getColumnIndex(image_name_user)));
            }

            /*dbName.add(new String(c.getString(c.getColumnIndex(column_name))));
            dbName.add(new String(c.getString(c.getColumnIndex(dob))));
            dbName.add(new Integer(c.getInt(c.getColumnIndex(bp))));
            dbName.add(new Integer(c.getInt(c.getColumnIndex(cholestrol))));
            dbName.add(new Integer(c.getInt(c.getColumnIndex(hypo))));
            dbName.add(new Integer(c.getInt(c.getColumnIndex(glucose))));*/

            c.moveToNext();
        }
        c.close();

        //while (!c.isAfterLast()){
    /*while(c.moveToNext()){
        Log.i("DIABETES1", "In DbHandler select product object while loop  name as "+c.getColumnIndex(column_name));
        if(c.getString(c.getColumnIndex(column_name))!=null){
            Log.i("DIABETES1", "In DbHandler select product object if loop  name as "+c.getColumnIndex(column_name));
            dbName1.add(new String(c.getString(c.getColumnIndex(column_name))));
            dbName1.add(new String(c.getString(c.getColumnIndex(dob))));
            dbName1.add(new Integer(c.getInt(c.getColumnIndex(bp))));
            dbName1.add(new Integer(c.getInt(c.getColumnIndex(cholestrol))));
            dbName1.add(new Integer(c.getInt(c.getColumnIndex(hypo))));
            dbName1.add(new Integer(c.getInt(c.getColumnIndex(glucose))));
            Log.i("DIABETES1", "In DbHandler select product object while loop  values added to dbname1");
            /*Products pd=new Products(c.getString(c.getColumnIndex(column_name)),c.getString(c.getColumnIndex(dob)),c.getInt(c.getColumnIndex(bp)),c.getInt(c.getColumnIndex(cholestrol)),c.getInt(c.getColumnIndex(hypo)),c.getInt(c.getColumnIndex(glucose)));
            Log.i("DIABETES1", "In DbHandler select product object with value"+c.getColumnIndex(column_name));
            dbName.add(pd);
            Log.i("DIABETES1", "In while "+c.getColumnCount());
            /*dbName[0] = c.getString(c.getColumnIndex(column_name));
            dbName[1] = c.getString(c.getColumnIndex(dob));
            dbName[2] = c.getString(c.getColumnIndex(bp));
            dbName[3] = c.getString(c.getColumnIndex(cholestrol));
            dbName[4] = c.getString(c.getColumnIndex(hypo));
            dbName[5] = c.getString(c.getColumnIndex(glucose));
            Log.i("DIABETES1", "In DbHandler after change");

        }
        else{
            Log.i("DIABETES1", "In DbHandler coulumn name null");
        }
    }
    c.close();*/
        Log.i("DIABETES1", "In DbHandler After while");
        db.close();
        return dbName;
    }

}

