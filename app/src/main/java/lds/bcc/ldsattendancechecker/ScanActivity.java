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

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class ScanActivity extends AppCompatActivity implements ZBarScannerView.ResultHandler {
    private ZBarScannerView mScannerView;
    DatabaseHelper databaseHelper = new DatabaseHelper(this);;
    String exact_time_lifeclass = "13:00:00";
    String exact_time_sol1 = "15:00:00";
    String exact_time_sol2 = "10:00:00";
    int week = 1;
    String[] qrCodeContents = null;
    String level;
    String student_number;
    String student_name;
    String student_nickname;
    String student_birthdate;
    String student_contact;
    String student_leader;
    String student_contactleader;
    String student_network;
    String student_class;
    //camera permission is needed.

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view

        Log.d("hello", "hello");
        level = getIntent().getStringExtra("level");
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

        AlertDialog alertDialog = new AlertDialog.Builder(ScanActivity.this).create();
                alertDialog.setTitle("Code scanned!");
                alertDialog.setMessage(result.getContents());
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(ScanActivity.this, AttendanceActivity.class);
                                startActivity(intent);
                            }
                        });
        alertDialog.show();


//        qrCodeContents = result.getContents().split(",");
//        student_number = qrCodeContents[1];
//        student_name = qrCodeContents[2];
//        student_nickname = qrCodeContents[3];
//        student_birthdate = qrCodeContents[4];
//        student_contact = qrCodeContents[5];
//        student_leader = qrCodeContents[6];
//        student_contactleader = qrCodeContents[7];
//        student_network = qrCodeContents[8];
//        student_class = qrCodeContents[0];
//
//
//        if(student_class.equals("lifeclass")){
//            AlertDialog alertDialog = new AlertDialog.Builder(ScanActivity.this).create();
////            if(databaseHelper.isStudentScanned(student_number, "lifeclass")){
////                alertDialog.setTitle("Stop!");
////                alertDialog.setMessage("Student number " + student_number + " is already scanned.");
////                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
////                        new DialogInterface.OnClickListener() {
////                            public void onClick(DialogInterface dialog, int which) {
////                                Intent intent = new Intent(ScanActivity.this, AttendanceActivity.class);
////                                startActivity(intent);
////                            }
////                        });
////            }else {
//
//                alertDialog.setTitle("Success!");
//                alertDialog.setMessage(student_name + " " + student_number + " is successfully scanned.");
//
//
//                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                    Calendar calendar = Calendar.getInstance();
//                                    SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
//                                    String timestamp = mdformat.format(calendar.getTime());
//                                    String student_status = null;
//                                    try {
//                                        Date exact_time = mdformat.parse(exact_time_lifeclass);
//                                        Date student_time = mdformat.parse(timestamp);
//
//                                        if (student_time.after(exact_time)) {
//                                            student_status = "late";
//                                        } else {
//                                            student_status = "present";
//                                        }
//                                    } catch (ParseException e) {
//                                        e.printStackTrace();
//                                    }
//                                    databaseHelper.addAttendanceLifeclass(new AttendanceLifeclassModel(String.valueOf(week), student_number, student_status, timestamp));
//
//                                Intent intent = new Intent(ScanActivity.this, AttendanceActivity.class);
//                                startActivity(intent);
//
//                            }
//                        });
////            }
//            alertDialog.show();
//        }else if(student_class.equals("sol1")){
//            AlertDialog alertDialog = new AlertDialog.Builder(ScanActivity.this).create();
////            if(!databaseHelper.isStudentScanned(student_number, "lifeclass")){
////                alertDialog.setTitle("Stop!");
////                alertDialog.setMessage("Student number " + student_number + " is already scanned.");
////                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
////                        new DialogInterface.OnClickListener() {
////                            public void onClick(DialogInterface dialog, int which) {
////                                Intent intent = new Intent(ScanActivity.this, AttendanceActivity.class);
////                                startActivity(intent);
////                            }
////                        });
////            }else {
//                alertDialog = new AlertDialog.Builder(ScanActivity.this).create();
//                alertDialog.setTitle("Success!");
//                alertDialog.setMessage(student_name + " " + student_number + " is successfully scanned.");
//                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                Calendar calendar = Calendar.getInstance();
//                                SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
//                                String timestamp = mdformat.format(calendar.getTime());
//                                String student_status = null;
//                                try {
//                                    Date exact_time = mdformat.parse(exact_time_lifeclass);
//                                    Date student_time = mdformat.parse(timestamp);
//
//                                    if (student_time.after(exact_time)) {
//                                        student_status = "late";
//                                    } else {
//                                        student_status = "present";
//                                    }
//                                } catch (ParseException e) {
//                                    e.printStackTrace();
//                                }
//                                databaseHelper.addAttendanceSOL1(new AttendanceSOL1Model(String.valueOf(week), student_number, student_status, timestamp));
//
//                                Intent intent = new Intent(ScanActivity.this, AttendanceActivity.class);
//                                startActivity(intent);
//
//                            }
//                        });
////            }
//                alertDialog.show();
//        }else if(student_class.equals("sol2")){
//            AlertDialog alertDialog = new AlertDialog.Builder(ScanActivity.this).create();
////            if(!databaseHelper.isStudentScanned(student_number, "lifeclass")){
////                alertDialog.setTitle("Stop!");
////                alertDialog.setMessage("Student number " + student_number + " is already scanned.");
////                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
////                        new DialogInterface.OnClickListener() {
////                            public void onClick(DialogInterface dialog, int which) {
////                                Intent intent = new Intent(ScanActivity.this, AttendanceActivity.class);
////                                startActivity(intent);
////                            }
////                        });
////            }else {
//                alertDialog = new AlertDialog.Builder(ScanActivity.this).create();
//                alertDialog.setTitle("Success!");
//                alertDialog.setMessage(student_name + " " + student_number + " is successfully scanned.");
//                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                    Calendar calendar = Calendar.getInstance();
//                                    SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
//                                    String timestamp = mdformat.format(calendar.getTime());
//                                    String student_status = null;
//                                    try {
//                                        Date exact_time = mdformat.parse(exact_time_lifeclass);
//                                        Date student_time = mdformat.parse(timestamp);
//
//                                        if (student_time.after(exact_time)) {
//                                            student_status = "late";
//                                        } else {
//                                            student_status = "present";
//                                        }
//                                    } catch (ParseException e) {
//                                        e.printStackTrace();
//                                    }
//                                    databaseHelper.addAttendanceSOL2(new AttendanceSOL2Model(String.valueOf(week), student_number, student_status, timestamp));
//
//                                Intent intent = new Intent(ScanActivity.this, AttendanceActivity.class);
//                                startActivity(intent);
//
//                            }
//                        });
////            }
//            alertDialog.show();
//        }
    }
}

