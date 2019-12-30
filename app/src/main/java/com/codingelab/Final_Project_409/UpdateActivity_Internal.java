package com.codingelab.Final_Project_409;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity_Internal extends AppCompatActivity {
    private EditText Stud_Name, Stud_Phone, Stud_Email;
    private Button btn_update;
    DBHelper mydb;
    private Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata);

        Stud_Name =  findViewById(R.id.Text_user_Name);
        Stud_Phone =  findViewById(R.id.Text_user_Phone);
        Stud_Email =  findViewById(R.id.Text_user_Email);

        id = Integer.parseInt(getIntent().getExtras().getString("id"));
        Stud_Phone.setText(getIntent().getExtras().getString("Phone"));
        Stud_Email.setText(getIntent().getExtras().getString("email"));
        Stud_Name.setText((getIntent().getExtras().getString("name")));
        btn_update = findViewById(R.id.btn_update);
        Log.d("Tag", getIntent().getExtras().getString("id"));
        mydb = new DBHelper(this);
    }

    public void Update(View v)
    {
        mydb.updateContact(id,
                Stud_Name.getText().toString(),
                Stud_Phone.getText().toString(),
                Stud_Email.getText().toString(),
                null, null);
        finish();
    }
}
