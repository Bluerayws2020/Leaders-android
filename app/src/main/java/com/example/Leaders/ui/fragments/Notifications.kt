package com.example.tasmeme.ui.fragments


import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.example.tasmeme.R
import com.example.tasmeme.databinding.FragmentNotificationsBinding


class Notifications : Fragment() {

    private lateinit var binding:FragmentNotificationsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentNotificationsBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            val leaveStr="المغادرات"
            val leaveNum="(30)"

            val leaveSpanableWhite=SpannableString(leaveStr)
            leaveSpanableWhite.setSpan(ForegroundColorSpan(Color.BLACK),0,leaveStr.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            val leaveSpanableBlue=SpannableString(leaveNum)
            leaveSpanableBlue.setSpan(
                ForegroundColorSpan(ContextCompat.getColor(requireContext(),R.color.action_bar_background)),
                0,
                leaveNum.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            val departureStr="انصراف"
            val departureNum="(12)"

            val departureSpanableWhite=SpannableString(departureStr)
            departureSpanableWhite.setSpan(ForegroundColorSpan(Color.BLACK),0,departureStr.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            val departureSpanableBlue=SpannableString(departureNum)
            departureSpanableBlue.setSpan(
                ForegroundColorSpan(ContextCompat.getColor(requireContext(),R.color.action_bar_background)),
                0,
                departureNum.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            val departureWithStr="انصراف مع شخص"
            val departureWithNum="(10)"

            val departureWithSpanableWhite=SpannableString(departureWithStr)
            departureWithSpanableWhite.setSpan(ForegroundColorSpan(Color.BLACK),0,departureWithStr.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            val departureWithSpanableBlue=SpannableString(departureWithNum)
            departureWithSpanableBlue.setSpan(
                ForegroundColorSpan(ContextCompat.getColor(requireContext(),R.color.action_bar_background)),
                0,
                departureWithNum.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            val departureWithSpannableStringBuilder =SpannableStringBuilder()
            departureWithSpannableStringBuilder.append(departureWithSpanableWhite)
            departureWithSpannableStringBuilder.append(" ")
            departureWithSpannableStringBuilder.append(departureWithSpanableBlue)


            val departureSpannableStringBuilder =SpannableStringBuilder()
            departureSpannableStringBuilder.append(departureSpanableWhite)
            departureSpannableStringBuilder.append(" ")
            departureSpannableStringBuilder.append(departureSpanableBlue)

            val spannableStringBuilder =SpannableStringBuilder()
            spannableStringBuilder.append(leaveSpanableWhite)
            spannableStringBuilder.append(" ")
            spannableStringBuilder.append(leaveSpanableBlue)

            btnLeaves.text=spannableStringBuilder
            btnDeparture.text=departureSpannableStringBuilder
            btnDepartureWithPerson.text=departureWithSpannableStringBuilder

            btnLeaves.setOnClickListener {

                val bundle= Bundle()
                bundle.putString("buttonClicked","leave")
                it.findNavController().navigate(R.id.action_notifications2_to_departure,bundle)
             }
            btnDeparture.setOnClickListener {
                val bundle= Bundle()
                bundle.putString("buttonClicked","departure")
                it.findNavController().navigate(R.id.action_notifications2_to_departure,bundle)
            }
            btnDepartureWithPerson.setOnClickListener {
                val bundle= Bundle()
                bundle.putString("buttonClicked","departureWith")
                it.findNavController().navigate(R.id.action_notifications2_to_departure,bundle)
            }

        }


    }


}