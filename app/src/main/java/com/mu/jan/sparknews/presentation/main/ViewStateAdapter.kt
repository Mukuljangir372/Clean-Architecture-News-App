package com.mu.jan.sparknews.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mu.jan.sparknews.data.local.NewsCategories
import com.mu.jan.sparknews.presentation.news.NewsFragment
import com.mu.jan.sparknews.utils.Const

class ViewStateAdapter(fragmentManager: FragmentManager,
                       lifecycle: Lifecycle
                       ) : FragmentStateAdapter(fragmentManager,lifecycle) {

    override fun getItemCount(): Int {
        return getList().size
    }
    override fun createFragment(position: Int): Fragment {
        var bundle = Bundle().apply {
            putString(Const.NewsCategory, getList()[position])
        }
        return NewsFragment().apply {
            arguments = bundle
        }
    }
    private fun getList() = NewsCategories.list()

}