package lds.bcc.ldsattendancechecker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class ScanActivity extends AppCompatActivity implements ZBarScannerView.ResultHandler {
    private ZBarScannerView mScannerView;
    DatabaseHelper databaseHelper = new DatabaseHelper(this);;
    String exact_time_lifeclass = "13:00:00";
    String exact_time_sol1 = "15:00:00";
    String exact_time_sol2 = "10:00:00";
    int week = 1;
    String qrCodeContents[];
    String student_number;
    String student_name;
    String student_nickname;
    String student_leader;
    String student_network;

    public String url = "http://bcc.pythonanywhere.com/";

    String level;
    //camera permission is needed.

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view


        level = getIntent().getStringExtra("level");
        Log.d("hello", level);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(final Result result) {
        // Do something with the result here
        Log.v("kkkk", result.getContents()); // Prints scan results
        Log.v("uuuu", result.getBarcodeFormat().getName()); // Prints the scan format (qrcode, pdf417 etc.)
        Log.d("resulteee: ", result.getContents() + "");

        qrCodeContents = result.getContents().split(",");
        student_number = qrCodeContents[0];
        student_name = qrCodeContents[1];
        student_nickname = qrCodeContents[2];
        student_leader = qrCodeContents[3];
        student_network = qrCodeContents[4];

        Log.d("hey", student_number + student_name + student_nickname + student_leader + student_network + "");

        if(level.equals("lifeclass")) {
            if(databaseHelper.isStudentScanned(student_number, level)) {
                AlertDialog alertDialog = new AlertDialog.Builder(ScanActivity.this).create();
                alertDialog.setTitle("Nope!");
                alertDialog.setMessage(student_number + " " + student_name + " is already scanned.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(ScanActivity.this, AttendanceActivity.class);
                                startActivity(intent);

                            }
                        });
                alertDialog.show();
            }else {
                AlertDialog alertDialog = new AlertDialog.Builder(ScanActivity.this).create();
                alertDialog.setTitle("Success!");
                alertDialog.setMessage(student_number + " " + student_name + " is successfully scanned.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Calendar calendar = Calendar.getInstance();
                                SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
                                String timestamp = mdformat.format(calendar.getTime());
                                String student_status = null;
                                try {
                                    Date exact_time = mdformat.parse(exact_time_lifeclass);
                                    Date student_time = mdformat.parse(timestamp);

                                    if (student_time.after(exact_time)) {
                                        student_status = "late";
                                    } else {
                                        student_status = "present";
                                    }
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                Log.e("name", student_name);
                                Log.d("hello", "" + String.valueOf(week) + student_number + student_status + timestamp + student_name + student_nickname + student_leader + student_network);
                                databaseHelper.addAttendanceLifeclass(new AttendanceLifeclassModel(String.valueOf(week), student_number,
                                        student_status, timestamp, student_name, student_nickname, student_leader, student_network));
                                Map<String, String> attendance = new HashMap<>();
                                attendance.put("class_week", String.valueOf(week));
                                attendance.put("student_number", student_number);
                                attendance.put("student_status", student_status);

                                HttpPostAsyncTask task = new HttpPostAsyncTask(attendance);
                                task.execute(url + "/postattendancelifeclass/");

                                Intent intent = new Intent(ScanActivity.this, AttendanceActivity.class);
                                startActivity(intent);

                            }
                        });
                alertDialog.show();
            }
        }else if(level.equals("sol1")){
            if(databaseHelper.isStudentScanned(student_number, level)) {
                AlertDialog alertDialog = new AlertDialog.Builder(ScanActivity.this).create();
                alertDialog.setTitle("Nope!");
                alertDialog.setMessage(student_number + " " + student_name + " is already scanned.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(ScanActivity.this, AttendanceActivity.class);
                                startActivity(intent);

                            }
                        });
                alertDialog.show();
            }else {
                AlertDialog alertDialog = new AlertDialog.Builder(ScanActivity.this).create();
                alertDialog.setTitle("Success!");
                alertDialog.setMessage(student_number + " " + student_name + " is successfully scanned.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Calendar calendar = Calendar.getInstance();
                                SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
                                String timestamp = mdformat.format(calendar.getTime());
                                String student_status = null;
                                try {
                                    Date exact_time = mdformat.parse(exact_time_sol1);
                                    Date student_time = mdformat.parse(timestamp);

                                    if (student_time.after(exact_time)) {
                                        student_status = "late";
                                    } else {
                                        student_status = "present";
                                    }
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                Log.e("name", student_name);
                                Log.d("hello", "" + String.valueOf(week) + student_number + student_status + timestamp + student_name + student_nickname + student_leader + student_network);
                                databaseHelper.addAttendanceSOL1(new AttendanceSOL1Model(String.valueOf(week), student_number,
                                        student_status, timestamp, student_name, student_nickname, student_leader, student_network));

                                Map<String, String> attendance = new HashMap<>();
                                attendance.put("class_week", String.valueOf(week));
                                attendance.put("student_number", student_number);
                                attendance.put("student_status", student_status);

                                HttpPostAsyncTask task = new HttpPostAsyncTask(attendance);
                                task.execute(url + "/postattendancesol1/");

                                Intent intent = new Intent(ScanActivity.this, AttendanceActivity.class);
                                startActivity(intent);

                            }
                        });
                alertDialog.show();
            }
        }else if(level.equals("sol2")){
            if(databaseHelper.isStudentScanned(student_number, level)) {
                AlertDialog alertDialog = new AlertDialog.Builder(ScanActivity.this).create();
                alertDialog.setTitle("Nope!");
                alertDialog.setMessage(student_number + " " + student_name + " is already scanned.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(ScanActivity.this, AttendanceActivity.class);
                                startActivity(intent);

                            }
                        });
                alertDialog.show();
            }else {
                AlertDialog alertDialog = new AlertDialog.Builder(ScanActivity.this).create();
                alertDialog.setTitle("Success!");
                alertDialog.setMessage(student_number + " " + student_name + " is successfully scanned.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Calendar calendar = Calendar.getInstance();
                                SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
                                String timestamp = mdformat.format(calendar.getTime());
                                String student_status = null;
                                try {
                                    Date exact_time = mdformat.parse(exact_time_sol2);
                                    Date student_time = mdformat.parse(timestamp);

                                    if (student_time.after(exact_time)) {
                                        student_status = "late";
                                    } else {
                                        student_status = "present";
                                    }
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                Log.e("name", student_name);
                                Log.d("hello", "" + String.valueOf(week) + student_number + student_status + timestamp + student_name + student_nickname + student_leader + student_network);
                                databaseHelper.addAttendanceSOL2(new AttendanceSOL2Model(String.valueOf(week), student_number,
                                        student_status, timestamp, student_name, student_nickname, student_leader, student_network));
                                Map<String, String> attendance = new HashMap<>();
                                attendance.put("class_week", String.valueOf(week));
                                attendance.put("student_number", student_number);
                                attendance.put("student_status", student_status);

                                HttpPostAsyncTask task = new HttpPostAsyncTask(attendance);
                                task.execute(url + "/postattendancesol2/");

                                Intent intent = new Intent(ScanActivity.this, AttendanceActivity.class);
                                startActivity(intent);

                            }
                        });
                alertDialog.show();
            }
        }
    }
}

