package com.example.assign11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        String name, phoneNumber, email;
        Intent i = getIntent();
        int count = (int)i.getIntExtra("Integer",0);
        SharedPreferences settings = getSharedPreferences("com.example.assign11",0);
        name = settings.getString("name["+count+"]", "");
        phoneNumber = settings.getString("number["+count+"]", "");
        email = settings.getString("email["+count+"]", "");
        TextView lbl = (TextView) findViewById(R.id.textView);
        lbl.setText(name+"\n\n"+phoneNumber+"\n\n"+email);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}