package com.example.test_recruitment

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.test_recruitment.sqllite.user

class MainActivity : AppCompatActivity() {

    var dbHelper: user? = null
    protected var cursor: Cursor? = null
    protected var cursorid: Cursor? = null

    private var id_device = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = user(this)
        val db: SQLiteDatabase = dbHelper!!.getReadableDatabase()

        cursor = db.rawQuery("SELECT * FROM USER", null)
        cursorid = db.rawQuery("SELECT id FROM USER", null)

        if (cursor?.count!! >0){
            cursorid?.moveToPosition(0)
            id_device = cursorid?.getInt(0)!!

            Handler().postDelayed({
                    startActivity(Intent(this@MainActivity, home::class.java))
                    finish()
            }, 3000)
        }else{
            Handler().postDelayed({
                startActivity(Intent(this@MainActivity, login::class.java))
                finish()
            }, 3000)
        }

    }
}