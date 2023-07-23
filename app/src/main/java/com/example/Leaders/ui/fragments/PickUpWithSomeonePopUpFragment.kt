package com.example.tasmeme.ui.fragments

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.Leaders.model.NetworkResults
import com.example.Leaders.viewModels.AppViewModel
import com.example.nerd_android.helpers.HelperUtils
import com.example.nerd_android.helpers.ViewUtils.hide
import com.example.tasmeme.R
import com.example.tasmeme.databinding.FragmentPickUpWithSomeonePopUpBinding
import com.example.tasmeme.ui.ParentActivity


class PickUpWithSomeonePopUpFragment : DialogFragment() {

    lateinit var binding:FragmentPickUpWithSomeonePopUpBinding
    private val viewModel by viewModels<AppViewModel>()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawableResource(R.drawable.dialog_background)
        // Add dialog properties as need

        //dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentPickUpWithSomeonePopUpBinding.inflate(layoutInflater)
        getDataForDeparture()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var nid = ParentsOptionsFragment.nid
        var uid= HelperUtils.getUID(requireContext())
//        var person_name= binding.personName.text.toString()
//        var personNumber=binding.phoneNumberLeaveWithPerson.text.toString()
//        var personRelative=binding.relativeRelation.text.toString()
        binding.leaveSendBtn.setOnClickListener {
        viewModel.createDeparture(nid.toString(),"2",uid,binding.personName.text.toString(),binding.phoneNumberLeaveWithPerson.text.toString(),binding.relativeRelation.text.toString())
        }
    }

    private fun getDataForDeparture() {
        viewModel.getCreateDepartureLiveData().observe(viewLifecycleOwner){
            when(it){
                is NetworkResults.Success ->{
                    if (it.data.status==200){
                        showMessage("تم اتمام طلبك بنجاح")
                        dismiss()
                        (requireActivity() as ParentActivity).goToProfile()
                    }else if (it.data.status == 400){
                        showMessage(it.data.message ?: "unExpected error")
                    }
                }

                is NetworkResults.Error->{
                    it.exception.printStackTrace()
                }
            }
        }
    }
    fun showMessage(message:String){
        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
    }

}