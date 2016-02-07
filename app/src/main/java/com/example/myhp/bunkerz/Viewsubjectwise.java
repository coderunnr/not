package com.example.myhp.bunkerz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
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
 * Created by my hp on 1/26/2016.
 */
public class Viewsubjectwise extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView lv;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewsubjectwise);
        lv=(ListView)findViewById(R.id.listsubjectwise);
        TextView tv=(TextView)findViewById(R.id.tvviewsubjectwise1);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "android.ttf");
        tv.setTypeface(custom_font);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar5);
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



       ArrayAdapter items = new CustomListAdapter(Viewsubjectwise.this , R.layout.custom_list ,list);



        lv.setAdapter(items);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle backpack=new Bundle();
        backpack.putString("subname",list.get(position));
        Class i = null;
        try {
            i = Class.forName("com.example.myhp.bunkerz.Viewsubjectattendance");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Intent b = new Intent(Viewsubjectwise.this, i);
        b.putExtras(backpack);
        startActivity(b);
        overridePendingTransition(R.anim.flip_horizontal_in, R.anim.flip_horizontal_out);
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
