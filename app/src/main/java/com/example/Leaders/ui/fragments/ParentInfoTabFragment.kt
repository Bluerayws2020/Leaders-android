package com.example.tasmeme.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.tasmeme.R
import com.example.tasmeme.databinding.ChiledItemBinding
import com.example.tasmeme.databinding.FragmentParentInfoTabBinding
import com.example.tasmeme.databinding.FragmentParentsInfoBinding


class ParentInfoTabFragment : Fragment() {
    companion object {
        private lateinit var binding: FragmentParentInfoTabBinding
        lateinit var nationalId: String
        lateinit var email: String
        lateinit var phone: String
        lateinit var password: String
        lateinit var fullName: String

        fun saveParentData() {
            nationalId = binding.nationalNumberTv.text.toString()
            d("parentAyham", nationalId)
            email = binding.emailTv.text.toString()
            phone = binding.phoneNumberTv.text.toString()
            password = binding.passwordTv.text.toString()
            fullName = binding.fullName.text.toString()
        }

        fun setBinding(fragmentBinding: FragmentParentInfoTabBinding) {
            binding = fragmentBinding
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_parent_info_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBinding(FragmentParentInfoTabBinding.bind(view))
    }
}
