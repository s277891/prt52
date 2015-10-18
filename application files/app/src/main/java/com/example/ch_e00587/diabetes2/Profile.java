package com.example.ch_e00587.diabetes2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.String;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Profile extends ActionBarActivity {
    EditText editText;
    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;
    EditText editText6;
    MyDbHandler myDbHandler;
    Button btn;
    Button button_date;
    Button btn5;
    String diabetes_check_result;
    private String Gallery_img_path;
    ImageView imageView;
    String imgDecodableString;
    Spinner spinner;
    static String sx;
    String diabtesValue1;
    String[] sex= new String[]{"Male","Female"};
    private int year;
    private int month;
    private int day;
    static final int DATE_PICKER_ID = 1003;
    ArrayList<String> diabtz=new ArrayList<String>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        Log.i("DIABETES1","In Activity Result");
        Log.i("DIABETES1","In Activity Result"+requestCode);
        Log.i("DIABETES1","In Activity Result resultCode "+resultCode);
        if(requestCode == 1001){
         if(resultCode == -1){
             Log.i("DIABETES1","In Activity Result got mapped");
             Uri selectedImage = data.getData();
             Log.i("DIABETES1","In Activity Result Uri is selectedImage "+selectedImage);
             String imgpath=getPath(selectedImage);
             String[] filePathColumn = { MediaStore.Images.Media.DATA };
             Log.i("DIABETES1","In Activity Result Uri is filePathColumn "+filePathColumn[0]);
             Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
             // Move to first row
             cursor.moveToFirst();

             int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
             imgDecodableString = cursor.getString(columnIndex);
             Log.i("DIABETES1","In Activity Result Uri is imgDecodableString  "+imgDecodableString );
             cursor.close();


             Bitmap bt=decodeSampledBitmapFromFile(imgDecodableString,1100,1100);

             imageView.setImageBitmap(bt);
             // /storage/sdcard0/Download/images (3).jpg
             int check=checkvalues();
             if(check == 0 || check == '0') {
                 deletebutton();
                 Log.i("DIABETES1", "After delete going to add");
                 adddbutton();
                 Log.i("DIABETES1", "After Add button Finish");
                // checkdiabetes2();
             }
             else{
                 Log.i("DIABETES1", "Nothing to do");
             }

         }
        }
    }

    public static Bitmap decodeSampledBitmapFromFile(String path, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        //BitmapFactory.Options optionss = new BitmapFactory.Options();
        //optionss.inPreferredConfig = Bitmap.Config.RGB_565;


        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        BitmapFactory.decodeFile(path,options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
// Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imageView=(ImageView) findViewById(R.id.imageUser);
        editText=(EditText) findViewById(R.id.editText);
        //editText1=(EditText) findViewById(R.id.editText1);
        editText2=(EditText) findViewById(R.id.editText2);
        editText3=(EditText) findViewById(R.id.editText3);
        editText4=(EditText) findViewById(R.id.editText4);
        editText5=(EditText) findViewById(R.id.editText5);
        editText6=(EditText) findViewById(R.id.editText6);

        btn=(Button) findViewById(R.id.button);
//      btn5=(Button) findViewById(R.id.button5);

        final Calendar c = Calendar.getInstance();
        year  = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day   = c.get(Calendar.DAY_OF_MONTH);
        addspinner();
        addDatePikker();
//      editText2.setText(new StringBuilder().append(month + 1).append("-").append(day).append("-").append(year).append(" "));

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                Log.i("DIABETES1","Setting intent for image get");
                startActivityForResult(galleryIntent, 1001);
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("DIABETES1","In listener of btn");
                int check=checkvalues();
                if(check == 0 || check == '0') {
                    deletebutton();
                    Log.i("DIABETES1", "After delete going to add");
                    adddbutton();
                    Log.i("DIABETES1", "After Add button Finish");
                    checkdiabetes2();
                }
                else{
                    Log.i("DIABETES1", "Nothing to do");
                }
            }

        });
  /*      btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //deletebutton(view);
                //printDatabase();
                clearData(view);
            }
        });*/
        myDbHandler=new MyDbHandler(this,null,null,1);
        printDatabase();
/*        if(editText1.getText().equals(null) || editText2.getText().equals(null) || editText3.getText().toString().equals(null) || editText3.getText().toString().equals("")  || editText3.getText().toString().equals(" ") || editText4.getText().toString().equals(null) || editText5.getText().toString().equals(null) || editText6.getText().toString().equals(null)){
            Log.i("DIABETES1", "Nothing to do in printing");
        }
        else{
        }*/

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this, pickerListener, year, month,day);
    }
    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            year  = selectedYear;
            month = selectedMonth;
            day   = selectedDay;

            // Show selected date
            editText2.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));

        }
    };
    public void addDatePikker(){
        editText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_PICKER_ID);
            }
        });
    }
    public void addspinner(){
        spinner = (Spinner) findViewById(R.id.spr_country_list);
        //spinner.setGravity(17)
        //spinner.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        // Array adapter to set data in Spinner Widget
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, sex);

        spinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener countrySelectedListener = new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> spinner, View container,
                                       int position, long id) {
                sx=(sex[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        };

        // Setting ItemClick Handler for Spinner Widget
        spinner.setOnItemSelectedListener(countrySelectedListener);

}
    public int checkvalues(){
        if(editText2.getText().equals(null)  || editText2.getText().toString().equals("")  || editText2.getText().toString().equals(" ")|| editText3.getText().toString().equals(null) || editText3.getText().toString().equals("")  || editText3.getText().toString().equals(" ") || editText4.getText().toString().equals(null)  || editText4.getText().toString().equals("")  || editText4.getText().toString().equals(" ") || editText5.getText().toString().equals(null)  || editText5.getText().toString().equals("")  || editText5.getText().toString().equals(" ")  || editText6.getText().toString().equals("")  || editText6.getText().toString().equals(" ")|| editText6.getText().toString().equals(null)){
            return 1;
        }
        else{
            return 0;
        }
    }
    public void printDatabase(){
        //String[] dbName=myDbHandler.dbToString();
        Log.i("DIABETES1", "In printDatabase");
        List<Object> dbName=new ArrayList(5);
        dbName=myDbHandler.dbToString();
        Products pd1=new Products();
        Log.i("DIABETES1", "In print DB getting from product class " + pd1.get_name());
        Log.i("DIABETES1", "In print DB getting from product class " + pd1.get_dob());
        Log.i("DIABETES1", "In print DB getting from product class " + pd1.get_bp());
        Log.i("DIABETES1", "In print DB getting from product class " + pd1.get_cholestrol());
        Log.i("DIABETES1", "In print DB getting from product class " + pd1.get_glucose());
        Log.i("DIABETES1", "In print DB getting from product class " + pd1.get_hypo());
        Log.i("DIABETES1", "In print DB getting from product class " + pd1.get_column_name_user());
        Log.i("DIABETES1", "In print DB getting from product class " + pd1.get_image_name_user());

        if(pd1.get_name() != null) {
            //editText1.setText(pd1.get_name().toString());
            if(pd1.get_name().toString().equalsIgnoreCase("male")) {
                spinner.setSelection(0);
            }
            else {
                spinner.setSelection(1);
            }
        }

        if(pd1.get_column_name_user() != null) {
            editText.setText(pd1.get_column_name_user().toString());
        }
        if(pd1.get_image_name_user() != null) {
            imgDecodableString=pd1.get_image_name_user();
            Log.i("DIABETES1","In getting image name from db as "+imgDecodableString);
            imgDecodableString=pd1.get_image_name_user();
            Log.i("DIABETES1", "Setting image from DB" + imgDecodableString);
            Bitmap bt=decodeSampledBitmapFromFile(imgDecodableString,225,225);

            imageView.setImageBitmap(bt);


//            imageView.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
        }
        if(pd1.get_dob() != null) {
            editText2.setText(pd1.get_dob().toString());
        }
        if(pd1.get_bp() != 0) {
            editText3.setText(""+pd1.get_bp());
        }
        if(pd1.get_cholestrol() != 0) {
            editText4.setText(""+pd1.get_cholestrol());
        }
        if(pd1.get_hypo() != 0) {
            editText5.setText(""+pd1.get_hypo());
        }
        if(pd1.get_glucose() != 0) {
            editText6.setText(""+pd1.get_glucose());
        }

        //editText.setText(dbName[0]);
        //Log.i("DIABETES1", "print DB");
    }
    public void checkdiabetes2(){
        //bp
        Log.i("DIABETES1", "After Add button Finish in checkdiabetes2" + Integer.parseInt(editText3.getText().toString()));
        int bp_pass = Integer.parseInt(editText3.getText().toString());
        Log.i("DIABETES1", "After Add button Finish after get bp");
        int cholestrol_pass = Integer.parseInt(editText4.getText().toString());
        Log.i("DIABETES1", "After Add button Finish after get choles");
        int hypop_pass = Integer.parseInt(editText5.getText().toString());
        Log.i("DIABETES1", "After Add button Finish after get hypo");
        int glucose_pass = Integer.parseInt(editText6.getText().toString());
        Log.i("DIABETES1", "Before BP Compare in checkdiabetes2");
        Log.i("DIABETES1", "Before Cholestrol Compare in checkdiabetes2");
        DiabetesResult db=new DiabetesResult();
        diabtesValue1=db.getCheckDiabetesValue();

        if(bp_pass >130){
            diabetes_check_result="bp";
            diabetes_check_result="You have been diagnosed with Daibetes 2 as your \n" +
                    " Blood Pressure is not in Range";
            db.setCheckDiabetesValue("You have been diagnosed with Daibetes 2 as your Blood Pressure is not in Range");
            diabtz.add("Blood Pressure");
            //return 1;
        }
        if (cholestrol_pass<1 || cholestrol_pass>4){
            diabetes_check_result="cholestrol";
            diabetes_check_result="You have been diagnosed with Daibetes 2 as your \n" +
                    " Cholestrol is not in Range";
            db.setCheckDiabetesValue("Your Cholestrol is not in Range");
            diabtz.add("Cholestrol");
            //return 2;
        }
        if (hypop_pass <47 || hypop_pass >53){
            diabetes_check_result="hypo";
            diabetes_check_result="You have been diagnosed with Daibetes 2 as your \n" +
                    "  Hypoglycemia is not in Range";
            db.setCheckDiabetesValue("Your Hypoglycemia is not in Range");
            diabtz.add("Hypoglycemia");
            //return 3;
        }
        if (glucose_pass<6 || glucose_pass>8){
            diabetes_check_result="glocose";
            diabetes_check_result="You have been diagnosed with Daibetes 2 as your \n" +
                    " Glucose is not in Range";
            db.setCheckDiabetesValue("Your Glucose is not in Range");
            diabtz.add("Glucose");
            //return 4;
        }

        Log.i("DIABETES1", "Value of diabetes_check_result "+diabetes_check_result);
        int sz=diabtz.size();
        Log.i("DIABETES1", "Value of Size of array is "+sz);
        if(sz==0){
            Log.i("DIABETES1", "Value of diabetes_check_result in else"+diabetes_check_result);
            Toast.makeText(getApplicationContext(),"Congratulations You are diabetes Free",Toast.LENGTH_LONG).show();
        }
        else{
            Log.i("DIABETES1", "Value of diabetes_check_result in if"+diabetes_check_result);
            Log.i("DIABETES1", "Value of diabetes_check_result in if--"+db.getCheckDiabetesValue());

            Intent intent=new Intent(this,Diabetes2Found.class);
            intent.putStringArrayListExtra("diabresult",diabtz);
            startActivity(intent);
        }

/*        if (!(diabetes_check_result.equalsIgnoreCase("ok"))){
            Log.i("DIABETES1", "Value of diabetes_check_result in if"+diabetes_check_result);
            Log.i("DIABETES1", "Value of diabetes_check_result in if--"+db.getCheckDiabetesValue());

            Intent intent=new Intent(this,Diabetes2Found.class);
            intent.putExtra("diabresult",diabetes_check_result);
            startActivity(intent);
        }
        else {
            Log.i("DIABETES1", "Value of diabetes_check_result in else"+diabetes_check_result);
            //Intent intent=new Intent(this,Diabetes2Found.class);
            //startActivity(intent);
            Toast.makeText(getApplicationContext(),"Congratulations You are diabetes Free",Toast.LENGTH_LONG).show();
        }*/
    }
    public void deletebutton(){
        Log.i("DIABETES1", "delete button clicked");
        //Products product=new Products(editText1.getText().toString(),"abcd",1,2,3,4);
        myDbHandler.deleteProduct("abc");
        Log.i("DIABETES1", "delete button clicked lower");
        //printDatabase();
    }
    public void clearData(View view){
        //editText1.setText("");
        editText2.setText("");
        editText3.setText("");
        editText4.setText("");
        editText5.setText("");
        editText6.setText("");
    }

    public void adddbutton(){
        //Log.i("DIABETES1", "button clicked with value"+editText1.getText().toString());
        Log.i("DIABETES1", "button clicked in add btn");
        if(editText3.getText().toString() != null) {


            Log.i("DIABETES1", "button clicked with value in if 1 "+sx);
            Log.i("DIABETES1", "button clicked with value in if 2 "+editText2.getText().toString());
            Log.i("DIABETES1", "button clicked with value in if 3 "+editText3.getText());
            Log.i("DIABETES1", "button clicked with value in if 3 "+editText3.getText());
            Log.i("DIABETES1", "button clicked with value in if 4 "+editText4.getText());
            Log.i("DIABETES1", "button clicked with value in if 5 "+editText5.getText());
            Log.i("DIABETES1", "button clicked with value in if 6 "+editText6.getText());
            Log.i("DIABETES1", "button clicked with value in if  "+editText.getText().toString());
            Log.i("DIABETES1", "button clicked with image url in DB "+imgDecodableString);

            int bp_pass = Integer.parseInt(editText3.getText().toString());
            int cholestrol_pass = Integer.parseInt(editText4.getText().toString());
            int hypop_pass = Integer.parseInt(editText5.getText().toString());
            int glucose_pass = Integer.parseInt(editText6.getText().toString());
            //imgDecodableString="/storage/sdcard0/Download/images (3).jpg";
            Products product=new Products(sx.toString(),editText2.getText().toString(),bp_pass,cholestrol_pass,hypop_pass,glucose_pass,editText.getText().toString(),imgDecodableString);
            Log.i("DIABETES1", "Returned from Products now calling db handler add prdct");
            myDbHandler.addProduct(product);
            Log.i("DIABETES1", "button clicked lower");


            //printDatabase();
        }
        else
        {
            Log.i("DIABETES1", "button clicked with value in else");
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
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

    @Override
    protected void onDestroy() {
//        Cleanup();
        super.onDestroy();
    }

  /*  private void Cleanup()
    {
        bitmap.recycle();
        System.gc();
        Runtime.getRuntime().gc();
    }*/
}
