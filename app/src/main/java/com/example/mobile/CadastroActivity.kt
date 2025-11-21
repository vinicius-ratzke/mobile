package com.example.mobile

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile.databinding.ActivityCadastroBinding

class CadastroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSalvar.setOnClickListener {
            saveTransaction()
        }
    }

    private fun saveTransaction() {
        val description = binding.etDescricao.text?.toString()?.trim().orEmpty()
        val valueText = binding.etValor.text?.toString()?.trim().orEmpty()

        if (description.isEmpty() || valueText.isEmpty()) {
            Toast.makeText(this, R.string.msg_cadastro_campos_obrigatorios, Toast.LENGTH_SHORT).show()
            return
        }

        val value = valueText.replace(",", ".").toDoubleOrNull()
        if (value == null) {
            Toast.makeText(this, R.string.msg_valor_invalido, Toast.LENGTH_SHORT).show()
            return
        }

        val type = if (binding.chipCredito.isChecked) TransactionType.CREDITO else TransactionType.DEBITO
        val transaction = Transaction(type, description, value)
        TransactionRepository.add(transaction)

        Toast.makeText(this, R.string.msg_cadastro_sucesso, Toast.LENGTH_SHORT).show()
        finish()
    }
}
