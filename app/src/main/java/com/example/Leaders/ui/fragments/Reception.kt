package com.example.tasmeme.ui.fragments

import android.content.Context
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
import com.example.nerd_android.helpers.HelperUtils.SHARED_PREF
import com.example.nerd_android.helpers.HelperUtils.UID
import com.example.nerd_android.helpers.ViewUtils.hide
import com.example.nerd_android.helpers.ViewUtils.show
import com.example.tasmeme.R
import com.example.tasmeme.adaptors.OnItemClickListener
import com.example.tasmeme.adaptors.ReceptionAdapter
import com.example.tasmeme.databinding.FragmentReceptionBinding
import com.example.tasmeme.ui.ReceptionActivity
import com.example.tasmeme.ui.TripActivity
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator


class Reception : Fragment() {
    private lateinit var adapter:ReceptionAdapter
    private lateinit var binding:FragmentReceptionBinding
    private val viewModel by viewModels<AppViewModel>()
    private lateinit var uid:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentReceptionBinding.inflate(inflater)
        binding.includedTap.textView.text = getString(R.string.orders)
        binding.includedTap.backButton.setOnClickListener {
            (activity as ReceptionActivity).onBackPressed()
        }
        binding.includedTap.sideMenuOpener.setOnClickListener {
            (activity as ReceptionActivity).openDrawer()
        }
        binding.paginationProgressBar.show()
        val sharedPreferences = activity?.getSharedPreferences(SHARED_PREF,Context.MODE_PRIVATE)
        uid= sharedPreferences?.getString(UID,"").toString()

        getData()
        update()
        viewModel.viewAllDepartures(uid)


        return binding.root
    }
    private fun showMessage(message:String){
        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()
    }
    fun getData(){

        viewModel.getViewAllDeparturesLiveData().observe(viewLifecycleOwner){
                result->


            when(result){
                is NetworkResults.Success->{
                    if(result.data.status==200){
                        adapter= ReceptionAdapter(result.data.data.departure,object :OnItemClickListener{
                            override fun onItemClick(position: Int, nid: String) {
                                binding.paginationProgressBar.show()
                                viewModel.updateDepartures(
                                    uid,nid, "7"
                                ) }


                        })
                        binding.apply {
                            recyclerView.adapter=adapter
                            recyclerView.layoutManager=LinearLayoutManager(requireContext())
                            recyclerView.itemAnimator= SlideInUpAnimator()
                            binding.paginationProgressBar.hide()
                        }
                    }
                    else{

                    }

                }
                is NetworkResults.Error->{
                    result.exception.printStackTrace()
                }
            }
        }}
    fun update(){
        viewModel.getUpdateDeparturesLiveData().observe(viewLifecycleOwner){
            when(it){
                is NetworkResults.Success->{
                    if(it.data.status==200){
                        viewModel.viewAllDepartures(uid)
                        binding.paginationProgressBar.hide()
                        showMessage(it.data.message)
                        viewModel.viewAllDepartures(uid)
                    }else{
                        showMessage(it.data.message)
                        binding.paginationProgressBar.hide()
                    }
                }
                is NetworkResults.Error->{
                    Log.e("ayham",it.exception.toString())
                    binding.paginationProgressBar.hide()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}