package com.example.ch_e00587.diabetes2;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import java.util.Calendar;


public class Home extends Activity {
    private String diabtesValue="";
    TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DiabetesResult db=new DiabetesResult();
        diabtesValue=db.getCheckDiabetesValue();


        Calendar cal=Calendar.getInstance();
        int day=cal.get(Calendar.DAY_OF_WEEK);
        TextView textView=(TextView) findViewById(R.id.tv);
        textView.setText("Welcome to Diabetes Advisor");
        textView1=(TextView) findViewById(R.id.tv1);
        //textView1.setMovementMethod(new ScrollingMovementMethod());

        Log.i("DIABETES1","In Home before else if part"+db.getCheckDiabetesValue());
        if(diabtesValue.equalsIgnoreCase("ok")) {
            switch (day) {
                case 1:
                    textView1.setText(" Eat plenty of fibre-rich food such as brown bread, pulses and cereals.");
                    break;
                case 2:
                    textView1.setText(" Do not go on a diet. Switch to healthier eating habits that you can continue long term.");
                    break;
                case 3:
                    textView1.setText(" Aim to eat the recommended five portions of fruit and vegetables every day to boost energy levels and general health.");
                    break;
                case 4:
                    textView1.setText(" Have a varied diet. Look for different, brightly coloured fruits and vegetables which generally provide plenty of nutrients.");
                    break;
                case 5:
                    textView1.setText(" Don't eat too much salt. The recommended daily intake is 5-6g.");
                    break;
                case 6:
                    textView1.setText(" Avoid wearing corset-style restrictive clothes. Normally you breathe using your lower lungs and your diaphragm moves up and down");
                    break;
                case 7:
                    textView1.setText(" Carrying a heavy handbag on your shoulder can throw your body off balance and seriously hamper your posture.");
                    break;
                default:
                    textView1.setText("Perform small muscle contractions throughout the day, at your desk, in queues protective effects but too much is bad news");
                    break;
            }
        }
        else {
            Log.i("DIABETES1","In Home else part"+db.getCheckDiabetesValue());
            textView1.setText(db.getCheckDiabetesValue());
        }
        Log.i("DIABETES1","Going to set the Scrolling ");
        textView1.setMovementMethod(new ScrollingMovementMethod());
        //textView1.setSelected(true);
        Log.i("DIABETES1","setted the Scrolling ");


/*        View view;
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.layout_tag, null);

        RelativeLayout item = (RelativeLayout) view.findViewById(R.id.borderlayout);*/
//        mainActivity.tv_main.setText("Main");

    }

}
