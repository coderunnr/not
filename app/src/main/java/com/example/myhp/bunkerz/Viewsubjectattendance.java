package com.example.myhp.bunkerz;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;


import java.sql.SQLException;

/**
 * Created by my hp on 1/26/2016.
 */
public class Viewsubjectattendance extends AppCompatActivity {
    TextView subname,present,absent,massbunk,status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewsubjectaatendance);
        subname=(TextView)findViewById(R.id.viewsubjectname);
        present=(TextView)findViewById(R.id.viewpresent);
        absent=(TextView)findViewById(R.id.viewabsent);
        massbunk=(TextView)findViewById(R.id.viewmassbunk);
        status=(TextView)findViewById(R.id.attendancestatus);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Bundle basket=getIntent().getExtras();
        Datahandle d=new Datahandle(this);
        String s=basket.getString("subname");
        String t="";
        String p="";
        String a="";
        String m="";
        String percent="";
        int ti,pi,ai,mi;
        try {
            d.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        t=d.gettotalattendance(s);
        p=d.getpresent(s);
        m=d.getmassbunk(s);
       ti=Integer.parseInt(t);
        pi=Integer.parseInt(p);
        mi=Integer.parseInt(m);
        SharedPreferences sf=getSharedPreferences("criteria",0);
        if(ti!=0){
            a=Integer.toString(ti - pi);
            if(sf.getBoolean("include",false)) {
                ti = ti + mi;
            }
        percent=String.format("%f",(float)pi/ti*100);
        }
        else {
            percent="0";
            a="0";
        }

        subname.setText(s);
        present.setText("Present: "+p);
        absent.setText("Absent"+a);
        massbunk.setText("Mass Bunk"+m);
        status.setText("Your Attendance In This Subject Is "+percent+" %");
        d.close();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
