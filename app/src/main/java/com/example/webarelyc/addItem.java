package com.example.webarelyc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addItem extends AppCompatActivity {
    DataClass mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        EditText name = (EditText) findViewById(R.id.name);
        EditText price = (EditText) findViewById(R.id.price);
        EditText avail = (EditText) findViewById(R.id.avail);
        Button sub = (Button) findViewById(R.id.submit);
        mydb = new DataClass(this);

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().length() > 0 && price.getText().toString().length() > 0 && avail.getText().toString().length() > 0 )
                {
                    mydb.addItem(name.getText().toString(),Integer.parseInt(price.getText().toString()),Integer.parseInt(avail.getText().toString()));
                    Toast.makeText(addItem.this, "Added Item", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(addItem.this, "Try Again", Toast.LENGTH_SHORT).show();
                }
                Intent i = new Intent(getApplicationContext(),adminUpdates.class);
                startActivity(i);

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