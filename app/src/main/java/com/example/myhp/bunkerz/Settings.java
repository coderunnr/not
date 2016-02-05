package com.example.myhp.bunkerz;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

/**
 * Created by my hp on 2/3/2016.
 */
public class Settings extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        CheckBox cb=(CheckBox)findViewById(R.id.checkBox);
        Button b=(Button)findViewById(R.id.button3);
        b.setOnClickListener(this);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar10);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        SharedPreferences sf=getSharedPreferences("criteria",0);
        if(sf.getBoolean("include",false))
        {
            cb.setChecked(true);
        }
        else
            cb.setChecked(false);

    }

    @Override
    public void onClick(View v) {

    }

    public void oncheck(View v){

        if(((CheckBox)v).isChecked()){
            SharedPreferences sf=getSharedPreferences("criteria",0);
            SharedPreferences.Editor editor=sf.edit();
            editor.putBoolean("include",true);
            editor.commit();
        }
        else{
            SharedPreferences sf=getSharedPreferences("criteria",0);
            SharedPreferences.Editor editor=sf.edit();
            editor.putBoolean("include",false);
            editor.commit();
        }

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
