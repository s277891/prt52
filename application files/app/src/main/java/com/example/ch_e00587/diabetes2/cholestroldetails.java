package com.example.ch_e00587.diabetes2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class cholestroldetails extends ActionBarActivity {
    TextView txtview;
    Button btn_diabtes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cholestroldetails);
        txtview=(TextView)findViewById(R.id.txtvw);
        txtview.setText(Html.fromHtml(getString(R.string.cholestrolreduce)));
        /*btn_diabtes = (Button) findViewById(R.id.button_diabetes);
        btn_diabtes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSubMenu(view);
            }
        });*/

    }

    public void startSubMenu(View view) {
        Intent Diabtes_sub_menu = new Intent(this, diabetes_sub_menu.class);
        startActivity(Diabtes_sub_menu);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cholestroldetails, menu);
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
}
