package com.example.tasmeme.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.FragmentTransitionSupport
import com.example.tasmeme.R
import com.example.tasmeme.adaptors.MorningTripAdapter
import com.example.tasmeme.adaptors.OnItemClickListener
import com.example.tasmeme.databinding.FragmentTripBinding


class TripFragment : Fragment() {
    private lateinit var binding:FragmentTripBinding
    private lateinit var adapter:MorningTripAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentTripBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= MorningTripAdapter(object:OnItemClickListener{
            override fun onItemClick(position: Int) {
                TODO("Not yet implemented")
            }

        })

        binding.apply {
            morningRec.adapter=adapter
            morningRec.layoutManager=LinearLayoutManager(requireContext())
        }
    }

}