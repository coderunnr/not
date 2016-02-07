package com.example.myhp.bunkerz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.github.florent37.materialtextfield.MaterialTextField;

import java.sql.SQLException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etcriteria;
    Button addsubj,save;
    LinearLayout l;
    int hint=0;
    ArrayList<EditText> etArray;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        etcriteria=(EditText)findViewById(R.id.etcriteria);
        addsubj=(Button)findViewById(R.id.button);
        TextView tv=(TextView)findViewById(R.id.showcriteriatv);
        l=(LinearLayout)findViewById(R.id.mainll);
        save=(Button)findViewById(R.id.bsavefirst);
        MaterialTextField mv=(MaterialTextField)findViewById(R.id.materialtv);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "android.ttf");
        save.setTypeface(custom_font);
        tv.setTypeface(custom_font);
        addsubj.setTypeface(custom_font);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar7);
        setSupportActionBar(toolbar);

        save.setOnClickListener(this);
        addsubj.setOnClickListener(this);

        etArray = new ArrayList<EditText>();
        sp=getSharedPreferences("calendar",0);
        SharedPreferences.Editor editor=sp.edit();
        editor.putInt("days",0);
        editor.commit();
        Bundle back=getIntent().getExtras();

        if(!back.getBoolean("show",true)){
            etcriteria.setVisibility(View.GONE);
            tv.setVisibility(View.GONE);
            mv.setVisibility(View.GONE);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }

    @Override
    public void onClick(View v) {
switch(v.getId()) {
    case R.id.button:
    createEditTextView();
        break;
    case R.id.bsavefirst:

        for(int i=0;i<hint;i++){
            EditText et=etArray.get(i);
if(et.getText().toString()!="") {
    Datahandle d = new Datahandle(this);
    try {
        d.open();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    d.createentry(et.getText().toString());
    d.close();
}

        }
        String s=etcriteria.getText().toString().trim();
        if(s.equals("")){
            s="75";
        }
        SharedPreferences sf=getSharedPreferences("criteria",0);
        SharedPreferences.Editor editor=sf.edit();
        editor.putInt("percent",Integer.parseInt(s));
        editor.putBoolean("include",false);
        editor.commit();




        try {
            Class u=Class.forName("com.example.myhp.bunkerz.Subjectlist");
            Bundle basket=new Bundle();
            basket.putString("day","Monday");
            Intent in =new Intent(MainActivity.this,u);
            in.putExtras(basket);
            startActivity(in);
            overridePendingTransition(R.anim.flip_horizontal_in, R.anim.flip_horizontal_out);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        break;

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

    protected void createEditTextView() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams (
                RelativeLayout.LayoutParams.MATCH_PARENT    ,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.setMargins(0, 10, 0, 10);
        EditText edittTxt = new EditText(this);


        edittTxt.setHint("Subject Name");
        edittTxt.setLayoutParams(params);
//
        edittTxt.setInputType(InputType.TYPE_CLASS_TEXT);
        edittTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        edittTxt.setId(hint);
        hint++;
       etArray.add(edittTxt);
        l.addView(edittTxt);

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}
