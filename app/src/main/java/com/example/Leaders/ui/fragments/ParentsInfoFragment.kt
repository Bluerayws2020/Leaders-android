package com.example.tasmeme.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.util.Log.e
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.ViewPager
import com.example.Leaders.adaptors.ChildTapAdapter
import com.example.Leaders.model.ChildData
import com.example.Leaders.model.ChildListData
import com.example.Leaders.model.LoginResponseModel
import com.example.Leaders.model.NetworkResults
import com.example.Leaders.viewModels.AppViewModel
import com.example.nerd_android.helpers.HelperUtils
import com.example.nerd_android.helpers.HelperUtils.CHILD_LIST
import com.example.nerd_android.helpers.HelperUtils.ISIN_PER
import com.example.nerd_android.helpers.HelperUtils.getFullName
import com.example.nerd_android.helpers.ViewUtils.hide
import com.example.nerd_android.helpers.ViewUtils.isInputEmpty
import com.example.nerd_android.helpers.ViewUtils.show
import com.example.tasmeme.adaptors.ViewPagerAdapter
import com.example.tasmeme.databinding.FragmentParentsInfoBinding
import com.example.tasmeme.ui.ParentActivity
import com.example.tasmeme.ui.fragments.ChildTabFragment.Companion.recyclerView

class ParentsInfoFragment : Fragment() {
    private lateinit var binding:FragmentParentsInfoBinding
    private val viewModel by viewModels<AppViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ISIN_PER=true
        binding= FragmentParentsInfoBinding.inflate(layoutInflater)
        binding.scroll.isFillViewport=true
        registerParent()
        return binding.root
    }

    private fun registerParent() {
        viewModel.getRetrieveParentRegistrationLiveData().observe(viewLifecycleOwner){
            when(it){
                is NetworkResults.Success->{
                    if (it.data.status==200){
                        showMessage("تم التسجيل بنجاح")
                        saveData(it)
                        binding.paginationProgressBar.hide()
                        startActivity(Intent(activity,ParentActivity::class.java))
                        activity?.finish()
                    }else{
                        binding.paginationProgressBar.hide()
                        showMessage(it.data.message.toString())
                        e("ayham",it.data.message.toString())
                    }
                }
                is NetworkResults.Error->{
                    binding.paginationProgressBar.hide()
                    showMessage(it.exception.toString())
                    e("ayham",it.exception.toString())
                }
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.paginationProgressBar.hide()

        //language of device
        //val language:String=Locale.getDefault().language

        val adapter = ViewPagerAdapter(parentFragmentManager)
        adapter.addFragment(ParentInfoTabFragment(), "معلوماتي")
        adapter.addFragment(ChildTabFragment(), "معلومات طفلي")
        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        binding.addNewNameTv.hide()
        binding.addNewNameIv.hide()
        binding.addNewNameIv.isClickable = false
        binding.addNewNameTv.isClickable = false
        binding.sendBtn.text = "التالي"

        binding.addNewNameIv.setOnClickListener {
            addAnotherChild()
        }
        binding.addNewNameTv.setOnClickListener {
            addAnotherChild()
        }

        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                ParentInfoTabFragment.saveParentData()
                //nothing
            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        binding.addNewNameTv.hide()
                        binding.addNewNameIv.hide()
                        binding.addNewNameIv.isClickable = false
                        binding.addNewNameTv.isClickable = false
                        binding.sendBtn.text = "التالي"

                    }
                    1 -> {
                        binding.addNewNameTv.show()
                        binding.addNewNameIv.show()
                        binding.addNewNameIv.isClickable = true
                        binding.addNewNameTv.isClickable = true
                        binding.sendBtn.text = "ارسال"
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
                //nothing
                ParentInfoTabFragment.saveParentData()
            }

        })


        binding.sendBtn.setOnClickListener {

//            val nationalNumber=ParentInfoTabFragment.binding.nationalNumberTv.text.toString()
//            val email=ParentInfoTabFragment.binding.emailTv.text.toString()
//            val phoneNumber=ParentInfoTabFragment.binding.phoneNumberTv.text.toString()
//            val password =ParentInfoTabFragment.binding.passwordTv.text.toString()
            d("ayham", CHILD_LIST.toString())

            val list = mutableListOf<ChildData>()
            if (binding.viewPager.currentItem == 0) {
                ParentInfoTabFragment.saveParentData()
                binding.viewPager.setCurrentItem(1, true)
            } else {
                d("ayham",ParentInfoTabFragment.nationalId)
                if (ParentInfoTabFragment.nationalId.isEmpty()) {
                    showMessage("Please Enter Parent National Number")

                } else if (ParentInfoTabFragment.email.isEmpty()) {

                    showMessage("Please Enter Parent email")

                } else if (ParentInfoTabFragment.phone.isEmpty()) {
                    showMessage("Please Enter Parent Phone Number")
                } else if (ParentInfoTabFragment.password.isEmpty()) {
                    showMessage("Please Enter Parent Password")
                } else {
                    binding.paginationProgressBar.show()
                    val nationalNumber=ParentInfoTabFragment.nationalId
                    val password=ParentInfoTabFragment.password
                    val phoneNumber=ParentInfoTabFragment.phone
                    val email=ParentInfoTabFragment.email
                    val fullname = ParentInfoTabFragment.fullName
                    for(i in CHILD_LIST.indices){
                        list.add(i, ChildData(CHILD_LIST[i].name, CHILD_LIST[i].department,
                            CHILD_LIST[i].grade, CHILD_LIST[i].section))
                    }

                    viewModel.retrieveParentRegistration(
                        nationalNumber,
                        password,
                        email,
                        phoneNumber,
                        list,
                        fullname
                    )
                    Log.d("data", list.toString())
                }
            }

        }


    }


    private fun showMessage(message: String){
        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
    }
    private fun addAnotherChild() {
        CHILD_LIST.add(ChildListData("","","","1", CHILD_LIST.lastIndex+1))
        ChildTabFragment.adapter.differ.submitList(CHILD_LIST)
        ChildTabFragment.adapter.notifyItemInserted(CHILD_LIST.size-1)
    }
    private fun saveData(result: NetworkResults.Success<LoginResponseModel>){

        Log.d("ayhamm",result.data.data.uid)
        val sharedPreferences = activity?.getSharedPreferences(
            HelperUtils.SHARED_PREF,
            AppCompatActivity.MODE_PRIVATE
        )
        val editor= sharedPreferences?.edit()
        editor?.putString(HelperUtils.UID,result.data.data.uid)
        editor?.putString(HelperUtils.TOKEN,result.data.data.token)
        editor?.putString(HelperUtils.ROLE,result.data.data.role)
        editor?.putString(HelperUtils.FULL_NAME,result.data.data.full_name)
        editor?.apply()

    }

}
