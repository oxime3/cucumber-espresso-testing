package com.bignerdranch.android.bqtabs;

import android.database.Cursor;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.bqtabs.database.BurgerQueenDBHelper;

public class _test extends AppCompatActivity {
    TextView username;
    TextView pswrd;
    TextView email;
    TextView location;
    TextView userdate;

    BurgerQueenDBHelper db;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        username = findViewById(R.id.dbusername);
        pswrd = findViewById(R.id.dbuserpassword);
        userdate = findViewById(R.id.dbstartdate);
        location = findViewById(R.id.dblocation);

        String dbuname = user.getUsername().toString();
        String dbpswrd = user.getPassword().toString();
        String locatiion = user.getHome_restaurant().toString();
        String date = user.getDate_registered().toString();

        username.setText(dbuname);
        pswrd.setText(dbpswrd);
        userdate.setText(date);
        location.setText(locatiion);

        db = new BurgerQueenDBHelper(this);
        Cursor cursor = db.alluserdata();
        if (cursor.getCount()==0){
            Toast.makeText(getApplicationContext(),"no data found",Toast.LENGTH_LONG).show();
        }else {
            while (cursor.moveToNext()){
                   Toast.makeText(getApplicationContext(),"email:" + cursor.getString(2),Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),"username:" + cursor.getString(3),Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),"username:" + cursor.getString(4),Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),"username:" + cursor.getString(5),Toast.LENGTH_LONG).show();




            }
        }
    }

}
