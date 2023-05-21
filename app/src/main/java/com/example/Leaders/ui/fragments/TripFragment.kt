package com.example.tasmeme.ui.fragments

import android.os.Bundle
import android.util.Log.e
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.FragmentTransitionSupport
import com.example.Leaders.model.NetworkResults
import com.example.Leaders.viewModels.AppViewModel
import com.example.tasmeme.R
import com.example.tasmeme.adaptors.MorningTripAdapter
import com.example.tasmeme.adaptors.OnItemClickListener
import com.example.tasmeme.databinding.FragmentTripBinding


class TripFragment : Fragment() {
    private lateinit var binding:FragmentTripBinding
    private lateinit var adapter:MorningTripAdapter
    private val viewModel by viewModels<AppViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getData()
        binding= FragmentTripBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.retrieveTripUsers("1")
        adapter= MorningTripAdapter(object:OnItemClickListener{

            override fun onItemClick(position: Int, nid: String) {

            }

        })


        binding.apply {
            morningRec.adapter=adapter
            morningRec.layoutManager=LinearLayoutManager(requireContext())
        }
    }
private fun getData(){
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
                e("ayham",it.exception.toString())
            }
        }
    }

}
    private fun showMessage(message:String){
        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
    }
}