package com.example.webarelyc;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;



public class order extends AppCompatActivity {

    DataClass mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        final String[] order = {""};
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        final AmazonS3 s3 = new AmazonS3Client(new BasicAWSCredentials(getString(R.string.acsesskey), getString(R.string.seckey)));
        TransferUtility tu = new TransferUtility(s3,getApplicationContext());

        final int[] pr = {0};

        AutoCompleteTextView search = (AutoCompleteTextView) findViewById(R.id.search);
        EditText q = (EditText) findViewById(R.id.quantity);
        Button badd = (Button) findViewById(R.id.add);
        Button ord = (Button) findViewById(R.id.order);
        ListView orders = (ListView)findViewById(R.id.orders);
        TextView tot = (TextView)findViewById(R.id.Price);
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
                orders_array[noOrders[0]] = search.getText().toString() + "          " + q.getText().toString() + "Nos        Amt:"  + Integer.toString(mydb.getPrice(iid)*Integer.parseInt(q.getText().toString()));
                noOrders[0]++;
                pr[0] += mydb.getPrice(iid)*Integer.parseInt(q.getText().toString());
                String prev_orders[] = new String[noOrders[0]];
//
                for(int i=0;i<noOrders[0];i++)
                {
                    prev_orders[i] = orders_array[i];
                }

                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_activated_1,prev_orders);
                orders.setAdapter(adapter1);

                tot.setText("Total Price:"+Integer.toString(pr[0]));
            }
        });

        ord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(order.this, "Ordered", Toast.LENGTH_SHORT).show();
                mydb.addOrder(0,order[0]);
            }
        });

        orders.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(order.this, Integer.toString(position), Toast.LENGTH_SHORT).show();

                return false;
            }
        });






    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mydb.dbclose();
    }
}