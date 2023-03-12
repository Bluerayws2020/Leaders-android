package com.example.tasmeme.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Leaders.adaptors.ChildTapAdapter
import com.example.nerd_android.helpers.HelperUtils.CHILD_LIST
import com.example.nerd_android.helpers.HelperUtils.IS_IN_CHILD
import com.example.tasmeme.R
import com.example.tasmeme.databinding.FragmentChildTabBinding
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator

class ChildTabFragment : Fragment() {

    companion object{
        private lateinit var binding:FragmentChildTabBinding
        @SuppressLint("StaticFieldLeak")
        lateinit var adapter:ChildTapAdapter
        val items= mutableListOf<String>("Item 1")
        lateinit var recyclerView:RecyclerView
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentChildTabBinding.inflate(layoutInflater)

        adapter= ChildTapAdapter(items)
        recyclerView= binding.recycler
        recyclerView.adapter= adapter
        recyclerView.itemAnimator=SlideInUpAnimator()
        recyclerView.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

        return binding.root
    }


}