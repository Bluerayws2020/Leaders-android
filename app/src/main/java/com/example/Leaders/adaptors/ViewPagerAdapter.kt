package com.example.tasmeme.adaptors

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(fragment: FragmentManager):FragmentStatePagerAdapter(fragment) {
    private var mFragmentList=ArrayList<Fragment>()
    private var mFragmentTiles=ArrayList<String>()

    override fun getCount(): Int =mFragmentList.size

    override fun getItem(position: Int): Fragment =mFragmentList[position]

    override fun getPageTitle(position: Int):String=mFragmentTiles[position]

    fun addFragment(fragment: Fragment,title:String){
        mFragmentList.add(fragment)
        mFragmentTiles.add(title)
    }


}