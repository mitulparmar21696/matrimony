package com.matrimony.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.matrimony.Bean.BeanCity;
import com.matrimony.R;

import java.util.ArrayList;

/**
 * Created by Admin on 2/21/2016.
 */
public class AdpaterCitySpinner extends BaseAdapter {

    ArrayList<BeanCity> arrayCity;
    Activity act;

    public AdpaterCitySpinner(Activity act, ArrayList<BeanCity> arrayCity) {
        this.arrayCity = arrayCity;
        this.act = act;
    }

    @Override
    public int getCount() {
        return arrayCity.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayCity.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {

        TextView txtID;
        TextView txtName;


    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        final ViewHolder holder;
        LayoutInflater inflater = act.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.sp_city, null);
            holder = new ViewHolder();
            holder.txtID = (TextView) convertView
                    .findViewById(R.id.sp_city_tv_id);
            holder.txtName = (TextView) convertView
                    .findViewById(R.id.sp_city_tv_name);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }



        holder.txtID.setText(arrayCity.get(position).getCityID()+"");
        holder.txtName.setText(arrayCity.get(position).getCityName()+"");


        return convertView;

    }
}
