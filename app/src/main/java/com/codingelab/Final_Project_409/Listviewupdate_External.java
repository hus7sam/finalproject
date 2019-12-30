package com.codingelab.Final_Project_409;

import android.content.Intent;
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

public class Listviewupdate_External extends AppCompatActivity {

    ListView listview;
    DB_management_External synchronization;
    JSONArray jsonArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listviewupdate);

        synchronization = new DB_management_External();
        listview=(ListView)findViewById(R.id.listViewUpdate);
        getJSON("http://192.168.43.220:80/sqli/getData.php");
        final Intent intent = new Intent(this, update_External_DB.class);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String id = ((TextView) view.findViewById(R.id.Column_id)).getText().toString();
                String name = ((TextView) view.findViewById(R.id.Column_Name)).getText().toString();
                String phone = ((TextView) view.findViewById(R.id.Column_Phone)).getText().toString();
                String mail = ((TextView) view.findViewById(R.id.Column_Email)).getText().toString();
                intent.putExtra("id", id);
                intent.putExtra("name", name);
                intent.putExtra("phone", phone);
                intent.putExtra("email", mail);
                startActivity(intent);
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
            protected void onPostExecute(String alsaedi) {
                super.onPostExecute(alsaedi);
                Toast.makeText(getApplicationContext(), alsaedi, Toast.LENGTH_SHORT).show();
                try {
                    loadIntoListView(alsaedi);
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
        String[] students = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            HashMap<String, String> HM = new HashMap<String, String>();
            JSONObject ob = jsonArray.getJSONObject(i);
            String Stud_id = students[i] = ob.getString("id"); HM.put("id",Stud_id);
            String Stud_Name = students[i] = ob.getString("name");  HM.put("name",Stud_Name);
            String Stud_Phone = students[i] = ob.getString("phone"); HM.put("phone",Stud_Phone);
            String Stud_Email = students[i] = ob.getString("email");  HM.put("email",Stud_Email);
            Items.add(HM);
        }
        ListAdapter myadapter = new SimpleAdapter(this, Items,
                R.layout.listview_rows,new String[]
                {
                        "id",
                        "name",
                        "phone",
                        "email"
                },
                new int[]
                        {
                        R.id.Column_id,
                        R.id.Column_Name,
                        R.id.Column_Phone,
                        R.id.Column_Email
                        });
        listview.setAdapter(myadapter);
    }
}
