package com.example.webarelyc;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class studentOrderMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_order_menu);

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
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This is an empty function for the Order button click event
                // You can add your desired functionality here
                // For example, showing a Toast message
                Toast.makeText(studentOrderMenu.this, "Order button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Find the Button for Complaints by its ID
        Button complaintsButton = findViewById(R.id.complaintsButton);
        // Set OnClickListener to handle button click event
        complaintsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This is an empty function for the Complaints button click event
                // You can add your desired functionality here
                // For example, opening a new activity to handle complaints
                // Replace the following line with your desired functionality
                Toast.makeText(studentOrderMenu.this, "Complaints button clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
