package com.example.tasmeme.ui.fragments

import DepartureAdapter
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.Leaders.model.NetworkResults
import com.example.Leaders.viewModels.AppViewModel
import com.example.nerd_android.helpers.HelperUtils.ISIN
import com.example.nerd_android.helpers.HelperUtils.SHARED_PREF
import com.example.nerd_android.helpers.HelperUtils.UID
import com.example.nerd_android.helpers.HelperUtils.showMessage
import com.example.nerd_android.helpers.ViewUtils.hide
import com.example.nerd_android.helpers.ViewUtils.show
import com.example.tasmeme.R
import com.example.tasmeme.adaptors.LeaveAdapter
import com.example.tasmeme.databinding.FragmentDepartureBinding
import com.example.tasmeme.ui.ManagerActivity
import com.example.tasmeme.ui.fragments.Notifications.Companion.DEPARTURE_TYPE
import de.hdodenhof.circleimageview.CircleImageView

class Departure : Fragment() {

    private lateinit var binding:FragmentDepartureBinding
    private val viewModel by viewModels<AppViewModel>()
    private lateinit var adapter:DepartureAdapter
    private lateinit var uid:String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        ISIN=true
        binding= FragmentDepartureBinding.inflate(layoutInflater)
        when(arguments?.getString(DEPARTURE_TYPE)){
            "2"->  setDepartureWithLayout()
            "1" -> setDepartureLayout()
        }

//        refresh
        binding.swipeToRefreshLayout.setOnRefreshListener {
            binding.wait.show()
            binding.pb.show()
            viewModel.viewAllDepartures(uid)
        }

        binding.includedTap.backButton.setOnClickListener {
            (activity as ManagerActivity).onBackPressed()
        }
        binding.includedTap.sideMenuOpener.setOnClickListener {
            (activity as ManagerActivity).openDrawer()
        }
        binding.pb.show()
        val sharedPreferences= activity?.getSharedPreferences(SHARED_PREF,Context.MODE_PRIVATE)
        uid = sharedPreferences?.getString(UID,"").toString()

        update()
        getdata()

        viewModel.viewAllDepartures(uid)

        return binding.root
    }
//  observe to Live Data
    private fun getdata() {
        viewModel.getViewAllDeparturesLiveData().observe(viewLifecycleOwner){
                result->
            when(result){
                is NetworkResults.Success ->{

                    binding.swipeToRefreshLayout.isRefreshing = false

                    if(result.data.status==200){

                        //filter list to show only students with status 5 & 8 and what is the departure type
                        val filteredList = result.data.data.departure.filter {
                            (it.status == "5" || it.status == "8") &&
                            it.departure_type == arguments?.getString(DEPARTURE_TYPE)
                        }


                        adapter.submitList(filteredList)
                        adapter.notifyDataSetChanged()
                        binding.pb.hide()
                        binding.wait.hide()

                        adapter.onButtonApproveClicked {
                            binding.pb.show()
                            viewModel.updateDepartures(
                                uid,it.nid,"6"
                            )
                            viewModel.viewAllDepartures(uid)


                        }
                        adapter.onButtonRejectClicked {
                            binding.pb.show()
                            viewModel.updateDepartures(
                                uid,it.nid,"8"
                            )
                            viewModel.viewAllDepartures(uid)

                        }

                    }
                    else if (result.data.status == 400){
                        showMessage(requireContext(),result.data.message ?:"unExpected Error")
                    }

                }
                is NetworkResults.Error ->{

                    binding.pb.hide()
                    binding.wait.hide()
                    Log.e("ayham",result.exception.toString())
                }

            }
        }
    }

    private fun setDepartureWithLayout() {
        binding.apply {
            departureType.text = "الانصراف مع شخص"
            binding.includedTap.textView.text = getString(R.string.departure_with_someOne)

            adapter=DepartureAdapter()
            recyclerDepature.adapter=adapter
            recyclerDepature.layoutManager=LinearLayoutManager(requireContext())
        }
    }

    private fun setDepartureLayout() {
        binding.apply {
            departureType.text = "انصراف"
            binding.includedTap.textView.text = getString(R.string.departure)
            adapter= DepartureAdapter()
            recyclerDepature.adapter=adapter
            recyclerDepature.layoutManager=LinearLayoutManager(requireContext())
    }}

    // observe to update departure status live data
    private fun update(){
        viewModel.getUpdateDeparturesLiveData().observe(viewLifecycleOwner){
            when(it){
                is NetworkResults.Success->{
                    //nothing
                    viewModel.viewAllDepartures(uid)
                    showMessage(requireContext(),it.data.message ?: "unExpected Error")

                }
                is NetworkResults.Error ->{
                    Log.e("ayham",it.exception.toString())
                }
            }
        }
    }




}