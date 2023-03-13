package com.example.tasmeme.adaptors

import android.graphics.Color
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.Leaders.model.Departure
import com.example.nerd_android.helpers.ViewUtils.hide
import com.example.nerd_android.helpers.ViewUtils.show
import com.example.tasmeme.R
import com.example.tasmeme.databinding.DepartItemsBinding
import com.example.tasmeme.model.DepartureModel


class DepartureAdapter(): RecyclerView.Adapter<DepartureAdapter.MyViewHolder>(){


    private val differCallback =object :DiffUtil.ItemCallback<Departure>(){
        override fun areItemsTheSame(oldItem: Departure, newItem: Departure): Boolean {
            return oldItem.nid==newItem.nid
        }
        override fun areContentsTheSame(oldItem: Departure, newItem: Departure): Boolean {
            return oldItem==newItem }

    }
    val differ =AsyncListDiffer(this,differCallback)
    inner class MyViewHolder(val binding:DepartItemsBinding):ViewHolder(binding.root){
        fun bind(){
            if(differ.currentList[adapterPosition].status=="6"){
                binding.btnDecline.hide()
                binding.btnAccept.show()
                binding.btnAccept.text="تم مغادرة الطالب بنجاح"
                binding.btnAccept.isClickable=false
                binding.btnDecline.isClickable=false
                binding.btnAccept.setBackgroundColor(Color.GRAY )
                binding.btnAccept.width= LinearLayout.LayoutParams.MATCH_PARENT
                val layoutParams = binding.btnAccept.layoutParams as LinearLayout.LayoutParams
                layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT
                binding.btnAccept.layoutParams = layoutParams
            }else if(differ.currentList[adapterPosition].status=="8"){
                binding.btnAccept.hide()
                binding.btnDecline.show()
                binding.btnDecline.text="تم رفص مغادرة الطالب بنجاح"
                binding.btnDecline.isClickable=false
                binding.btnAccept.isClickable=false
                binding.btnDecline.width= LinearLayout.LayoutParams.MATCH_PARENT
                val layoutParams = binding.btnDecline.layoutParams as LinearLayout.LayoutParams
                layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT
                binding.btnDecline.layoutParams = layoutParams
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding=DepartItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
    return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.name.text=differ.currentList[position].student
        d("ayham",differ.currentList[position].phone_number)

        holder.binding.btnAccept.setOnClickListener {

            onApproveClickListener?.let {
                it(differ.currentList[position])
            }
        }
        holder.binding.btnDecline.setOnClickListener {

            onRejectClickListener?.let {
                it(differ.currentList[position])
            }
        }
      holder.bind()
    }
    private var onApproveClickListener:((Departure)->Unit)?=null

    fun onButtonApproveClicked(listener:(Departure)->Unit){
        onApproveClickListener=listener
    }
    private var onRejectClickListener:((Departure)->Unit)?=null

    fun onButtonRejectClicked(listener:(Departure)->Unit){
        onRejectClickListener=listener
    }

}