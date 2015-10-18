package com.example.ch_e00587.diabetes2;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;


public class MainActivity extends TabActivity {

    TextView tv_main;
    static TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("DIABETES1", "On Create main activity");
        tv_main=(TextView) findViewById(R.id.layoutTextView);

        tabHost=(TabHost) findViewById(android.R.id.tabhost);
        TabSpec tab1=tabHost.newTabSpec("Home");
        TabSpec tab2=tabHost.newTabSpec("Info");
        TabSpec tab3=tabHost.newTabSpec("Stories");
        TabSpec tab4=tabHost.newTabSpec("User Profile");

        tab1.setIndicator("");
        tab1.setContent(new Intent(this,Home.class));
        tab1.setIndicator("",getApplicationContext().getResources().getDrawable(R.drawable.homebuttom));

        tab4.setIndicator("");
        tab4.setContent(new Intent(this,Profile.class));
        tab4.setIndicator("",getApplicationContext().getResources().getDrawable(R.drawable.userbutton));

        tab2.setIndicator("Info");
        tab2.setContent(new Intent(this,Info.class));
        tab2.setIndicator("",getApplicationContext().getResources().getDrawable(R.drawable.iconbutton));

        tab3.setIndicator("stories");
        tab3.setContent(new Intent(this,stories.class));
        //LayoutSetter LS=new LayoutSetter();
        tab3.setIndicator("",getApplicationContext().getResources().getDrawable(R.drawable.storiesbutton));

        tabHost.addTab(tab1);

        tabHost.addTab(tab2);
        tabHost.addTab(tab3);
        tabHost.addTab(tab4);

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
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
