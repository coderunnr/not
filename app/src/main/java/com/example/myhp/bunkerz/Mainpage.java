package com.example.myhp.bunkerz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.sql.SQLException;

public class Mainpage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    Button addattendance,viewsub,addsubjectwise;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        addattendance = (Button) findViewById(R.id.addattendance);
        viewsub=(Button)findViewById(R.id.opensubjectwise);
        addsubjectwise=(Button)findViewById(R.id.subjectwiseadd);
        tv = (TextView) findViewById(R.id.tvpercentage);
        viewsub.setOnClickListener(this);
        addattendance.setOnClickListener(this);
        addsubjectwise.setOnClickListener(this);
        try {
            viewattendance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        SharedPreferences prefs=getSharedPreferences("myfirsttime",0);
        prefs.edit().putBoolean("myfirst",false).commit();
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
                Intent b = new Intent(Mainpage.this, i);
                startActivity(b);
                break;
            case R.id.opensubjectwise:
                Class i1 = null;
                try {
                    i1 = Class.forName("com.example.myhp.bunkerz.Viewsubjectwise");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Intent y = new Intent(Mainpage.this, i1);
                startActivity(y);
                break;
            case R.id.subjectwiseadd:
                Class i2 = null;
                try {
                    i2 = Class.forName("com.example.myhp.bunkerz.Addsubjectwise");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Intent x = new Intent(Mainpage.this, i2);
                startActivity(x);
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


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainpage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

      if (id == R.id.changefulltime) {
            // Handle the camera action
          Timetable t=new Timetable(this);
          try {
              t.open();
          } catch (SQLException e) {
              e.printStackTrace();
          }
          t.droptable();
          t.close();

          Class i = null;
          Bundle basket=new Bundle();
          basket.putString("day","Monday");
          try {
              i = Class.forName("com.example.myhp.bunkerz.Subjectlist");

          } catch (ClassNotFoundException e) {
              e.printStackTrace();
          }
          Intent b = new Intent(Mainpage.this, i);
          b.putExtras(basket);
          startActivity(b);



      } else if (id == R.id.addasub) {

          Class u= null;
          Bundle b=new Bundle();
          b.putBoolean("show",false);
          try {
              u = Class.forName("com.example.myhp.bunkerz.MainActivity");
          } catch (ClassNotFoundException e) {
              e.printStackTrace();
          }
         Intent in =new Intent(Mainpage.this,u);
          in.putExtras(b);
         startActivity(in);




        } else if (id == R.id.deleteattendance) {

        } else if (id == R.id.settings) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
