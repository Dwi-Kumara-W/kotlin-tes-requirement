package com.example.test_recruitment.model

data class ResponseOrder(
	val pesan: String? = null,
	val data: List<DataItem?>? = null,
	val kode: Int? = null
)

data class DataItem(
	val nama_produk: String? = null,
	val target: String? = null,
	val jumlah_aksi: Int? = null,
	val status: String? = null
)

