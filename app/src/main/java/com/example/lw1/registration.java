package com.example.lw1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class registration extends AppCompatActivity {
    private String TAG = "Жизненный цикл";
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_layout);
        Button Enter = findViewById(R.id.Enterbutton);
        Intent openIntent = new Intent(this, hello.class);
        Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                openIntent.putExtra("hello", "Hello from FirstActivity");
                startActivity(openIntent);
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        Toast.makeText(getApplicationContext(), "onStart()", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onStart()");
    }
}
