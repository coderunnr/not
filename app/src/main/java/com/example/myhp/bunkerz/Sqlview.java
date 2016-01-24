package com.example.myhp.bunkerz;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.SQLException;

public class Sqlview extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlview);
        TextView tv=(TextView)findViewById(R.id.binfosql);
        Timetable info= new Timetable(this);
        try {
            info.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String data=info.getData();
        info.close();
        tv.setText(data);
    }
}
