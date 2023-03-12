package com.example.Leaders.adaptors

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.nerd_android.helpers.ViewUtils.hide
import com.example.nerd_android.helpers.ViewUtils.show
import com.example.tasmeme.databinding.ChiledItemBinding



class ChildTapAdapter(

    private val items: MutableList<String>):RecyclerView.Adapter<ChildTapAdapter.MyViewHolder>() {
    lateinit var binding1: ChiledItemBinding

    val data = mutableListOf<String>()


    inner class MyViewHolder(val binding:ChiledItemBinding):ViewHolder(binding.root){

        fun bind(){
        binding.apply {

            localCheckBox.isChecked=true
            localCheckBox.setOnClickListener{
                globalCheckBox.isChecked=false
                localCheckBox.isChecked=true

            }
            globalCheckBox.setOnClickListener{
                globalCheckBox.isChecked=true
                localCheckBox.isChecked=false

            }
        }
        if (items.size==1){
            binding.close.hide()
           binding.close.isClickable=false
        }else{
           binding.close.show()
            binding.close.isClickable=true
        }
    }
    init {
        binding.close.setOnClickListener {
            items.removeAt(adapterPosition)
            notifyItemRemoved(adapterPosition)
            notifyItemRangeChanged(adapterPosition,items.size)
        }
    }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding=ChiledItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        binding1=binding
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
    return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind()



        }
    fun addItem(item:String){
        items.add(item)
        notifyItemInserted(items.size-1)
        //notifyItemRangeChanged(0,items.count())
    }
    fun getItem(position: Int)
        = MyViewHolder(binding1)



}