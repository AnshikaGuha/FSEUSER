package com.fsec3grp1.fseuser.DB

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.fsec3grp1.fseuser.UserLogin

private val DB_NAME = "userlogin.db"
private val DB_VERSION = 2

public class LoginDBAdapter(context: Context)
    : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    val TAG = LoginDBAdapter::class.java.simpleName

    private val TABLE_LOGIN = "login"
    private val COLUMN_ID = "user_id"
    private val COLUMN_USERNAME = "username"
    private val COLUMN_NAME = "name"
    private val COLUMN_EMAIL = "email"
    private val COLUMN_CITY = "city"
    private val COLUMN_PASSWORD = "password"
    private val COLUMN_CPASSWORD = "cpassword"

    private val createTable = ("CREATE TABLE " + TABLE_LOGIN + "" + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_USERNAME + " TEXT NOT NULL, "
            + COLUMN_NAME + " TEXT NOT NULL, "
            + COLUMN_EMAIL + " TEXT NOT NULL, "
            + COLUMN_CITY + " TEXT NOT NULL, "
            + COLUMN_PASSWORD + " TEXT NOT NULL, "
            + COLUMN_CPASSWORD + " TEXT NOT NULL)")

    // drop table sql query
    private val dropTable = "DROP TABLE IF EXISTS $TABLE_LOGIN"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createTable)
        Log.v(TAG, "Table created")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(dropTable)//Drop User Table if exist
        Log.v(TAG, "Table Dropped")
        onCreate(db)// Create tables again
    }

    fun insertUser(userLogin: UserLogin): Int {
        val returnCheck: Int
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_USERNAME, userLogin.username)
        values.put(COLUMN_NAME, userLogin.name)
        values.put(COLUMN_EMAIL, userLogin.email)
        values.put(COLUMN_CITY, userLogin.city)
        values.put(COLUMN_PASSWORD, userLogin.password)
        values.put(COLUMN_CPASSWORD, userLogin.cpassword)
        // Inserting Row
        returnCheck = db.insert(TABLE_LOGIN, null, values).toInt()
        db.close()
        return returnCheck
    }

    fun updateUser(userLogin: UserLogin): Int{
        val db = this.writableDatabase
        var returnCheck: Int = -1

        val values = ContentValues()
        values.put(COLUMN_USERNAME, userLogin.username)
        values.put(COLUMN_NAME, userLogin.name)
        values.put(COLUMN_EMAIL, userLogin.email)
        values.put(COLUMN_CITY, userLogin.city)
        values.put(COLUMN_PASSWORD, userLogin.password)
        values.put(COLUMN_CPASSWORD, userLogin.cpassword)
        // updating row
        returnCheck = db.update(TABLE_LOGIN, values, "$COLUMN_USERNAME = ?"
            ,arrayOf(userLogin.username) )
        db.close()
        return returnCheck
    }

}