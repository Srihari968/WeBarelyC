package com.example.webarelyc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class viewOrders extends AppCompatActivity {

    DataClass mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);
        mydb = new DataClass(this);

        Cursor cur = mydb.getAllCurrentOrders();

        ListView L = (ListView)findViewById(R.id.showorders) ;

        String orders[] = new String[cur.getCount()];
        //Toast.makeText(this, Integer.toString(cur.getCount()), Toast.LENGTH_SHORT).show();
        int i=0;
        int orderIds[] = new int[cur.getCount()];
        while(cur.moveToNext())
        {
            orders[i] =mydb.getUName(cur.getInt(3)) + "\n";
            orderIds[i] = cur.getInt(0);
            Toast.makeText(this, orders[i], Toast.LENGTH_SHORT).show();
            String curr_order = cur.getString(1);
            while(curr_order.length() > 0)
            {
                int j = curr_order.indexOf(':');
                orders[i] = orders[i] + mydb.getItemName((Integer.parseInt(curr_order.substring(0,j))));
                int k = curr_order.indexOf('\n');
                orders[i] = orders[i] +  curr_order.substring(j,k) + "Amt\n";
                if(k+1 > curr_order.length() -1)
                    break;
                curr_order = curr_order.substring(k+1);
            }
            i++;
        }
        if(i>=1) {

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item_view, orders);
            L.setAdapter(adapter);
        }
        L.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mydb.setFinished(orderIds[position]);
                Intent i = new Intent(getApplicationContext(), viewOrders.class);
                startActivity(i);
                //onCreate(savedInstanceState);

                return false;
            }
        });


    }

    @Override
    protected void onDestroy() {
        mydb.dbclose();
        super.onDestroy();
    }
}