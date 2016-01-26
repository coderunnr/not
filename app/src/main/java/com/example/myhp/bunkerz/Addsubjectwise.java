package com.example.myhp.bunkerz;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by my hp on 1/26/2016.
 */
public class Addsubjectwise extends Activity implements AdapterView.OnItemClickListener,DialogInterface.OnClickListener{
        ListView lv;
        ArrayList<String> list;
    String p;
    String[] status={"Absent","Present","MassBunk"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addsubjectwise);
        lv=(ListView)findViewById(R.id.listofsubjects);
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
        b.updateattendance(which,p);
        b.close();
    }
}
