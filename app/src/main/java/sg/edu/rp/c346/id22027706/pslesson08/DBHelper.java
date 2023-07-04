package sg.edu.rp.c346.id22027706.pslesson08;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "tasks.db";

    private static final String TABLE_LIST = "list";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_SINGERS = "singers";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_STARS = "stars";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_LIST +  "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_SINGERS + " TEXT,"
                + COLUMN_YEAR + " INTEGER,"
                + COLUMN_STARS + " INTEGER )";
        db.execSQL(createTableSql);
        Log.i("info" ,"created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIST);
        // Create table(s) again
        onCreate(db);
    }

    public void insertTask(String title, String singers, String year, String stars){

        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_SINGERS, singers);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_STARS, stars);
        // Insert the row into the TABLE_TASK
        db.insert(TABLE_LIST, null, values);
        // Close the database connection
        db.close();
    }

    public ArrayList<List> getLists() {
        ArrayList<List> lists = new ArrayList<List>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS, COLUMN_YEAR, COLUMN_STARS};
        Cursor cursor = db.query(TABLE_LIST, columns, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                String year = cursor.getString(3);
                String stars = cursor.getString(4);
                List obj = new List(id, title, singers, year, stars);
                lists.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lists;
    }

}

