package com.matrimony.Design;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.matrimony.Adapter.Adapter_displayAll;
import com.matrimony.Bean.BeanRegistration;
import com.matrimony.DBHelper.DB_Favourite;
import com.matrimony.DBHelper.DB_Registration;
import com.matrimony.R;

import java.util.ArrayList;

public class FavListActivity extends AppCompatActivity {

    ListView lstAll;
    DB_Favourite dbr;
    ArrayList<BeanRegistration> arrayReg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_list);

        dbr=new DB_Favourite(this);

        lstAll=(ListView)findViewById(R.id.fav_lst_all);


        arrayReg=dbr.selectAll();
        lstAll.setAdapter(new Adapter_displayAll(this, arrayReg));


        lstAll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strid = ((TextView) view.findViewById(R.id.list_all_tv_id)).getText().toString();
                Intent in = new Intent(FavListActivity.this, RegDetailActivity.class);
                in.putExtra("RegID", strid);
                startActivity(in);
            }
        });
    }
}
