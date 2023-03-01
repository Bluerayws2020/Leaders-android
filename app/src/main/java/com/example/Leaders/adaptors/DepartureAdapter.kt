package com.example.tasmeme.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tasmeme.R
import com.example.tasmeme.model.DepartureModel


class DepartureAdapter(): RecyclerView.Adapter<DepartureAdapter.MyViewHolder>(){

    val list= arrayListOf<DepartureModel>(
        DepartureModel("1","احمد محمد حماد","12:00","عم الطالب"),
        DepartureModel("1","سالم العلي ","12:00","عم الطالب"),
        DepartureModel("1","خالد السعدي","12:00","عم الطالب"),
        DepartureModel("1","رامي محمد ايهاب","12:00","عم الطالب")

    )
    inner class MyViewHolder(view:View):ViewHolder(view){
        val nameTv=view.findViewById<TextView>(R.id.name)
        val timeTv=view.findViewById<TextView>(R.id.time_of_leave)
        val relativeTv=view.findViewById<TextView>(R.id.connection)
        val acceptBTN=view.findViewById<Button>(R.id.btn_Accept)
        val declineBTN=view.findViewById<Button>(R.id.btn_decline)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val item =layoutInflater.inflate(R.layout.depart_items,parent,false)
        return MyViewHolder(item)
    }

    override fun getItemCount(): Int {
    return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.apply {
            nameTv.text=list[position].name_of_departor
            timeTv.text=list[position].time_of_leave
            relativeTv.text=list[position].relative_relation
        }
    }
}