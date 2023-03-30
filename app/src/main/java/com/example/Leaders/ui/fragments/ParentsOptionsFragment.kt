package com.example.tasmeme.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.example.Leaders.adaptors.SpinnerAdapter
import com.example.Leaders.model.NetworkResults
import com.example.Leaders.model.Student
import com.example.Leaders.model.ViewParentProfileInfoModel
import com.example.Leaders.ui.fragments.RequestPermissionFragment
import com.example.Leaders.viewModels.AppViewModel
import com.example.nerd_android.helpers.HelperUtils.getUID
import com.example.nerd_android.helpers.ViewUtils.hide
import com.example.nerd_android.helpers.ViewUtils.show
import com.example.tasmeme.databinding.FragmentParentsOptionsBinding
import java.util.Objects


class ParentsOptionsFragment : Fragment() {
    private lateinit var binding:FragmentParentsOptionsBinding
    private val viewModel by viewModels<AppViewModel>()
    private var spinnerResults:NetworkResults.Success<ViewParentProfileInfoModel>?=null
    private var isOneClicked=false
    private var isTwoClicked=false
    private var isThreeClicked=false


    companion object{
        var uid:String?=null
        var nid:String?=null

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getData()
        binding= FragmentParentsOptionsBinding.inflate(layoutInflater)



        getDataForDeparture()
        // Inflate the layout for this fragment
        return binding.root

    }

    private fun getDataForDeparture() {
        viewModel.getCreateDepartureLiveData().observe(viewLifecycleOwner){
            when(it){
                is NetworkResults.Success ->{
                    binding.pb.hide()
                    if (it.data.status==200){
                        val showPopUp= PleaseWaitFragment ()
                        showPopUp.show((activity as AppCompatActivity).supportFragmentManager,"showPopUp")
                    }
                }

                is NetworkResults.Error->{
                    binding.pb.hide()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            //radio button click mechanism
            departureCheckbox.setOnClickListener{
                departureWihSomeoneCheckBox.isChecked=false
                departureCheckbox.isChecked=true
                requestPermission.isChecked=false
                isTwoClicked=false
                isThreeClicked=false
                if(isOneClicked) {
                    departureCheckbox.isChecked = false
                    isOneClicked=false

                }else{
                    departureCheckbox.isChecked = true
                    isOneClicked=true

                }

            }
            departureWihSomeoneCheckBox.setOnClickListener{
                departureCheckbox.isChecked=false
                departureWihSomeoneCheckBox.isChecked=true
                requestPermission.isChecked=false
                isOneClicked=false
                isThreeClicked=false
                if(isTwoClicked) {
                    departureWihSomeoneCheckBox.isChecked = false
                    isTwoClicked=false
                }else{
                    departureWihSomeoneCheckBox.isChecked = true
                    isTwoClicked=true

                }

            }
            requestPermission.setOnClickListener {
                departureWihSomeoneCheckBox.isChecked=false
                departureCheckbox.isChecked=false
                requestPermission.isChecked=true
                isTwoClicked=false
                isOneClicked=false
                if(isThreeClicked) {
                    requestPermission.isChecked = false
                    isThreeClicked=false

                }else{
                    requestPermission.isChecked = true
                    isThreeClicked=true

                }

            }


        btnSend.setOnClickListener{
            //val showPopUp=  PickUpWithSomeonePopUpFragment()
            //showPopUp.show((activity as AppCompatActivity).supportFragmentManager,"showPopUp")

            if(departureCheckbox.isChecked==true){
                binding.pb.show()
                var spinnerPosition=0
                binding.spinner.onItemSelectedListener =object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                       spinnerPosition=position
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        spinnerPosition=0
                    }



             }
                var nid= spinnerResults!!.data.data.students[spinnerPosition].sid
                var departureType="1"
                var uid= getUID(requireContext())
                viewModel.createDeparture(nid,departureType,uid,"1","1","1")


            }else if (departureWihSomeoneCheckBox.isChecked==true) {
                val showPopUp = PickUpWithSomeonePopUpFragment()
                showPopUp.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
                var spinnerPosition=0
                binding.spinner.onItemSelectedListener =object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        spinnerPosition=position
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        spinnerPosition=0
                    }



                }
                 nid = spinnerResults!!.data.data.students[spinnerPosition].sid
                //var departureType="1"
                // uid= getUID(requireContext())
            }
            else if(requestPermission.isChecked==true){
                val showPopUp=RequestPermissionFragment()
                showPopUp.show((activity as AppCompatActivity).supportFragmentManager,"showPopUp")
                var spinnerPosition=0
                binding.spinner.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        spinnerPosition=position
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        spinnerPosition=0
                    }

                }
                nid= spinnerResults!!.data.data.students[spinnerPosition].sid
            }
            else{
                Toast.makeText(requireContext(),"الرجاء اختبار احد خيارات المغادرة ",Toast.LENGTH_LONG).show()
            }
        }}
        binding.pb.show()
        viewModel.getParentProfileData(getUID(requireContext()))
    }

    private fun getData() {
        viewModel.getParentProfileLiveData().observe(viewLifecycleOwner){
            when(it){
                is NetworkResults.Success ->{
                    binding.pb.hide()
                    if(it.data.status==200){
                        setUpSpinner(requireContext(),it.data.data.students)
                        spinnerResults=it
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

