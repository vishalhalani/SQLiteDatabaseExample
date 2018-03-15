package com.vishal.sqlitedatabaseexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vishal.halani on 15-Mar-18.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SQLiteExample.db";
    private static final int DATABASE_VERSION = 2;

    private static final String PERSON_TABLE_NAME = "person";
    private static final String PERSON_COLUMN_ID = "_id";
    private static final String PERSON_COLUMN_NAME = "name";
    private static final String PERSON_COLUMN_MOBILE_NUMBER = "mobile_number";
    private static final String PERSON_COLUMN_GENDER = "gender";
    private static final String PERSON_COLUMN_AGE = "age";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + PERSON_TABLE_NAME +
                        "(" + PERSON_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                        PERSON_COLUMN_NAME + " TEXT, " +
                        PERSON_COLUMN_AGE + " INTEGER, " +
                        PERSON_COLUMN_MOBILE_NUMBER + " TEXT, " +
                        PERSON_COLUMN_GENDER + " TEXT)"


        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PERSON_TABLE_NAME);
        onCreate(db);
    }

    // insert record in table
    public boolean insertPerson(String name, String gender, int age, String mobile) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PERSON_COLUMN_NAME, name);
        contentValues.put(PERSON_COLUMN_GENDER, gender);
        contentValues.put(PERSON_COLUMN_AGE, age);
        contentValues.put(PERSON_COLUMN_MOBILE_NUMBER, mobile);
        db.insert(PERSON_TABLE_NAME, null, contentValues);
        return true;
    }

    // insert record in table
    public boolean insertPerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PERSON_COLUMN_NAME, person.getName());
        contentValues.put(PERSON_COLUMN_GENDER, person.getGender());
        contentValues.put(PERSON_COLUMN_AGE, person.getAge());
        contentValues.put(PERSON_COLUMN_MOBILE_NUMBER, person.getMobileNumber());
        db.insert(PERSON_TABLE_NAME, null, contentValues);
        return true;
    }

    // count table records
    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, PERSON_TABLE_NAME);
        return numRows;
    }

    // update record using id
    public boolean updatePerson(Integer id, String name, String gender, int age, String mobile) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PERSON_COLUMN_NAME, name);
        contentValues.put(PERSON_COLUMN_GENDER, gender);
        contentValues.put(PERSON_COLUMN_AGE, age);
        contentValues.put(PERSON_COLUMN_MOBILE_NUMBER, mobile);
        db.update(PERSON_TABLE_NAME, contentValues, PERSON_COLUMN_ID + " = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    // update record using person model
    public boolean updatePerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PERSON_COLUMN_NAME, person.getName());
        contentValues.put(PERSON_COLUMN_GENDER, person.getGender());
        contentValues.put(PERSON_COLUMN_AGE, person.getAge());
        contentValues.put(PERSON_COLUMN_MOBILE_NUMBER, person.getMobileNumber());
        db.update(PERSON_TABLE_NAME, contentValues, PERSON_COLUMN_ID + " = ? ", new String[]{Integer.toString(person.getId())});
        return true;
    }

    // delete record using id
    public Integer deletePerson(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(PERSON_TABLE_NAME,
                PERSON_COLUMN_ID + " = ? ",
                new String[]{Integer.toString(id)});
    }

    // delete record using person model
    public Integer deletePerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(PERSON_TABLE_NAME,
                PERSON_COLUMN_ID + " = ? ",
                new String[]{Integer.toString(person.getId())});
    }

//    public Cursor getPerson(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
////        Cursor res =  db.rawQuery("SELECT * FROM " + PERSON_TABLE_NAME + " WHERE " +
////                PERSON_COLUMN_ID + " = ?", new String[]{Integer.toString(id)});
//        Cursor cursor = db.query(PERSON_TABLE_NAME, new String[]{PERSON_COLUMN_ID,
//                        PERSON_COLUMN_NAME, PERSON_COLUMN_AGE, PERSON_COLUMN_GENDER}, PERSON_COLUMN_ID + "=?",
//                new String[]{String.valueOf(id)}, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        return cursor;
//    }

    // Get person using id
    public Person getPerson(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery("SELECT * FROM " + PERSON_TABLE_NAME + " WHERE " +
//                PERSON_COLUMN_ID + " = ?", new String[]{Integer.toString(id)});
        Cursor cursor = db.query(PERSON_TABLE_NAME, new String[]{PERSON_COLUMN_ID,
                        PERSON_COLUMN_NAME, PERSON_COLUMN_AGE, PERSON_COLUMN_MOBILE_NUMBER, PERSON_COLUMN_GENDER}, PERSON_COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Person person = new Person(cursor.getInt(0),
                cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4));

        return person;
    }

//    public Cursor getAllPersons() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res = db.rawQuery("SELECT * FROM " + PERSON_TABLE_NAME, null);
//        return res;
//    }

    // Getting All Person
    public List<Person> getAllPerson() {
        List<Person> personList = new ArrayList<Person>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + PERSON_TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                Person person = new Person(cursor.getInt(0),
                        cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4));

                // Adding contact to list
                personList.add(person);
            } while (cursor.moveToNext());
        }

        // return contact list
        return personList;
    }
}