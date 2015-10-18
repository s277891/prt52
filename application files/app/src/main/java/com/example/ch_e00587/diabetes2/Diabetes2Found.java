package com.example.ch_e00587.diabetes2;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class Diabetes2Found extends ListActivity {
    TextView tv;
    TextView textViewchkresult;
    TextView tv1;
    ScrollView sw;
    ListView listView;
    Button btn_diabtes;
    Set illnessset1 = new HashSet();
    List illnessset = new ArrayList();
    List bp = new ArrayList();
    List chol = new ArrayList();
    List hypo = new ArrayList();
    List glucose = new ArrayList();

    ArrayList<String> al = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diabetes2_found);
        Log.i("DIABETES1", "In On create");
        addbimariyaan();
       // textViewchkresult = (TextView) findViewById(R.id.checkresult);
        tv = (TextView) findViewById(R.id.checkresult1);
        tv1=(TextView)findViewById(R.id.tv_diabetes_riskcause);
        btn_diabtes = (Button) findViewById(R.id.button_diabetes);
        btn_diabtes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSubMenu(view);
            }
        });
        //listView=(ListView) findViewById(an);
        Log.i("DIABETES1", "In On create after textViewchkresult");
        al = getIntent().getExtras().getStringArrayList("diabresult");
        Log.i("DIABETES1", "In On create after al");


        String diab_val = "";
        for (int i = 0; i < al.size(); i++) {

            if (al.size() <= 1) {
                diab_val = al.get(i);
                if (al.get(i).toString().equalsIgnoreCase("Blood Pressure")) {
                    illnessset.addAll(bp);
                } else if (al.get(i).toString().equalsIgnoreCase("Cholestrol")) {
                    illnessset.addAll(chol);
                } else if (al.get(i).toString().equalsIgnoreCase("Hypoglycemia")) {
                    illnessset.addAll(hypo);
                } else if (al.get(i).toString().equalsIgnoreCase("Glucose")) {
                    illnessset.addAll(glucose);
                } else {
                    illnessset.addAll(bp);
                }
            } else {
                if (i == 0) {
                    diab_val = al.get(i);
                } else if (i == al.size() - 1) {
                    diab_val = diab_val + " & " + al.get(i);
                } else {
                    diab_val = diab_val + ", " + al.get(i);
                }

                if (al.get(i).toString().equalsIgnoreCase("Blood Pressure")) {
                    illnessset.addAll(bp);
                } else if (al.get(i).toString().equalsIgnoreCase("Cholestrol")) {
                    illnessset.addAll(chol);
                } else if (al.get(i).toString().equalsIgnoreCase("Hypoglycemia")) {
                    illnessset.addAll(hypo);
                } else if (al.get(i).toString().equalsIgnoreCase("Glucose")) {
                    illnessset.addAll(glucose);
                } else {
                    illnessset.addAll(bp);
                }

            }
        }
        Log.i("DIABETES1", "In On create after illness list set");
        Log.i("DIABETES1", "In On create after illness list set" + illnessset.size());
        //tv.setText("You are having Risk of below mentioned Diseases.");
        tv.setText(Html.fromHtml(getString(R.string.ManageDiabetesNew)));
        if (illnessset.size() >= 1) {
            //textViewchkresult.setText("You have been Diagnosed with Diabetes due to "+diab_val);
            tv1.setText("According to your medical details you should reduce the following health risks:");

            //textViewchkresult.setText("You have been Diagnosed with Diabetes due to ");
        } else {
            Log.i("DIABETES1", "In Diabetes found but array is not there");
        }
        Iterator it = illnessset.iterator();
        //textViewchkresult_above.setText("You are having below mentioned Risks"+"\n");
        String final_putIntextView = "";
        while (it.hasNext()) {
            Log.i("DIABETES1", "The iterator val is " + it.next().toString());
            final_putIntextView = it.next().toString() + "\n";
        }
        Log.i("DIABETES1", "the final_putIntextView val is " + final_putIntextView);
        //textViewchkresult_above.setText(final_putIntextView);
        Log.i("DIABETES1", "Before adapter");
        illnessset1.addAll(illnessset);
        illnessset.clear();
        illnessset.addAll(illnessset1);
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, illnessset);
        //ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, al);
        setListAdapter(adapter);
        //   tv1.setText(Html.fromHtml(getString(R.string.ManageDiabetesNew)));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String item = (String) getListAdapter().getItem(position);
        //Toast.makeText(this, item, Toast.LENGTH_LONG).show();
        if(item.equalsIgnoreCase("circulation problem")){
            Log.i("DIABETES1","going in circulation problem");
            Intent Diabtes_sub_menu = new Intent(this, circulationproblem.class);
            startActivity(Diabtes_sub_menu);
        }
        else if(item.equalsIgnoreCase("strokes")){
            Log.i("DIABETES1","going in cholestrol");
            Intent Diabtes_sub_menu = new Intent(this, strokes.class);
            startActivity(Diabtes_sub_menu);
        }
        else if(item.equalsIgnoreCase("heart attack")){
            Log.i("DIABETES1","going in hypoglycemia");
            Intent Diabtes_sub_menu = new Intent(this, heartattack.class);
            startActivity(Diabtes_sub_menu);
        }
        else if(item.equalsIgnoreCase("kidney damage")){
            Log.i("DIABETES1","going in glucose");
            Intent Diabtes_sub_menu = new Intent(this, kidney.class);
            startActivity(Diabtes_sub_menu);
        }
        else if(item.equalsIgnoreCase("Eye damage")){
            Log.i("DIABETES1","going in glucose");
            Intent Diabtes_sub_menu = new Intent(this, eyedamage.class);
            startActivity(Diabtes_sub_menu);
        }
        else if(item.equalsIgnoreCase("Erectile Dysfunction")){
            Log.i("DIABETES1","going in glucose");
            Intent Diabtes_sub_menu = new Intent(this, erectile.class);
            startActivity(Diabtes_sub_menu);
        }
        else{
            Log.i("DIABETES1","going in else of all issues");
            Intent Diabtes_sub_menu = new Intent(this, heartattack.class);
            startActivity(Diabtes_sub_menu);
        }
    }

    private void addbimariyaan() {
        Log.i("DIABETES1", "In add bimariyaan");
        bp.add("Heart Attack");
        bp.add("Strokes");
        bp.add("Eye Damage");
        bp.add("Kidney Damage");
        bp.add("Circulation problem");
        bp.add("Erectile dysfunction");
        chol.add("Heart Attack");
        chol.add("Strokes");
        chol.add("Circulation problem");
        chol.add("Erectile dysfunction");
        glucose.add("Heart Attack");
        glucose.add("Strokes");
        glucose.add("Eye Damage");
        glucose.add("Kidney Damage");
        glucose.add("Circulation problem");
        glucose.add("Erectile dysfunction");
}

    public void startSubMenu(View view) {
        Intent Diabtes_sub_menu = new Intent(this, diabetes_sub_menu.class);
        startActivity(Diabtes_sub_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_diabetes2_found, menu);
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