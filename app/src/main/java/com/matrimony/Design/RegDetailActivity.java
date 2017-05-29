package com.matrimony.Design;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.matrimony.Bean.BeanRegistration;
import com.matrimony.DBHelper.DB_City;
import com.matrimony.DBHelper.DB_Registration;
import com.matrimony.R;

public class RegDetailActivity extends AppCompatActivity {

    TextView tvName;
    TextView tvAddress;
    TextView tvCity;
    TextView tvMobile;
    TextView tvEmail;
    TextView tvDOB;
    TextView tvGender;
    DB_Registration dbr;
    DB_City dbc;
    BeanRegistration br;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_detail);


        tvName=(TextView)findViewById(R.id.regDetail_tv_name);
        tvAddress=(TextView)findViewById(R.id.regDetail_tv_address);
        tvCity=(TextView)findViewById(R.id.regDetail_tv_City);
        tvMobile=(TextView)findViewById(R.id.regDetail_tv_mobile);
        tvEmail=(TextView)findViewById(R.id.regDetail_tv_email);
        tvDOB=(TextView)findViewById(R.id.regDetail_tv_dob);
        tvGender=(TextView)findViewById(R.id.regDetail_tv_gender);

        String regID=getIntent().getStringExtra("RegID");
        dbr=new DB_Registration(this);
        dbc=new DB_City(this);
        br=dbr.selectByID(Integer.parseInt(regID));

        tvName.setText(br.getRegName());
        tvAddress.setText(br.getRegAddress());
        tvCity.setText(dbc.selectByID(br.getRegCityID()));
        tvMobile.setText(br.getRegMobile());
        tvEmail.setText(br.getRegEmail());
        tvDOB.setText(br.getRegDOB());
        tvGender.setText(br.getRegGender());




    }
}
