package com.example.Leaders.ui.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.Leaders.adaptors.SpinnerAdapter
import com.example.Leaders.model.NetworkResults
import com.example.Leaders.model.Student
import com.example.Leaders.model.ViewParentProfileInfoModel
import com.example.Leaders.viewModels.AppViewModel
import com.example.nerd_android.helpers.HelperUtils
import com.example.nerd_android.helpers.ViewUtils.hide
import com.example.nerd_android.helpers.ViewUtils.show
import com.example.tasmeme.R
import com.example.tasmeme.databinding.FragmentParentProfileBinding


class ParentProfile : Fragment() {
    private lateinit var binding:FragmentParentProfileBinding
    private  val viewModel by viewModels<AppViewModel>()
    private var spinnerResults:NetworkResults.Success<ViewParentProfileInfoModel>?=null
  override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentParentProfileBinding.inflate(layoutInflater)
        binding.pb.show()
        getData()
        viewModel.getParentProfileData(HelperUtils.getUID(requireContext()))
        return binding.root
    }
    private fun setUpSpinner(requireContext: Context, students: List<Student>) {
        val adapter= SpinnerAdapter(requireContext,students)
        binding.spinner.adapter=adapter
    }
    private fun getData() {
        viewModel.getParentProfileLiveData().observe(viewLifecycleOwner){
            when(it){
                is NetworkResults.Success ->{
                    binding.pb.hide()
                    if(it.data.status==200){
                        setUpSpinner(requireContext(),it.data.data.students)
                        spinnerResults=it
                        binding.fullName.text=Editable.Factory.getInstance().newEditable(it.data.data.full_name)
                        binding.textName.text=it.data.data.full_name
                        binding.textNumber.text=it.data.data.phone_number
                        binding.phoneNumberTv.text=Editable.Factory.getInstance().newEditable(it.data.data.phone_number)
                        binding.spinner.onItemSelectedListener =object : AdapterView.OnItemSelectedListener {

                            override fun onItemSelected(
                                parent: AdapterView<*>?,
                                view: View?,
                                position: Int,
                                id: Long
                            ) {
                                binding.childName.text=Editable.Factory.getInstance().newEditable(it.data.data.students[position].full_name)
                                binding.childClass.text=Editable.Factory.getInstance().newEditable(it.data.data.students[position].`class`)
                                binding.childDepartment.text=Editable.Factory.getInstance().newEditable(it.data.data.students[position].section)
                            }

                            override fun onNothingSelected(parent: AdapterView<*>?) {
                                binding.childName.text=Editable.Factory.getInstance().newEditable(it.data.data.students[0].full_name)
                                binding.childClass.text=Editable.Factory.getInstance().newEditable(it.data.data.students[0].`class`)
                                binding.childDepartment.text=Editable.Factory.getInstance().newEditable(it.data.data.students[0].section)

                            }



                        }
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

}