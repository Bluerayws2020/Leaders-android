package com.example.Leaders.adaptors

import android.util.Log.e
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.Leaders.model.ChildListData
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
            return oldItem.id==newItem.id
    }
    }


    val differ= AsyncListDiffer(this,differCallback)
    inner class MyViewHolder(val binding:ChiledItemBinding):ViewHolder(binding.root){
        fun bind(){
            var job : Job?=null
            var currentList = differ.currentList
            try{

                binding.apply {

                    localCheckBox.isChecked=true
                    localCheckBox.setOnClickListener{
                        globalCheckBox.isChecked=false
                        localCheckBox.isChecked=true
                        CHILD_LIST[adapterPosition].section="1"
                    }
                    globalCheckBox.setOnClickListener{
                        globalCheckBox.isChecked=true
                        localCheckBox.isChecked=false
                        CHILD_LIST[adapterPosition].section="2"

                    }
                }
                binding.fourSectionsNameEt.setText(currentList[adapterPosition].name)
                binding.gradeEt.setText(currentList[adapterPosition].grade)
                binding.departmentEt.setText(currentList[adapterPosition].department)

                if(currentList[adapterPosition].section=="1"){
                    binding.localCheckBox.isChecked=true
                }else{
                    binding.globalCheckBox.isChecked=true
                }

                if (adapterPosition==0){
                    binding.close.hide()
                    binding.close.isClickable=false
                }else{
                    binding.close.show()
                    binding.close.isClickable=true
                }

                binding.close.setOnClickListener {
                    CHILD_LIST.removeAt(adapterPosition)
                    updateChildIds()
                    differ.submitList(CHILD_LIST)
                    notifyItemRemoved(adapterPosition)
                }
                binding.fourSectionsNameEt.addTextChangedListener {
                        editable ->
                    job?.cancel()
                    MainScope().launch {
                        delay(500L)
                        if((adapterPosition!=-1) &&(adapterPosition<CHILD_LIST.size)){
                        CHILD_LIST[adapterPosition].name=editable.toString()}

                    }

                }

                binding.gradeEt.addTextChangedListener {
                        editable ->
                    job?.cancel()
                    MainScope().launch {
                        delay(500L)
                        if((adapterPosition!=-1) &&(adapterPosition<CHILD_LIST.size)){
                        CHILD_LIST[adapterPosition].grade=editable.toString()}
                    }}

                binding.departmentEt.addTextChangedListener {
                        editable ->
                    job?.cancel()
                    MainScope().launch {
                        delay(500L)
                        if((adapterPosition!=-1) &&(adapterPosition<CHILD_LIST.size)){
                        CHILD_LIST[adapterPosition].department=editable.toString()}
                    }} }catch (e:Exception){
                e("ayham",e.toString())
            }}

//    fun addItem(item:String){
//        items.add(item)
//        notifyItemInserted(items.size-1)
//        //notifyItemRangeChanged(0,items.count())
//    }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding=ChiledItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        binding1=binding
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
    return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    holder.bind()
    }
    private fun updateChildIds() {
        for (i in 0 until CHILD_LIST.size) {
            CHILD_LIST[i].id = i
        }
    }

}