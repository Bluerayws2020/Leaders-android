package com.example.tasmeme.ui.fragments

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Leaders.model.NetworkResults
import com.example.Leaders.viewModels.AppViewModel
import com.example.nerd_android.helpers.HelperUtils.ISIN
import com.example.nerd_android.helpers.HelperUtils.SHARED_PREF
import com.example.nerd_android.helpers.HelperUtils.UID
import com.example.nerd_android.helpers.ViewUtils.hide
import com.example.nerd_android.helpers.ViewUtils.show
import com.example.tasmeme.R
import com.example.tasmeme.adaptors.DepartureAdapter
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

    private fun getdata() {

        viewModel.getViewAllDeparturesLiveData().observe(viewLifecycleOwner){
                result->
            when(result){
                is NetworkResults.Success ->{
                    if(result.data.status==200){
                        adapter.differ.submitList(result.data.data.departure)
                        adapter.notifyDataSetChanged()
                        binding.pb.hide()
                        adapter.onButtonApproveClicked {
                            binding.pb.show()
                            viewModel.updateDepartures(
                                uid,it.nid,"6"
                            )


                        }
                        adapter.onButtonRejectClicked {
                            binding.pb.show()
                            viewModel.updateDepartures(
                                uid,it.nid,"8"
                            )
                            viewModel.viewAllDepartures(uid)


                            }

                    }

                }
                is NetworkResults.Error ->{
                    binding.pb.hide()
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

    private fun update(){
        viewModel.getUpdateDeparturesLiveData().observe(viewLifecycleOwner){
            when(it){
                is NetworkResults.Success->{
                    //nothing
                    viewModel.viewAllDepartures(uid)
                    showMessage(it.data.message)

                }
                is NetworkResults.Error ->{
                    Log.e("ayham",it.exception.toString())
                }
            }
        }
    }

private fun showMessage(message:String){
    Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
}



}