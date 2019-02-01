package lds.bcc.ldsattendancechecker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AttendanceActivity extends AppCompatActivity {

    static String qrCodeResult;
    DatabaseStudent db = new DatabaseStudent(this);
    ListView lv;
    List form;
    public static TextView tvresult;
    ArrayList<DataModel> dataModels;
    ListView listView;
    private static CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        Toolbar tb = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(tb);
//        final ActionBar ab = getSupportActionBar();
//
//        ab.setTitle("Scan Activity");
//        ab.setDisplayShowHomeEnabled(true); // show or hide the default home button
//        ab.setDisplayHomeAsUpEnabled(true);
//        ab.setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
//        ab.setDisplayShowTitleEnabled(true); // disable the default title element here (for centered title)


        init();
    }


    private void init() {
        lv = findViewById(R.id.listView);

        Button btn = findViewById(R.id.btn);
        tvresult = findViewById(R.id.tvResult);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AttendanceActivity.this, ScanActivity.class);
                startActivity(intent);

                listView=(ListView)findViewById(R.id.list);


                dataModels= new ArrayList<>();

                dataModels.add(new DataModel("Apple Pie", "Android 1.0", "1","September 23, 2008"));
                dataModels.add(new DataModel("Banana Bread", "Android 1.1", "2","February 9, 2009"));
                dataModels.add(new DataModel("Cupcake", "Android 1.5", "3","April 27, 2009"));
                dataModels.add(new DataModel("Donut","Android 1.6","4","September 15, 2009"));
                dataModels.add(new DataModel("Eclair", "Android 2.0", "5","October 26, 2009"));
                dataModels.add(new DataModel("Froyo", "Android 2.2", "8","May 20, 2010"));
                dataModels.add(new DataModel("Gingerbread", "Android 2.3", "9","December 6, 2010"));
                dataModels.add(new DataModel("Honeycomb","Android 3.0","11","February 22, 2011"));
                dataModels.add(new DataModel("Ice Cream Sandwich", "Android 4.0", "14","October 18, 2011"));
                dataModels.add(new DataModel("Jelly Bean", "Android 4.2", "16","July 9, 2012"));
                dataModels.add(new DataModel("Kitkat", "Android 4.4", "19","October 31, 2013"));
                dataModels.add(new DataModel("Lollipop","Android 5.0","21","November 12, 2014"));
                dataModels.add(new DataModel("Marshmallow", "Android 6.0", "23","October 5, 2015"));

                adapter= new CustomAdapter(dataModels,getApplicationContext());

                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        DataModel dataModel= dataModels.get(position);

                        Snackbar.make(view, dataModel.getName()+"\n"+dataModel.getType()+" API: "+dataModel.getVersion_number(), Snackbar.LENGTH_LONG)
                                .setAction("No action", null).show();
                    }
                });
            }
        });


//        String[] columns = new String[]{"student_number", "student_name",
//                "student_nickname", "student_birthdate", "student_contact",
//                "student_leader", "student_contactleader", "student_network",
//                "student_class"};
//
//        int[] to = new int[]{R.id.student_number, R.id.student_name,
//                R.id.student_nickname, R.id.student_birthdate, R.id.student_contact,
//                R.id.student_leader, R.id.student_contactleader, R.id.student_network,
//                R.id.student_class};

//        System.out.println("resultcontents: " + qrCodeResult);
//        /** Items entered by the user is stored in this ArrayList variable */
//        ArrayList<String> list = new ArrayList<String>();
//
//        /** Declaring an ArrayAdapter to set items to ListView */
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.student_card, list);
//        list.add(qrCodeResult);
//        lv.setAdapter(adapter);

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
