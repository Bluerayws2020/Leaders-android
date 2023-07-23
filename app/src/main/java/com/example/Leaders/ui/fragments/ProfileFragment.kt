package com.example.tasmeme.ui.fragments

import android.annotation.SuppressLint
import android.app.ActionBar
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.Leaders.adaptors.StringSpinnerAdapter
import com.example.Leaders.model.NetworkResults
import com.example.Leaders.ui.fragments.CreateTripPopUb
import com.example.Leaders.viewModels.AppViewModel
import com.example.nerd_android.helpers.HelperUtils.ISIN_PER_PRO
import com.example.nerd_android.helpers.HelperUtils.SHARED_PREF
import com.example.nerd_android.helpers.HelperUtils.UID
import com.example.nerd_android.helpers.HelperUtils.getRole
import com.example.nerd_android.helpers.HelperUtils.getUID
import com.example.nerd_android.helpers.HelperUtils.showMessage
import com.example.nerd_android.helpers.ViewUtils.hide
import com.example.tasmeme.R
import com.example.tasmeme.databinding.FragmentProfileBinding
import com.example.tasmeme.ui.TripActivity


class ProfileFragment : Fragment() {
        private lateinit var binding:FragmentProfileBinding
        private val viewModel by viewModels<AppViewModel>()
    private lateinit var mpopup:PopupWindow

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ISIN_PER_PRO=true
        // Inflate the layout for this fragment
        binding= FragmentProfileBinding.inflate(layoutInflater)

        binding.includedTap.textView.text = getString(R.string.my_profile)

        binding.includedTap.sideMenuOpener.setOnClickListener {
            (activity as TripActivity).openDrawer()
        }
        binding.includedTap.cardView.hide()
        binding.includedTap.cardView.isClickable = false
        binding.includedTap.backButton.isClickable = false

        Toast.makeText(requireContext(),"معلوماتي",Toast.LENGTH_SHORT).show()
        if (getRole(requireContext())=="parent"){
            getParentData()
            viewModel.getParentProfileData(getUID(requireContext()))
        }else{
            getData()
        viewModel.getUserProfileData(getUID(requireContext()))
       }

        binding.profileImageFirst.setOnClickListener{
            showPopUp()
        }

        return binding.root
    }

    private fun getParentData() {
        viewModel.getParentProfileLiveData().observe(viewLifecycleOwner){
                result->
            when(result){
                is NetworkResults.Success->{
                    if(result.data.status==200){
                        binding.nameTv.text=result.data.data.full_name
                        binding.phoneNumberTv.text=result.data.data.phone_number
                        binding.profileImageFirst.setImageResource(R.drawable.leaders)
                    }

                }
                is NetworkResults.Error->{
                    Log.e("ayham",result.exception.toString())
                }
            }

        }
    }

    private fun getData(){
        viewModel.getUserProfileLiveData().observe(viewLifecycleOwner){
                result->
            when(result){
                is NetworkResults.Success->{
                    if(result.data.status==200){
                        binding.nameTv.text=result.data.data.full_name
                        binding.phoneNumberTv.text=result.data.data.phone_number
                        binding.profileImageFirst.setImageResource(R.drawable.leaders)
                    }else if (result.data.status == 400){
                        showMessage(requireContext(),result.data.message ?:"unExpected Error")
                    }

                }
                is NetworkResults.Error->{
                    Log.e("ayham",result.exception.toString())
                }
            }

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



}

    override fun onResume() {
        super.onResume()
    }

    private fun showPopUp(){


        // Inflate the popup layout
        val popupView:View = layoutInflater.inflate(R.layout.creat_trip_popup, null)

        // Create the PopupWindow
        mpopup = PopupWindow(
            popupView, ActionBar.LayoutParams.FILL_PARENT,
            ActionBar.LayoutParams.MATCH_PARENT, true
        )

        mpopup!!.animationStyle = android.R.style.Animation_Dialog
        mpopup!!.showAtLocation(popupView, Gravity.CENTER, 0, 0) // Displaying popup

        // Handle interactions in the popup
        val closeButton = popupView.findViewById<ImageView>(R.id.close)
        val spinner = popupView.findViewById<Spinner>(R.id.spinner)
        val adapter = StringSpinnerAdapter(requireContext(), listOf("dummy1","dummy2","dummy3","dummy4"))
        spinner.adapter = adapter
        closeButton.setOnClickListener {
            // Dismiss the popup window when the button is clicked
            mpopup.dismiss()
        }
    }



}