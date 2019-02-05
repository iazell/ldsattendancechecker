package lds.bcc.ldsattendancechecker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import java.util.HashMap;
import java.util.List;

public class AttendanceActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    List<AttendanceLifeclassModel> attendanceLifeclassModel;
    List<StudentModel> studentModel;
    ArrayList<DataModel> dataModels;
    ListView lv;
    public static SimpleAdapter adapter;
    public static CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        init();
    }

    private void init() {
        lv=findViewById(R.id.list);
        databaseHelper = new DatabaseHelper(this);
        attendanceLifeclassModel= databaseHelper.getAllAttendanceLifeclass();

        dataModels= new ArrayList<>();
        String status = null;
        for (AttendanceLifeclassModel cm : attendanceLifeclassModel) {
            Log.d("view", cm.getClass_week()+"");
            Log.d("view", cm.getStudent_number()+"");
            Log.d("view", cm.getStudent_status()+"");
            Log.d("view", cm.getStudent_time()+"");

            if(cm.getStudent_status().equals("present"))
                status = "On-Time";
            else if(cm.getStudent_status().equals("late"))
                status = "Late";

            dataModels.add(new DataModel(status, cm.getStudent_time(), cm.getStudent_number(), cm.getClass_week()));
        }
        customAdapter= new CustomAdapter(dataModels,getApplicationContext());
        lv.setAdapter(customAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AttendanceActivity.this, ScanActivity.class);
                startActivity(intent);
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

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(AttendanceActivity.this, MainActivity.class);
        startActivity(intent);
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AttendanceActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
