package com.matrimony.Design;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.matrimony.Adapter.Adapter_displayAll;
import com.matrimony.R;
import com.matrimony.WebServiceActivity;

public class DashBoardActivity extends AppCompatActivity {

    Button btnRegistration;
    Button btnDisplayAll;
    Button btnSearch;
    Button btnWeb;
    Button btnfav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        btnRegistration = (Button) findViewById(R.id.dashboard_btn_Reg);
        btnDisplayAll = (Button) findViewById(R.id.dashboard_btn_displayall);
        btnSearch = (Button) findViewById(R.id.dashboard_btn_search);
        btnWeb = (Button) findViewById(R.id.dashboard_btn_webservice);
        btnfav = (Button) findViewById(R.id.dashboard_btn_favourite);
        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoardActivity.this,RegistrationActivity.class);
                intent.putExtra("Title","Save");
                startActivity(intent);
            }
        });

        btnDisplayAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoardActivity.this,DisplayAllActivity.class);
                startActivity(intent);
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoardActivity.this,FilterActivity.class);
                startActivity(intent);
            }
        });

        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkConnected()) {
                    Intent intent = new Intent(DashBoardActivity.this, WebServiceActivity.class);
                    startActivity(intent);
                }
                else
                {
                    new AlertDialog.Builder(new ContextThemeWrapper(getApplicationContext(),
                            android.R.style.Theme_Holo_Light_Dialog))

                            .setIcon(android.R.drawable.ic_dialog_alert) // icon that you want
                            .setTitle("Confirm") // title of your dialog
                            .setMessage("Check Internet Connection!") // message of dialog
                            .setPositiveButton("Yes",null).show();
                }
            }
        });
        btnfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoardActivity.this,FavListActivity.class);
                startActivity(intent);
            }
        });

    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_splash, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_registration:
                Intent intent = new Intent(DashBoardActivity.this,RegistrationActivity.class);
                intent.putExtra("Title","Save");
                startActivity(intent);
                return true;

            case R.id.action_all:
                Intent intent1 = new Intent(DashBoardActivity.this,DisplayAllActivity.class);
                startActivity(intent1);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
