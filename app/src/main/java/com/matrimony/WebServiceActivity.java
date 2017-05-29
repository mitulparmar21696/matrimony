package com.matrimony;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.matrimony.Adapter.AdapterWebService;
import com.matrimony.Bean.BeanWebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class WebServiceActivity extends AppCompatActivity {



    JSONObject jsonObject = null;
    private final String NAMESPACE = "http://tempuri.org/";
    private final String URL = "http://180.211.109.178/darshanws/busservice.asmx";
    private final String SOAP_ACTION = "http://tempuri.org/BUS_Bus_SelectAll_Test";
    private final String METHOD_NAME = "BUS_Bus_SelectAll_Test";
    private String webResponse = "";



    Activity act;
    ListView lstDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);
        act = this;
        lstDisplay = (ListView) findViewById(R.id.webservice_list);

        DownLoadData dw = new DownLoadData();
        dw.execute();


    }


    public class DownLoadData extends AsyncTask<String, Void, String> {



        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pd = new ProgressDialog(act);
            pd.setMessage("Please Wait.....");
            pd.setIndeterminate(false);
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.setCancelable(false);
            pd.show();


        }

        @Override
        protected String doInBackground(String... urls) {


            SoapObject request_Zone = new SoapObject(NAMESPACE, METHOD_NAME);
            PropertyInfo appName=new PropertyInfo();
            appName.setName("AppName");
            appName.setValue("ACPC");
            request_Zone.addProperty(appName);
            SoapSerializationEnvelope envelope_Zone = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope_Zone.dotNet = true;
            envelope_Zone.setOutputSoapObject(request_Zone);
            HttpTransportSE androidHttpTransport_Zone = new HttpTransportSE(URL);

            try {
                androidHttpTransport_Zone.call(SOAP_ACTION, envelope_Zone);
            } catch (IOException e) {
                e.printStackTrace();

            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
            SoapPrimitive response_Zone = null;
            try {
                response_Zone = (SoapPrimitive) envelope_Zone.getResponse();
            } catch (SoapFault e) {
                e.printStackTrace();

            }

            webResponse = response_Zone.toString();




            return webResponse;

        }


        @Override
        protected void onPostExecute(String webResponse) {

            JSONArray jsonArray = null;

            ArrayList<BeanWebService> arrayWeb = new ArrayList<BeanWebService>();

            try {
                jsonArray = new JSONArray(webResponse);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    jsonObject = jsonArray.getJSONObject(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                BeanWebService bw = new BeanWebService();
                try {
                    bw.setStrBusID(jsonObject.getString("BusID"));
                    bw.setStrBusNo(jsonObject.getString("BusNo"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                arrayWeb.add(bw);

            }

            lstDisplay.setAdapter(new AdapterWebService(act, arrayWeb));


        }
    }
}

