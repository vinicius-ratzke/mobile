package com.example.mobile

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile.databinding.ActivityExtratoBinding

class ExtratoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExtratoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExtratoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        updateList()
    }

    private fun updateList() {
        val transactions = TransactionRepository.all()
        binding.listExtrato.adapter = TransactionAdapter(this, transactions)
        binding.tvEmpty.visibility = if (transactions.isEmpty()) View.VISIBLE else View.GONE
    }
}
