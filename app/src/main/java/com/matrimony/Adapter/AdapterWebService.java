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
import com.matrimony.Bean.BeanWebService;
import com.matrimony.R;

import java.util.ArrayList;

/**
 * Created by Admin on 2/21/2016.
 */
public class AdapterWebService extends BaseAdapter {

    ArrayList<BeanWebService> arrayCity;
    Activity act;

    public AdapterWebService(Activity act, ArrayList<BeanWebService> arrayCity) {
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
        TextView txtNo;


    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        final ViewHolder holder;
        LayoutInflater inflater = act.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_webservice, null);
            holder = new ViewHolder();
            holder.txtID = (TextView) convertView
                    .findViewById(R.id.list_ws_id);
            holder.txtNo = (TextView) convertView
                    .findViewById(R.id.list_ws_no);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }



        holder.txtID.setText(arrayCity.get(position).getStrBusID()+"");
        holder.txtNo.setText(arrayCity.get(position).getStrBusNo()+"");


        return convertView;

    }
}
