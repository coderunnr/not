package com.example.myhp.bunkerz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.SQLException;

/**
 * Created by my hp on 1/24/2016.
 */
public class Front extends Activity implements View.OnClickListener {
    Button addattendance,viewsub;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front);
        addattendance = (Button) findViewById(R.id.addattendance);
        viewsub=(Button)findViewById(R.id.opensubjectwise);
        tv = (TextView) findViewById(R.id.tvpercentage);
        viewsub.setOnClickListener(this);
        addattendance.setOnClickListener(this);
        try {
            viewattendance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void viewattendance() throws SQLException {
        Datahandle d = new Datahandle(this);
        d.open();
        int a=d.getattendance();
        int b=d.gettotalattendance();
        float f=(float)a/b;
        f=f*100;
        tv.setText(String.format("%.2f",f));
        d.close();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Datahandle d = new Datahandle(this);
        try {
            d.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int a=d.getattendance();
        int b=d.gettotalattendance();
        if(b!=0) {
            float af = (float) a;
            float bf = (float) b;
            float f = af / bf;
            f = f * 100.0f;
            tv.setText("Attendance Percentage" + f);
        }
        else {
            tv.setText("Start Attending Classes");
        }
        d.close();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addattendance:
            Class i = null;
            try {
                i = Class.forName("com.example.myhp.bunkerz.Datewise");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Intent b = new Intent(Front.this, i);
            startActivity(b);
                break;
            case R.id.opensubjectwise:
                Class i1 = null;
                try {
                    i1 = Class.forName("com.example.myhp.bunkerz.Viewsubjectwise");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Intent y = new Intent(Front.this, i1);
                startActivity(y);
                break;

       /* Class c = null;
        try {
            c = Class.forName("com.example.myhp.bunkerz.Sqlview");
            Intent in = new Intent(Front.this, c);
            startActivity(in);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        }
    }
}
