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

    DatabaseHelper db = new DatabaseHelper(this);
    DatabaseStudent dbClient = new DatabaseStudent(this);

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
        form.add(new MenuForm("Lifeclass Men", R.drawable.common_google_signin_btn_icon_dark_focused));
        form.add(new MenuForm("Lifeclass Women", R.drawable.common_full_open_on_phone));
        form.add(new MenuForm("SOL1 Men", R.drawable.common_full_open_on_phone));
        form.add(new MenuForm("SOL1 Women", R.drawable.common_full_open_on_phone));
        form.add(new MenuForm("SOL2 Men", R.drawable.common_full_open_on_phone));
        form.add(new MenuForm("SOL2 Women", R.drawable.common_full_open_on_phone));
        form.add(new MenuForm("Sync", R.drawable.common_full_open_on_phone));


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
                            scan();
                            break;

                        case 1:
                            scan();
                            break;

                        case 2:
                            scan();
                            break;

                        case 3:
                            scan();
                            break;

                        case 4:
                            scan();
                            break;

                        case 5:
                            scan();
                            break;

                        case 6:
                            scan();
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

        Intent i = new Intent(MainActivity.this, SyncActivity.class);
        startActivity(i);


    }


    private void scan() {
        Intent i = new Intent(MainActivity.this, AttendanceActivity.class);
        startActivity(i);
        finish();

    }
}
