package com.example.ch_e00587.diabetes2;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TextView;


public class diabetes_sub_menu extends TabActivity {
    TabHost tabHost;
    TextView tv_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_main=(TextView) findViewById(R.id.layoutTextView);
        tv_main.setText("Activity");
        tabHost=(TabHost) findViewById(android.R.id.tabhost);
        TabHost.TabSpec tab5=tabHost.newTabSpec("Eat Healthy");
        TabHost.TabSpec tab6=tabHost.newTabSpec("Activity");
        TabHost.TabSpec tab7=tabHost.newTabSpec("Stories");
        //TabHost.TabSpec tab8=tabHost.newTabSpec("Home");
        tab5.setIndicator("Eat Healthy");
        tab5.setContent(new Intent(this,EatHealthy.class));
        tab6.setIndicator("Activity");
        tab6.setContent(new Intent(this,Physicalactivity.class));
        tab7.setIndicator("Stories");
        tab7.setContent(new Intent(this,stories.class));
        //tab8.setIndicator("Home");
        //tab8.setContent(new Intent(this,Home.class));
        //tab8.setIndicator("",getApplicationContext().getResources().getDrawable(R.drawable.home));
        tabHost.addTab(tab5);
        tabHost.addTab(tab6);
        tabHost.addTab(tab7);
        //tabHost.addTab(tab8);
        tabHost.setCurrentTab(1);

        tabHost=getTabHost();

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                Log.i("***Selected Tab", "Im currently in tab with index::" + tabHost.getCurrentTab());
                tv_main.setTypeface(null, Typeface.BOLD);
                tv_main.setText(tabHost.getCurrentTabTag());
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_diabetes_sub_menu, menu);
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
