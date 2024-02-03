package com.example.webarelyc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class viewMyOrders extends AppCompatActivity {
    DataClass mydb;
    String uname = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_orders);
        Bundle ext = getIntent().getExtras();
         uname = "";
        if (ext != null) {
            uname = ext.getString("uname");
        }
        mydb = new DataClass(this);

        ListView L = (ListView) findViewById(R.id.list);
        TextView total = (TextView) findViewById(R.id.tot);
        int tot = 0;

        Cursor cur = mydb.getUserOrders(mydb.getUserId(uname));
        if (cur != null) {

            String orders[] = new String[cur.getCount()-1];
            //Toast.makeText(this, Integer.toString(cur.getCount()), Toast.LENGTH_SHORT).show();
            int i = 0;
            int orderIds[] = new int[cur.getCount()];
            while (cur.moveToNext()) {
                tot += cur.getInt(4);
                orders[i] = mydb.getUName(cur.getInt(3)) + "\n";
                orderIds[i] = cur.getInt(0);
                Toast.makeText(this, orders[i], Toast.LENGTH_SHORT).show();
                String curr_order = cur.getString(1);
                while (curr_order.length() > 0) {
                    int j = curr_order.indexOf(':');
                    orders[i] = orders[i] + mydb.getItemName((Integer.parseInt(curr_order.substring(0, j))));
                    int k = curr_order.indexOf('\n');
                    orders[i] = orders[i] + curr_order.substring(j, k) + "Nos\n";
                    if (k + 1 > curr_order.length() - 1)
                        break;
                    curr_order = curr_order.substring(k + 1);
                }
                if(cur.getInt(2) == 1)
                {
                    orders[i] = orders[i] + "Status:Completed"+ "\n\n";
                }
                else orders[i] = orders[i] + "Status:Pending"+"\n\n";
                i++;
            }
            if (i >= 1) {

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item_view, orders);
                 L.setAdapter(adapter);
                total.setText("Total Spent:" + Integer.toString(tot));
            }

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(),studentOrderMenu.class);
        i.putExtra("uname",uname);
        startActivity(i);
    }
}