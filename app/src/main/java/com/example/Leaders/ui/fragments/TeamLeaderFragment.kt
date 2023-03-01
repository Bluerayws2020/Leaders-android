package com.example.tasmeme.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasmeme.R
import com.example.tasmeme.adaptors.AdminRecyclerAdapter
import com.example.tasmeme.adaptors.MorningTripAdapter
import com.example.tasmeme.databinding.FragmentTeamLeaderBinding


class TeamLeaderFragment : Fragment() {
   private lateinit var binding:FragmentTeamLeaderBinding
   private lateinit var adapter: AdminRecyclerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter= AdminRecyclerAdapter()
        binding= FragmentTeamLeaderBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            departmentManagerRecycler.adapter=adapter
            departmentManagerRecycler.layoutManager=LinearLayoutManager(requireContext())
        }
    }

}