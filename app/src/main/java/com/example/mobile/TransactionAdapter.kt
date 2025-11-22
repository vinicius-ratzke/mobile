package com.example.mobile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import com.example.mobile.databinding.ItemTransactionBinding

class TransactionAdapter(
    context: Context,
    private val transactions: MutableList<Transaction>
) : ArrayAdapter<Transaction>(context, 0, transactions) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemTransactionBinding
        val rowView: View

        if (convertView == null) {
            binding = ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            rowView = binding.root
            rowView.tag = binding
        } else {
            rowView = convertView
            binding = convertView.tag as ItemTransactionBinding
        }

        val item = transactions[position]
        binding.tvType.text = item.type.label
        binding.tvDescription.text = item.description
        binding.tvAmount.text = item.formattedAmount

        val colorRes = if (item.type == TransactionType.CREDITO) {
            R.color.transaction_credit
        } else {
            R.color.transaction_debit
        }
        binding.tvAmount.setTextColor(ContextCompat.getColor(context, colorRes))

        return rowView
    }
}
