package com.example.arbuz.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.arbuz.activities.MoreInfoPageActivity
import com.example.arbuz.databinding.UniqueZakazBinding
import com.example.arbuz.models.ArbuzZakaz

class ZakazListAdapter(val context: Context, val list: ArrayList<ArbuzZakaz>): RecyclerView.Adapter<ZakazListAdapter.myViewHolder>() {
    class myViewHolder(uniqueZakazBinding: UniqueZakazBinding): RecyclerView.ViewHolder(uniqueZakazBinding.root) {
        var binding = uniqueZakazBinding

        fun bind(arbuzZakaz: ArbuzZakaz){
            binding.tvDate.text = arbuzZakaz.date
            binding.tvQuantity.text = "Количество: ${arbuzZakaz.arbuzQuantity}"
            binding.tvTime.text =  "Время: ${arbuzZakaz.time}"
            binding.tvWeight.text = "Дата: ${arbuzZakaz.arbuzWeight}"
            binding.tvPrice.text = arbuzZakaz.arbuzPrice
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view = UniqueZakazBinding.inflate(LayoutInflater.from(context), parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val uniqueZakaz = list[position]
        holder.bind(uniqueZakaz)
        holder.binding.cardView.setOnClickListener {
            val intent = Intent(context, MoreInfoPageActivity::class.java)
            intent.putExtra("gryadka", uniqueZakaz.arbuzGryadka)
            intent.putExtra("status", uniqueZakaz.arbuzStatus)
            intent.putExtra("weight", uniqueZakaz.arbuzWeight)
            intent.putExtra("quantity", uniqueZakaz.arbuzQuantity)
            intent.putExtra("price", uniqueZakaz.arbuzPrice)
            intent.putExtra("address", uniqueZakaz.address)
            intent.putExtra("phoneNumber", uniqueZakaz.phoneNumber)
            intent.putExtra("date", uniqueZakaz.date)
            intent.putExtra("time", uniqueZakaz.time)
            intent.putExtra("checkCut", uniqueZakaz.checkCut)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}