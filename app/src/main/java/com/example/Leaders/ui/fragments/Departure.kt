package com.example.tasmeme.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasmeme.adaptors.DepartureAdapter
import com.example.tasmeme.adaptors.LeaveAdapter
import com.example.tasmeme.databinding.FragmentDepartureBinding

class Departure : Fragment() {

    private lateinit var binding:FragmentDepartureBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding= FragmentDepartureBinding.inflate(layoutInflater)
        when(arguments?.getString("buttonClicked")){
            "leave"-> setLeaveLayout()
            "departure" -> setDepartureLayout()
            else -> setDepartureWithLayout()
        }

        return binding.root
    }

    private fun setDepartureWithLayout() {
        binding.apply {
            departureType.text = "الانصراف مع شخص"
            val adapter=DepartureAdapter()
            recyclerDepature.adapter=adapter
            recyclerDepature.layoutManager=LinearLayoutManager(requireContext())
        }
    }

    private fun setDepartureLayout() {
        binding.apply {
            departureType.text = "انصراف"
            val adapter= LeaveAdapter()
            recyclerDepature.adapter=adapter
            recyclerDepature.layoutManager=LinearLayoutManager(requireContext())
    }}

    private fun setLeaveLayout() {
        binding.apply {
            departureType.text = "المغادرة"
           val adapter=LeaveAdapter()
            recyclerDepature.adapter=adapter
            recyclerDepature.layoutManager=LinearLayoutManager(requireContext())}
    }


}