package com.example.webarelyc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Student_login extends AppCompatActivity {

    DataClass mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);



        Button submit = (Button)findViewById(R.id.submit);
        mydb = new DataClass(this);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText user = (EditText)findViewById(R.id.username);
                EditText pass = (EditText)findViewById(R.id.password);
                String u =user.getText().toString();
                String p=pass.getText().toString();

                if(mydb.uname_pwd_check(u,p) == 1) {

                    Intent intent = new Intent(getApplicationContext(), studentOrderMenu.class);
                    intent.putExtra("uname",u);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Student_login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
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