package com.matrimony.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.matrimony.Bean.BeanCity;
import com.matrimony.Bean.BeanRegistration;
import com.matrimony.Utility.Constant;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

/**
 * Created by Admin on 2/21/2016.
 */
public class DB_Registration extends SQLiteAssetHelper {

    public DB_Registration(Context context) {
        super(context, Constant.dbName, null, Constant.dbVersion);
    }


    public void insertData(BeanRegistration br) {
        int regID = 1;
        SQLiteDatabase db1 = getReadableDatabase();
        String strQuery = "select MAX(RegID) as HighestValue from Mat_Registration";
        Cursor cur = db1.rawQuery(strQuery, null);
        if (cur.moveToFirst()) {
            if (cur.getCount() > 0)
                regID = cur.getInt(cur.getColumnIndex("HighestValue")) + 1;
        }

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("RegID", regID);
        cv.put("RegName", br.getRegName());
        cv.put("RegAddress", br.getRegAddress());
        cv.put("CityID", br.getRegCityID());
        cv.put("RegMobile", br.getRegMobile());
        cv.put("RegEmail", br.getRegEmail());
        cv.put("RegGender", br.getRegGender());
        cv.put("RegDOB", br.getRegDOB());

        db.insert("Mat_Registration", null, cv);
        db.close();


    }

    public ArrayList<BeanRegistration> selectAll() {
        ArrayList<BeanRegistration> arrayReg = new ArrayList<BeanRegistration>();
        SQLiteDatabase db1 = getReadableDatabase();
        String strQuery = "select " +
                "reg.RegID," +
                "reg.RegName," +
                "reg.RegAddress," +
                "reg.RegGender," +
                "reg.RegEmail," +
                "reg.RegMobile," +
                "reg.RegDOB," +
                "reg.CityID," +
                "CASE WHEN fav.RegID is null THEN \"0\" ELSE fav.RegID END as FRegID " +
                "from Mat_Registration reg " +
                "left outer join Mat_Favourite fav " +
                "on reg.RegID=fav.RegID";
        Cursor cur = db1.rawQuery(strQuery, null);

        if (cur.moveToFirst()) {
            do {
                BeanRegistration br = new BeanRegistration();
                br.setRegID(cur.getInt(cur.getColumnIndex("RegID")));
                br.setRegName(cur.getString(cur.getColumnIndex("RegName")));
                br.setRegAddress(cur.getString(cur.getColumnIndex("RegAddress")));
                br.setRegCityID(cur.getInt(cur.getColumnIndex("CityID")));
                br.setRegGender(cur.getString(cur.getColumnIndex("RegGender")));
                br.setRegMobile(cur.getString(cur.getColumnIndex("RegMobile")));
                br.setRegEmail(cur.getString(cur.getColumnIndex("RegEmail")));
                br.setRegDOB(cur.getString(cur.getColumnIndex("RegDOB")));
                br.setFregID(cur.getInt(cur.getColumnIndex("FRegID")));
                arrayReg.add(br);


            } while (cur.moveToNext());
        }

        db1.close();
        return arrayReg;
    }

    public BeanRegistration selectByID(int regID) {

        BeanRegistration br = new BeanRegistration();
        SQLiteDatabase db1 = getReadableDatabase();
        String strQuery = "select * from Mat_Registration where RegID=" + regID;
        Cursor cur = db1.rawQuery(strQuery, null);

        if (cur.moveToFirst()) {


            br.setRegID(cur.getInt(cur.getColumnIndex("RegID")));
            br.setRegName(cur.getString(cur.getColumnIndex("RegName")));
            br.setRegAddress(cur.getString(cur.getColumnIndex("RegAddress")));
            br.setRegCityID(cur.getInt(cur.getColumnIndex("CityID")));
            br.setRegGender(cur.getString(cur.getColumnIndex("RegGender")));
            br.setRegMobile(cur.getString(cur.getColumnIndex("RegMobile")));
            br.setRegEmail(cur.getString(cur.getColumnIndex("RegEmail")));
            br.setRegDOB(cur.getString(cur.getColumnIndex("RegDOB")));


        }

        db1.close();
        return br;
    }

    public void deletByID(int regID) {


        SQLiteDatabase db1 = getWritableDatabase();
        String strQuery = "delete from Mat_Registration where RegID=" + regID;
        db1.execSQL(strQuery);

        db1.close();
    }


    public void updateData(BeanRegistration br) {


        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("RegName", br.getRegName());
        cv.put("RegAddress", br.getRegAddress());
        cv.put("CityID", br.getRegCityID());
        cv.put("RegMobile", br.getRegMobile());
        cv.put("RegEmail", br.getRegEmail());
        cv.put("RegGender", br.getRegGender());
        cv.put("RegDOB", br.getRegDOB());


        db.update("Mat_Registration", cv, "RegID=" + br.getRegID(), null);
        db.close();


    }

    public ArrayList<BeanRegistration> filterRecord(int cityID, String strGender) {
        strGender = '"' + strGender + '"';
        ArrayList<BeanRegistration> arrayReg = new ArrayList<BeanRegistration>();
        SQLiteDatabase db1 = getReadableDatabase();

        String strQuery = "select " +
                "reg.RegID," +
                "reg.RegName," +
                "reg.RegAddress," +
                "reg.RegGender," +
                "reg.RegEmail," +
                "reg.RegMobile," +
                "reg.RegDOB," +
                "reg.CityID," +
                "CASE WHEN fav.RegID is null THEN \"0\" ELSE fav.RegID END as FRegID " +
                "from Mat_Registration reg " +
                "left outer join Mat_Favourite fav " +
                "on reg.RegID=fav.RegID where reg.CityID=" + cityID + " and reg.RegGender=" + strGender;
        Cursor cur = db1.rawQuery(strQuery, null);

        if (cur.moveToFirst()) {
            do {
                BeanRegistration br = new BeanRegistration();
                br.setRegID(cur.getInt(cur.getColumnIndex("RegID")));
                br.setRegName(cur.getString(cur.getColumnIndex("RegName")));
                br.setRegAddress(cur.getString(cur.getColumnIndex("RegAddress")));
                br.setRegCityID(cur.getInt(cur.getColumnIndex("CityID")));
                br.setRegGender(cur.getString(cur.getColumnIndex("RegGender")));
                br.setRegMobile(cur.getString(cur.getColumnIndex("RegMobile")));
                br.setRegEmail(cur.getString(cur.getColumnIndex("RegEmail")));
                br.setRegDOB(cur.getString(cur.getColumnIndex("RegDOB")));
                br.setFregID(cur.getInt(cur.getColumnIndex("FRegID")));
                arrayReg.add(br);


            } while (cur.moveToNext());
        }

        db1.close();
        return arrayReg;
    }


}
