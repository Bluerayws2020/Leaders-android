package com.example.Leaders.ui.fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.tasmeme.R
import com.example.tasmeme.databinding.CreatTripPopupBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CreateTripPopUb : DialogFragment() {

    lateinit var binding: CreatTripPopupBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        // Set the dialog background
        dialog.window?.setBackgroundDrawableResource(R.drawable.dialog_background)
        // For example, you can set animation, gravity, etc. here

        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CreatTripPopupBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }


}