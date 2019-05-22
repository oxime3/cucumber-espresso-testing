package com.bignerdranch.android.bqtabs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.bignerdranch.android.bqtabs.database.BurgerQueenDBHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    SQLiteOpenHelper openHelper;

    private EditText username;
   private EditText confirmpassword;
   private EditText password;
   private EditText useremail;
   private Spinner locationSpinner;



    private Button submitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        openHelper = new BurgerQueenDBHelper(this);
        username = (EditText) findViewById(R.id.usernameId) ;
        useremail = (EditText) findViewById(R.id.useremail);
        password = (EditText) findViewById(R.id.userpassword);
        confirmpassword = (EditText) findViewById(R.id.userConfrimPassword);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = pref.edit();




        locationSpinner = (Spinner) findViewById(R.id.locationSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.burgerlocation, android.R.layout.simple_dropdown_item_1line);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(adapter);
        locationSpinner.setOnItemSelectedListener(this);

        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String validemail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" +
                        "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+";
                String email = useremail.getText().toString();
                Matcher matcher= Pattern.compile(validemail).matcher(email);

               String passwrd = password.getText().toString();
               String cnfrmpasswrd = confirmpassword.getText().toString();

                if (!matcher.matches()){
                    useremail.setError("Invalid Username");
                    Animation shake = AnimationUtils.loadAnimation(SignUp.this,R.anim.shake);
                    useremail.startAnimation(shake);
//                    Toast.makeText(getApplicationContext(),"Enter Valid Email-Id",Toast.LENGTH_LONG).show();
                } else if (!passwrd.equals(cnfrmpasswrd)) {
                    password.setError("Password doesn't match");
                    confirmpassword.setError("Password doesn't match");
                    Animation shake = AnimationUtils.loadAnimation(SignUp.this,R.anim.shake);
                    password.startAnimation(shake);
                    Animation shakecnfm = AnimationUtils.loadAnimation(SignUp.this,R.anim.shake);
                    confirmpassword.startAnimation(shakecnfm);
                }
//                    Toast.makeText(getApplicationContext(),"Passwords doesn't match",Toast.LENGTH_LONG).show();
//                }
                else {

                    String uname = username.getText().toString();
                    String uemail = useremail.getText().toString();
                    String upass = password.getText().toString();
                    String location = locationSpinner.getSelectedItem().toString();
                    int index = locationSpinner.getSelectedItemPosition();
                    editor.putInt("Index", index);
                    editor.apply();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String startdate = dateFormat.format(new Date());


                    UserBase userbase = UserBase.get(getBaseContext());
                    userbase.registerNewUser(uname,upass,uemail,location,startdate);

                    Intent intent = new Intent(SignUp.this,Login.class);
                    startActivity(intent);


                }

//                startActivity(new Intent(SignUp.this, Login.class));
            }

        });
    }
//
//    private void insertData(String uname, String uemail, String upass, String location, String startdate) {
//        ContentValues values = new ContentValues();
//        values.put(BurgerQueenDBHelper.DATABASE_NAME.UserTable.Cols.USERNAME);}


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
