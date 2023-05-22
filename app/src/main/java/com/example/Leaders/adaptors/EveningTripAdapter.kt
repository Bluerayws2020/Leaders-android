package com.example.tasmeme.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.Leaders.adaptors.TripInnerAdapter
import com.example.Leaders.model.EveningTripAction
import com.example.Leaders.model.TripStudents
import com.example.nerd_android.helpers.ViewUtils.hide
import com.example.nerd_android.helpers.ViewUtils.show
import com.example.tasmeme.R
import com.example.tasmeme.databinding.EveningTripItemsBinding

import java.util.zip.Inflater

class EveningTripAdapter(
    private val listener: OnItemClickListener):RecyclerView.Adapter<EveningTripAdapter.MyViewHolder>() {
    var list= listOf<TripStudents>()
    var eveningTripList = listOf<EveningTripAction>()
    inner class MyViewHolder(val binding:EveningTripItemsBinding,private val listener:OnItemClickListener):ViewHolder(binding.root),View.OnClickListener{
        val name=binding.eveningTripName
            //view.findViewById<TextView>(R.id.evening_trip_name)
        val recyclerView = binding.recycler
//        val leftBtn=
//            //view.findViewById<Button>(R.id.we_left_school)
//        val inTheWayBtn=view.findViewById<Button>(R.id.we_are_on_our_way)
//        val nearByBtn=view.findViewById<Button>(R.id.we_are_near_by)
//        val stuArraivedBtn = view.findViewById<Button>(R.id.stu_arrived)
        private var isOpen=false
        init {
//            leftBtn.hide()
//            inTheWayBtn.hide()
//            nearByBtn.hide()
//            stuArraivedBtn.hide()
            recyclerView.hide()

            itemView.setOnClickListener{
                if(isOpen==true){
//                    leftBtn.hide()
//                    inTheWayBtn.hide()
//                    nearByBtn.hide()
//                    stuArraivedBtn.hide()
                    recyclerView.hide()
                    isOpen=false
                }else{
//                    leftBtn.show()
//                    inTheWayBtn.show()
//                    nearByBtn.show()
//                    stuArraivedBtn.show()
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
        return MyViewHolder(binding,listener)
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