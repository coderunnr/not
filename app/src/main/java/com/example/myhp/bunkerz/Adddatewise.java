package com.example.myhp.bunkerz;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by my hp on 1/24/2016.
 */
public class Adddatewise extends Activity implements View.OnClickListener {
    int hint;
    LinearLayout l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adddatewise);
        l=(LinearLayout)findViewById(R.id.adddatewiselayout);
        createtextview();
    }

    private void createtextview() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams (
                RelativeLayout.LayoutParams.MATCH_PARENT    ,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.setMargins(0, 10, 0, 10);
        TextView tv=new TextView(this);
        Button b=new Button(this);

        b.setText("add");
        tv.setLayoutParams(params);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        tv.setId(hint);
        b.setId(hint);
        b.setOnClickListener(this);
        hint++;
        b.setLayoutParams(params);
        l.addView(tv);
        l.addView(b);

    }

    @Override
    public void onClick(View v) {

    }
}
