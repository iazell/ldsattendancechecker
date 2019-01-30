package lds.bcc.ldsattendancechecker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
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
    SimpleAdapter adapter;
    public static TextView tvresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        Toolbar tb = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(tb);
        final ActionBar ab = getSupportActionBar();

        ab.setTitle("Scan Activity");
        ab.setDisplayShowHomeEnabled(true); // show or hide the default home button
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
        ab.setDisplayShowTitleEnabled(true); // disable the default title element here (for centered title)


        init();
    }


    private void init() {
        lv = findViewById(R.id.listView);

        Button btn = findViewById(R.id.btn);
        tvresult = findViewById(R.id.tvresult);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AttendanceActivity.this, ScanActivity.class);
                startActivity(intent);
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
    public void onBackPressed() {
        super.onBackPressed();
    }
}
