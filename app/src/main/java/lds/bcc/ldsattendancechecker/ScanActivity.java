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
    String qrCodeContents = null;
    String student_number = null;
    //camera permission is needed.

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view

        Log.d("hello", "hello");
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
        qrCodeContents = result.getContents();
        if(qrCodeContents.contains("lifeclass")) {
            student_number = qrCodeContents.replace("lifeclass", "");
        }else if(qrCodeContents.contains("sol1")){
            student_number = qrCodeContents.replace("sol1", "");
        }else if(qrCodeContents.contains("sol1")){
            student_number = qrCodeContents.replace("sol2", "");
        }

        AlertDialog alertDialog = new AlertDialog.Builder(ScanActivity.this).create();
        alertDialog.setTitle("Success!");
        alertDialog.setMessage("Student number " + student_number + " is successfully scanned.");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(qrCodeContents.contains("lifeclass")){
                            String student_number = qrCodeContents.replace("lifeclass", "");
                            Calendar calendar = Calendar.getInstance();
                            SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
                            String timestamp = mdformat.format(calendar.getTime());
                            String student_status = null;
                            try {
                                Date exact_time = mdformat.parse(exact_time_lifeclass);
                                Date student_time = mdformat.parse(timestamp);

                                if(student_time.after(exact_time)){
                                    student_status = "late";
                                }else{
                                    student_status = "present";
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            databaseHelper.addAttendanceLifeclass(new AttendanceLifeclassModel(String.valueOf(week), student_number, student_status, timestamp));
                        }else if(qrCodeContents.contains("sol1")){

                        }else if(qrCodeContents.contains("sol1")){

                        }

                        Intent intent = new Intent(ScanActivity.this, AttendanceActivity.class);
                        startActivity(intent);

                    }
                });
        alertDialog.show();
    }
}

