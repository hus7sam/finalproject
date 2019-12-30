package com.codingelab.Final_Project_409;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    private Button onSyn;
    private Button btnexternalDBPHP;
    private Button btnSqlLite;

    private DB_management_External DBmanagementExternal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        this.onSyn=(Button)findViewById(R.id.onSyn);
        this.DBmanagementExternal =new DB_management_External();


        this.onSyn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    TruncateServerTable();

                    SyncData();
                    Toast.makeText(MainActivity.this, "All Data Synchronized", Toast.LENGTH_SHORT).show();

                }
                catch(Exception ex)
                {
                    Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        this.btnexternalDBPHP = (Button) findViewById(R.id.btnexternalPHP);
        btnexternalDBPHP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MoveTo_PHP = new Intent(MainActivity.this, Control_Panel_External.class);
                startActivity(MoveTo_PHP);
            }
        });

        // فتح قاعدة البيانات المحلية
        btnSqlLite = (Button) findViewById(R.id.btnLocalDb);
        final Intent sqlLiteIntent = new Intent(this, Control_Panel_Internal.class);
        btnSqlLite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(sqlLiteIntent);
            }
        });

    }

    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }


            @Override
            protected String doInBackground(Void... voids) {



                try {
                    //creating a URL
                    URL url = new URL(urlWebService);

                    //Opening the URL using HttpURLConnection
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    //StringBuilder object to read the string from the service
                    StringBuilder sb = new StringBuilder();

                    //We will use a buffered reader to read the string from service
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    //A simple string to read values from each line
                    String json;

                    //reading until we don't find null
                    while ((json = bufferedReader.readLine()) != null) {

                        //appending it to string builder
                        sb.append(json + "\n");
                    }

                    //finally returning the read string
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }

            }
        }

        //creating asynctask object and executing it
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void TruncateServerTable() {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL("http://172.20.10.4:80/sqli/mysql_truncate.php");

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void SyncData()
    {
        DBHelper mydb = new DBHelper(this);

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.colmn_row,
                mydb.getData(),
                new String[]{"id", "name","phone","email"},
                null);


        for(int i = 0 ; i < adapter.getCount();i++) {
            DBmanagementExternal.doInBackground("DBmanagementExternal",mydb.getAllContactsIDs().get(i),mydb.getAllContactsNames().get(i),mydb.getAllContactsPhones().get(i),mydb.getAllContactsEmails().get(i));

        }

    }

}
