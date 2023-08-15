package com.mycompany.movies.view.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mycompany.movies.view.activity.DetailsActivity
import com.mycompany.movies.view.fragment.AboutMoviesFragment
import com.mycompany.movies.view.fragment.ProductionFragment

class ViewPagerAdapter(fragment: DetailsActivity) : FragmentStateAdapter(fragment) {

    private val tabView = arrayOf("sobre o Filme", "Produções")
    private val fragment = arrayOf(AboutMoviesFragment(), ProductionFragment())
    override fun getItemCount(): Int {
     return   fragment.size
    }

    override fun createFragment(position: Int): Fragment {
       return fragment[position]
    }

    fun getTab(position: Int): String{
        return tabView[position]
    }
}