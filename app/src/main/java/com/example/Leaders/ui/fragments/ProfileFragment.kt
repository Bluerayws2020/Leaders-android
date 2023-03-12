package com.example.tasmeme.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.Leaders.model.NetworkResults
import com.example.Leaders.viewModels.AppViewModel
import com.example.nerd_android.helpers.HelperUtils.ISIN_PER_PRO
import com.example.nerd_android.helpers.HelperUtils.SHARED_PREF
import com.example.nerd_android.helpers.HelperUtils.UID
import com.example.nerd_android.helpers.HelperUtils.getRole
import com.example.nerd_android.helpers.HelperUtils.getUID
import com.example.tasmeme.R
import com.example.tasmeme.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
        private lateinit var binding:FragmentProfileBinding
        private val viewModel by viewModels<AppViewModel>()

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ISIN_PER_PRO=true
        // Inflate the layout for this fragment
        binding= FragmentProfileBinding.inflate(layoutInflater)
        if (getRole(requireContext())=="parent"){
            getParentData()
            viewModel.getParentProfileData(getUID(requireContext()))
        }else{
            getData()
        viewModel.getUserProfileData(getUID(requireContext()))
       }

        return binding.root
    }

    private fun getParentData() {
        viewModel.getParentProfileLiveData().observe(viewLifecycleOwner){
                result->
            when(result){
                is NetworkResults.Success->{
                    if(result.data.status==200){
                        binding.nameTv.text=result.data.data.full_name
                        binding.phoneNumberTv.text=result.data.data.phone_number
                        binding.profileImageFirst.setImageResource(R.drawable.leaders)
                    }

                }
                is NetworkResults.Error->{
                    Log.e("ayham",result.exception.toString())
                }
            }

        }
    }

    private fun getData(){
        viewModel.getUserProfileLiveData().observe(viewLifecycleOwner){
                result->
            when(result){
                is NetworkResults.Success->{
                    if(result.data.status==200){
                        binding.nameTv.text=result.data.data.full_name
                        binding.phoneNumberTv.text=result.data.data.phone_number
                        binding.profileImageFirst.setImageResource(R.drawable.leaders)
                    }

                }
                is NetworkResults.Error->{
                    Log.e("ayham",result.exception.toString())
                }
            }

        }
    }


}