package com.example.arbuz.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.arbuz.models.ArbuzZakaz
import com.google.firebase.database.*

class ListOfZakazViewModel(application: Application): AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private lateinit var mDbReference: DatabaseReference
    var listZakaz = arrayListOf<ArbuzZakaz>()
    var liveDataList = MutableLiveData<ArrayList<ArbuzZakaz>>()

    fun getList(){
        mDbReference = FirebaseDatabase.getInstance().reference.child("Zakaz")
        mDbReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    listZakaz.clear()
                    for(element in snapshot.children){
                        val item = element.getValue(ArbuzZakaz::class.java)
                        listZakaz.add(item!!)
                        liveDataList.value = listZakaz
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "$error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}