package com.example.exampleproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.exampleproject.CaptureImage.ImageCaptureClass;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "example_project.db";
    private static final String IMAGE = "image";
    private static final String TABLE_IMAGE = "image_table";
    private static final String CREATE_TABLE_IMAGE = " create table if not exists " + TABLE_IMAGE + " (" +
            IMAGE + " TEXT(10) DEFAULT null );";


    Context context;
    SQLiteDatabase db;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL(CREATE_TABLE_IMAGE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertImage(ImageCaptureClass captureClass) {
        this.db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(IMAGE, String.valueOf(captureClass.getBitmap()));
        long status = db.insert(TABLE_IMAGE, null, cv);
    }

    public Cursor getImages() {
        this.db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_IMAGE, null);
        return cursor;
    }
}

