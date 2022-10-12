package com.example.test_recruitment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test_recruitment.R
import com.example.test_recruitment.model.DataItem

class orderadapter(val dataOrder: List<DataItem?>?) : RecyclerView.Adapter<orderadapter.MyViewHolder>() {

    class MyViewHolder (view: View) :RecyclerView.ViewHolder(view){
        val TV_target = view.findViewById<TextView>(R.id.item_target)
        val TV_nama_produk = view.findViewById<TextView>(R.id.item_nama_produk)
        val TV_status = view.findViewById<TextView>(R.id.item_status)
        val TV_jumlah_aksi = view.findViewById<TextView>(R.id.item_jumlah_aksi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): orderadapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: orderadapter.MyViewHolder, position: Int) {
        holder.TV_target.text = dataOrder?.get(position)?.target
        holder.TV_nama_produk.text = dataOrder?.get(position)?.nama_produk
        holder.TV_status.text = dataOrder?.get(position)?.status
        holder.TV_jumlah_aksi.text = dataOrder?.get(position)?.jumlah_aksi.toString()

    }

    override fun getItemCount(): Int {
        if (dataOrder != null){
            return dataOrder.size
        }
        return 0
    }
}