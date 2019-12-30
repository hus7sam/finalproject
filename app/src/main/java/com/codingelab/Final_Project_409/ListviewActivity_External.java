package com.codingelab.Final_Project_409;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class ListviewActivity_External extends AppCompatActivity {

    ListView listview;
    DB_management_External synchronization;
    JSONArray jsonArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        synchronization = new DB_management_External();
        listview=(ListView)findViewById(R.id.listView);
        getJSON("http://192.168.43.220:80/sqli/getData.php");
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String id = ((TextView) view.findViewById(R.id.Column_id)).getText().toString();
                String msg = synchronization.doInBackground("delete", id);
                Toast.makeText( getBaseContext(),msg,Toast.LENGTH_SHORT);
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
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
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
    ArrayList<HashMap<String, String>> Items = new ArrayList<HashMap<String, String>>();

    private void loadIntoListView(String json) throws JSONException {
        jsonArray = new JSONArray(json);
        String[] users = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            JSONObject ob = jsonArray.getJSONObject(i);
            String user_id = users[i] = ob.getString("id"); map.put("id",user_id);
            String user_name = users[i] = ob.getString("name"); map.put("name",user_name);
            String user_phone = users[i] = ob.getString("phone"); map.put("phone",user_phone);
            String user_email = users[i] = ob.getString("email"); map.put("email",user_email);
            Items.add(map);
        }
        ListAdapter myadapter = new SimpleAdapter(this, Items,
                R.layout.listview_rows,new String[]
                { "id", "name", "phone", "email"},
                new int[] {
                                R.id.Column_id,
                                R.id.Column_Name,
                                R.id.Column_Phone,
                                R.id.Column_Email});
        listview.setAdapter(myadapter);


    }
}
