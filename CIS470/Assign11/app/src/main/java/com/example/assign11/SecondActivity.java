package com.example.assign11;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
    public void onStart(){
        super.onStart();
    }
    public void onRestart(){
        super.onRestart();
    }
    public void onResume(){
        super.onResume();
    }
    public void onPause(){
        super.onPause();
    }
    public void onStop(){
        super.onStop();
    }
    public void onDestroy(){
        super.onDestroy();
    }

    public void onClick(View view) {
        EditText txt_name = (EditText)findViewById(R.id.txtName);
        EditText txt_num = (EditText) findViewById(R.id.txtNum);
        EditText txt_email = (EditText) findViewById(R.id.txtEmail);
        SharedPreferences settings = getSharedPreferences("com.example.assign11",0);
        int count2 = settings.getInt("count",0);
        count2++;
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("count",count2);
        count2--;
        editor.putString("name["+count2+"]", txt_name.getText().toString());
        editor.putString("number["+count2+"]", txt_num.getText().toString());
        editor.putString("email["+count2+"]", txt_email.getText().toString());
        editor.apply();
        finish();
    }
}
