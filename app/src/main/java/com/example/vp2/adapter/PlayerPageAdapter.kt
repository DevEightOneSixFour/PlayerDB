package com.example.vp2.adapter

import com.example.vp2.views.GameFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.vp2.constants.MINECRAFT
import com.example.vp2.constants.STEAM
import com.example.vp2.constants.XBOX
import com.example.vp2.views.ConstructFragment

class PlayerPageAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle)
    : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        // platform type needs to be lowercase for the call to work
        return when(position) {
            0 -> GameFragment.getNewGamerFragment(STEAM.lowercase())
            1 -> GameFragment.getNewGamerFragment(XBOX.lowercase())
            else -> GameFragment.getNewGamerFragment(MINECRAFT.lowercase())
        }
//        return when(position) {
//            0 -> ConstructFragment(STEAM.lowercase())
//            1 -> ConstructFragment(XBOX.lowercase())
//            else -> ConstructFragment(MINECRAFT.lowercase())
//        }
    }
}