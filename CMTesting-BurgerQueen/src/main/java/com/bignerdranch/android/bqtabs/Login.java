package com.bignerdranch.android.bqtabs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.bqtabs.database.BurgerQueenDBHelper;

public class Login extends AppCompatActivity{
    private Button lgnButton;



    private Button submitButton;
    TextView loginText;
    TextView loginTitle;
    Typeface myfont;
    Typeface secondfont;

    EditText loginusername;
    EditText loginpassword;

    BurgerQueenDBHelper BurgerQueenDB;
    User user_session_obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences( this );
        final SharedPreferences.Editor edit =pref.edit();


//        loginTitle = (TextView) findViewById(R.id.logintext) ;
        loginText = (TextView) findViewById(R.id.textlogin);
        loginusername = findViewById(R.id.loginusernme);
        loginpassword = findViewById(R.id.loginpswrd);


        myfont = Typeface.createFromAsset(this.getAssets(),"fonts/Fadli Script.otf");
        secondfont = Typeface.createFromAsset(this.getAssets(),"fonts/KaushanScript-Regular.otf");
        loginText.setTypeface(myfont);
//        loginTitle.setTypeface(secondfont);

        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserBase userbase = UserBase.get(getBaseContext());

                String entered_name = loginusername.getText().toString();
                String password = loginpassword.getText().toString();
                user_session_obj = userbase.tryLogin(entered_name, password);
                int user_id = user_session_obj.getId();
                edit.putInt( "ID", user_id );
                edit.apply();
                String useri = String.valueOf(user_id);

                if(user_session_obj.getUsername() == null){
                    Log.i("Valid", "false");
                    Toast.makeText(getBaseContext(),"Username/Password Incorrect",Toast.LENGTH_LONG).show();
                    loginusername.setError("Username/Password Incorrect");
                    Animation shake = AnimationUtils.loadAnimation(Login.this,R.anim.shake);
                    loginusername.startAnimation(shake);
                    loginpassword.setError("Username/Password Incorrect");
                    Animation shakepswd = AnimationUtils.loadAnimation(Login.this,R.anim.shake);
                    loginpassword.startAnimation(shakepswd);
                }
                else{
                    Log.i("Valid", "true");
                    Log.i("User ID", useri);
                    Log.i("UserName: ", user_session_obj.getUsername());
                   Intent intent = new Intent(getBaseContext(),navigation.class);
                   intent.putExtra("homefrag",true);
                   startActivity(intent);
                }

            }
        });
    }

    public void signinText(View view) {
        startActivity(new Intent(Login.this, SignUp.class));
    }
}
