package com.codingelab.Final_Project_409;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class update_External_DB extends AppCompatActivity {

    EditText name,phone,email;
    Button update;
    private DB_management_External DBmanagementExternal;
    String id = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbupdate);

        this.DBmanagementExternal =new DB_management_External();


        update=(Button)findViewById(R.id.bttnInsert);
        name=(EditText)findViewById(R.id.editTxtName);
        phone=(EditText)findViewById(R.id.editTxtPhone);
        email=(EditText)findViewById(R.id.editTxtEmail);

        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        phone.setText(intent.getStringExtra("phone"));
        email.setText(intent.getStringExtra("email"));
        id = intent.getStringExtra("id");
        final Intent updateIntent = new Intent(this, Listviewupdate_External.class);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg= DBmanagementExternal.doInBackground("update",name.getText().toString(),phone.getText().toString(),email.getText().toString(), id);
                Toast.makeText(getBaseContext(),msg, Toast.LENGTH_SHORT).show();
                startActivity(updateIntent);
            }
        });
    }
}
