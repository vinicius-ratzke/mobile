package com.example.mobile

import java.text.NumberFormat
import java.util.Locale

enum class TransactionType(val label: String) {
    DEBITO("Débito"),
    CREDITO("Crédito");

    companion object {
        fun from(isCredit: Boolean): TransactionType = if (isCredit) CREDITO else DEBITO
    }
}

data class Transaction(
    val type: TransactionType,
    val description: String,
    val amount: Double
) {
    val formattedAmount: String
        get() = currencyFormatter.format(amount)

    companion object {
        private val currencyFormatter: NumberFormat =
            NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    }
}

object TransactionRepository {
    private val transactions: MutableList<Transaction> = mutableListOf()

    fun add(transaction: Transaction) {
        transactions.add(0, transaction)
    }

    fun all(): MutableList<Transaction> = transactions

    fun isEmpty(): Boolean = transactions.isEmpty()
}
