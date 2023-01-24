package com.example.assign91;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        CreateMenu(menu);
        return true;
    }

    private void CreateMenu(Menu menu) {
        MenuItem mnu1 = menu.add(0,0,0,"Item 1");
        {
            mnu1.setIcon(R.mipmap.ic_launcher);
            mnu1.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
        MenuItem mnu2 = menu.add(0,1,1,"Item 2");
        {
            mnu2.setIcon(R.mipmap.ic_launcher);
            mnu2.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
        MenuItem mnu3 = menu.add(0,2,2,"Item 3");
        {
            mnu3.setIcon(R.mipmap.ic_launcher);
            mnu3.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
        MenuItem mnu4 = menu.add(0,3,3,"Item 4");
        {
            mnu4.setIcon(R.mipmap.ic_launcher);
            mnu4.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
        MenuItem mnu5 = menu.add(0,4,4,"Item 5");
        {
            mnu5.setIcon(R.mipmap.ic_launcher);
            mnu5.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
    }

    private boolean MenuChoice(MenuItem item) {
        TextView t1,t2,t3,t4;
        switch (item.getItemId()) {
            case 0:
                t1 = (TextView) findViewById(R.id.text1);
                t1.setText("You Clicked on Item 1");
                return true;
            case 1:
                t2 = (TextView) findViewById(R.id.text2);
                t2.setText("You Clicked on Item 2");
                return true;
            case 2:
                t3 = (TextView) findViewById(R.id.text3);
                t3.setText("You Clicked on Item 3");
                return true;
            case 3:
                t4 = (TextView) findViewById(R.id.text4);
                t4.setText("You Clicked on Item 4");
                return true;
            case 4:
                t1 = (TextView) findViewById(R.id.text1);
                t2 = (TextView) findViewById(R.id.text2);
                t3 = (TextView) findViewById(R.id.text3);
                t4 = (TextView) findViewById(R.id.text4);
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                return true;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        //return super.onOptionsItemSelected(item);
        return MenuChoice(item);
    }
}