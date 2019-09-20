package com.example.mada_use2016oct0004;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    EditText editName, editIndexno, editMobilenumber, editEmail, editCurrentgpa, editPassword, editReenterPassword;
    Button editButton , loginButton;
    Spinner indexnofirstpart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DatabaseHelper(this);

        editName = findViewById(R.id.name);
        editIndexno = findViewById(R.id.indexno);
        editEmail = findViewById(R.id.email);
        editMobilenumber = findViewById(R.id.mobilenumber);
        editCurrentgpa = findViewById(R.id.currentgpa);
        editPassword = findViewById(R.id.password);
        editReenterPassword = findViewById(R.id.reenterpassword);
        editButton = findViewById(R.id.button);
        loginButton = findViewById(R.id.button2);
        indexnofirstpart = findViewById(R.id.indexno2);
        AddData();


    }


    public void AddData() {
        editButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String s1 = editName.getText().toString();
                        String s2 = editIndexno.getText().toString();
                        String s3 = editEmail.getText().toString();
                        String s4 = editMobilenumber.getText().toString();
                        String s5 = editCurrentgpa.getText().toString();
                        String s6 = editPassword.getText().toString();
                        String s7 = editReenterPassword.getText().toString();
                        String full_index;

                        if (s1.equals("") || TextUtils.isEmpty(s1)) {
                            Toast.makeText(MainActivity.this, "Cannot make Name field empty", Toast.LENGTH_SHORT).show();
                        } else if (s2.equals("") || TextUtils.isEmpty(s2)) {
                            Toast.makeText(MainActivity.this, "Cannot make index field empty", Toast.LENGTH_SHORT).show();
                        } else if (s3.equals("") || TextUtils.isEmpty(s3)) {
                            Toast.makeText(MainActivity.this, "Cannot make E-mail field empty", Toast.LENGTH_SHORT).show();
                        } else if (!Patterns.EMAIL_ADDRESS.matcher(s3).matches()) {

                            Toast.makeText(MainActivity.this, "Invalid E-mail Address", Toast.LENGTH_LONG).show();
                        } else if (s4.equals("") || TextUtils.isEmpty(s4)) {
                            Toast.makeText(MainActivity.this, "Cannot make index field empty", Toast.LENGTH_SHORT).show();
                        } else if (!Patterns.PHONE.matcher(s4).matches()) {
                            Toast.makeText(MainActivity.this, "Invalid Mobile Number", Toast.LENGTH_LONG).show();
                        } else if (s5.equals("") || TextUtils.isEmpty(s5)) {
                            Toast.makeText(MainActivity.this, "Cannot make GPA field empty", Toast.LENGTH_SHORT).show();
                        } else if (s6.equals("") || TextUtils.isEmpty(s6)) {
                            Toast.makeText(MainActivity.this, "Cannot make Password field empty", Toast.LENGTH_SHORT).show();
                        } else if (s7.equals("") || TextUtils.isEmpty(s7)) {
                            Toast.makeText(MainActivity.this, "Cannot make Password field empty", Toast.LENGTH_SHORT).show();
                        } else if (!s6.equals(s7)) {
                            Toast.makeText(MainActivity.this, "Password is not matched", Toast.LENGTH_SHORT).show();
                        } else {
                            full_index = indexnofirstpart.getSelectedItem().toString() + s2.substring(0, 3);
                            boolean isInserted = mydb.insertdata(editName.getText().toString(), full_index, editEmail.getText().toString(), editMobilenumber.getText().toString(), editCurrentgpa.getText().toString(), editPassword.getText().toString());
                            if (isInserted = true)
                                Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(MainActivity.this, "Data Base Error", Toast.LENGTH_LONG).show();
                        }


                    }
                }
        );
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                Intent intent= new Intent(getApplicationContext(),login.class);
                startActivity(intent);
            }




        });

    }

}
