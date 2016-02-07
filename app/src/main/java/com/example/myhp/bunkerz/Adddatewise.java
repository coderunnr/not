package com.example.myhp.bunkerz;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by my hp on 1/24/2016.
 */
public class Adddatewise extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener,DialogInterface.OnClickListener {
    int hint;
    LinearLayout l;
    ListView lv;
    String[] status={"Absent","Present","MassBunk"};
    String p;
    ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adddatewise);
        l=(LinearLayout)findViewById(R.id.adddatewiselayout);
        lv=(ListView)findViewById(R.id.listsubject);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        TextView test=(TextView)findViewById(R.id.textView);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "android.ttf");
        test.setTypeface(custom_font);
        Bundle bucket=getIntent().getExtras();
        int g=bucket.getInt("dayofweek");
        test.setText(getweekname(g));
        Timetable xn=new Timetable(this);
        try {
            xn.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String paths[]=xn.gettv(g);
        xn.close();
        list = new ArrayList<String>();
        for (int i = 0; i < paths.length; i++) {
            list.add(paths[i]);
        }
        ArrayAdapter<String> items=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        lv.setAdapter(items);
        lv.setOnItemClickListener(this);
        //createtextview();
    }

    private String getweekname(int g) {
String s=null;
        switch(g){
            case 6:
              s="Monday";
                break;
            case 7:
                s="Tuesday";
                break;
            case 1:
                s="Wednesday";
                break;
            case 2:
                s="Thursday";
                break;
            case 3:
                s="Friday";
                break;
            case 4:
                s="Saturday";
                break;
            case 5:
                s="Sunday";
                break;


        }
        return s;
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
    /*   private void createtextview() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams (
                RelativeLayout.LayoutParams.MATCH_PARENT    ,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.setMargins(0, 10, 0, 10);
        TextView tv=new TextView(this);
        Button b=new Button(this);

        b.setText("add");
        tv.setLayoutParams(params);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        tv.setId(hint);
        b.setId(hint);
        b.setOnClickListener(this);
        hint++;
        b.setLayoutParams(params);
        l.addView(tv);
        l.addView(b);

    }*/

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view,int position, long id){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Status");
        builder.setItems(status, this);
        p=list.get(position);
        builder.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Datahandle b=new Datahandle(this);
        try {
            b.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        b.updateattendance(which,p);
        b.close();
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
