package com.example.Leaders.adaptors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.Leaders.model.ChildListData
import com.example.Leaders.model.GetCurrentDepartureInfoData
import com.example.nerd_android.helpers.HelperUtils.CHILD_LIST
import com.example.nerd_android.helpers.ViewUtils.hide
import com.example.nerd_android.helpers.ViewUtils.show
import com.example.tasmeme.databinding.ChiledItemBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ChildTapAdapter():RecyclerView.Adapter<ChildTapAdapter.MyViewHolder>() {
    lateinit var binding1: ChiledItemBinding

    private val differCallback=object : DiffUtil.ItemCallback<ChildListData>(){
        override fun areItemsTheSame(oldItem: ChildListData, newItem: ChildListData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ChildListData, newItem: ChildListData): Boolean {
            return oldItem==newItem
//                    &&
//                    (oldItem.section==newItem.section)&&
//                    (oldItem.grade==newItem.grade)&&(oldItem.department==newItem.department) }
    }}


    val differ= AsyncListDiffer(this,differCallback)
    inner class MyViewHolder(val binding:ChiledItemBinding):ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding=ChiledItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        binding1=binding
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
    return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
         var job : Job?=null
         var currentList = differ.currentList
       holder.binding.apply {

            localCheckBox.isChecked=true
            localCheckBox.setOnClickListener{
                globalCheckBox.isChecked=false
                localCheckBox.isChecked=true
                CHILD_LIST[position].section=true
            }
            globalCheckBox.setOnClickListener{
                globalCheckBox.isChecked=true
                localCheckBox.isChecked=false
                CHILD_LIST[position].section=false
            }
       }
        holder.binding.fourSectionsNameEt.setText(currentList[position].name)
        holder.binding.gradeEt.setText(currentList[position].grade)
        holder.binding.departmentEt.setText(currentList[position].department)

        if(currentList[position].section){
            holder.binding.localCheckBox.isChecked=true
        }else{
            holder.binding.globalCheckBox.isChecked=true
        }

        if (position==0){
            holder.binding.close.hide()
            holder.binding.close.isClickable=false
        }else{
            holder.binding.close.show()
            holder.binding.close.isClickable=true
        }

        holder.binding.close.setOnClickListener {
            CHILD_LIST.removeAt(position)
            differ.submitList(CHILD_LIST)
            notifyItemRemoved(position)
        }
        holder.binding.fourSectionsNameEt.addTextChangedListener {
            editable ->
            job?.cancel()
            MainScope().launch {
                delay(500L)
                if(editable.toString().isNotEmpty()){
                    CHILD_LIST[position].name=editable.toString()
                }
            }

        }

        holder.binding.gradeEt.addTextChangedListener {
                editable ->
            job?.cancel()
            MainScope().launch {
                delay(500L)
                if(editable.toString().isNotEmpty()){
                    CHILD_LIST[position].grade=editable.toString()
                }
            }}

        holder.binding.departmentEt.addTextChangedListener {
                editable ->
            job?.cancel()
            MainScope().launch {
                delay(500L)
                if(editable.toString().isNotEmpty()){
                    CHILD_LIST[position].department=editable.toString()
                }
            }} }
//    fun addItem(item:String){
//        items.add(item)
//        notifyItemInserted(items.size-1)
//        //notifyItemRangeChanged(0,items.count())
//    }


}