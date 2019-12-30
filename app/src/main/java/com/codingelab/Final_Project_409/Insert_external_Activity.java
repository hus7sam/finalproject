package com.codingelab.Final_Project_409;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Insert_external_Activity extends AppCompatActivity {

    EditText name,phone,email;
    Button insert;
    private DB_management_External DBmanagementExternal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phpdb);

        this.DBmanagementExternal =new DB_management_External();

        insert=(Button)findViewById(R.id.bttnInsert);
        name=(EditText)findViewById(R.id.editTxtName);
        phone=(EditText)findViewById(R.id.editTxtPhone);
        email=(EditText)findViewById(R.id.editTxtEmail);


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg= DBmanagementExternal.doInBackground("insert",name.getText().toString(),phone.getText().toString(),email.getText().toString());
                Toast.makeText(getBaseContext(),msg, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
