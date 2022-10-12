package com.example.test_recruitment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.test_recruitment.model.ResponseLogin
import com.example.test_recruitment.retrofit.ApiService
import com.example.test_recruitment.sqllite.user
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class login : AppCompatActivity() {

    var username: String? = null
    var password: String? = null

    var dbHelper: user? = null

    private var input_username: EditText? = null
    private var input_password: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        input_username = findViewById(R.id.et_username_login)
        input_password = findViewById(R.id.et_password_login)
        val btn_login = findViewById<Button>(R.id.btn_login)

        dbHelper = user(this)

        btn_login.setOnClickListener(View.OnClickListener {
            send_data()
        })

    }

    private fun send_data() {

        username = input_username?.text.toString()
        password = input_password?.text.toString()

        if (username.equals("")){
            Toast.makeText(applicationContext, "Maaf anda belum input username", Toast.LENGTH_SHORT).show()
        }else if (password.equals("")){
            Toast.makeText(applicationContext, "Maaf anda belum input password", Toast.LENGTH_SHORT).show()
        }else{

            ApiService.getService().login(username, password)?.enqueue(object :
                Callback<ResponseLogin> {
                override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {

                    if (response.isSuccessful){
                        val responseLogin = response.body()
                        val id_user = responseLogin?.id
                        val pesan = responseLogin?.pesan
                        val kode = responseLogin?.kode
                        if (kode == 1){

                            val id_login = 1
                            Toast.makeText(applicationContext, pesan, Toast.LENGTH_SHORT).show()
                            val db = dbHelper!!.writableDatabase
                            db.execSQL("insert into user values( '$id_login','$id_user')")
                            val gohome = Intent(this@login, home::class.java)
                            startActivity(gohome)
                            finish();
                        }
                        Toast.makeText(applicationContext, pesan, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }

            })

        }

    }
}