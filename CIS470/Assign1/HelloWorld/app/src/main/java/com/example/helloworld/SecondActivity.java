package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

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
        SharedPreferences settings = getSharedPreferences("com.example.helloworld",0);
        int count2 = settings.getInt("count",0);
        count2++;
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("count",count2);
        editor.apply();
        finish();
    }
}
