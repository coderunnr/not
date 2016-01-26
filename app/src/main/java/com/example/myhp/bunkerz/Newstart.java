package com.example.myhp.bunkerz;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

/**
 * Created by my hp on 1/27/2016.
 */
public class Newstart extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newstart);

        SharedPreferences prefs = getSharedPreferences("myfirsttime", 0);
        final boolean be = prefs.getBoolean("myfirst", true);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (be) {

                        Class i2 = null;
                        Bundle b=new Bundle();
                        b.putBoolean("show",true);
                        try {
                            i2 = Class.forName("com.example.myhp.bunkerz.MainActivity");
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        Intent x = new Intent(Newstart.this, i2);
                        x.putExtras(b);
                        startActivity(x);


                    } else {
                        Class i = null;
                        try {
                            i = Class.forName("com.example.myhp.bunkerz.Mainpage");
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        Intent k = new Intent(Newstart.this, i);
                        startActivity(k);
                    }

                }
            }
        };
        timer.start();

    }
}
