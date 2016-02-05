package com.example.myhp.bunkerz;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Calendar;

/**
 * Created by my hp on 1/24/2016.
 */
public class Datewise extends AppCompatActivity implements CalendarView.OnDateChangeListener {
    CalendarView cv;
    boolean done[];
    Calendar cal;
    TextView tv;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datewiseattendance);
        cv=(CalendarView)findViewById(R.id.calendarView);
        tv=(TextView)findViewById(R.id.lastdate);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        cv.setOnDateChangeListener(this);
        sp=getSharedPreferences("calendar",0);
        int days=sp.getInt("days",0);
        done=new boolean[32];
        for(int i=0;i<32;i++){
            done[i]=true;
        }
        for(int i=0;i<=days;i++){
            done[i]=false;
        }
        tv.setText("Attendance is added till "+days);
        cal=Calendar.getInstance();
       // int daysInMonth = cal.getActualMaximum(cal.DAY_OF_MONTH);
        //cv.setMaxDate(daysInMonth);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
        if((cal.get(Calendar.DAY_OF_MONTH)>=dayOfMonth)&&(cal.get(Calendar.MONTH)>=month)&&(cal.get(Calendar.YEAR)>=year)){
            if(done[dayOfMonth]){
                done[dayOfMonth]=false;
                Calendar c = Calendar.getInstance();
                c.set(year,month,dayOfMonth);
                int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

                Bundle basket=new Bundle();
                basket.putInt("dayofweek",dayOfWeek);
                Class g=null;
                try {
                    g=Class.forName("com.example.myhp.bunkerz.Adddatewise");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Intent in=new Intent(Datewise.this,g);
                in.putExtras(basket);
                startActivity(in);


            }
            else{
                Toast to=Toast.makeText(this,"Attendance already added",Toast.LENGTH_LONG);
                to.show();
            }
        }
        else{
            Toast t=Toast.makeText(this,"Future dates cannot be selected",Toast.LENGTH_LONG);
            t.show();
        }
    }
}
