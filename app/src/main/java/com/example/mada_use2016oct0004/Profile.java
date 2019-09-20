package com.example.mada_use2016oct0004;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {
    Cursor cursor;
    DatabaseHelper openHelper;
    SQLiteDatabase db;
    String D1,D2,D3,D4,D5;
    TextView E1,E2,E3,E4,E5;
    Button EB;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();
        E1=findViewById(R.id.textView);
        E2=findViewById(R.id.textView2);
        E3=findViewById(R.id.textView3);
        E4=findViewById(R.id.textView4);
        E5=findViewById(R.id.textView5);
        EB= findViewById(R.id.emailbutton);


        String userName = getIntent().getStringExtra("index");

    cursor = db.rawQuery(String.format(" SELECT " +DatabaseHelper.COL_1+ " , " +DatabaseHelper.COL_2+ " , " +DatabaseHelper.COL_3+ " , " +DatabaseHelper.COL_4+ " , " +DatabaseHelper.COL_5+ " FROM "+DatabaseHelper.TABLE_NAME+" WHERE "+DatabaseHelper.COL_2+ " =?  "), new String[]{userName});
        while (cursor.moveToNext()) {
        D1 = cursor.getString(0);
       D2 = cursor.getString(1);
        D3 = cursor.getString(2);
        D4 = cursor.getString(3);
        D5 = cursor.getString(4);

           TextView tv1 = (E1) ;
          tv1.setText("Name  :"+D1);
            TextView tv2 = (E2) ;
            tv2.setText("Index no  :"+D2);
            TextView tv3 = (E3) ;
            tv3.setText("Mail  :"+D3);
            TextView tv4 = (E4) ;
            tv4.setText("Mobile Number  :"+D4);
            TextView tv5 = (E5) ;
            tv5.setText("GPA  :"+D5);


    //Toast.makeText(this, D1 + D2 + D3 + D4 + D5, Toast.LENGTH_LONG).show();


    EB.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {


            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"Recipient"});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Student Details");
            emailIntent.putExtra(Intent.EXTRA_TEXT   , "Name  :"+D1+"\n Index no  :"+D2+"\n Mail  :"+D3+"\n Mobile Number  :"+D4+"\n GPA  :"+D5);

            startActivity((Intent.createChooser(emailIntent,"Send Mail")));
            finish();
            Log.i("Finished sending Email.","");
        }




    });

}

    }
}