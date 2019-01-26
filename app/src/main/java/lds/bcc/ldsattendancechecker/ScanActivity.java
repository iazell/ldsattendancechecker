package lds.bcc.ldsattendancechecker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class ScanActivity extends AppCompatActivity implements ZBarScannerView.ResultHandler {
    private ZBarScannerView mScannerView;
    ListView lv;
    List form;
    SimpleAdapter adapter;

    //camera permission is needed.

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
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
    public void handleResult(me.dm7.barcodescanner.zbar.Result result) {
        // Do something with the result here
        Log.v("kkkk", result.getContents()); // Prints scan results
        Log.v("uuuu", result.getBarcodeFormat().getName()); // Prints the scan format (qrcode, pdf417 etc.)

        lv = findViewById(R.id.listView);
        String[] columns = new String[]{"student_number", "student_name",
                "student_nickname", "student_birthdate", "student_contact",
                "student_leader", "student_contactleader", "student_network",
                "student_class"};

        int[] to = new int[]{R.id.student_number, R.id.student_name,
                R.id.student_nickname, R.id.student_birthdate, R.id.student_contact,
                R.id.student_leader, R.id.student_contactleader, R.id.student_network,
                R.id.student_class};

//
//        final ArrayList<HashMap<String, String>> mClient = new ArrayList<>();
//
//        for (ClientModel cm : form) {
//            HashMap<String, String> clients = new HashMap<>();
//            clients.put("clientCode", cm.getCode());
//            clients.put("clientName", cm.getTrade_name());
//            clients.put("clientAddress", cm.getAddress());
//
//            mClient.add(clients);
//        }


        BreakIterator boundary = BreakIterator.getCharacterInstance();
        final ArrayList<HashMap<String, String>> studentMap = new ArrayList<>();
        String text = result.getContents();
        HashMap<String, String> qrCodeResult = new HashMap<>();
        boundary.setText(text);
        int start = boundary.first();
        for (int end = boundary.next(); end != BreakIterator.DONE; end = boundary.next()) {
            System.out.println(start + " " + text.substring(start, end));
            qrCodeResult.put(columns[start], text.substring(start, end));
            studentMap.add(qrCodeResult);
            start = end;
        }

        adapter = new SimpleAdapter(this,
                studentMap, R.layout.student_card, columns, to);


        lv.setAdapter(adapter);
        onBackPressed();

        // If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);
    }
}

