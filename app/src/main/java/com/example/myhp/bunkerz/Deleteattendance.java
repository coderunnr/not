package com.example.myhp.bunkerz;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by my hp on 2/2/2016.
 */
public class Deleteattendance extends AppCompatActivity implements AdapterView.OnItemClickListener,DialogInterface.OnClickListener{
    ListView lv;
    ArrayList<String> list;
    String p;
    String[] status={"Delete"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deleteattendance);
        lv=(ListView)findViewById(R.id.listofsubjectsd);
        TextView tv=(TextView)findViewById(R.id.tvdisplaysubjectwised);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "android.ttf");
        tv.setTypeface(custom_font);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar8);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Datahandle xn=new Datahandle(this);
        try {
            xn.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String paths[]=xn.getData().split("\n");
        xn.close();
        list = new ArrayList<String>();
        for (int i = 0; i < paths.length; i++) {
            list.add(paths[i]);
        }
        ArrayAdapter<String> items=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        lv.setAdapter(items);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
        b.deleteattendance(which,p);
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
