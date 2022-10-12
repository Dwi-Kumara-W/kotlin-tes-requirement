package com.example.test_recruitment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class login : AppCompatActivity() {

    var username: String? = null
    var password: String? = null

    private var input_username: EditText? = null
    private var input_password: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        input_username = findViewById(R.id.et_username_login)
        input_password = findViewById(R.id.et_password_login)
        val btn_login = findViewById<Button>(R.id.btn_login)

        btn_login.setOnClickListener(View.OnClickListener {
            send_data()
        })

    }

    private fun send_data() {

        username = input_username?.text.toString()
        password = input_password?.text.toString()

    }
}