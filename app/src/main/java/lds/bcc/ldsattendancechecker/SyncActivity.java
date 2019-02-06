package lds.bcc.ldsattendancechecker;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Base64;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


class SyncActivity  extends AsyncTask<String, String, String> {

    boolean warning_indicator = true;
    Context serviceContext;
    ProgressDialog progressDialog;
    WebRequest wr = new WebRequest();
    private SQLiteDatabase database;
    private DatabaseHelper db;


    public SyncActivity(Context context) {
        this.serviceContext = context;
        db = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = db.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public boolean isOpen() {
        return database.isOpen();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(serviceContext);
        progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Sync Student details");
        progressDialog.setMessage("Downloading Students");
        progressDialog.setCancelable(false);
        progressDialog.show();


    }


    protected void onProgressUpdate(String... progUpdate) {

        int max = Integer.parseInt(progUpdate[0]), prog = Integer.parseInt(progUpdate[1]);

        progressDialog.setIndeterminate(false);
        progressDialog.setMax(100);
        progressDialog.setProgress((int) (prog * 100 / max));


        progressDialog.setMessage("Downloading students " + ((int) (prog * 100 / max) + "%"));

    }


    @Override
    protected String doInBackground(String... params) {
        String json = "";
        try {
            if (!isCancelled()) {
                warning_indicator = true;
                String address = params[0];

                JSONArray students = new JSONArray(wr.makeWebServiceCall(address, WebRequest.GET));

                db.deleteStudents();

                for (int i = 0; i < students.length(); i++) {
                    JSONObject c = students.getJSONObject(i);

                    saveStudents(
                            c.getString("student_number"),
                            c.getString("student_name"),
                            c.getString("student_nickname"),
                            c.getString("student_birthdate"),
                            c.getString("student_contact"),
                            c.getString("student_leader"),
                            c.getString("student_contactleader"),
                            c.getString("student_network"),
                            c.getString("student_level"));


                    publishProgress(String.format("%d", students.length())
                            , String.format("%d", i));

                }

            }


        } catch (Exception e) {
            e.printStackTrace();
            warning_indicator = false;
        }

        return json;
    }


    @Override
    protected void onPostExecute(String strFromDoInBg) {

        String message = warning_indicator ? "Synchronization Successful" : "Can't Access Server!";
        progressDialog.dismiss();
        AlertDialog.Builder alert = new AlertDialog.Builder(serviceContext);
        alert.setTitle("Students");
        alert.setMessage(message);

        alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
            }
        });

        alert.show();
    }


    private void saveStudents(String student_number, String student_name, String student_nickname,
                              String student_birthdate, String student_contact, String student_leader, String student_contactleader,
                             String student_network, String student_level) {

        db.addStudents(new StudentModel(student_number, student_name, student_nickname, student_birthdate
                , student_contact, student_leader, student_contactleader, student_network, student_level));
    }

}

