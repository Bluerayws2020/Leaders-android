package com.example.Leaders.adaptors

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.VirtualLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.Leaders.model.EveningTripAction
import com.example.tasmeme.R
import com.example.tasmeme.databinding.TripInnerRecyclerItemBinding
import java.util.*

class TripInnerAdapter(val context:Context) :RecyclerView.Adapter<TripInnerAdapter.InnerTripViewHolder>() {

    var list = listOf<EveningTripAction>()
    @RequiresApi(Build.VERSION_CODES.M)
    private val colorList = listOf(context.getColor(R.color.admin_recycle_Orange),context.getColor(R.color.pink) , context.getColor(R.color.cyan), context.getColor(R.color.admin_recycle_green))


    inner class InnerTripViewHolder(val binding : TripInnerRecyclerItemBinding):ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerTripViewHolder {
        val binding = TripInnerRecyclerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return InnerTripViewHolder(binding)
    }

    override fun getItemCount(): Int =list.size

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: InnerTripViewHolder, position: Int) {
        val currentItem = list[position]
        holder.apply {
            val random = Random()
            val randomIndex = random.nextInt(colorList.size)
            val randomColor = colorList[randomIndex]
            binding.btn.setBackgroundColor(randomColor)
            binding.btn.text = currentItem.name
        }
    }
}