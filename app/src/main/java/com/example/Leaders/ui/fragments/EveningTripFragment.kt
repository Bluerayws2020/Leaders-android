package com.example.tasmeme.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Leaders.model.NetworkResults
import com.example.Leaders.viewModels.AppViewModel
import com.example.tasmeme.R
import com.example.tasmeme.adaptors.EveningTripAdapter
import com.example.tasmeme.databinding.FragmentEveningTripBinding
import com.example.tasmeme.ui.TripActivity


class EveningTripFragment : Fragment() {
        private lateinit var binding:FragmentEveningTripBinding
        private lateinit var adapter:EveningTripAdapter
        private val viewModel by viewModels<AppViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentEveningTripBinding.inflate(layoutInflater)
        getData()

        binding.includedTap.textView.text = getString(R.string.eveningTrip)

        binding.includedTap.backButton.setOnClickListener {
            (activity as TripActivity).onBackPressed()
        }
        binding.includedTap.sideMenuOpener.setOnClickListener {
            (activity as TripActivity).openDrawer()
        }

        viewModel.getTripFromOptions().observe(viewLifecycleOwner){
            when(it){
            is NetworkResults.Success ->{
            if(it.data.status==200){
                adapter.eveningTripList = it.data.data.evening_trip_actions
                adapter.notifyDataSetChanged()
            }else{
                it.data.message?.let{
                        message ->
                    showMessage(message)
                }}
        }
            is NetworkResults.Error ->{
            Log.e("ayham", it.exception.toString())
        }
            else -> {
            //nothing
        }
        }}
        viewModel.retrieveTripFromOptions()

        return binding.root
    }

    private fun getData() {
        viewModel.getTripUsers().observe(viewLifecycleOwner){
            when(it){
                is NetworkResults.Success ->{
                    if(it.data.status==200){
                        adapter.list=it.data.data.students
                        adapter.notifyDataSetChanged()
                    }else{
                        it.data.message?.let{
                                message ->
                            showMessage(message)
                        }}
                }
                is NetworkResults.Error ->{
                    Log.e("ayham", it.exception.toString())
                }
                else -> {
                    //nothing
                }
            }
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter= EveningTripAdapter()
        viewModel.retrieveTripUsers("2")
        binding.apply {

                eveningRec.adapter=adapter
                eveningRec.layoutManager=LinearLayoutManager(requireContext())

            }




}


}