package com.example.webarelyc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class EditItems extends AppCompatActivity {
    DataClass mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_items);
        mydb = new DataClass(this);
        Cursor cur = mydb.getAllItems();
        AutoCompleteTextView L = (AutoCompleteTextView) findViewById(R.id.search);
        CheckBox C = (CheckBox) findViewById(R.id.C);

        String arr[] = new String[cur.getCount()];

        int i=0;
        while(cur.moveToNext())
        {
            arr[i] = cur.getString(0);
            i++;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.dropdown,arr);
        L.setAdapter(adapter);

        Button b = (Button) findViewById(R.id.save);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(C.isChecked())
                {
                    mydb.setAvailibility(L.getText().toString(),1);
                }
                else
                    mydb.setAvailibility(L.getText().toString(),0);

                Toast.makeText(EditItems.this, "Saved", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),Staff_home.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(),Staff_home.class);
        startActivity(i);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mydb.dbclose();
    }
}