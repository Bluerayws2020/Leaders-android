package com.example.Leaders.ui.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.tasmeme.R
import com.example.tasmeme.databinding.FragmentRequestPermissionBinding


class RequestPermissionFragment : DialogFragment() {

    private lateinit var binding:FragmentRequestPermissionBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawableResource(R.drawable.dialog_background)
        // Add dialog properties as needed
        return dialog
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentRequestPermissionBinding.inflate(layoutInflater)
        return binding.root
    }



}