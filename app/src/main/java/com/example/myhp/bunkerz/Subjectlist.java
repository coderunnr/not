package com.example.myhp.bunkerz;

import android.app.Activity;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by my hp on 1/23/2016.
 */
public class Subjectlist extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener{
    LinearLayout linear;
    Button addsub,savetimetab,done;
    int hint;
    TextView tv;
    ArrayAdapter<String> adapter;
    boolean activate=true;
    static String starttime="0",stoptime="0",temp;
    String subjectname,day;
    SharedPreferences somedata;
    static int z=0;
    Bundle basket;
    Class open;
    Intent next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subjectlist);
        linear=(LinearLayout)findViewById(R.id.lsubjectlist);
        addsub=(Button)findViewById(R.id.button2);
        savetimetab=(Button)findViewById(R.id.bsavetimetab);
        tv=(TextView)findViewById(R.id.tday);
        done=(Button)findViewById(R.id.bdone);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "android.ttf");
        done.setTypeface(custom_font);
        tv.setTypeface(custom_font);
        savetimetab.setTypeface(custom_font);
        addsub.setTypeface(custom_font);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar6);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        addsub.setOnClickListener(this);
        savetimetab.setOnClickListener(this);
        done.setOnClickListener(this);
       String result[]=getit();
        adapter=new ArrayAdapter<String>(Subjectlist.this, android.R.layout.simple_spinner_item,result);
        somedata=getSharedPreferences("shared",0);
        SharedPreferences.Editor editor=somedata.edit();
        editor.putInt("hint", hint);
        editor.commit();
subjectname=result[0];
        Bundle backpack=getIntent().getExtras();
       day= backpack.getString("day");
        tv.setText(day);

    }

    private String[] getit() {
        Datahandle info=new Datahandle(this);
        try {
            info.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String result=info.getData();
        info.close();
        return result.split("\n");
    }

    @Override
    public void onClick(View v) {
 if(v.getId()==R.id.button2) {
     if(activate==true) {
         RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                 RelativeLayout.LayoutParams.MATCH_PARENT,
                 RelativeLayout.LayoutParams.WRAP_CONTENT);

         params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
         params.setMargins(0, 10, 0, 10);
         Spinner sp = new Spinner(this);
         Button b = new Button(this);
         Button b2 = new Button(this);
         sp.setLayoutParams(params);
         b.setLayoutParams(params);
         b2.setLayoutParams(params);
         b.setText("time");
         b2.setText("end time");
         b.setOnClickListener(this);
         b2.setOnClickListener(this);

         b.setId(hint);
         if(android.os.Build.VERSION.SDK_INT >= 21){
             b.setBackground(getResources().getDrawable(R.color.colorPrimary, null));
         } else {
             b.setBackground(getResources().getDrawable(R.color.colorPrimary));
         }

         b2.setId(hint);

         if(android.os.Build.VERSION.SDK_INT >= 21){
             b2.setBackground(getResources().getDrawable(R.color.colorPrimary, null));
         } else {
             b2.setBackground(getResources().getDrawable(R.color.colorPrimary));
         }

         b.setTextColor(Color.WHITE);
         b2.setTextColor(Color.WHITE);
         sp.setAdapter(adapter);
         sp.setId(hint);
         hint++;
         SharedPreferences.Editor editor=somedata.edit();
         editor.putInt("hint", hint);
         editor.commit();
         sp.setOnItemSelectedListener(this);
         linear.addView(sp);
         linear.addView(b);
         linear.addView(b2);
         activate=false;
         done.setVisibility(View.INVISIBLE);
     }
     else{
         Toast t=Toast.makeText(Subjectlist.this,"Save the Subject First",Toast.LENGTH_LONG);
         t.show();
     }

 }
 else if(v.getId()==R.id.bsavetimetab){
     Timetable g=new Timetable(this);
     try {
         g.open();
     } catch (SQLException e) {
         e.printStackTrace();
     }
Integer integer=new Integer(starttime);
     Integer stopint=new Integer(stoptime);
   if(integer>stopint){
        temp=starttime;
        starttime=stoptime;
        stoptime=temp;

   }
        g.createentry(subjectname,starttime,stoptime,day);

     g.close();
     activate=true;
     done.setVisibility(View.VISIBLE);
     //Class c= null;
   /*  try {
         c = Class.forName("com.example.myhp.bunkerz.Sqlview");
         Intent in=new Intent(Subjectlist.this,c);
         startActivity(in);
     } catch (ClassNotFoundException e) {
         e.printStackTrace();
     }*/

 }

else if(v.getId()==R.id.bdone){
    switch(day) {
        case "Monday":
            basket=new Bundle();
            basket.putString("day","Tuesday");
            open = null;
            try {
                open = Class.forName("com.example.myhp.bunkerz.Subjectlist");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            next = new Intent(Subjectlist.this,open);
            next.putExtras(basket);
            startActivity(next);
            overridePendingTransition(R.anim.flip_horizontal_in, R.anim.flip_horizontal_out);
            break;
        case "Tuesday":
            basket=new Bundle();
            basket.putString("day","Wednesday");
            open = null;
            try {
                open = Class.forName("com.example.myhp.bunkerz.Subjectlist");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            next = new Intent(Subjectlist.this,open);
            next.putExtras(basket);
            startActivity(next);
            overridePendingTransition(R.anim.flip_horizontal_in, R.anim.flip_horizontal_out);
            break;
        case "Wednesday":
            basket=new Bundle();
            basket.putString("day","Thursday");
            open = null;
            try {
                open = Class.forName("com.example.myhp.bunkerz.Subjectlist");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            next = new Intent(Subjectlist.this,open);
            next.putExtras(basket);
            startActivity(next);
            overridePendingTransition(R.anim.flip_horizontal_in, R.anim.flip_horizontal_out);
            break;
        case "Thursday":
            basket=new Bundle();
            basket.putString("day","Friday");
            open = null;
            try {
                open = Class.forName("com.example.myhp.bunkerz.Subjectlist");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            next = new Intent(Subjectlist.this,open);
            next.putExtras(basket);
            startActivity(next);
            overridePendingTransition(R.anim.flip_horizontal_in, R.anim.flip_horizontal_out);
            break;
        case "Friday":
            basket=new Bundle();
            basket.putString("day","Saturday");
            open = null;
            try {
                open = Class.forName("com.example.myhp.bunkerz.Subjectlist");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            next = new Intent(Subjectlist.this,open);
            next.putExtras(basket);
            startActivity(next);
            overridePendingTransition(R.anim.flip_horizontal_in, R.anim.flip_horizontal_out);
            break;
        case "Saturday":
            Class c= null;
    try {
         c = Class.forName("com.example.myhp.bunkerz.Mainpage");
         Intent in=new Intent(Subjectlist.this,c);
         startActivity(in);
        overridePendingTransition(R.anim.flip_horizontal_in, R.anim.flip_horizontal_out);
     } catch (ClassNotFoundException e) {
         e.printStackTrace();
     }
            break;
    }
 }
 else {

         DialogFragment newFragment = new TimePickerFragment();
         newFragment.show(getSupportFragmentManager(), "timePicker");


 }
 }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        subjectname=parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            int j=hourOfDay*60+minute;
            if(z%2==0)
{

    starttime=Integer.toString(j);
    z++;
}
            else{
stoptime=Integer.toString(j);
                z++;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sh=getSharedPreferences("shared",0);
        hint=sh.getInt("hint",0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
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


