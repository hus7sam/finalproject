package com.codingelab.Final_Project_409;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Control_Panel_External extends AppCompatActivity {
    private Button add, UPdate, showinfo, show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_php);


        this.add=(Button)findViewById(R.id.btnexternalPHP);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MoveTo_phpdbActivity = new Intent(Control_Panel_External.this, Insert_external_Activity.class);
                startActivity(MoveTo_phpdbActivity);
            }
        });
        
        
        this.showinfo=(Button)findViewById(R.id.btn_Show);
        showinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MoveTo_ListviewActivity = new Intent(Control_Panel_External.this, ListviewActivity_External.class);
                startActivity(MoveTo_ListviewActivity);
            }
        });


        this.UPdate = (Button) findViewById(R.id.btn_update);
        UPdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MoveTo_Listviewupdate = new Intent(Control_Panel_External.this, Listviewupdate_External.class);
                startActivity(MoveTo_Listviewupdate);
            }
        });

        this.show = (Button) findViewById(R.id.btn_Search);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Control_Panel_External.this, Sreach_External.class);
                startActivity(i);
            }
        });
    }


}
