package com.example.tasmeme.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tasmeme.R
import com.example.tasmeme.model.DepartureModel

class LeaveAdapter:RecyclerView.Adapter<LeaveAdapter.MyViewHolder>() {
    val list= arrayListOf<DepartureModel>(
        DepartureModel("1","احمد محمد حماد","12:00","عم الطالب"),
        DepartureModel("1","سالم العلي ","12:00","عم الطالب"),
        DepartureModel("1","خالد السعدي","12:00","عم الطالب"),
        DepartureModel("1","رامي محمد ايهاب","12:00","عم الطالب")

    )
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val nameTv=view.findViewById<TextView>(R.id.leave_name)
        val timeTv=view.findViewById<TextView>(R.id.leave_time_of_stu_leave)
       // val relativeTv=view.findViewById<TextView>(R.id.connection)
        val grade=view.findViewById<TextView>(R.id.leave_grade)
        val acceptBTN=view.findViewById<Button>(R.id.btn_Accept)
        val declineBTN=view.findViewById<Button>(R.id.btn_decline)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        val item =layoutInflater.inflate(R.layout.leaves_items,parent,false)
        return MyViewHolder(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.apply {
            nameTv.text=list[position].name_of_departor
            timeTv.text=list[position].time_of_leave
            grade.text="الرابع"
           // relativeTv.text=list[position].relative_relation
        }
    }
}