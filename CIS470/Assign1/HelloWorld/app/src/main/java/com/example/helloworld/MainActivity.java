package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;
import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {
    int request_Code = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences settings = getSharedPreferences("com.example.helloworld",0);
        int count2 = 0;
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("count",count2);
        editor.apply();
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
        startActivityForResult(new Intent("com.example.helloworld.SecondActivity"),request_Code);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == requestCode) {
                SharedPreferences settings = getSharedPreferences("com.example.helloworld",0);
                int currentCount = settings.getInt("count",0);
                makeText(this, valueOf(currentCount), LENGTH_SHORT).show();
        }
    }
}