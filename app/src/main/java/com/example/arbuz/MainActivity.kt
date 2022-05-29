package com.example.arbuz

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.arbuz.databinding.ActivityMainBinding
import com.example.arbuz.fragments.ListOfZakazFragment
import com.example.arbuz.fragments.MakeZakazFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeFragment(ListOfZakazFragment())
//        binding.floatBtn.setOnClickListener {
//            changeFragment(MakeZakazFragment())
//            binding.floatBtn.visibility = View.GONE
//        }
    }

    private fun changeFragment(fragm: Fragment) {
        val fragment = supportFragmentManager.beginTransaction()
        fragment.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
        fragment.replace(R.id.fragment_container, fragm)
        fragment.addToBackStack(null)
        fragment.commit()
    }
}