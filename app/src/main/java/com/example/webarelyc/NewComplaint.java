package com.example.webarelyc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NewComplaint extends AppCompatActivity {
    DataClass mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_complaint);
        TextView t = (TextView) findViewById(R.id.comp);
        Button sub = (Button)findViewById(R.id.submit);

        mydb = new DataClass(this);

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t.getText().toString().length() > 0) {
                    mydb.addComplaint(t.getText().toString());
                    Toast.makeText(NewComplaint.this, "Complaint added", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mydb.dbclose();
    }
}