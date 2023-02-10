package com.example.assign11;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int request_Code = 1;
    String[][] contact = new String[20][3];
    ArrayList<String> names = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences settings = getSharedPreferences("com.example.assign11",0);
        ListView lstView = (ListView)findViewById(R.id.listView);
        lstView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lstView.setTextFilterEnabled(true);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, names);
        lstView.setAdapter(adapter);

        int count2 = 0;
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("count",count2);
        editor.apply();

        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent doing = new Intent("com.example.assign11.ThirdActivity");
                doing.putExtra("Integer", position);
                startActivity(doing);
            }
        });

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
        startActivityForResult(new Intent("com.example.assign11.SecondActivity"),request_Code);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == requestCode) {
            SharedPreferences settings = getSharedPreferences("com.example.assign11",0);
            int count2 = settings.getInt("count",0);
            names.add("");
            for (int i = 0; i<count2; i++) {
                contact[i][0] = settings.getString("name[" + i + "]", "");
                contact[i][1] = settings.getString("number[" + i + "]", "");
                contact[i][2] = settings.getString("email[" + i + "]", "");
                names.set(i, settings.getString("name["+i+"]",""));
            }
            adapter.notifyDataSetChanged();
        }
    }
}