package com.example.tasmeme.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tasmeme.R
import com.example.tasmeme.databinding.FragmentChildTabBinding

class ChildTabFragment : Fragment() {
    private lateinit var binding:FragmentChildTabBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentChildTabBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        binding.apply {
            localCheckBox.setOnClickListener{
                globalCheckBox.isChecked=false
                localCheckBox.isChecked=true
            }
            globalCheckBox.setOnClickListener{
                globalCheckBox.isChecked=true
                localCheckBox.isChecked=false
            }
        }

        return binding.root
    }


}