package com.redhead.y14.routes;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    /* renamed from: tv */
    private TextView f56tv;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0546R.layout.activity_splash);
        this.f56tv = (TextView) findViewById(C0546R.C0548id.f52tv);
        this.f56tv.startAnimation(AnimationUtils.loadAnimation(this, C0546R.anim.transition));
        final Intent i = new Intent(this, Home.class);
        new Thread() {
            public void run() {
                try {
                    sleep(3500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Throwable th) {
                    Splash.this.startActivity(i);
                    Splash.this.finish();
                    throw th;
                }
                Splash.this.startActivity(i);
                Splash.this.finish();
            }
        }.start();
    }
}
