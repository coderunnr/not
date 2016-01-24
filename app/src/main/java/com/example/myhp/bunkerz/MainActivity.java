package com.example.myhp.bunkerz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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
        l=(LinearLayout)findViewById(R.id.linearfirst);
        save=(Button)findViewById(R.id.bsavefirst);
        save.setOnClickListener(this);
        addsubj.setOnClickListener(this);
        etArray = new ArrayList<EditText>();
        sp=getSharedPreferences("calendar",0);
        SharedPreferences.Editor editor=sp.edit();
        editor.putInt("days",0);
        editor.commit();
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

            Datahandle d=new Datahandle(this);
            try {
                d.open();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            d.createentry(et.getText().toString());
            d.close();

        }



        try {
            Class u=Class.forName("com.example.myhp.bunkerz.Subjectlist");
            Bundle basket=new Bundle();
            basket.putString("day","Monday");
            Intent in =new Intent(MainActivity.this,u);
            in.putExtras(basket);
            startActivity(in);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        break;

}
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

}
