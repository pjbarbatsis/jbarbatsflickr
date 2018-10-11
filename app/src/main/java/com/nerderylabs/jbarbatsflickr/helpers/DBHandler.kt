package com.nerderylabs.jbarbatsflickr.helpers

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.content.ContentValues
import android.database.Cursor
import android.util.Log
import com.nerderylabs.jbarbatsflickr.model.Photo

// https://www.techotopia.com/index.php/A_Kotlin_Android_SQLite_Database_Tutorial
// https://medium.com/coding-and-learning/android-kotlin-with-sqlite-connectivity-crud-f8a8e0158717

class DBHandler(context: Context, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_PHOTOS_TABLE = ("CREATE TABLE " +
                TABLE_PHOTOS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_TITLE + " TEXT," +
                COLUMN_SECRET + " TEXT," + COLUMN_SERVER + " TEXT," + COLUMN_FARM + " TEXT" + ")")
        db.execSQL(CREATE_PHOTOS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHOTOS)
        onCreate(db)
    }


    companion object {

        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "photosDB.db"
        val TABLE_PHOTOS = "photos"

        val COLUMN_ID = "_id"
        val COLUMN_TITLE = "title"
        val COLUMN_SECRET = "secret"
        val COLUMN_SERVER = "server"
        val COLUMN_FARM = "farm"
    }

    fun addPhoto(photo: Photo) {

        val values = ContentValues()

        values.put(COLUMN_TITLE, photo.title)
        values.put(COLUMN_SECRET, photo.secret)
        values.put(COLUMN_SERVER, photo.server)
        values.put(COLUMN_FARM, photo.farm)

        val db = this.writableDatabase

        db.insert(TABLE_PHOTOS, null, values)
        db.close()
    }

    fun getPhotos(): List<Photo> {

        val db = this.writableDatabase
        val photos = ArrayList<Photo>()
        val cursor: Cursor
        cursor = db.rawQuery("SELECT * FROM PHOTOS", null)

        if (cursor.moveToFirst()) {
            do {
                val photoId = cursor.getString(cursor.getColumnIndex(COLUMN_ID))
                val photoTitle = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE))
                val photoSecret = cursor.getString(cursor.getColumnIndex(COLUMN_SECRET))
                val photoServer = cursor.getString(cursor.getColumnIndex(COLUMN_SERVER))
                val photoFarm = cursor.getString(cursor.getColumnIndex(COLUMN_FARM))
                val photo = Photo(photoId, photoTitle, photoSecret, photoServer, photoFarm)
                photos.add(photo)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return photos
    }

    fun updatePhoto(photo: Photo) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("photoID", photo.id)
        values.put("photoTitle", photo.title)
        values.put("photoSecret", photo.secret)
        values.put("photoServer", photo.server)
        values.put("photoFarm", photo.farm)

        val retVal = db.update(TABLE_PHOTOS, values, "_id = " + photo.id, null)
        if (retVal >= 1) {
            Log.v("@@@WWe", " Record updated")
        } else {
            Log.v("@@@WWe", " Not updated")
        }
        db.close()

    }
}