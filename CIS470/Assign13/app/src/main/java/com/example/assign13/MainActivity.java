package com.example.assign13;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import android.view.ContextMenu;
import android.widget.Button;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {
    MenuItem[] mnu = new MenuItem[7];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int[] count = {0};
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnCreateContextMenuListener(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txt_URL = (EditText)findViewById(R.id.txtURL);
                    WebView wv = (WebView) findViewById(R.id.webview);
                    WebSettings webSettings = wv.getSettings();
                    webSettings.setBuiltInZoomControls(true);
                    wv.loadUrl(txt_URL.getText().toString());
            }
        });

        Button btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count[0] <7) {
                    EditText txt_URL = (EditText) findViewById(R.id.txtURL);
                    mnu[count[0]].setTitle(txt_URL.getText().toString());
                    count[0]++;
                }
            }
        });

        //WebView wv = (WebView) findViewById(R.id.webview);
        //WebSettings webSettings = wv.getSettings();
        //webSettings.setBuiltInZoomControls(true);
        //wv.loadUrl(
        //       "https://www.futurity.org/wp/wp-content/uploads/2019/02/viceroy-butterfly_1600.jpg");
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        createMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        createMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return menuChoice(item);
    }

    private void createMenu(Menu menu) {
        mnu[0] = menu.add(0,0,0,"Item 1");
        mnu[1] = menu.add(0,1,1,"Item 2");
        mnu[2] = menu.add(0,2,2,"Item 3");
        mnu[3] = menu.add(0,3,3,"Item 4");
        mnu[4] = menu.add(0,4,4,"Item 5");
        mnu[5] = menu.add(0,5,5,"Item 6");
        mnu[6] = menu.add(0,6,6,"Item 7");
    }

    private boolean menuChoice(MenuItem item) {
        WebView wv = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = wv.getSettings();
        webSettings.setBuiltInZoomControls(true);
        switch (item.getItemId()) {
            case 0:
                Toast.makeText(this, "You clicked on Item 1", Toast.LENGTH_SHORT).show();
                wv.loadUrl(mnu[0].getTitle().toString());
                return true;
            case 1:
                Toast.makeText(this, "You clicked on Item 2", Toast.LENGTH_SHORT).show();
                wv.loadUrl(mnu[1].getTitle().toString());
                return true;
            case 2:
                Toast.makeText(this, "You clicked on Item 3", Toast.LENGTH_SHORT).show();
                wv.loadUrl(mnu[2].getTitle().toString());
                return true;
            case 3:
                Toast.makeText(this, "You clicked on Item 4", Toast.LENGTH_SHORT).show();
                wv.loadUrl(mnu[3].getTitle().toString());
                return true;
            case 4:
                Toast.makeText(this, "You clicked on Item 5", Toast.LENGTH_SHORT).show();
                wv.loadUrl(mnu[4].getTitle().toString());
                return true;
            case 5:
                Toast.makeText(this, "You clicked on Item 6", Toast.LENGTH_SHORT).show();
                wv.loadUrl(mnu[5].getTitle().toString());
                return true;
            case 6:
                Toast.makeText(this, "You clicked on Item 7", Toast.LENGTH_SHORT).show();
                wv.loadUrl(mnu[6].getTitle().toString());
                return true;
        }
        return false;
    }
}