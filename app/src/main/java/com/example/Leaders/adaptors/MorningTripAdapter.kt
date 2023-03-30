package com.example.tasmeme.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.Leaders.model.TripStudents
import com.example.nerd_android.helpers.ViewUtils.hide
import com.example.nerd_android.helpers.ViewUtils.show
import com.example.tasmeme.R

class MorningTripAdapter(private val listener: OnItemClickListener):RecyclerView.Adapter<MorningTripAdapter.MyViewHolder>() {

    var list= listOf<TripStudents>()

    inner class MyViewHolder(view: View,private val listener: OnItemClickListener): RecyclerView.ViewHolder(view),View.OnClickListener{

        val inTheWayBtn=view.findViewById<Button>(R.id.BtnOnOurWay)
        val weArrivedBtn=view.findViewById<Button>(R.id.BtnArrived)
        val stuTakenBtn=view.findViewById<Button>(R.id.BtnStuTaken)
        val arrivedToSchool=view.findViewById<Button>(R.id.BtnStuArrivedToSchool)
        val timeOutBtn=view.findViewById<Button>(R.id.BtnTimeOut)
        val name=view.findViewById<TextView>(R.id.morning_trip_name)
        private var isOpen=false

        init {
            inTheWayBtn.hide()
            weArrivedBtn.hide()
            stuTakenBtn.hide()
            arrivedToSchool.hide()
            timeOutBtn.hide()
            view.setOnClickListener {
                if (isOpen==true) {
                    inTheWayBtn.hide()
                    weArrivedBtn.hide()
                    stuTakenBtn.hide()
                    arrivedToSchool.hide()
                    timeOutBtn.hide()
                    isOpen=false

                }else{
                    inTheWayBtn.show()
                    weArrivedBtn.show()
                    stuTakenBtn.show()
                    arrivedToSchool.show()
                    timeOutBtn.show()
                    isOpen=true
                }

            }
        }


        override fun onClick(v: View?) {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val item =layoutInflater.inflate(R.layout.morning_trip_items,parent,false)
        return MyViewHolder(item,listener)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text= list[position].full_name
    }

}