package com.example.webarelyc;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class order extends AppCompatActivity {

    DataClass mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        String order = "";

        AutoCompleteTextView search = (AutoCompleteTextView) findViewById(R.id.search);
        EditText q = (EditText) findViewById(R.id.quantity);
        Button badd = (Button) findViewById(R.id.add);
        Button ord = (Button) findViewById(R.id.order);
        mydb = new DataClass(this);

        Cursor items = mydb.getItems();
        String itemNames[] = new String[items.getCount()];
        int i=0;
        while(items.moveToNext())
        {
            itemNames[i] = items.getString(0);
            i++;
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_order,R.id.search,itemNames);
        search.setAdapter(adapter);

        badd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });






    }
}