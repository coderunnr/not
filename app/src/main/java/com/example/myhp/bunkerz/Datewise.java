package com.example.myhp.bunkerz;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by my hp on 1/24/2016.
 */
public class Datewise extends Activity implements CalendarView.OnDateChangeListener {
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
        cv.setOnDateChangeListener(this);
        sp=getSharedPreferences("calendar",0);
        int days=sp.getInt("days",0);
        done=new boolean[31];
        for(int i=0;i<=days;i++){
            done[i]=false;
        }
        tv.setText("Attendance is added till "+days);
        cal=Calendar.getInstance();
    }

    @Override
    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
        if(cal.DAY_OF_MONTH>=dayOfMonth&&cal.MONTH>=month&&cal.YEAR>=year){
            if(done[dayOfMonth]){
                done[dayOfMonth]=false;
                Calendar c = Calendar.getInstance();
                c.set(year, month, dayOfMonth);
                int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

                Bundle basket=new Bundle();
                basket.putInt(dayOfWeek);
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
