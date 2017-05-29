package com.matrimony.Design;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.matrimony.Adapter.Adapter_displayAll;
import com.matrimony.Bean.BeanRegistration;
import com.matrimony.DBHelper.DB_Registration;
import com.matrimony.R;

import java.util.ArrayList;

public class DisplayAllActivity extends AppCompatActivity {


    ListView lstAll;
    DB_Registration dbr;
    ArrayList<BeanRegistration> arrayReg;
    ArrayList<BeanRegistration> tempReg;
    Activity mactivity;

    EditText edSearch;

    String strRegID="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all);
        mactivity=this;
         dbr=new DB_Registration(this);

        lstAll=(ListView)findViewById(R.id.displayall_lst_all);
        edSearch=(EditText)findViewById(R.id.displayall_ed_search);

        registerForContextMenu(lstAll);
        arrayReg=dbr.selectAll();
        lstAll.setAdapter(new Adapter_displayAll(this,arrayReg));


        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String strname=edSearch.getText().toString();
                tempReg=new ArrayList<BeanRegistration>();
                for(int i=0;i<arrayReg.size();i++)
                {
                    if(arrayReg.get(i).getRegName().startsWith(strname))
                        tempReg.add(arrayReg.get(i));
                }
                lstAll.setAdapter(new Adapter_displayAll(mactivity,tempReg));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        lstAll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strid = ((TextView) view.findViewById(R.id.list_all_tv_id)).getText().toString();
                Intent in = new Intent(DisplayAllActivity.this, RegDetailActivity.class);
                in.putExtra("RegID", strid);
                startActivity(in);
            }
        });

        lstAll.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                strRegID= ((TextView) view.findViewById(R.id.list_all_tv_id)).getText().toString();
                return false;
            }
        });


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add("Edit");
        menu.add("Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {




        String str=item.getTitle().toString();
        if(str.equalsIgnoreCase("Edit"))
        {
            Intent in = new Intent(DisplayAllActivity.this, RegistrationActivity.class);
            in.putExtra("Title","Edit");
            in.putExtra("ID",strRegID);
            startActivity(in);

        }
        else
        {


            new AlertDialog.Builder(new ContextThemeWrapper(this,
                    android.R.style.Theme_Holo_Light_Dialog))

                    .setIcon(android.R.drawable.ic_dialog_alert) // icon that you want
                    .setTitle("Confirm") // title of your dialog
                    .setMessage("Are you sure want to delete?") // message of dialog
                    .setPositiveButton("Yes", // String for positive
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    // do positive action here


                                    dbr.deletByID(Integer.parseInt(strRegID));
                                    arrayReg=dbr.selectAll();
                                    lstAll.setAdapter(new Adapter_displayAll(mactivity,arrayReg));

                                }
                            }).setNegativeButton("No", // String for negative action
                    null).show();



        }


        return super.onOptionsItemSelected(item);


    }


}
