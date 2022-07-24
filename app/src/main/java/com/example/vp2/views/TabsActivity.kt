package com.example.vp2.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.example.vp2.R
import com.example.vp2.adapter.PlayerPageAdapter
import com.example.vp2.constants.MINECRAFT
import com.example.vp2.constants.STEAM
import com.example.vp2.constants.XBOX
import com.example.vp2.databinding.ActivityTabsBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TabsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTabsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val playerAdapter = PlayerPageAdapter(supportFragmentManager, lifecycle)

        binding.viewPager2.adapter = playerAdapter

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> updateTabText(0)
                    1 -> updateTabText(1)
                    2 -> updateTabText(2)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.apply {
                        text = STEAM
                        icon = ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.icon_steam,
                            null
                        )
                    }
                }
                1 -> {
                    tab.apply {
                        text = XBOX
                        icon = ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.icon_xbox,
                            null
                        )
                    }
                }
                else -> {
                    tab.apply {
                        text = MINECRAFT
                        icon = ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.icon_minecraft,
                            null
                        )
                    }
                }
            }
        }.attach()
    }

    private fun updateTabText(platform: Int) {
        when (platform) {
            0 -> {
                binding.tabLayout.apply {
                    setTabTextColors(
                        resources.getColor(R.color.white, null),
                        resources.getColor(R.color.dark_blue, null)
                    )
                    getTabAt(platform)?.icon?.setTint(
                        resources.getColor(R.color.dark_blue, null)
                    )
                }
            }
            1 -> {
                binding.tabLayout.apply {
                    setTabTextColors(
                        resources.getColor(R.color.white, null),
                        resources.getColor(R.color.green, null)
                    )
                    getTabAt(platform)?.icon?.setTint(
                        resources.getColor(R.color.green, null)
                    )
                }
            }
            2 -> {
                binding.tabLayout.apply {
                    setTabTextColors(
                        resources.getColor(R.color.white, null),
                        resources.getColor(R.color.teal_700, null)
                    )
                    getTabAt(platform)?.icon?.setTint(
                        resources.getColor(R.color.teal_700, null)
                    )
                }
            }
        }
    }
}