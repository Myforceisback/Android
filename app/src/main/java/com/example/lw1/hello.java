package com.example.lw1;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class hello extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helloact);
        Button ClickMe = findViewById(R.id.buttonClickMe);
        Button ClickMe1 = findViewById(R.id.buttonClickMe1);
        TextView textView = findViewById(R.id.TextViewHello);

        ClickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickMe.setText("CLICKED!!!");
                String tmps = (String)textView.getText();
                if (tmps.equals("Hello!")) {
                    textView.setText("1");
                } else{
                   int tmp = Integer.valueOf((String)textView.getText());
                   tmp++;
                   textView.setText(String.valueOf(tmp));
                }
            }
        });
        ClickMe1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickMe1.setText("CLICKED!!!");
                String tmps = (String)textView.getText();
                if (tmps.equals("Hello!")) {
                    textView.setText("1");
                } else{
                    int tmp = Integer.valueOf((String)textView.getText());
                    tmp++;
                    textView.setText(String.valueOf(tmp));
                }
            }
        });
    }
}
