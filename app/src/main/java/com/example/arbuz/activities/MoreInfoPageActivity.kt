package com.example.arbuz.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.arbuz.R
import com.example.arbuz.databinding.ActivityMoreInfoPageBinding


class MoreInfoPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMoreInfoPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoreInfoPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationIcon(null)
        binding.toolbar.title = ""
        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);

        getInfo()

        binding

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getInfo() {
        val gryadka = intent.getStringExtra("gryadka")
        val status = intent.getStringExtra("status")
        val weight = intent.getStringExtra("weight")
        val quantity = intent.getIntExtra("quantity", 0)
        val price = intent.getStringExtra("price")
        val address = intent.getStringExtra("address")
        val phoneNumber = intent.getStringExtra("phoneNumber")
        val date = intent.getStringExtra("date")
        val time = intent.getStringExtra("time")
        val checkCut = intent.getBooleanExtra("checkCut", false)

        binding.tvPlace.text = "Место на грядке: $gryadka"
        binding.tvStatus.text = status
        binding.tvWeight.text = weight
        binding.tvQuantity.text = "Количество: $quantity"
        binding.tvPrice.text = price
        binding.tvAddress.text = address
        binding.tvPhoneNumber.text = phoneNumber
        binding.tvDate.text = date
        binding.tvTime.text = time
        binding.checkbox.isChecked = checkCut
    }
}