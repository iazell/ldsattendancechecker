package lds.bcc.ldsattendancechecker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_SETTING_TABLE = "CREATE TABLE " + tbl_students + "("
                + student_number + " INTEGER PRIMARY KEY , "
                + student_name + " TEXT ,"
                + student_nickname + " TEXT ,"
                + student_birthdate + " TEXT ,"
                + student_contact + " TEXT ,"
                + student_leader + " TEXT ,"
                + student_contactleader + " TEXT ,"
                + student_network + " TEXT, "
                + student_class + " TEXT);";
        db.execSQL(CREATE_SETTING_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + tbl_students);
        onCreate(db);

    }



}
