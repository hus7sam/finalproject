package com.codingelab.Final_Project_409;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Control_Panel_Internal extends AppCompatActivity {

    DBHelper mydb;

    Button btnShow, btn_showal, btn_Add, button_last, Search1;
    TextView textViewName,textViewPhone;
    EditText editTextName, editTextPhone, editTextEmail,  editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlitedb);

        ArrayAdapter<String> arrayAdapter;

        mydb = new DBHelper(this);

        editTextName = (EditText)findViewById(R.id.Edit_User_Name);
        editTextPhone = (EditText)findViewById(R.id.Edit_User_Phone);
        editTextEmail = (EditText)findViewById(R.id.Edit_User_Email);

        btn_Add = (Button) findViewById(R.id.btnAdd);
        btnShow = (Button) findViewById(R.id.btnShow1);
        btn_showal = (Button) findViewById(R.id.bttnShowAll);

        btn_Add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),
                        "bttnOnClick Pressed", Toast.LENGTH_SHORT).show();

                String getName = editTextName.getText().toString();
                String getPhone = editTextPhone.getText().toString();
                String getEmail = editTextEmail.getText().toString();

                if (mydb.insertContact(getName, getPhone, getEmail)) {
                    Log.v("georgeLog", "Successfully inserted record to db");
                    Toast.makeText(getApplicationContext(),
                            "Inserted:" + getName + ", " + getPhone + "," + getEmail, Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getApplicationContext(), "DID NOT insert to db :-(", Toast.LENGTH_SHORT).show();
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("georgeLog", "clicked on fetch");
                Cursor getData=mydb.getData(1);

                if (getData.moveToNext()) {
                    Log.v("georgeLog", "data found in DB...");
                    String dName = getData.getString(getData.getColumnIndex("name"));
                    String dPhone = getData.getString(getData.getColumnIndex("phone"));
                    String dEmail = getData.getString(getData.getColumnIndex("email"));
                    Toast.makeText(getApplicationContext(),
                            "rec: " + dName + ", " + dPhone + ", " + dEmail, Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getApplicationContext(),
                            "did not get any data...:-(", Toast.LENGTH_LONG).show();
                getData.close();
            }
        });

        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewPhone = (TextView) findViewById(R.id.textViewPhone);
        button_last = (Button) findViewById(R.id.button);
        button_last.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("georgeLog", "clicked on fetch");
                Cursor getData=mydb.getData2();

                if (getData.moveToNext()) {
                    Log.v("georgeLog", "data found in DB...");
                    String dName = getData.getString(getData.getColumnIndex("name"));
                    String dPhone = getData.getString(getData.getColumnIndex("phone"));
                    String dEmail = getData.getString(getData.getColumnIndex("email"));
                    Toast.makeText(getApplicationContext(),
                            "rec: " + dName + ", " + dPhone + ", " + dEmail, Toast.LENGTH_LONG).show();
                    textViewName.setText(dName);
                    textViewPhone.setText(dPhone);

                }
                else
                    Toast.makeText(getApplicationContext(),
                            "did not get any data...:-(", Toast.LENGTH_LONG).show();
                getData.close();
            }
        });

        btn_showal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Result.class);
                startActivity(intent);
            }
        });
    }

    public void search1(View view) {
        Cursor getData=mydb.getData3("name");

        editText = (EditText) findViewById(R.id.editText);
        Search1=(Button)findViewById(R.id.btnSearch);
        if (getData.moveToNext()) {
            Log.v("georgeLog", "data found in DB...");
            String dname = getData.getString(getData.getColumnIndex("name"));

        }else
            Toast.makeText(getApplicationContext(),
                    "did not get any data...:-(", Toast.LENGTH_LONG).show();
        getData.close();

        Search1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Result.class);
                startActivity(intent);
            }
        });
    }
}
