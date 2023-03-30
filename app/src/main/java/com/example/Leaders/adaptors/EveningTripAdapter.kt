package com.example.tasmeme.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.Leaders.model.TripStudents
import com.example.nerd_android.helpers.ViewUtils.hide
import com.example.nerd_android.helpers.ViewUtils.show
import com.example.tasmeme.R

import java.util.zip.Inflater

class EveningTripAdapter(private val listener: OnItemClickListener):RecyclerView.Adapter<EveningTripAdapter.MyViewHolder>() {
    var list= listOf<TripStudents>()
    inner class MyViewHolder(view:View,private val listener:OnItemClickListener):ViewHolder(view),View.OnClickListener{
        val name=view.findViewById<TextView>(R.id.evening_trip_name)
        val leftBtn=view.findViewById<Button>(R.id.we_left_school)
        val inTheWayBtn=view.findViewById<Button>(R.id.we_are_on_our_way)
        val nearByBtn=view.findViewById<Button>(R.id.we_are_near_by)
        val stuArraivedBtn = view.findViewById<Button>(R.id.stu_arrived)
        private var isOpen=false
        init {
            leftBtn.hide()
            inTheWayBtn.hide()
            nearByBtn.hide()
            stuArraivedBtn.hide()

            view.setOnClickListener{
                if(isOpen==true){
                    leftBtn.hide()
                    inTheWayBtn.hide()
                    nearByBtn.hide()
                    stuArraivedBtn.hide()
                    isOpen=false
                }else{
                    leftBtn.show()
                    inTheWayBtn.show()
                    nearByBtn.show()
                    stuArraivedBtn.show()
                    isOpen=true
                }
            }
        }

        override fun onClick(v: View?) {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val item=layoutInflater.inflate(R.layout.evening_trip_items,parent,false)
        return MyViewHolder(item,listener)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.name.text=list[position].full_name

    }
}