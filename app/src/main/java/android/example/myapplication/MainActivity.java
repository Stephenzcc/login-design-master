package android.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    private EditText etUserName, etEMail, etPassword, letEMail, letPassword;
    private Button bLogin, bRegister;
    private String strUserName;
    private String strEMail;
    private String strPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);
        //setContentView(R.layout.layout_login);
        //setContentView(R.layout.activity_main);
    }





    public void Sign_up(View view) {
        etUserName = findViewById(R.id.name);
        etEMail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        bRegister = findViewById(R.id.btnRegister);
        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        strUserName = etUserName.getText().toString();
        strEMail = etEMail.getText().toString();
        strPassword = etPassword.getText().toString();
        if(strUserName.equals(null)||strEMail.equals(null)||strPassword.equals(null)){
            Toast.makeText(this, "Text can't be null!", Toast.LENGTH_SHORT).show();
        }
        else{
            editor.putString("UserName", strUserName);
            editor.putString("EMail", strEMail);
            editor.putString("Password", strPassword);
            editor.commit();
            Toast.makeText(this, "Click the button below to login.", Toast.LENGTH_SHORT).show();
        }
    }
    public void Sign_in(View view) {
        letEMail = findViewById(R.id.lemail);
        letPassword = findViewById(R.id.lpassword);
        bLogin = findViewById(R.id.btnLogin);
        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        String ema = sharedPreferences.getString("EMail","null");
        String pas = sharedPreferences.getString("Password","null");

        if(letEMail.getText().toString().equals(ema)&&letPassword.getText().toString().equals(pas)){
            new Pages().execute(R.layout.activity_main);
        }
        else
            Toast.makeText(this, "EMail or Password error!", Toast.LENGTH_SHORT).show();
    }

    public void turnToLogin(View view) {
        new Pages().execute(R.layout.layout_login);
    }
    public void turnToRegister(View view) {
        new Pages().execute(R.layout.layout_register);
    }

    private class Pages extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected Integer doInBackground(Integer... ints) {
            return ints[0];
        }

        protected void onPostExecute(Integer integer) {
            setContentView(integer);
        }
    }
}


