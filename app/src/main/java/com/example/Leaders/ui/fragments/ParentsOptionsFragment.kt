package com.example.tasmeme.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.example.Leaders.adaptors.SpinnerAdapter
import com.example.Leaders.model.NetworkResults
import com.example.Leaders.model.Student
import com.example.Leaders.viewModels.AppViewModel
import com.example.nerd_android.helpers.HelperUtils.getUID
import com.example.nerd_android.helpers.ViewUtils.hide
import com.example.nerd_android.helpers.ViewUtils.show
import com.example.tasmeme.databinding.FragmentParentsOptionsBinding


class ParentsOptionsFragment : Fragment() {
    private lateinit var binding:FragmentParentsOptionsBinding
    private val viewModel by viewModels<AppViewModel>()
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
        binding.pb.show()
        getData()
        viewModel.getParentProfileData(getUID(requireContext()))
    }

    private fun getData() {
        viewModel.getParentProfileLiveData().observe(viewLifecycleOwner){
            when(it){
                is NetworkResults.Success ->{
                    binding.pb.hide()
                    if(it.data.status==200){
                        setUpSpinner(requireContext(),it.data.data.students)
                    } else{
                        binding.pb.hide()
                        Toast.makeText(requireContext(),"Un Expected Error Please Try Again", Toast.LENGTH_SHORT).show()
                    }
                }
                is NetworkResults.Error->{
                    binding.pb.hide()
                    Log.e("ayham", it.exception.toString())
                }
            }
        }
    }

    private fun setUpSpinner(requireContext: Context, students: List<Student>) {
        val adapter= SpinnerAdapter(requireContext,students)
        binding.spinner.adapter=adapter
    }
}

