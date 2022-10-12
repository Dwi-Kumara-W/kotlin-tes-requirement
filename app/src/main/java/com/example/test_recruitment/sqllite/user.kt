package com.example.test_recruitment.sqllite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class user(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERTSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val sql = ("CREATE TABLE USER ( id INTEGER PRIMARY KEY, id_device INTEGER" + ")")
        Log.d("Data", "onCreate: $sql")
        db.execSQL(sql)
    }

    override fun onUpgrade(arg0: SQLiteDatabase, arg1: Int, arg2: Int) {}

    companion object {
        private const val DATABASE_NAME = "PenggunaKu.db"
        private const val DATABASE_VERTSION = 1
    }
}