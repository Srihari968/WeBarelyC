package com.example.webarelyc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class studentOrderMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_order_menu);
        Intent i = getIntent();
        Bundle ext = i.getExtras();
        String uname= "";
        if(i!=null)
        {
            uname = ext.getString("uname");
        }

        // Find the Button for Mess Time Table by its ID
        Button messTimeTableButton = findViewById(R.id.messTimeTableButton);

        // Set OnClickListener to handle button click event
        messTimeTableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This is an empty function for the Mess Time Table button click event
                // You can add your desired functionality here
                // For example, opening a new activity to display the mess time table
                // Replace the following line with your desired functionality
                Toast.makeText(studentOrderMenu.this, "Mess Time Table button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Find the Button for Order by its ID
        Button orderButton = findViewById(R.id.orderButton);
        // Set OnClickListener to handle button click event
        String finalUname = uname;
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This is an empty function for the Order button click event
                // You can add your desired functionality here
                // For example, showing a Toast message
                Intent i = new Intent(getApplicationContext(), order.class);
                i.putExtra("uname", finalUname);
                startActivity(i);
                Toast.makeText(studentOrderMenu.this, "Order button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Find the Button for Complaints by its ID
        Button complaintsButton = findViewById(R.id.complaintsButton);
        Button myords = (Button)findViewById(R.id.myorders);
        // Set OnClickListener to handle button click event
        complaintsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This is an empty function for the Complaints button click event
                // You can add your desired functionality here
                // For example, opening a new activity to handle complaints
                // Replace the following line with your desired functionality
                //Toast.makeText(studentOrderMenu.this, "Complaints button clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),NewComplaint.class);
                startActivity(i);
            }
        });

        myords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),viewMyOrders.class);
                i.putExtra("uname",finalUname);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(),Student_login.class);
        startActivity(i);
    }
}
