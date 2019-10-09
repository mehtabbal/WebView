package com.example.wview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton showAlert = findViewById(R.id.showAlert);
        final View alertView = getLayoutInflater().inflate(R.layout.alert, null);
        final TextView enterCards = (TextView) alertView.findViewById(R.id.enterCards);
        final Button drawCards = (Button) alertView.findViewById(R.id.drawCards);

        final WebView webV = (WebView) findViewById(R.id.webV);
        webV.getSettings().setJavaScriptEnabled(true);
        webV.setWebViewClient(new WebViewClient());




        AlertDialog.Builder alertBox = new AlertDialog.Builder(MainActivity.this);
        alertBox.setView(alertView);
        final AlertDialog showBox = alertBox.create();


        showAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showBox.show();
                drawCards.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                            showBox.dismiss();


                        String cards = enterCards.getEditableText().toString();
                        int number = Integer.parseInt(cards);
                        webV.loadUrl("file:///android_asset/UI.html");
                        webV.setWebViewClient(new WebViewClient() {
                            @Override
                            public void onPageFinished(WebView webV, String url){
                                webV.loadUrl("javascript:addNode("+cards+")");
                            }

                        });
                    };
                });
            }
        });



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {

            case R.id.reset:
                WebView webV = (WebView) findViewById(R.id.webV);
                webV.clearView();
        }






        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.reset) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}



