package com.matrimony.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.matrimony.Bean.BeanCity;
import com.matrimony.Bean.BeanRegistration;
import com.matrimony.DBHelper.DB_Favourite;
import com.matrimony.R;

import java.util.ArrayList;

/**
 * Created by Admin on 2/21/2016.
 */
public class Adapter_displayAll extends BaseAdapter {

    ArrayList<BeanRegistration> arrayCity;
    Activity act;
    DB_Favourite dbf;

    public Adapter_displayAll(Activity act, ArrayList<BeanRegistration> arrayCity) {
        this.arrayCity = arrayCity;
        this.act = act;
        dbf=new DB_Favourite(act);
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
        TextView txtGender;
        ImageView imgFav;


    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        final ViewHolder holder;
        LayoutInflater inflater = act.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_all_layout, null);
            holder = new ViewHolder();
            holder.txtID = (TextView) convertView
                    .findViewById(R.id.list_all_tv_id);
            holder.txtName = (TextView) convertView
                    .findViewById(R.id.list_all_tv_name);
            holder.txtGender = (TextView) convertView
                    .findViewById(R.id.list_all_tv_gender);
            holder.imgFav = (ImageView) convertView
                    .findViewById(R.id.list_all_img_fav);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }



        holder.txtID.setText(arrayCity.get(position).getRegID()+"");
        holder.txtName.setText(arrayCity.get(position).getRegName() + "");
        holder.txtGender.setText(arrayCity.get(position).getRegGender() + "");
        if(arrayCity.get(position).getFregID()>0) {
            holder.imgFav.setImageResource(R.mipmap.ic_fav_dark);
            holder.imgFav.setTag("Dark=" + holder.txtID.getText());
        }
        else
        {
            holder.imgFav.setImageResource(R.mipmap.ic_fav_light);
            holder.imgFav.setTag("Light=" + holder.txtID.getText());
        }


        holder.imgFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tagName = holder.imgFav.getTag().toString();
                String str[] = tagName.split("=");
                tagName = str[0];
                String strID = str[1];


                if (tagName.equalsIgnoreCase("Light")) {
                    holder.imgFav.setImageResource(R.mipmap.ic_fav_dark);
                    holder.imgFav.setTag("Dark=" + strID);
                    dbf.insertFavourite(strID);
                } else {
                    holder.imgFav.setImageResource(R.mipmap.ic_fav_light);
                    holder.imgFav.setTag("Light=" + strID);
                    dbf.deletByID(strID);
                }
            }
        });


        return convertView;

    }
}
