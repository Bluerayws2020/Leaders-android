package com.example.tasmeme.adaptors

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tasmeme.R
import com.example.tasmeme.model.AdminModel

class AdminRecyclerAdapter():RecyclerView.Adapter<AdminRecyclerAdapter.MyViewHolder>() {
    val list= arrayListOf<AdminModel>(AdminModel("1","احمد",1),
    AdminModel("2","تيسير",2),
    AdminModel("3","محمد",3),
        )

    inner class MyViewHolder(view:View):ViewHolder(view){
        val name=view.findViewById<TextView>(R.id.admin_item_stu_name)
        val condition=view.findViewById<TextView>(R.id.admin_item_stu_condition)
        val card1=view.findViewById<CardView>(R.id.card_admin)
        val card2=view.findViewById<CardView>(R.id.card2_admin)
        fun bind(item:AdminModel){
            when(item.condition){
                1 ->{
                    //card1.setCardBackgroundColor())
                    card2.background.setTint(ContextCompat.getColor(itemView.context,R.color.admin_recycle_green))
                    //card2.setCardBackgroundColor(Color.GREEN)
                    card1.background.setTint(ContextCompat.getColor(itemView.context,R.color.admin_recycle_green))
                    condition.text="غادر المدرسة"
                }
                2->{
                    card1.background.setTint(ContextCompat.getColor(itemView.context,R.color.admin_recycle_Orange))
                    card2.background.setTint(ContextCompat.getColor(itemView.context,R.color.admin_recycle_Orange))
                    condition.text="غادر الصف"
                }
                else->{
                    card1.background.setTint(ContextCompat.getColor(itemView.context,R.color.admin_recycle_Red))
                    card2.background.setTint(ContextCompat.getColor(itemView.context,R.color.admin_recycle_Red))
                    condition.text="لم يغادر"
                }


            }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val item=layoutInflater.inflate(R.layout.admin_recycler_items,parent,false)
        return MyViewHolder(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.apply {
            name.text=list[position].stuName

            val context = holder.itemView.context
            bind(list[position])
            }}
    }
