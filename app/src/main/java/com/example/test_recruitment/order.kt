package com.example.test_recruitment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.test_recruitment.adapter.orderadapter
import com.example.test_recruitment.model.ResponseOrder
import com.example.test_recruitment.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class order : AppCompatActivity() {

    private var RV_order: RecyclerView? = null
    private var icon_data_order: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        RV_order = findViewById(R.id.rv_data_order)
        icon_data_order = findViewById(R.id.gambar_data_order)
        val srlDataOrder = findViewById<SwipeRefreshLayout>(R.id.srl_data_order)

        srlDataOrder.setOnRefreshListener(OnRefreshListener {
            srlDataOrder.setRefreshing(true)
            getDataOrder()
            srlDataOrder.setRefreshing(false)
        })

    }

    override fun onStart() {
        super.onStart()
        getDataOrder()
    }

    private fun getDataOrder() {

        ApiService.getService().getOrder().enqueue(object : Callback<ResponseOrder> {
            override fun onResponse(call: Call<ResponseOrder>, response: Response<ResponseOrder>) {

                if (response.isSuccessful){
                    val responseOrder = response.body()
                    val dataOrder = responseOrder?.data
                    val pesan = responseOrder?.pesan
                    val kode = responseOrder?.kode
                    if (kode == 1){
                        icon_data_order?.visibility = View.GONE
                        RV_order?.visibility = View.VISIBLE
//                        Toast.makeText(applicationContext, pesan, Toast.LENGTH_SHORT).show()
                        val mahasiswaAdapter = orderadapter(dataOrder)
                        RV_order?.apply {
                            layoutManager = LinearLayoutManager(this@order)
                            setHasFixedSize(true)
                            mahasiswaAdapter.notifyDataSetChanged()
                            adapter = mahasiswaAdapter
                        }
                    }else{
                        icon_data_order?.visibility = View.VISIBLE
                        RV_order?.visibility = View.GONE
                    }

//                    Log.d("ups", dataMahasiswa.toString())
                }
            }

            override fun onFailure(call: Call<ResponseOrder>, t: Throwable) {
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })

    }

}