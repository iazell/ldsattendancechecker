package lds.bcc.ldsattendancechecker;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    //database version
    private static final int DATABASE_VERSION = 1;
    ///database name
    private static final String DATABASE_NAME = "ldstracker";

    ///table name
    private static final String tbl_students = "tbl_students";

    ///columns...
    private static final String student_number = "student_number";
    private static final String student_name = "student_name";
    private static final String student_nickname = "student_nickname";
    private static final String student_birthdate = "student_birthdate";
    private static final String student_contact = "student_contact";
    private static final String student_leader = "student_leader";
    private static final String student_contactleader = "student_contactleader";
    private static final String student_network = "student_network";
    private static final String student_class = "student_class";

    private static final String tbl_attendance_lifeclass = "tbl_attendance_lifeclass";
    ///columns...
    public static final String id = "id";
    public static final String class_week = "class_week";
    public static final String student_num = "student_number";
    public static final String student_status = "student_status";
    public static final String student_time = "student_time";

    private static final String tbl_attendance_SOL1 = "tbl_attendance_SOL1";
    ///columns...
    public static final String id_SOL1 = "id";
    public static final String class_week_SOL1 = "class_week";
    public static final String student_number_SOL1 = "student_number";
    public static final String student_status_SOL1 = "student_status";
    public static final String student_time_SOL1 = "student_time";

    private static final String tbl_attendance_SOL2 = "tbl_attendance_SOL1";
    ///columns...
    public static final String id_SOL2 = "id";
    public static final String class_week_SOL2 = "class_week";
    public static final String student_number_SOL2 = "student_number";
    public static final String student_status_SOL2 = "student_status";
    public static final String student_time_SOL2 = "student_time";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_STUDENTS_TABLE = "CREATE TABLE IF NOT EXISTS " + tbl_students + "("
                + student_number + " INTEGER PRIMARY KEY , "
                + student_name + " TEXT ,"
                + student_nickname + " TEXT ,"
                + student_birthdate + " TEXT ,"
                + student_contact + " TEXT ,"
                + student_leader + " TEXT ,"
                + student_contactleader + " TEXT ,"
                + student_network + " TEXT, "
                + student_class + " TEXT)";

        Log.e("sql query", CREATE_STUDENTS_TABLE);
        db.execSQL(CREATE_STUDENTS_TABLE);

        String CREATE_ATTENDANCE_TABLE = "CREATE TABLE IF NOT EXISTS " + tbl_attendance_lifeclass + "("
                + id + " INTEGER PRIMARY KEY , "
                + class_week + " TEXT ,"
                + student_num + " TEXT ,"
                + student_status + " TEXT ,"
                + student_time+ " TEXT  )";
        db.execSQL(CREATE_ATTENDANCE_TABLE);

        Log.e("sql query", CREATE_ATTENDANCE_TABLE);

        String CREATE_ATTENDANCE_SOL1_TABLE = "CREATE TABLE IF NOT EXISTS " + tbl_attendance_SOL1 + "("
                + id + " INTEGER PRIMARY KEY , "
                + class_week + " TEXT ,"
                + student_num + " TEXT ,"
                + student_status + " TEXT ,"
                + student_time+ " TEXT  )";
        db.execSQL(CREATE_ATTENDANCE_TABLE);

        Log.e("sql query", CREATE_ATTENDANCE_SOL1_TABLE);

        String CREATE_ATTENDANCE_SOL2_TABLE = "CREATE TABLE IF NOT EXISTS " + tbl_attendance_SOL2 + "("
                + id + " INTEGER PRIMARY KEY , "
                + class_week + " TEXT ,"
                + student_num + " TEXT ,"
                + student_status + " TEXT ,"
                + student_time+ " TEXT  )";
        db.execSQL(CREATE_ATTENDANCE_TABLE);

        Log.e("sql query", CREATE_ATTENDANCE_SOL2_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + tbl_students);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + tbl_attendance_lifeclass);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + tbl_attendance_SOL1);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + tbl_attendance_SOL2);
        onCreate(db);
    }

    public void addStudents(StudentModel studentModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(student_number, studentModel.getStudent_number());
        values.put(student_name, studentModel.getStudent_name());
        values.put(student_nickname, studentModel.getStudent_nickname());
        values.put(student_birthdate, studentModel.getStudent_birthdate());
        values.put(student_contact, studentModel.getStudent_contact());
        values.put(student_leader, studentModel.getStudent_leader());
        values.put(student_contactleader, studentModel.getStudent_contactleader());
        values.put(student_network, studentModel.getStudent_network());
        values.put(student_class, studentModel.getStudent_class());

        // Inserting Row
        db.insert(tbl_students, null, values);
        db.close(); // Closing database connection
    }

    public void deleteStudents() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tbl_students, null, null);
        db.close();
    }

    public void updateStudents(StudentModel studentModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(student_number, studentModel.getStudent_number());
        values.put(student_name, studentModel.getStudent_name());
        values.put(student_nickname, studentModel.getStudent_nickname());
        values.put(student_birthdate, studentModel.getStudent_birthdate());
        values.put(student_contact, studentModel.getStudent_contact());
        values.put(student_leader, studentModel.getStudent_leader());
        values.put(student_contactleader, studentModel.getStudent_contactleader());
        values.put(student_network, studentModel.getStudent_network());
        values.put(student_class, studentModel.getStudent_class());

        db.update(tbl_students, values, "student_number= '" + student_number + "'", null);
        db.close(); // Closing database connection
    }

    public List<StudentModel> getAllStudents() {
        List<StudentModel> contentList = new ArrayList<StudentModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + tbl_students;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                StudentModel students = new StudentModel();
                students.setStudent_number(cursor.getString(1));
                students.setStudent_name(cursor.getString(2));
                students.setStudent_nickname(cursor.getString(3));
                students.setStudent_birthdate(cursor.getString(4));
                students.setStudent_birthdate(cursor.getString(4));
                students.setStudent_leader(cursor.getString(6));
                students.setStudent_contactleader(cursor.getString(7));
                students.setStudent_network(cursor.getString(8));
                students.setStudent_class(cursor.getString(9));

                contentList.add(students);
            } while (cursor.moveToNext());
        }
        // return quote list
        return contentList;
    }

    public void addAttendanceLifeclass(AttendanceLifeclassModel attendanceLifeclassModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(class_week, attendanceLifeclassModel.getClass_week());
        values.put(student_number, attendanceLifeclassModel.getStudent_number());
        values.put(student_status, attendanceLifeclassModel.getStudent_status());
        values.put(student_time, attendanceLifeclassModel.getStudent_time());

        Log.d("data", (String) values.get(class_week));
        Log.d("data", (String) values.get(student_number));
        Log.d("data", (String) values.get(student_status));
        Log.d("data", (String) values.get(student_time));


        // Inserting Row
        db.insert(tbl_attendance_lifeclass, null, values);
        db.close(); // Closing database connection
    }

    public void deleteAttendanceLifeclass() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tbl_attendance_lifeclass, null, null);
        db.close();
    }

    public void updateAttendanceLifeclass(AttendanceLifeclassModel attendanceLifeclassModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(class_week, attendanceLifeclassModel.getClass_week());
        values.put(student_number, attendanceLifeclassModel.getStudent_number());
        values.put(student_status, attendanceLifeclassModel.getStudent_status());
        values.put(student_status, attendanceLifeclassModel.getStudent_time());

        db.update(tbl_attendance_lifeclass, values, "student_number= '" + student_number + "'", null);
        db.close(); // Closing database connection
    }

    public List<AttendanceLifeclassModel> getAllAttendanceLifeclass() {
        List<AttendanceLifeclassModel> contentList = new ArrayList<AttendanceLifeclassModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + tbl_attendance_lifeclass +  " ORDER BY " + id + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AttendanceLifeclassModel attendance = new AttendanceLifeclassModel();
                attendance.setClass_week(cursor.getString(1));
                attendance.setStudent_number(cursor.getString(2));
                attendance.setStudent_status(cursor.getString(3));
                attendance.setStudent_time(cursor.getString(4));
                contentList.add(attendance);
            } while (cursor.moveToNext());
        }
        // return quote list
        return contentList;
    }

    public void addAttendanceSOL1(AttendanceSOL1Model attendanceSOL1Model) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(class_week_SOL1, attendanceSOL1Model.getClass_week());
        values.put(student_number_SOL1, attendanceSOL1Model.getStudent_number());
        values.put(student_status_SOL1, attendanceSOL1Model.getStudent_status());
        values.put(student_time_SOL1, attendanceSOL1Model.getStudent_time());

        Log.d("data", (String) values.get(class_week));
        Log.d("data", (String) values.get(student_number));
        Log.d("data", (String) values.get(student_status));
        Log.d("data", (String) values.get(student_time));


        // Inserting Row
        db.insert(tbl_attendance_SOL1, null, values);
        db.close(); // Closing database connection
    }

    public void deleteAttendanceSOL1() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tbl_attendance_SOL1, null, null);
        db.close();
    }

    public void updateAttendanceSOL1(AttendanceSOL1Model attendanceSOL1Model) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(class_week, attendanceSOL1Model.getClass_week());
        values.put(student_number, attendanceSOL1Model.getStudent_number());
        values.put(student_status, attendanceSOL1Model.getStudent_status());
        values.put(student_status, attendanceSOL1Model.getStudent_time());

        db.update(tbl_attendance_SOL1, values, "student_number= '" + student_number_SOL1 + "'", null);
        db.close(); // Closing database connection
    }

    public List<AttendanceSOL1Model> getAllAttendanceSOL1() {
        List<AttendanceSOL1Model> contentList = new ArrayList<AttendanceSOL1Model>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + tbl_attendance_SOL1 +  " ORDER BY " + id_SOL1 + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AttendanceSOL1Model attendance = new AttendanceSOL1Model();
                attendance.setClass_week(cursor.getString(1));
                attendance.setStudent_number(cursor.getString(2));
                attendance.setStudent_status(cursor.getString(3));
                attendance.setStudent_time(cursor.getString(4));
                contentList.add(attendance);
            } while (cursor.moveToNext());
        }
        // return quote list
        return contentList;
    }

    public void addAttendanceSOL2(AttendanceSOL2Model attendanceSOL2Model) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(class_week_SOL2, attendanceSOL2Model.getClass_week());
        values.put(student_number_SOL2, attendanceSOL2Model.getStudent_number());
        values.put(student_status_SOL2, attendanceSOL2Model.getStudent_status());
        values.put(student_time_SOL2, attendanceSOL2Model.getStudent_time());

        Log.d("data", (String) values.get(class_week_SOL2));
        Log.d("data", (String) values.get(student_number_SOL2));
        Log.d("data", (String) values.get(student_status_SOL2));
        Log.d("data", (String) values.get(student_time_SOL2));


        // Inserting Row
        db.insert(tbl_attendance_SOL2, null, values);
        db.close(); // Closing database connection
    }

    public void deleteAttendanceSOL2() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tbl_attendance_SOL2, null, null);
        db.close();
    }

    public void updateAttendanceSOL2(AttendanceSOL2Model attendanceSOL2Model) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(class_week_SOL2, attendanceSOL2Model.getClass_week());
        values.put(student_number_SOL2, attendanceSOL2Model.getStudent_number());
        values.put(student_status_SOL2, attendanceSOL2Model.getStudent_status());
        values.put(student_status_SOL2, attendanceSOL2Model.getStudent_time());

        db.update(tbl_attendance_SOL2, values, "student_number= '" + student_number_SOL2 + "'", null);
        db.close(); // Closing database connection
    }

    public List<AttendanceSOL2Model> getAllAttendanceSOL2() {
        List<AttendanceSOL2Model> contentList = new ArrayList<AttendanceSOL2Model>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + tbl_attendance_SOL2 +  " ORDER BY " + id_SOL2 + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AttendanceSOL2Model attendance = new AttendanceSOL2Model();
                attendance.setClass_week(cursor.getString(1));
                attendance.setStudent_number(cursor.getString(2));
                attendance.setStudent_status(cursor.getString(3));
                attendance.setStudent_time(cursor.getString(4));
                contentList.add(attendance);
            } while (cursor.moveToNext());
        }
        // return quote list
        return contentList;
    }

    public boolean isStudentScanned(String student_number, String level)

    {

        Cursor DB_cursor = null;
        String DB_query = "";
        SQLiteDatabase db = this.getWritableDatabase();

        try
        {
            DB_query = "SELECT * FROM tbl_attendance_"+ level + " WHERE student_number=" + student_number;
            DB_cursor = db.rawQuery(DB_query, null);

            if (DB_cursor.moveToFirst())
            {
                DB_cursor.close();
                return true;
            }
            else
            {
                DB_cursor.close();
                return false;
            }
        }
        catch (Exception ex)
        {
            DB_cursor.close();
            return false;
        }
    }

}
