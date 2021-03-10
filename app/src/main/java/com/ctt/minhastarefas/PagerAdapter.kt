package com.ctt.minhastarefas

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ctt.minhastarefas.fragments.FazerFragment
import com.ctt.minhastarefas.fragments.FeitasFragment
import com.ctt.minhastarefas.fragments.ProgressoFragment

class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when(position){

            0 -> FazerFragment()
            1 -> ProgressoFragment()
            2 -> FeitasFragment()
            else -> FazerFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "A fazer"
            1 -> "Em progresso"
            2 -> "Feitas"
            else -> super.getPageTitle(position)
        }
    }
}