package com.example.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//Create a new database
class ArticleDbHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {


    // Functions to create new database
    override fun onCreate(database: SQLiteDatabase?) {
        val sql =
            "CREATE TABLE ${DB.TABLE_NAME}(" + "${DB.ID} INTEGER PRIMARY KEY," + "${DB.TITLE} TEXT," + "${DB.BODY} TEXT" + ")"
        database?.execSQL(sql)
    }

    // Functions to Upgrade database
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }


    companion object {
        private const val DB_NAME = "Article Database"
        private const val DB_VERSION = 1
    }

}