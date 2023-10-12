package com.example.lw1;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class hello extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helloact);
        EditText mEditText = findViewById(R.id.TextEdit);
        Button AddButton = findViewById(R.id.AddButton);
        Button DelButton = findViewById(R.id.DeleteButton);
        ListView listView = findViewById(R.id.ViewList);
        ArrayList<String> mArrList = new ArrayList<String>();
        ArrayList<String> RemList = new ArrayList<String>();
        ArrayAdapter<String> mTextAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mArrList);
        listView.setAdapter(mTextAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String tmp = mTextAdapter.getItem(i);
                if (listView.isItemChecked(i)){
                    view.setBackgroundColor(Color.YELLOW);
                    RemList.add(tmp);
                }
                else {
                    view.setBackgroundColor(0xFFFFFFF);
                    RemList.remove(tmp);
                }
            }
        });
        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tmp = String.valueOf(mEditText.getText());
                mTextAdapter.add(tmp);
                mEditText.setText("");
                mTextAdapter.notifyDataSetChanged();
            }
        });
        DelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < RemList.size(); i++){
                    mTextAdapter.remove(RemList.get(i));
                }
                RemList.clear();
                mTextAdapter.notifyDataSetChanged();
            }
        });
    }
}
