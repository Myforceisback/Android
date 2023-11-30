package com.example.lw1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class registration extends AppCompatActivity {
    private String TAG = "Жизненный цикл";
    EditText loginText;
    EditText passwordText;
    SharedPreferences sharedPref;
    MyDatabase db = new MyDatabase(this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_layout);
        Button Enter = findViewById(R.id.Enterbutton);
        Button Registration = findViewById(R.id.Regbutton);
        Button ChangePass = findViewById(R.id.Changebutton);
        loginText = findViewById(R.id.editTextEmailAddress);
        passwordText = findViewById(R.id.editTextPassword);
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        Intent openIntent = new Intent(this, hello.class);

        Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String login = loginText.getText().toString();
                String pass = passwordText.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (db.checkUser(new User(login, pass))){
                            editor.putString("login", login);
                            editor.putString("password", pass);
                            editor.apply();
                            openIntent.putExtra("hello", "Hello from FirstActivity");
                            openIntent.putExtra("login", login);
                            openIntent.putExtra("pass", pass);
                            startActivity(openIntent);
                        }
                        else{
                            Log.v("Enter", "ERROR");
                        }
                    }
                }).start();
            }
        });

        Registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = loginText.getText().toString();
                String pass = passwordText.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (!db.checkUser(new User(login, pass))) {
                            db.addUser(new User(login, pass));
                            openIntent.putExtra("hello", "Hello from FirstActivity");
                            openIntent.putExtra("login", loginText.getText().toString());
                            openIntent.putExtra("pass", passwordText.getText().toString());
                            startActivity(openIntent);
                        }
                        else{
                            Log.v("Registration", "ERROR");
                        }
                    }
                }).start();
            }
        });
        ChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = loginText.getText().toString();
                String pass = passwordText.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if(db.refreshPass(new User(login, pass))){
                            Log.v("Change pass", "Success");
                        }
                        else{
                            Log.v("Change pass", "ERROR");
                        }
                    }
                }).start();
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        try {
            loginText.setText(sharedPref.getString("login", ""));
            passwordText.setText(sharedPref.getString("password", ""));
        }
        catch (RuntimeException ex){
            ex.fillInStackTrace();
        }
        Toast.makeText(getApplicationContext(), "onStart()", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onStart()");
    }
}
