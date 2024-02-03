package com.example.webarelyc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Staff_home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_home);

        Button order = findViewById(R.id.order);//
           order.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                         //   Toast.makeText(Staff_home.this, "order button clicked", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), viewOrders.class);
                        startActivity(i);
                    }
            });
            Button update = findViewById(R.id.update);
            update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                          //  Toast.makeText(Staff_home.this, "update button clicked", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(),addItem.class);
                        startActivity(i);
                    }
            });
            Button comps = findViewById(R.id.comps);
            comps.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                            //Toast.makeText(Staff_home.this, "complaints button clicked", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(),ViewComplaints.class);
                        startActivity(i);
                    }
            });
    }
}

