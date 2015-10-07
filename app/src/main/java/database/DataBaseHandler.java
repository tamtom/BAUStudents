package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;

import itdeveapps.baustudents.MyNote;

/**
 * Created by omar on 10/7/2015.
 */
public class DataBaseHandler extends SQLiteOpenHelper {
    private final ArrayList<MyNote> myNotes = new ArrayList<>();

    public DataBaseHandler(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTE_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + " (" + Constants.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + Constants.TITLE_NAME +
                " TEXT, " + Constants.CONTENT_NAME + " TEXT, " + Constants.DATE_NAME + " LONG);";
        db.execSQL(CREATE_NOTE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(db);
    }

    public void deleteWish(int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.TABLE_NAME, Constants.KEY_ID + " = ? ",
                new String[]{String.valueOf(id)});

        db.close();

    }

    //add content to table
    public void addWishes(MyNote note) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.TITLE_NAME, note.getTitle());
        values.put(Constants.CONTENT_NAME, note.getContent());
        values.put(Constants.DATE_NAME, System.currentTimeMillis());


        db.insert(Constants.TABLE_NAME, null, values);
        //db.insert(Constants.TABLE_NAME, null, values);

        // Log.v("Wish successfully!", "yeah!!");

        db.close();


    }

    //Get all wishes
    public ArrayList<MyNote> getWishes() {

        myNotes.clear();

        String selectQuery = "SELECT * FROM " + Constants.TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        // Cursor cursor = db.rawQuery(selectQuery, null);

        Cursor cursor = db.query(Constants.TABLE_NAME, new String[]{Constants.KEY_ID, Constants.TITLE_NAME, Constants.CONTENT_NAME, Constants.DATE_NAME}, null, null, null, null, Constants.DATE_NAME + " DESC");

        //loop through cursor
        if (cursor.moveToFirst()) {

            do {

                MyNote note = new MyNote();
                note.setTitle(cursor.getString(cursor.getColumnIndex(Constants.TITLE_NAME)));
                note.setContent(cursor.getString(cursor.getColumnIndex(Constants.CONTENT_NAME)));
                note.setItemId(cursor.getInt(cursor.getColumnIndex(Constants.KEY_ID)));

                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
                String dataData = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.DATE_NAME))).getTime());

                note.setRecoredDate(dataData);

                myNotes.add(note);

            } while (cursor.moveToNext());
        }


        cursor.close();
        db.close();

        return myNotes;

    }


}
