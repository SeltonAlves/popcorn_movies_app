package com.mycompany.movies.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mycompany.movies.view.fragment.CurrentlyInMoviesFragment
import com.mycompany.movies.view.fragment.FutureMoviesFragment

class TabViewPagerAdapter(
    fragment: FragmentActivity
) : FragmentStateAdapter(fragment) {
    private val tab = arrayOf("Atualmente nos Cinemas", "Estão Por vim")
    private val fragment = arrayOf(CurrentlyInMoviesFragment(), FutureMoviesFragment())

    override fun getItemCount(): Int = fragment.size

    override fun createFragment(position: Int): Fragment {
        return fragment[position]
    }

    fun getTabText(position: Int): String {
        return tab[position]
    }
}