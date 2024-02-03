package com.example.webarelyc;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;




public class order extends AppCompatActivity {

    DataClass mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        final String[] order = {""};

        AutoCompleteTextView search = (AutoCompleteTextView) findViewById(R.id.search);
        EditText q = (EditText) findViewById(R.id.quantity);
        Button badd = (Button) findViewById(R.id.add);
        Button ord = (Button) findViewById(R.id.order);
        ListView orders = (ListView)findViewById(R.id.orders);
        mydb = new DataClass(this);

        Cursor items = mydb.getItems();
        String itemNames[] = new String[items.getCount()];
        String orders_array[] = new String[100];
        final int[] noOrders = {0};
        int i=0;
        while(items.moveToNext())
        {
            itemNames[i] = items.getString(0);
            i++;
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,itemNames);
        search.setAdapter(adapter);


        badd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int iid = mydb.getItemID(search.getText().toString());
                order[0] = order[0] + Integer.toString(iid)+ ":"+ q.getText() + "\n";
                Toast.makeText(order.this, order[0], Toast.LENGTH_SHORT).show();
                orders_array[noOrders[0]] = search.getText().toString() + "\t\t\t\t\t\t\t\t\t\t\t" + q.getText().toString();
                noOrders[0]++;
                String prev_orders[] = new String[noOrders[0]];

                for(int i=0;i<noOrders[0];i++)
                {
                    prev_orders[i] = orders_array[i];
                }
                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_activated_1,prev_orders);
                orders.setAdapter(adapter1);
            }
        });

        ord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(order.this, "Ordered", Toast.LENGTH_SHORT).show();
            }
        });






    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mydb.dbclose();
    }
}