package com.example.tasmeme.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tasmeme.R
import com.example.tasmeme.databinding.FragmentParentsOptionsBinding


class ParentsOptionsFragment : Fragment() {
    private lateinit var binding:FragmentParentsOptionsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentParentsOptionsBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            departureCheckbox.setOnClickListener{
                departureWihSomeoneCheckBox.isChecked=false
                leaveCheckbox.isChecked=false
                departureCheckbox.isChecked=true
            }
            departureWihSomeoneCheckBox.setOnClickListener{
                leaveCheckbox.isChecked=false
                departureCheckbox.isChecked=false
                departureWihSomeoneCheckBox.isChecked=true
            }
            leaveCheckbox.setOnClickListener{
                departureCheckbox.isChecked=false
                departureWihSomeoneCheckBox.isChecked=false
            }

        btnSend.setOnClickListener{
            //val showPopUp=  PickUpWithSomeonePopUpFragment()
        //showPopUp.show((activity as AppCompatActivity).supportFragmentManager,"showPopUp")
            if(departureCheckbox.isChecked==true){
                val showPopUp= PleaseWaitFragment ()
                showPopUp.show((activity as AppCompatActivity).supportFragmentManager,"showPopUp")
            }else if (departureWihSomeoneCheckBox.isChecked==true){
                val showPopUp=  PickUpWithSomeonePopUpFragment()
                showPopUp.show((activity as AppCompatActivity).supportFragmentManager,"showPopUp")
            }else if(leaveCheckbox.isChecked==true){
                val showPopUp=  LeavePupUpFragment()
                showPopUp.show((activity as AppCompatActivity).supportFragmentManager,"showPopUp")
            }
            else{
                Toast.makeText(requireContext(),"الرجاء اختبار احد خيارات المغادرة ",Toast.LENGTH_LONG).show()
            }
        }}
    }

}