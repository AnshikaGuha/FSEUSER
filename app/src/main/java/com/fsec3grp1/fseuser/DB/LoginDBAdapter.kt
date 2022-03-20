package com.fsec3grp1.fseuser.DB

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.fsec3grp1.fseuser.User

private val DB_NAME = "FSEUsers.db"
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
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
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

    fun insertUser(userLogin: User): Int {
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
    fun displayUser(username: String): User {
        // array of columns to fetch
        val columns = arrayOf(COLUMN_ID,COLUMN_USERNAME,COLUMN_NAME,COLUMN_EMAIL,COLUMN_CITY,COLUMN_PASSWORD,COLUMN_CPASSWORD)
        // selection argument
        val selectionArgs = arrayOf(username)
        val db = this.readableDatabase
        println(username)
        // query the user table
        val cursor = db.query(
            TABLE_LOGIN,                //Table to query
            columns,                    //columns to return
            "$COLUMN_USERNAME = ?",  //columns for the WHERE clause
            selectionArgs,             //The values for the WHERE clause
            null,               //group the rows
            null,                //filter by row groups
            null                //The sort order
        )
        var user = User(id=-1,username = "",name = "",email = "",city = "",password = "",cpassword = "")
        if (cursor != null && cursor.count > 0) {
            cursor.moveToFirst()

            user = User(
                id = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID)).toInt(),
                username = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USERNAME)),
                name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)),
                city = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CITY)),
                password = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD)),
                cpassword = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CPASSWORD))
            )
            cursor.close()
            db.close()

        }
        return user
    }

    fun updateUser(user: User): Int {
        val db = this.writableDatabase
        var returnCheck: Int = -1

        val values = ContentValues()
        values.put(COLUMN_USERNAME, user.username)
        values.put(COLUMN_NAME, user.name)
        values.put(COLUMN_EMAIL, user.email)
        values.put(COLUMN_CITY, user.city)
        values.put(COLUMN_PASSWORD, user.password)
        values.put(COLUMN_CPASSWORD, user.cpassword)
        // updating row
        returnCheck = db.update(TABLE_LOGIN, values, "$COLUMN_USERNAME = ?"
            ,arrayOf(user.username) )
        db.close()
        return returnCheck
    }

//    fun deleteUser(userID: String): Int {
//        val db = this.writableDatabase
//        var returnCheck: Int = -1
//        // delete user record by id
//        returnCheck = db.delete(TABLE_LOGIN, "$COLUMN_ID = ?", arrayOf(userID))
//        db.close()
//        return returnCheck
//    }

}