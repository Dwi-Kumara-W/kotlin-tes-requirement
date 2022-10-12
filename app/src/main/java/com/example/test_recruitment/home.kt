package com.example.test_recruitment

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.test_recruitment.sqllite.user

class home : AppCompatActivity() {

    var dbHelper: user? = null
    protected var cursor: Cursor? = null
    protected var cursorid: Cursor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btn_logout = findViewById<Button>(R.id.btn_logout)

        dbHelper = user(this)
        val db = dbHelper!!.readableDatabase

        cursor = db.rawQuery("SELECT * FROM USER", null)
        cursorid = db.rawQuery("SELECT id_device FROM USER", null)

        btn_logout.setOnClickListener(View.OnClickListener {
            proses_logout()
        })

    }

    private fun proses_logout() {
        val db = dbHelper!!.writableDatabase
        db.execSQL("delete from USER")
        val intent = Intent(this@home, MainActivity::class.java)
        intent.flags =
            Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}