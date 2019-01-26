package lds.bcc.ldsattendancechecker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseStudent extends SQLiteOpenHelper {

    //database version
    private static final int DATABASE_VERSION = 1;
    ///database name
    private static final String DATABASE_NAME = "ldstracker";

    ///table name
    public static final String tbl_students = "tbl_students";
    ///columns...
    public static final String student_number = "student_number";
    public static final String student_name = "student_name";
    public static final String student_nickname = "student_nickname";
    public static final String student_birthdate = "student_birthdate";
    public static final String student_contact = "student_contact";
    public static final String student_leader = "student_leader";
    public static final String student_contactleader = "student_contactleader";
    public static final String student_network = "student_network";
    public static final String student_class = "student_class";


    public DatabaseStudent(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

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
}
