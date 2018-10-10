package com.nerderylabs.jbarbatsflickr

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.content.ContentValues
import android.util.Log
import com.nerderylabs.jbarbatsflickr.model.TimeObject
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

// https://www.techotopia.com/index.php/A_Kotlin_Android_SQLite_Database_Tutorial

class DBHandler(context: Context, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TIMES_TABLE = ("CREATE TABLE " +
                TABLE_TIMES + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_TIME + " TEXT)")
        db.execSQL(CREATE_TIMES_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIMES)
        onCreate(db)
    }


    companion object {

        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "timesDB.db"
        val TABLE_TIMES = "times"

        val COLUMN_ID = "_id"
        val COLUMN_TIME = "timestamp"
    }

    fun addTimestamp(timeObject: TimeObject) {
        val values = ContentValues()
        values.put(COLUMN_TIME, timeObject.time.toString())

        val db = this.writableDatabase

        db.insert(TABLE_TIMES, null, values)
        db.close()
    }

    fun findMostRecentTimestamp(): TimeObject {

        val query = "SELECT * FROM $TABLE_TIMES ORDER BY $COLUMN_ID DESC LIMIT 1"
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)
        val time: TimeObject

        if (cursor.moveToFirst()) {
            cursor.moveToFirst()

            val id = Integer.parseInt(cursor.getString(0))


            Log.d("id", cursor.getString(0))
            Log.d("time", cursor.getString(1))

            val formatter = DateTimeFormat.forPattern("yyyy/mm/dd HH:mm:ss")
            val timestamp = formatter.parseDateTime(cursor.getString(1))

            time = TimeObject(id, timestamp)
            cursor.close()
            db.close()
            return time
        } else {
            db.close()
            return TimeObject(0, DateTime.now())
        }
    }

    // "The method will use a SQL SELECT statement to search for the entry based on the [criteria]
    // and, if located, delete it from the table. The success or otherwise of the deletion will be
    // reflected in a Boolean return value"
    fun removeOldestTimestamp(): Boolean {
        var result = false
        val query = "SELECT * FROM $TABLE_TIMES ORDER BY $COLUMN_ID ASC LIMIT 1"
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            cursor.moveToFirst()
            val id = Integer.parseInt(cursor.getString(0))
            db.delete(TABLE_TIMES, COLUMN_ID + " = ?", arrayOf(id.toString()))
            cursor.close()
            result = true
        }
        db.close()

        return result
    }
}