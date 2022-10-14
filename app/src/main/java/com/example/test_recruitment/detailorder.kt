package com.example.test_recruitment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.test_recruitment.model.ResponseDetailOrders
import com.example.test_recruitment.model.ResponseLogin
import com.example.test_recruitment.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class detailorder : AppCompatActivity() {

    var id: Int? = null
    private var TV_target: TextView? = null
    private var TV_nama_produk: TextView? = null
    private var TV_jumlah_aksi: TextView? = null
    private var TV_status: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailorder)

        TV_target = findViewById(R.id.TV_target_detail)
        TV_nama_produk = findViewById(R.id.TV_nama_produk_detail)
        TV_jumlah_aksi = findViewById(R.id.TV_jumlah_aksi_detail)
        TV_status = findViewById(R.id.TV_status_detail)

        val i = intent
        id = i.getStringExtra("id")?.toInt()

//        detail_order()

    }

    override fun onStart() {
        super.onStart()
        detail_order()
    }

    private fun detail_order() {

        ApiService.getService().detail(id)?.enqueue(object :
            Callback<ResponseDetailOrders> {
            override fun onResponse(call: Call<ResponseDetailOrders>, response: Response<ResponseDetailOrders>) {

                if (response.isSuccessful){
                    val responseLogin = response.body()
                    val id_user = responseLogin?.id
                    val pesan = responseLogin?.pesan
                    var target = responseLogin?.target
                    val nama_produk = responseLogin?.nama_produk
                    val jumlah_aksi = responseLogin?.jumlah_aksi
                    val status = responseLogin?.status
                    val kode = responseLogin?.kode

                    if (kode == 1){
                        TV_target?.text = target.toString()
                        TV_nama_produk?.setText(nama_produk.toString())
                        TV_jumlah_aksi?.setText(jumlah_aksi.toString())
                        TV_status?.setText(status.toString())
                    }

                    Toast.makeText(applicationContext, nama_produk, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseDetailOrders>, t: Throwable) {
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }
}