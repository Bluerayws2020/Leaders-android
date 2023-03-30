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
import com.example.tasmeme.adaptors.OnItemClickListener
import com.example.tasmeme.databinding.FragmentEveningTripBinding


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

        adapter= EveningTripAdapter(object :OnItemClickListener{
            override fun onItemClick(position: Int, nid: String) {

            }})
        viewModel.retrieveTripUsers()
        binding.apply {

                eveningRec.adapter=adapter
                eveningRec.layoutManager=LinearLayoutManager(requireContext())

            }



}}