package com.example.Leaders.adaptors

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log.d
import android.util.Log.e
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.Leaders.model.GetCurrentDepartureInfoData
import com.example.tasmeme.R
import com.example.tasmeme.adaptors.OnItemClickListener
import com.example.tasmeme.databinding.NotificationItemsBinding

class NotificationsAdapter(val context:Context):RecyclerView.Adapter<NotificationsAdapter.MyViewHolder>() {



    private val differCallback=object : DiffUtil.ItemCallback<GetCurrentDepartureInfoData>(){
        override fun areItemsTheSame(oldItem: GetCurrentDepartureInfoData, newItem: GetCurrentDepartureInfoData): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: GetCurrentDepartureInfoData, newItem: GetCurrentDepartureInfoData): Boolean {
            return oldItem==newItem
        }

    }
    val differ= AsyncListDiffer<GetCurrentDepartureInfoData>(this,differCallback)

    inner class MyViewHolder(val binding: NotificationItemsBinding):ViewHolder(binding.root)
    {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding=NotificationItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        var nonNullValues=0
        for (item in differ.currentList){
            if((item!=null)&&(item?.name!=null)&&(item?.count!=null)){
                nonNullValues++
            }}

            d("ayham",differ.currentList.size.toString())
        return nonNullValues
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = differ.currentList[position]

        data?.name?.let {
            //try {


            val departureName = data.name
            val departureCount = "(${data.count.toString()})"
            val spannableName = SpannableString(departureName)
            val spannableCount = SpannableString(departureCount)
            val spannableStringBuilder = SpannableStringBuilder()

            spannableCount.setSpan(
                ForegroundColorSpan(Color.BLUE),
                0,
                departureCount.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannableName.setSpan(
                ForegroundColorSpan(Color.BLACK),
                0,
                departureName.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            spannableStringBuilder.append(spannableName)
            spannableStringBuilder.append(" ")
            spannableStringBuilder.append(spannableCount)


            holder.binding.btnLeaves.text = spannableStringBuilder
        }//catch (e:Exception){
          //  e("ayham",e.toString())
        //}
            holder.binding.btnLeaves.setOnClickListener {
                onClickListener?.let {
                    it(data)
                }
            }
        }

    private var onClickListener: ((GetCurrentDepartureInfoData)->Unit)?=null

    fun onItemClickListener(listener:(GetCurrentDepartureInfoData)->Unit){
        onClickListener=listener
    }}
