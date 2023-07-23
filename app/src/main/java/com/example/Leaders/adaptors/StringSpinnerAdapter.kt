package com.example.Leaders.adaptors

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.example.Leaders.model.Student
import com.example.tasmeme.R

class StringSpinnerAdapter(context: Context, items: List<String>) :
    ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, items) {

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(android.R.layout.simple_spinner_dropdown_item, parent, false)
        val item = getItem(position)
        view.findViewById<TextView>(android.R.id.text1).gravity = Gravity.RIGHT or Gravity.CENTER_VERTICAL
        view.findViewById<TextView>(android.R.id.text1)
            .text = item

        return view
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(android.R.layout.simple_spinner_item, parent, false)

        val item = getItem(position)
        view.findViewById<TextView>(android.R.id.text1).gravity = Gravity.RIGHT or Gravity.CENTER_VERTICAL
        view.findViewById<TextView>(android.R.id.text1).typeface = ResourcesCompat.getFont(view.context,
            R.font.arabicmedium)
        view.findViewById<TextView>(android.R.id.text1)
            .text = item


        return view
    }


}
