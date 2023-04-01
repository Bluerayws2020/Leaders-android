package com.example.Leaders.adaptors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.Leaders.model.Student
import com.example.Leaders.model.ViewParentProfileInfoModelData

class SpinnerAdapter(context: Context, items: List<Student>) :
    ArrayAdapter<Student>(context, android.R.layout.simple_spinner_item, items) {

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(android.R.layout.simple_spinner_dropdown_item, parent, false)
        val item = getItem(position)
        view.findViewById<TextView>(android.R.id.text1).text = item?.full_name

        return view
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(android.R.layout.simple_spinner_item, parent, false)

        val item = getItem(position)
        view.findViewById<TextView>(android.R.id.text1).text =item?.full_name

        return view
    }


}
