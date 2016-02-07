package com.example.myhp.bunkerz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.imanoweb.calendarview.CalendarListener;
import com.imanoweb.calendarview.CustomCalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by my hp on 2/7/2016.
 */
public class Datewisecustom extends AppCompatActivity implements CalendarListener{
CustomCalendarView calendarView;
    boolean done[];
    Calendar cal;
    TextView tv;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datewisecustom);


        //Initialize CustomCalendarView from layout
        calendarView = (CustomCalendarView) findViewById(R.id.calendar_view);

//Initialize calendar with date
        Calendar currentCalendar = Calendar.getInstance(Locale.getDefault());

//Show Monday as first date of week
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);

//Show/hide overflow days of a month
        calendarView.setShowOverflowDate(false);

//call refreshCalendar to update calendar the view
        calendarView.refreshCalendar(currentCalendar);

//Handling custom calendar events
        calendarView.setCalendarListener(this);


        tv=(TextView)findViewById(R.id.lastdate);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbarcustom);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        sp=getSharedPreferences("calendar",0);
        int days=sp.getInt("days",0);
        done=new boolean[32];
        for(int i=0;i<32;i++){
            done[i]=true;
        }
        for(int i=0;i<=days;i++){
            done[i]=false;
        }
      //  tv.setText("Attendance is added till "+days);
        cal=Calendar.getInstance();
    }

    @Override
    public void onDateSelected(Date date) {
        int year=date.getYear();
        int dayOfMonth=date.getDate();
        int month=date.getMonth();
        if(date.compareTo(cal.getTime())<=0){
            //if(done[dayOfMonth]){
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
                Intent in=new Intent(Datewisecustom.this,g);
                in.putExtras(basket);
                startActivity(in);
            overridePendingTransition(R.anim.flip_horizontal_in, R.anim.flip_horizontal_out);

            //}
            //else{
             //   Toast to=Toast.makeText(this,"Attendance already added",Toast.LENGTH_LONG);
              //  to.show();
          //  }
        }
        else{
            Toast t=Toast.makeText(this,"Future dates cannot be selected",Toast.LENGTH_LONG);
            t.show();
        }
    }

    @Override
    public void onMonthChanged(Date date) {

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
