package com.example.tasmeme.ui.fragments


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Leaders.adaptors.NotificationsAdapter
import com.example.Leaders.model.NetworkResults
import com.example.Leaders.viewModels.AppViewModel
import com.example.nerd_android.helpers.HelperUtils.SHARED_PREF
import com.example.nerd_android.helpers.HelperUtils.UID
import com.example.nerd_android.helpers.ViewUtils.hide
import com.example.nerd_android.helpers.ViewUtils.show
import com.example.tasmeme.R
import com.example.tasmeme.databinding.FragmentNotificationsBinding
import com.example.tasmeme.ui.ManagerActivity
import com.example.tasmeme.ui.TripActivity


class Notifications : Fragment() {

    private lateinit var binding:FragmentNotificationsBinding
    private val viewModel by viewModels<AppViewModel>()
    private lateinit var adapter:NotificationsAdapter
    companion object{
        const val DEPARTURE_TYPE="DEPARTURE_TYPE"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentNotificationsBinding.inflate(layoutInflater)
        binding.pb.show()
        binding.includedTap.textView.text = getString(R.string.notifications)
        binding.includedTap.backButton.setOnClickListener {
            (activity as ManagerActivity).onBackPressed()
        }
        binding.includedTap.sideMenuOpener.setOnClickListener {
            (activity as ManagerActivity).openDrawer()
        }
        val sharedPreferences=activity?.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        val uid = sharedPreferences?.getString(UID,"")

        adapter= NotificationsAdapter(requireContext())
        binding.recyclerView.adapter=adapter

        getData()

        viewModel.getCurrentDepartureInfo(uid!!)

        binding.recyclerView.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        return binding.root
    }

    private fun getData() {
        viewModel.getCurrentDepartureInfoLiveData().observe(viewLifecycleOwner){
            when(it){
                is NetworkResults.Success->{
                    if(it.data.status==200){
                        binding.pb.hide()
                        adapter.differ.submitList(it.data.data)
                        d("ayham",it.data.data.toString())
                        adapter.onItemClickListener {
                            val bundle= Bundle()
                            bundle.putString(DEPARTURE_TYPE,it.id)
                            findNavController().navigate(R.id.departure,bundle)
                        }
                    }else if(it.data.status  == 400){
                        binding.pb.hide()
                        showMessage(it.data.message.toString())
                    }
                }
                is NetworkResults.Error->{
                    binding.pb.hide()
                    Log.e("ayham",it.exception.toString())
                }
            }
        }

        }
    private fun showMessage(message:String){
        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


}