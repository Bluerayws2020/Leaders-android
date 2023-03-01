package com.example.tasmeme.ui.fragments

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tasmeme.adaptors.ViewPagerAdapter
import com.example.tasmeme.databinding.FragmentParentsInfoBinding
import java.util.Locale


class ParentsInfoFragment : Fragment() {
    private lateinit var binding:FragmentParentsInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentParentsInfoBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //language of device
        val language:String=Locale.getDefault().language

            val adapter=ViewPagerAdapter(parentFragmentManager)
            adapter.addFragment(ChildTabFragment(),"معلومات طفلي")
            adapter.addFragment(ParentInfoTabFragment(),"معلوماتي")
            binding.viewPager.adapter=adapter
            binding.tabLayout.setupWithViewPager(binding.viewPager)
        }

    }
