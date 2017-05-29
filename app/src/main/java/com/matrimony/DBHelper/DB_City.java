package com.matrimony.DBHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.matrimony.Bean.BeanCity;
import com.matrimony.Utility.Constant;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

/**
 * Created by Admin on 2/22/2016.
 */
public class DB_City extends SQLiteAssetHelper {

    public DB_City(Context context) {
        super(context, Constant.dbName, null, Constant.dbVersion);
    }

    public ArrayList<BeanCity> selectAllCity() {
        ArrayList<BeanCity> arrayCity = new ArrayList<BeanCity>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "Select * from MST_City order by CityID";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {

                BeanCity bc = new BeanCity();
                bc.setCityID(cursor.getInt(cursor.getColumnIndex("CityID")));
                bc.setCityName(cursor.getString(cursor.getColumnIndex("CityName")));
                arrayCity.add(bc);
            } while (cursor.moveToNext());
        }
        db.close();
        return arrayCity;
    }

    public String selectByID(int cityid) {

        SQLiteDatabase db = getReadableDatabase();
        String query = "Select CityName from MST_City where CityID=" + cityid;
        Cursor cursor = db.rawQuery(query, null);
        String strcity = "";
        if (cursor.moveToFirst())
            strcity = cursor.getString(cursor.getColumnIndex("CityName"));

        db.close();
        return strcity;
    }

}
