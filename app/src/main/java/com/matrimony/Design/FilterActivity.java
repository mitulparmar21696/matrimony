package com.matrimony.Design;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.matrimony.Adapter.Adapter_displayAll;
import com.matrimony.Adapter.AdpaterCitySpinner;
import com.matrimony.Bean.BeanCity;
import com.matrimony.Bean.BeanRegistration;
import com.matrimony.DBHelper.DB_City;
import com.matrimony.DBHelper.DB_Registration;
import com.matrimony.R;

import java.util.ArrayList;

public class FilterActivity extends AppCompatActivity {
    RadioButton rbMale;
    RadioButton rbFemale;
    Spinner spCity;
    Button btnSearch;
    ListView lstDisplay;

    DB_City dbc;
    ArrayList<BeanCity> arrayCity;
    DB_Registration dbr;
    ArrayList<BeanRegistration> arrayReg;
    int intCity=1;
    Activity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        act=this;
        dbr=new DB_Registration(this);

        rbMale=(RadioButton)findViewById(R.id.filter_rb_male);
        rbFemale=(RadioButton)findViewById(R.id.filter_rb_female);
        spCity=(Spinner)findViewById(R.id.filter_sp_city);
        btnSearch=(Button)findViewById(R.id.filter_btn_search);
        lstDisplay=(ListView)findViewById(R.id.filter_list);

        dbc=new DB_City(this);
        arrayCity=dbc.selectAllCity();
        spCity.setAdapter(new AdpaterCitySpinner(this,arrayCity));

        spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tvid = ((TextView) view.findViewById(R.id.sp_city_tv_id));
                intCity = Integer.parseInt(tvid.getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lstDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strid = ((TextView) view.findViewById(R.id.list_all_tv_id)).getText().toString();
                Intent in = new Intent(FilterActivity.this, RegDetailActivity.class);
                in.putExtra("RegID", strid);
                startActivity(in);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strGender="";
                if(rbMale.isChecked())
                    strGender="Male";
                else
                    strGender="Female";

                arrayReg=dbr.filterRecord(intCity,strGender);
                lstDisplay.setAdapter(new Adapter_displayAll(act,arrayReg));


            }
        });
    }
}
