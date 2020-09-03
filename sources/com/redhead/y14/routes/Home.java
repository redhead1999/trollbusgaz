package com.redhead.y14.routes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {
    Button button1;
    Button button2;
    Button button3;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0546R.layout.activity_home);
        this.button1 = (Button) findViewById(C0546R.C0548id.button1);
        this.button2 = (Button) findViewById(C0546R.C0548id.button2);
        this.button3 = (Button) findViewById(C0546R.C0548id.button3);
        this.button1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Home.this, MainActivityAuto.class);
                Bundle b = new Bundle();
                b.putString("classID", "2");
                intent.putExtras(b);
                Home.this.startActivity(intent);
            }
        });
        this.button2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Home.this, MainActivityTroll.class);
                Bundle b = new Bundle();
                b.putString("classID", "1");
                intent.putExtras(b);
                Home.this.startActivity(intent);
            }
        });
        this.button3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Home.this, MainActivityGazel.class);
                Bundle b = new Bundle();
                b.putString("classID", "3");
                intent.putExtras(b);
                Home.this.startActivity(intent);
            }
        });
    }
}
