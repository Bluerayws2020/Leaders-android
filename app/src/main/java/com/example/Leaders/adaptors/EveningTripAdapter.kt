package com.example.tasmeme.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.Leaders.adaptors.TripInnerAdapter
import com.example.Leaders.model.EveningTripAction
import com.example.Leaders.model.TripStudents
import com.example.nerd_android.helpers.ViewUtils.hide
import com.example.nerd_android.helpers.ViewUtils.show
import com.example.tasmeme.databinding.EveningTripItemsBinding

class EveningTripAdapter :RecyclerView.Adapter<EveningTripAdapter.MyViewHolder>() {
    var list= listOf<TripStudents>()
    var eveningTripList = listOf<EveningTripAction>()
    inner class MyViewHolder(val binding: EveningTripItemsBinding):ViewHolder(binding.root),View.OnClickListener{
        val name=binding.eveningTripName
            //view.findViewById<TextView>(R.id.evening_trip_name)
            private val recyclerView = binding.recycler

        private var isOpen=false
        init {

            recyclerView.hide()

            itemView.setOnClickListener{
                if(isOpen){
                    recyclerView.hide()
                    isOpen=false
                }else{
                    recyclerView.show()
                    isOpen=true
                }
            }
        }

        override fun onClick(v: View?) {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = EveningTripItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val adapter = TripInnerAdapter(holder.binding.root.context)
        holder.apply {
            adapter.list = eveningTripList
            binding.recycler.layoutManager = LinearLayoutManager(binding.root.context,LinearLayoutManager.VERTICAL,false)
            binding.recycler.adapter = adapter
        }
        holder.name.text=list[position].full_name

    }
}