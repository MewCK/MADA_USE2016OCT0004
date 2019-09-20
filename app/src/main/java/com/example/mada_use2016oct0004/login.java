package com.example.mada_use2016oct0004;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.constraint.Group;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class login extends AppCompatActivity {
    SQLiteDatabase db;
    DatabaseHelper openHelper;
    Button loginButton ,  signupButton;
    EditText indexno, Password;
    Cursor cursor;
    Spinner indexnofirstpart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();
        loginButton = findViewById(R.id.button3);
        signupButton = findViewById(R.id.button5);
        indexno = findViewById(R.id.logindexno);
        Password = findViewById(R.id.logpassword);
        indexnofirstpart=findViewById(R.id.spinner2);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String index = indexnofirstpart.getSelectedItem().toString() + indexno.getText().toString().substring(0, 3);
                try{
                    String pass = Password.getText().toString();
                    cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s =? AND %s=?", DatabaseHelper.TABLE_NAME, DatabaseHelper.COL_2, DatabaseHelper.COL_6),new String[]{index,pass} );
                    if( cursor!=null){
                        if (cursor.getCount()>0){
                            cursor.moveToFirst();
                            Toast.makeText(getApplicationContext(),"Login successfully",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(),Profile.class);
                            intent.putExtra("index",index);
                            startActivity(intent);

                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Login Error",Toast.LENGTH_LONG).show();
                        }
                    }
                }catch(Exception e){
                    Toast.makeText(login.this, "Null", Toast.LENGTH_SHORT).show();
                }


            }
        });

        signupButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });

    }
}
