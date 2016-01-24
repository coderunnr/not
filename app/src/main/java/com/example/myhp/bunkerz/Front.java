package com.example.myhp.bunkerz;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.SQLException;

/**
 * Created by my hp on 1/24/2016.
 */
public class Front extends Activity implements View.OnClickListener {
    Button addattendance;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front);
        addattendance=(Button)findViewById(R.id.addattendance);
        tv=(TextView)findViewById(R.id.tvpercentage);
        addattendance.setOnClickListener(this);
        try {
            viewattendance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void viewattendance() throws SQLException {
                Datahandle d=new Datahandle(this);
        d.open();
        tv.setText(Integer.toString(d.getattendance()));
        d.close();
        ;
    }



    @Override
    public void onClick(View v) {

    }
}
