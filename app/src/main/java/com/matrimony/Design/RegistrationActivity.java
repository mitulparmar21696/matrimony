package com.matrimony.Design;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.matrimony.Adapter.AdpaterCitySpinner;
import com.matrimony.Bean.BeanCity;
import com.matrimony.Bean.BeanRegistration;
import com.matrimony.DBHelper.DB_City;
import com.matrimony.DBHelper.DB_Registration;
import com.matrimony.R;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class RegistrationActivity extends AppCompatActivity {

    EditText edName;
    EditText edAddress;
    EditText edEmail;
    EditText edMobile;
    EditText edDOB;
    RadioButton rbMale;
    RadioButton rbFemale;
    Spinner spCity;
    Button btnSubmit;
    Button btnCancel;


    private DatePickerDialog birthDatePickerDialog;
    private SimpleDateFormat dateFormatter;

    DB_Registration dbr;
    DB_City dbc;
    ArrayList <BeanCity> arrayCity;
    BeanRegistration breg;
    int strCity=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        dbr=new DB_Registration(this);
        dbc=new DB_City(this);
        breg=new BeanRegistration();
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        edName = (EditText) findViewById(R.id.reg_ed_name);
        edAddress = (EditText) findViewById(R.id.reg_ed_address);
        edEmail = (EditText) findViewById(R.id.reg_ed_email);
        edMobile = (EditText) findViewById(R.id.reg_ed_mobile);
        edDOB = (EditText) findViewById(R.id.reg_ed_dob);
        edDOB.setInputType(InputType.TYPE_NULL);

        rbMale = (RadioButton) findViewById(R.id.reg_rb_male);
        rbFemale = (RadioButton) findViewById(R.id.reg_rb_female);
        spCity = (Spinner) findViewById(R.id.reg_sp_city);
        btnSubmit = (Button) findViewById(R.id.reg_btn_submit);
        btnCancel = (Button) findViewById(R.id.reg_btn_Cancel);


        arrayCity=dbc.selectAllCity();
        spCity.setAdapter(new AdpaterCitySpinner(this,arrayCity));

        final String strStatus=getIntent().getStringExtra("Title");
        String strRegID=getIntent().getStringExtra("ID");


        if(strStatus.equalsIgnoreCase("Edit"))
        {

            breg=dbr.selectByID(Integer.parseInt(strRegID));
            edName.setText(breg.getRegName());
            edAddress.setText(breg.getRegAddress());
            spCity.setSelection(breg.getRegCityID()-1);
            edMobile.setText(breg.getRegMobile());
            edEmail.setText(breg.getRegEmail());
            edDOB.setText(breg.getRegDOB());
            String strGen=breg.getRegGender();
            if(strGen.equalsIgnoreCase("Male"))
                rbMale.setChecked(true);
            else
                rbFemale.setChecked(true);
            btnSubmit.setText("Update");
        }



        spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tvid =((TextView)view.findViewById(R.id.sp_city_tv_id));
                strCity=Integer.parseInt(tvid.getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        setDateTimeField();

        edDOB.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                birthDatePickerDialog.show();
                //setDateTimeField();
                return false;
            }
        });

        edDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag = 0;
                BeanRegistration br = new BeanRegistration();

                br.setRegCityID(strCity);
                if (edName.getText().length() > 0)
                    br.setRegName(edName.getText().toString());
                else {
                    flag = 1;
                    edName.setError("Enter Name");
                }

                if (edAddress.getText().length() > 0)
                    br.setRegAddress(edAddress.getText().toString());
                else {
                    flag = 1;
                    edAddress.setError("Enter Address");
                }


                br.setRegCityID(strCity);

                if (edMobile.getText().length() > 0)
                    br.setRegMobile(edMobile.getText().toString());
                else {
                    flag = 1;
                    edMobile.setError("Enter Mobile");
                }

                if (edEmail.getText().length() > 0)
                    br.setRegEmail(edEmail.getText().toString());
                else {
                    flag = 1;
                    edEmail.setError("Enter Email");
                }

                if (edDOB.getText().length() > 0)
                    br.setRegDOB(edDOB.getText().toString());
                else {
                    flag = 1;
                    edDOB.setError("Select DOB");
                }

                if (rbMale.isChecked())
                    br.setRegGender("Male");
                else
                    br.setRegGender("Female");

                if (flag == 0) {
                    if(strStatus.equalsIgnoreCase("Edit"))
                    {
                        br.setRegID(breg.getRegID());
                        dbr.updateData(br);
                        Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                        finish();
                    }
                    else {
                        dbr.insertData(br);
                        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
                        clearData();
                    }
                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearData();
            }
        });

    }

    void clearData() {
        edName.setText("");
        edAddress.setText("");
        edDOB.setText("");
        edMobile.setText("");
        edEmail.setText("");
        spCity.setSelection(0);
        rbMale.setChecked(true);

    }

    private void setDateTimeField() {

        Calendar newCalendar = Calendar.getInstance();
        birthDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                edDOB.setText(dateFormatter.format(newDate.getTime()));


            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


    }




}
