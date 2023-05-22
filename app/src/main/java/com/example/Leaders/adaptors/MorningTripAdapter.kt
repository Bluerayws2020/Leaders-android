package com.example.tasmeme.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Leaders.adaptors.MoriningTripInnerAdapter
import com.example.Leaders.model.MorningTripAction
import com.example.Leaders.model.TripStudents
import com.example.nerd_android.helpers.ViewUtils.hide
import com.example.nerd_android.helpers.ViewUtils.show
import com.example.tasmeme.R
import com.example.tasmeme.databinding.MorningTripItemsBinding

class MorningTripAdapter(private val listener: OnItemClickListener):RecyclerView.Adapter<MorningTripAdapter.MyViewHolder>() {

    var list= listOf<TripStudents>()
    var morningTripList = listOf<MorningTripAction>()

    inner class MyViewHolder(val binding :MorningTripItemsBinding ,private val listener: OnItemClickListener): RecyclerView.ViewHolder(binding.root),View.OnClickListener{

        private var isOpen=false

        init {
            binding.recycler.hide()
            itemView.setOnClickListener {
                if (isOpen==true) {
                    binding.recycler.hide()
                    isOpen=false

                }else{
                    binding.recycler.show()
                    isOpen=true
                }

            }
        }


        override fun onClick(v: View?) {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val binding =MorningTripItemsBinding.inflate(layoutInflater,parent,false)
        return MyViewHolder(binding,listener)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val adapter = MoriningTripInnerAdapter(holder.binding.root.context)
        holder.apply {
            binding.morningTripName.text= list[position].full_name
            adapter.list = morningTripList
            binding.recycler.adapter = adapter
            binding.recycler.layoutManager = LinearLayoutManager(binding.root.context,LinearLayoutManager.VERTICAL,false)
            }

            adapter.onItemClickListener {
                data ->
                onItemClickListener?.let {
                    it(data)
                }
            }
    }
    private var onItemClickListener :((MorningTripAction)-> Unit)? = null
    fun onItemClickListener(listener : (MorningTripAction)-> Unit){
        onItemClickListener = listener
    }

}