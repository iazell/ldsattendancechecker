package lds.bcc.ldsattendancechecker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<MenuForm> form;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar tb = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(tb);
        final ActionBar ab = getSupportActionBar();
        ab.setLogo(R.drawable.ic_launcher_background);
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
        form.add(new MenuForm("Lifeclass", R.drawable.classroom));
        form.add(new MenuForm("School of Leaders 1", R.drawable.group));
        form.add(new MenuForm("School of Leaders 2", R.drawable.team));
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
        SyncActivity task = new SyncActivity(MainActivity.this);
        task.execute("http://192.168.116:8000/getstudents/");
    }


    private void scan(String level) {
        Intent i = new Intent(MainActivity.this, AttendanceActivity.class);
        i.putExtra("level", level);
        startActivity(i);
        finish();

    }
}
