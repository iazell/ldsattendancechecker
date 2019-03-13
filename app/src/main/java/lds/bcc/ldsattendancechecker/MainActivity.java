package lds.bcc.ldsattendancechecker;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private List<MenuForm> form;
    ProgressDialog progressDialog;
    DatabaseHelper databaseHelper = new DatabaseHelper(this);

    String admin_lifeclass = "admin_lifeclass";
    String admin_lifeclass_password = "lifeclassadmin2019";

    String admin_sol1 = "admin_sol1";
    String admin_sol1_password = "admin_sol1_2019";

    String admin_sol2 = "admin_sol2";
    String admin_sol2_password = "sol2-admin-2019";


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
        form.add(new MenuForm("Upload student record", R.drawable.syncing));
        form.add(new MenuForm("Outbox", R.drawable.outbox));
        form.add(new MenuForm("Logout", R.drawable.logout_button));

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

                    Log.d("touch", position+"");
                    final String[] input_username = new String[1];
                    final String[] input_password = new String[1];




                    switch (position) {

                        case 0: {
                            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
                            View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                            alertDialogBuilder.setView(promptView);
                            alertDialogBuilder.setTitle("Admin Credentials");

                            final EditText username = (EditText) promptView.findViewById(R.id.username);
                            final EditText password = (EditText) promptView.findViewById(R.id.password);

                            // setup a dialog window
                            alertDialogBuilder.setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            System.out.println(admin_lifeclass);
                                            System.out.println(admin_lifeclass_password);
                                            System.out.println(username.getText().toString());
                                            System.out.println(password.getText().toString());
                                            if (admin_lifeclass.equals(username.getText().toString()) && admin_lifeclass_password.equals(password.getText().toString())) {
                                                System.out.println("heyy");
                                                scan("lifeclass");
                                            } else
                                                Toast.makeText(MainActivity.this, "Wrong credentials!",
                                                        Toast.LENGTH_LONG).show();
                                        }
                                    })
                                    .setNegativeButton("Cancel",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.cancel();
                                                }
                                            });

                            // create an alert dialog
                            AlertDialog alert = alertDialogBuilder.create();
                            alert.show();
                        }
                            break;

                        case 1:{
                            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
                            View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                            alertDialogBuilder.setView(promptView);
                            alertDialogBuilder.setTitle("Admin Credentials");

                            final EditText username = (EditText) promptView.findViewById(R.id.username);
                            final EditText password = (EditText) promptView.findViewById(R.id.password);

                            // setup a dialog window
                            alertDialogBuilder.setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            System.out.println(admin_lifeclass);
                                            System.out.println(admin_lifeclass_password);
                                            System.out.println(username.getText().toString());
                                            System.out.println(password.getText().toString());
                                            if (admin_sol1.equals(username.getText().toString()) && admin_sol1_password.equals(password.getText().toString())) {
                                                System.out.println("heyy");
                                                scan("sol1");
                                            } else
                                                Toast.makeText(MainActivity.this, "Wrong credentials!",
                                                        Toast.LENGTH_LONG).show();
                                        }
                                    })
                                    .setNegativeButton("Cancel",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.cancel();
                                                }
                                            });

                            // create an alert dialog
                            AlertDialog alert = alertDialogBuilder.create();
                            alert.show();
                        }
                            break;

                        case 2:{
                            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
                            View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                            alertDialogBuilder.setView(promptView);
                            alertDialogBuilder.setTitle("Admin Credentials");

                            final EditText username = (EditText) promptView.findViewById(R.id.username);
                            final EditText password = (EditText) promptView.findViewById(R.id.password);

                            // setup a dialog window
                            alertDialogBuilder.setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            System.out.println(admin_lifeclass);
                                            System.out.println(admin_lifeclass_password);
                                            System.out.println(username.getText().toString());
                                            System.out.println(password.getText().toString());
                                            if (admin_sol2.equals(username.getText().toString()) && admin_sol2_password.equals(password.getText().toString())) {
                                                System.out.println("heyy");
                                                scan("sol2");
                                            } else
                                                Toast.makeText(MainActivity.this, "Wrong credentials!",
                                                        Toast.LENGTH_LONG).show();
                                        }
                                    })
                                    .setNegativeButton("Cancel",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.cancel();
                                                }
                                            });

                            // create an alert dialog
                            AlertDialog alert = alertDialogBuilder.create();
                            alert.show();
                        }
                            break;

                        case 3:
                            List<AttendanceLifeclassModel> attendanceLifeclassModel = databaseHelper.getAllAttendanceLifeclass();
                            List<AttendanceSOL1Model> attendanceSOL1Model = databaseHelper.getAllAttendanceSOL1();
                            List<AttendanceSOL2Model> attendanceSOL2Model = databaseHelper.getAllAttendanceSOL2();
                            if(attendanceLifeclassModel!=null || attendanceSOL1Model!=null || attendanceSOL2Model!=null) {
                                sync();
                            }
                            Toast.makeText(MainActivity.this, "No record available.",
                                    Toast.LENGTH_LONG).show();
                            break;

                        case 4:
                            Toast.makeText(MainActivity.this, "This module is not yet available.",
                                    Toast.LENGTH_LONG).show();
                            break;

                        case 5:
                            logout();
                            break;

                        case 6:

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

    private void logout() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

    }

    private void sync() {

            progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(false);
            // Progress dialog horizontal style
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            // Progress dialog title
            progressDialog.setTitle("Student attendance record");
            // Progress dialog message
            progressDialog.setMessage("Please wait, we are downloading this week's attendance record...");
            progressDialog.setProgress(0);
            progressDialog.show();

            final int totalProgressTime = 100;
            final Thread t = new Thread() {
                @Override
                public void run() {
                    int jumpTime = 0;

                    while(jumpTime < totalProgressTime) {
                        try {
                            Map<String, String> attendance = new HashMap<>();
                            List<AttendanceLifeclassModel> attendanceLifeclassModel = databaseHelper.getAllAttendanceLifeclass();
                            if(attendanceLifeclassModel!=null) {
                                for (AttendanceLifeclassModel cm : attendanceLifeclassModel) {
                                    Log.d("post", cm.getClass_week() + "");
                                    Log.d("post", cm.getStudent_number() + "");
                                    Log.d("post", cm.getStudent_status() + "");
                                    attendance.put("class_week", cm.getClass_week());
                                    attendance.put("student_number", cm.getStudent_number());
                                    attendance.put("student_status", cm.getStudent_status());

//                                HttpPostAsyncTask task = new HttpPostAsyncTask(attendance);
//                                task.execute(url + "/postattendancelifeclass/");
                                    sleep(200);
                                    jumpTime += 5;

                                }
                                progressDialog.setProgress(jumpTime);

                                if (jumpTime == 100) {
                                    progressDialog.dismiss();
                                }
                            }
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                            alertDialog.setTitle("Nope!");
                            alertDialog.setMessage("Can't connect server.");
                            alertDialog.show();
                        }
                    }
                }
            };
            t.start();


    }


    private void scan(String level) {
        Intent i = new Intent(MainActivity.this, AttendanceActivity.class);
        i.putExtra("level", level);
        startActivity(i);
        finish();

    }
}
