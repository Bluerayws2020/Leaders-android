package com.example.tasmeme.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tasmeme.R
import com.example.tasmeme.model.ReceptionModel

class ReceptionAdapter(): RecyclerView.Adapter<ReceptionAdapter.MyViewHolder>(){
    val list= arrayListOf<ReceptionModel>(
        ReceptionModel("1","الطالب أحمد محمد"," / الصف الرابع","غادر الصف الساعة 12:00","بموافقة من المشرفة ديمة"),
        ReceptionModel("2","الطالب ايهم حماد"," / الصف الرابع","غادر الصف الساعة 12:00","بموافقة من المشرفة ديمة"),
        ReceptionModel("3","الطالب احمد ابو شنب"," / الصف الرابع","غادر الصف الساعة 12:00","بموافقة من المشرفة ديمة"),
    )

    inner class MyViewHolder(view:View):ViewHolder(view){
        val name=view.findViewById<TextView>(R.id.rec_item_stu_name)
        val grade=view.findViewById<TextView>(R.id.rec_item_stu_grade)
        val timeToLeave=view.findViewById<TextView>(R.id.rec_item_stu_time_to_leave)
        val adminName=view.findViewById<TextView>(R.id.rec_item_admin_name)
        val checkBox=view.findViewById<CheckBox>(R.id.rec_item_checkBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val item= layoutInflater.inflate(R.layout.reception_rec_items,parent,false)
        return MyViewHolder(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.apply {
            name.text=list[position].name
            grade.text=list[position].grade
            timeToLeave.text=list[position].time
            adminName.text=list[position].admin_name
        }
    }
}