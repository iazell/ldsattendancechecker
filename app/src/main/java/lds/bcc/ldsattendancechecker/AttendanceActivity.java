package lds.bcc.ldsattendancechecker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;

public class AttendanceActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    List<AttendanceLifeclassModel> attendanceLifeclassModel;
    List<AttendanceSOL1Model> attendanceSOL1Model;
    List<AttendanceSOL2Model> attendanceSOL2Model;
    List<StudentModel> studentModel;
    ArrayList<DataModel> dataModels;
    ListView lv;
    public static SimpleAdapter adapter;
    public static CustomAdapter customAdapter;
    String level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        level = getIntent().getStringExtra("level");

        init();
    }

    private void init() {
        lv=findViewById(R.id.list);
        databaseHelper = new DatabaseHelper(this);


        if(level.equals("lifeclass")){
            attendanceLifeclassModel= databaseHelper.getAllAttendanceLifeclass();
            dataModels= new ArrayList<>();
            String status = null;
            for (AttendanceLifeclassModel cm : attendanceLifeclassModel) {
                Log.d("lifeclass", cm.getClass_week()+"");
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
        }else if(level.equals("sol1")){
            attendanceSOL1Model= databaseHelper.getAllAttendanceSOL1();
            dataModels= new ArrayList<>();
            String status = null;
            for (AttendanceSOL1Model cm : attendanceSOL1Model) {
                Log.d("sol1", cm.getClass_week()+"");
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
        }else if(level.equals("sol2")){
            attendanceSOL2Model= databaseHelper.getAllAttendanceSOL2();
            dataModels= new ArrayList<>();
            String status = null;
            for (AttendanceSOL2Model cm : attendanceSOL2Model) {
                Log.d("sol2", cm.getClass_week()+"");
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
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AttendanceActivity.this, ScanActivity.class);
                intent.putExtra("level", level);
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
            System.out.println("hello");
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
