package com.example.arbuz.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.example.arbuz.models.ArbuzZakaz
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlin.random.Random

class MakeZakazViewModel(application: Application): AndroidViewModel(application) {

    var weight = 0
    var weightFinal = 0
    private val context = getApplication<Application>().applicationContext
    private lateinit var mDbReference: DatabaseReference

    fun addToDb(arbuzZakaz: ArbuzZakaz): String{
        mDbReference = FirebaseDatabase.getInstance().getReference("Zakaz")

        mDbReference.child(arbuzZakaz.id).setValue(arbuzZakaz).addOnCompleteListener {
            if (it.isSuccessful){

            }
        }.addOnCanceledListener {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
        }

        return "good"
    }

    fun randomStatus(): String {
        val random = Random.nextInt(1, 4)

        when (random) {
            1 -> {
                weight = 1
                randomWeight()
            }
            2, 3 -> {
                weight = 2
                randomWeight()
            }
        }

        var status = ""
        when (random) {
            1 -> status = "Неспелый"
            2 -> status = "Спелый"
            3 -> status = "Уже сорван"
        }

        return status
    }

    fun randomWeight() {
        val random1 = Random.nextInt(4, 7)
        val random2 = Random.nextInt(7, 12)
        if (weight == 1) {
            weightFinal = random1
        } else {
            weightFinal = random2
        }
    }
}