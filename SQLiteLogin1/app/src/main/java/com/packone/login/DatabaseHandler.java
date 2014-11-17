package com.packone.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Kanyilidz Muhammedmehdi, Seckin Berkay
 * @version: 0.9.3
 * @date: 01.10.2014
 * Beschreibung: This Class contains all Operations for deleting updating and creating an entry in the Database
 * Futhermore there are implemented some Connection Methods
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "GetFit2";

    /* ------------------------- TABLES ------------------------------ */
    private static final String TABLE_CONTACTS = "login";
    private static final String TABLE_EXERCISE = "exercise";
    private static final String TABLE_FRAGENKATALOG = "fragenkatalog";

    /* ======== COLUMNS from TABLE  "LOGIN" ========= */
    //login
    private static final String KEY_USERNAME = "uname";
    private static final String KEY_PASSWORD = "pword";
    private static final String KEY_EMAIL = "emial";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_HEIGHT = "height";
    private static final String KEY_WEIGHT = "weight";

    /* ======== COLUMNS from TABLE  "EXERCISES" ========= */
    private static final String KEY_EXID = "id";
    private static final String KEY_EXERCISEBEZ = "exercisebez";

    /* ======== COLUMNS from TABLE "FRAGENKATALOG"  ========= */
    private static final String KEY_FRAGE1 = "frage1";
    private static final String KEY_FRAGE2 = "frage2";
    private static final String KEY_FRAGE3 = "frage3";
    private static final String KEY_FRAGE4 = "frage4";
    private static final String KEY_FRAGE5 = "frage5";


    private int exists;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Insert: ", "---! CREATE TABLES: LOGIN, EXERCISE, FRAGENKATLOG !---");
        this.exists = 0;
        //========= CREATE TABLE LOGIN ==========
        String CREATE_CONTACTS_TABLE =
                "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "("
                    + KEY_USERNAME + " TEXT,"
                    + KEY_PASSWORD + " TEXT,"
                    + KEY_EMAIL + " TEXT,"
                    + KEY_GENDER + " TEXT,"
                    + KEY_HEIGHT + " INTEGER,"
                    + KEY_WEIGHT + " FLOAT"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

        //========= CREATE TABLE EXERCISE ==========
        String CREATE_EXERCISE_TABLE =
                "CREATE TABLE IF NOT EXISTS " + TABLE_EXERCISE + "("
                    + KEY_EXID + " INTEGER PRIMARY KEY,"
                    + KEY_EXERCISEBEZ + " TEXT"
                 + ")";
        db.execSQL(CREATE_EXERCISE_TABLE);

        //========= CREATE TABLE FRAGENKATALOG ==========
        String CREATE_FRAGENKATALOG_TABLE =
                "CREATE TABLE IF NOT EXISTS " + TABLE_FRAGENKATALOG + "("
                    + KEY_FRAGE1 + " TEXT,"
                    + KEY_FRAGE2 + " TEXT,"
                    + KEY_FRAGE3 + " TEXT,"
                    + KEY_FRAGE4 + " TEXT,"
                    + KEY_FRAGE5 + " TEXT" +
                ")";
        db.execSQL(CREATE_EXERCISE_TABLE);

    }

    // Dropping all Tables in a Database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISE);
        // Create tables again
        onCreate(db);
    }

    /**************************************************************************************/
    /************************ OPERATIONS FOR THE LOGIN TABLE ******************************/
    /**************************************************************************************/
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, contact.getUname()); // Contact Name
        values.put(KEY_PASSWORD, contact.getPword()); // Contact Name
        values.put(KEY_EMAIL, contact.getEmail()); // Contact Phone
        values.put(KEY_GENDER, contact.getGender()); // Contact Name
        values.put(KEY_HEIGHT, contact.getHeight()); // Contact Name
        values.put(KEY_WEIGHT, contact.getWeight()); // Contact Phone

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);

        db.close(); // Closing database connection
    }

    // Getting single contact
    Contact getContact(String uname) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_USERNAME,
                        KEY_PASSWORD, KEY_EMAIL, KEY_GENDER, KEY_HEIGHT, KEY_WEIGHT}, KEY_USERNAME + "=?",
                new String[]{String.valueOf(uname)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(
                cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)));
        // return contact
        return contact;
    }

    // Getting All Contacts
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setUname(cursor.getString(0));
                contact.setPword(cursor.getString(1));
                contact.setEmail(cursor.getString(2));
                contact.setGender(cursor.getString(3));
                contact.setHeight(Integer.parseInt(cursor.getString(4)));
                contact.setWeight(Integer.parseInt(cursor.getString(5)));

                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());

        }
        // return contact list
        return contactList;
    }

    // Updating single contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PASSWORD, contact.getPword());
        values.put(KEY_EMAIL, contact.getEmail());
        values.put(KEY_GENDER, contact.getGender());
        values.put(KEY_HEIGHT, contact.getHeight());
        values.put(KEY_WEIGHT, contact.getWeight());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_USERNAME + " = ?",
                new String[]{String.valueOf(contact.getUname())});
    }

    // Deleting single contact
    public void deleteContact() {
        SQLiteDatabase db = this.getWritableDatabase();
        /**db.delete(TABLE_CONTACTS, KEY_USERNAME + " = ?",
         new String[] { String.valueOf(contact.getID()) });
         */

        Log.d("Insert: ", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        String DEL = "DELETE FROM login";
        Log.d("Insert: ", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        db.execSQL(DEL);
        db.close();
    }

    // Getting contacts Count
    public int getContactsCount(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String count = "SELECT count(uname) FROM login where uname = ?";
        Cursor mcursor = db.rawQuery(count, new String[]{name});
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);

        return icount;
    }
    /**************************************************************************************/
    /************************ OPERATIONS FOR THE EXERCISE TABLE ***************************/
    /**************************************************************************************/
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    void addExercise(Exercise exercise) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EXID, exercise.getExId()); // Contact Name
        values.put(KEY_EXERCISEBEZ, exercise.getExBez()); // Contact Name

        // Inserting Row
        db.insert(TABLE_EXERCISE, null, values);

        db.close(); // Closing database connection
    }

    // Getting single exercise
    Exercise getExercise(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_EXID,
                        KEY_EXERCISEBEZ}, KEY_EXID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Exercise exercise = new Exercise(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));
        // return contact
        return exercise;
    }

    // Getting All exercises
    public List<Exercise> getAllExercises() {
        List<Exercise> exerciseList = new ArrayList<Exercise>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EXERCISE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Exercise exercise = new Exercise();
                exercise.setExId(Integer.parseInt(cursor.getString(0)));
                exercise.setExBez(cursor.getString(1));

                // Adding contact to list
                exerciseList.add(exercise);
            } while (cursor.moveToNext());
        }
        // return contact list
        return exerciseList;
    }

    //TODO: - (später erst) Exercise Methoden Kommentare anpassen
    // Updating single contact
    public int updateExercise(Exercise exercise) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EXERCISEBEZ, exercise.getExBez());

        // updating row
        return db.update(TABLE_EXERCISE, values, KEY_EXERCISEBEZ + " = ?",
                new String[]{String.valueOf(exercise.getExBez())});
    }

    // Deleting all exercises
    public void deleteExercise() {
        SQLiteDatabase db = this.getWritableDatabase();
        /**db.delete(TABLE_CONTACTS, KEY_USERNAME + " = ?",
         new String[] { String.valueOf(contact.getID()) });
         */

        Log.d("Insert: ", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        String DEL = "DELETE FROM exercise";
        Log.d("Insert: ", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        db.execSQL(DEL);
        db.close();
    }

    // Getting contacts Count
    public int getExerciseCount(String exbez) {
        SQLiteDatabase db = this.getWritableDatabase();
        String count = "SELECT count(" + KEY_EXID + ") FROM login where " + KEY_EXERCISEBEZ + "= ?";
        Cursor mcursor = db.rawQuery(count, new String[]{exbez});
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);

        return icount;
    }

    /**************************************************************************************/
    /************************ OPERATIONS FOR THE FRAGENKATALOG TABLE **********************/
    /**************************************************************************************/
    //TODO OPERATIONEN FÜR FRAGENKATALOG TABELLE
}