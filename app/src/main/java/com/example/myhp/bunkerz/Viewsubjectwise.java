package com.example.myhp.bunkerz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by my hp on 1/26/2016.
 */
public class Viewsubjectwise extends Activity implements AdapterView.OnItemClickListener{
    ListView lv;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewsubjectwise);
        lv=(ListView)findViewById(R.id.listsubjectwise);
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

    }
}
