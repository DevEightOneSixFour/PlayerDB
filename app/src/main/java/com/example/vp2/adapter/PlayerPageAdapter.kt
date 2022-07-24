package com.example.vp2.adapter

import com.example.vp2.views.GameFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.vp2.api.PlayerService
import com.example.vp2.constants.MINECRAFT
import com.example.vp2.constants.STEAM
import com.example.vp2.constants.XBOX

class PlayerPageAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle)
    : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val playerService = PlayerService.getInstance()

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        // needs to be lowercase for the call to work
        return when(position) {
            0 -> GameFragment(playerService, STEAM.lowercase())
            1 -> GameFragment(playerService, XBOX.lowercase())
            else -> GameFragment(playerService, MINECRAFT.lowercase())
        }
    }
}