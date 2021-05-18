package com.mu.jan.sparknews.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.google.android.material.tabs.TabLayoutMediator
import com.mu.jan.sparknews.R
import com.mu.jan.sparknews.data.local.NewsCategories
import com.mu.jan.sparknews.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        binding?.apply {
            //adapter
            viewPager.adapter = ViewStateAdapter(supportFragmentManager,lifecycle)
            //tabLayout
            TabLayoutMediator(tabLayout,viewPager) { tab, position ->
                tab.text = NewsCategories.list()[position]
            }.attach()
        }
    }
    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}