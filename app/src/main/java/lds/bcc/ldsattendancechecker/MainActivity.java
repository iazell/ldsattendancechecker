package lds.bcc.ldsattendancechecker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private List<MenuForm> form;
    ProgressDialog progressDialog;
    DatabaseHelper databaseHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar tb = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(tb);
        final ActionBar ab = getSupportActionBar();
        ab.setLogo(R.drawable.rsz_bcc);
        ab.setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
        ab.setDisplayShowTitleEnabled(true); // disable the default title element here (for centered title)
        init();
    }

    private void init() {

        RecyclerView recyclerView = findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        form = new ArrayList<>();
        form.add(new MenuForm("Lifeclass", R.drawable.classroom)); //1ebe54
        form.add(new MenuForm("School of Leaders 1", R.drawable.group)); //e52d27
        form.add(new MenuForm("School of Leaders 2", R.drawable.team)); //8600ef
        form.add(new MenuForm("Sync student details", R.drawable.sync));
        form.add(new MenuForm("Send mass message", R.drawable.message));
        form.add(new MenuForm("Call Cell Leader", R.drawable.call));

        RecyclerView.Adapter adapter = new DataAdapter(form);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);


                    switch (position) {

                        case 0:
                            scan("lifeclass");
                            break;

                        case 1:
                            scan("sol1");
                            break;

                        case 2:
                            scan("sol2");
                            break;

                        case 3:
                            sync();
                            break;

                        case 4:
                            scan("");
                            break;

                        case 5:
                            scan("");
                            break;

                        case 6:
                            scan("");
                            break;

                        case 7:
                            sync();
                            break;

                        default:
                    }


                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });


    }

    private void sync() {
        String url = "http://192.168.254.108:8000";
        Log.d("url", url);

        List<AttendanceLifeclassModel> attendanceLifeclassModel = databaseHelper.getAllAttendanceLifeclass();
        if (attendanceLifeclassModel != null) {
            Map<String, String> attendance = new HashMap<>();
            Map<String, String> post_attendance = new HashMap<>();
            for (AttendanceLifeclassModel cm : attendanceLifeclassModel) {
                Log.d("post", cm.getClass_week()+"");
                Log.d("post", cm.getStudent_number()+"");
                Log.d("post", cm.getStudent_status()+"");
                attendance.put("class_week", cm.getClass_week());
                attendance.put("student_number", cm.getStudent_number());
                attendance.put("student_status", cm.getStudent_status());

                post_attendance.putAll(attendance);
            }

            HttpPostAsyncTask task = new HttpPostAsyncTask(post_attendance);
            task.execute(url + "/postattendancelifeclass/");
        }

//
//        Log.d("url", url);
//        List<AttendanceSOL1Model> attendanceSOL1Model = databaseHelper.getAllAttendanceSOL1();
//        if (attendanceSOL1Model != null) {
//            Map<String, String> attendanceSOL1 = new HashMap<>();
//            for (AttendanceLifeclassModel cm : attendanceLifeclassModel) {
//                Log.d("post", cm.getClass_week()+"");
//                Log.d("post", cm.getStudent_number()+"");
//                Log.d("post", cm.getStudent_status()+"");
//                attendanceSOL1.put("class_week", cm.getClass_week());
//                attendanceSOL1.put("student_number", cm.getStudent_number());
//                attendanceSOL1.put("student_status", cm.getStudent_status());
//            }
//            HttpPostAsyncTask taskSOL1 = new HttpPostAsyncTask(attendanceSOL1);
//            taskSOL1.execute(url + "/postattendancesol1/");
//        }
//
//
//        Log.d("url", url);
//        List<AttendanceSOL2Model> attendanceSOL2Model = databaseHelper.getAllAttendanceSOL2();
//        if (attendanceSOL2Model != null) {
//            Map<String, String> attendanceSOL2 = new HashMap<>();
//            for (AttendanceLifeclassModel cm : attendanceLifeclassModel) {
//                Log.d("post", cm.getClass_week()+"");
//                Log.d("post", cm.getStudent_number()+"");
//                Log.d("post", cm.getStudent_status()+"");
//                attendanceSOL2.put("class_week", cm.getClass_week());
//                attendanceSOL2.put("student_number", cm.getStudent_number());
//                attendanceSOL2.put("student_status", cm.getStudent_status());
//            }
//            HttpPostAsyncTask taskSOL2 = new HttpPostAsyncTask(attendanceSOL2);
//            taskSOL2.execute(url + "/postattendancesol2/");
//        }

    }


    private void scan(String level) {
        Intent i = new Intent(MainActivity.this, AttendanceActivity.class);
        i.putExtra("level", level);
        startActivity(i);
        finish();

    }
}
