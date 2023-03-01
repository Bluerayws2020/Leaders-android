package com.example.tasmeme.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasmeme.R
import com.example.tasmeme.adaptors.EveningTripAdapter
import com.example.tasmeme.adaptors.OnItemClickListener
import com.example.tasmeme.databinding.FragmentEveningTripBinding


class EveningTripFragment : Fragment() {
        private lateinit var binding:FragmentEveningTripBinding
        private lateinit var adapter:EveningTripAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentEveningTripBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter= EveningTripAdapter(object :OnItemClickListener{
            override fun onItemClick(position: Int) {
                TODO("Not yet implemented")
            }

        })
        binding.apply {

            eveningRec.adapter=adapter
            eveningRec.layoutManager=LinearLayoutManager(requireContext())

        }
    }


}