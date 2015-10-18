package com.example.ch_e00587.diabetes2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;


public class EatHealthy extends ActionBarActivity {
    TextView tv;
    ScrollView sw;
    Button btn;
    //WebView Wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eat_healthy);
        tv=(TextView) findViewById(R.id.textvieweathealthy);
        sw= (ScrollView) this.findViewById(R.id.Eathealthy_ScrollView);

        tv.setText(Html.fromHtml(getString(R.string.EatHealthy)));

        //Wv.loadUrl("file:///android_asset/index.html");
        btn=(Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn.setBackgroundResource(R.drawable.submitbtn_bg_hover);
                startSubMenu(view);
            }
        });
        sw.fullScroll(View.FOCUS_DOWN);
    }
    public void startSubMenu(View view){
        Intent eatlink=new Intent(this,EatHealthyLink.class);
        startActivity(eatlink);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_eat_healthy, menu);
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
