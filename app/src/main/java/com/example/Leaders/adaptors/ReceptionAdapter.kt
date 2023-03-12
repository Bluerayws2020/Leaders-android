package com.example.tasmeme.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.Leaders.model.Departure
import com.example.tasmeme.R
import com.example.tasmeme.databinding.FragmentProfileBinding
import com.example.tasmeme.databinding.ReceptionRecItemsBinding
import com.example.tasmeme.model.ReceptionModel

class ReceptionAdapter(List: List<Departure>, val listener: OnItemClickListener): RecyclerView.Adapter<ReceptionAdapter.MyViewHolder>(){
    private lateinit var list:List<Departure>
    init {
    list=List
    }
    inner class MyViewHolder(val binding:ReceptionRecItemsBinding,private val listener: OnItemClickListener ):ViewHolder(binding.root),OnClickListener{


        override fun onClick(v: View?) {
            listener.onItemClick(adapterPosition,list[adapterPosition].nid)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding=ReceptionRecItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
         holder.binding.apply {
             recItemStuName.text="الطالب"+list[position].student
             if(list[position].status=="7"){
                 recItemCheckBox.isChecked=true
             }else{
                 recItemCheckBox.isChecked==false
             }
         }

        holder.binding.recItemCheckBox.setOnClickListener {
            listener.onItemClick(position,list[position].nid)
            }

    }
}