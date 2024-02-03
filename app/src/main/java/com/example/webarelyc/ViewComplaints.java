package com.example.webarelyc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewComplaints extends AppCompatActivity {

    DataClass mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_complaints);

        mydb = new DataClass(this);
        Cursor res = mydb.getComplaints();
        ListView L = (ListView)findViewById(R.id.list);
        int n =0;
        if(res != null)
            n = res.getCount();
        String arr[] = new String[n];



        int i  =0;

        if(res!=null)
        while (res.moveToNext()) {
            arr[i] = res.getString(0);
            i++;
        }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, arr);
            L.setAdapter(adapter);


        L.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mydb.deleteComplaint(arr[position]);
                Intent i = new Intent(getApplicationContext(),ViewComplaints.class);
                startActivity(i);
                return false;
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