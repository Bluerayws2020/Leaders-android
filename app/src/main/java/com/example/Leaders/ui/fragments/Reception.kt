package com.example.tasmeme.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasmeme.R
import com.example.tasmeme.adaptors.ReceptionAdapter
import com.example.tasmeme.databinding.FragmentReceptionBinding


class Reception : Fragment() {
    private lateinit var adapter:ReceptionAdapter
    private lateinit var binding:FragmentReceptionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentReceptionBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= ReceptionAdapter()
        binding.apply {
            recyclerView.adapter=adapter
            recyclerView.layoutManager=LinearLayoutManager(requireContext())
        }
    }
}