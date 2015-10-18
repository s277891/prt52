package com.example.ch_e00587.diabetes2;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class erectile extends ListActivity {
    List strokeslist = new ArrayList();
    Button btn_diabtes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erectile);
        addcirulationlist();
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, strokeslist);
        setListAdapter(adapter);
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

    private void addcirulationlist() {
        strokeslist.add("Lowering BP");
        strokeslist.add("Lowering Cholestrol");
        strokeslist.add("Lowering Glucose Level");
        strokeslist.add("Stop Smoking");
        strokeslist.add("Dealing With Depression");
        strokeslist.add("Increase Physical");
        strokeslist.add("Better Food Choice");
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);

        if(item.equalsIgnoreCase("Lowering BP")){
            Log.i("DIABETES1", "going in lowering bp");
            Intent Diabtes_sub_menu = new Intent(this, bloodpressuredetails.class);
            startActivity(Diabtes_sub_menu);
        }
        else if(item.equalsIgnoreCase("lowering cholestrol")){
            Log.i("DIABETES1","going in cholestrol");
            Intent Diabtes_sub_menu = new Intent(this, cholestroldetails.class);
            startActivity(Diabtes_sub_menu);
        }
        else if(item.equalsIgnoreCase("lowering glucose level")){
            Log.i("DIABETES1","going in glucose level");
            Intent Diabtes_sub_menu = new Intent(this, glucosedetails.class);
            startActivity(Diabtes_sub_menu);
        }
        else if(item.equalsIgnoreCase("stop smoking")){
            Log.i("DIABETES1","going in stop smoking");
            Intent Diabtes_sub_menu = new Intent(this, stopsmokingdetails.class);
            startActivity(Diabtes_sub_menu);
        }
        else if(item.equalsIgnoreCase("Reduce waist")){
            Log.i("DIABETES1","going in Reduce waist");
            Intent Diabtes_sub_menu = new Intent(this, Reducewaistdetails.class);
            startActivity(Diabtes_sub_menu);
        }
        else if(item.equalsIgnoreCase("Increase physical")){
            Log.i("DIABETES1","going in Increase physical");
            Intent Diabtes_sub_menu = new Intent(this, Increasephysicaldetails.class);
            startActivity(Diabtes_sub_menu);
        }
        else if(item.equalsIgnoreCase("Better food choice")){
            Log.i("DIABETES1","going in Better food choice");
            Intent Diabtes_sub_menu = new Intent(this, Betterfoodchoicedetails.class);
            startActivity(Diabtes_sub_menu);
        }
        else if(item.equalsIgnoreCase("Taking aspirin")){
            Log.i("DIABETES1","going in Taking aspirin");
            Intent Diabtes_sub_menu = new Intent(this, takingaspirindetails.class);
            startActivity(Diabtes_sub_menu);
        }
        else if(item.equalsIgnoreCase("dealing with depression")){
            Log.i("DIABETES1","going in Better food choice");
            Intent Diabtes_sub_menu = new Intent(this, dealingwithdepressiondetails.class);
            startActivity(Diabtes_sub_menu);
        }
        else{
            Log.i("DIABETES1","In else of circulation problem");
            Intent Diabtes_sub_menu = new Intent(this, bloodpressuredetails.class);
            startActivity(Diabtes_sub_menu);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_erectile, menu);
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
