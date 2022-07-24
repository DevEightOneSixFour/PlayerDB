package com.example.vp2.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.vp2.R
import com.example.vp2.api.PlayerService
import com.example.vp2.constants.MINECRAFT
import com.example.vp2.constants.STEAM
import com.example.vp2.constants.XBOX
import com.example.vp2.databinding.FragmentGameBinding
import com.example.vp2.model.Data
import com.example.vp2.model.Player
import com.example.vp2.model.PlayerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GameFragment : BaseFragment() {

    companion object {
        const val PLATFORM_KEY = "platform_key"

        fun getNewGamerFragment(platform: String): GameFragment {
            val fragment = GameFragment()
            val bundle = Bundle()
            bundle.putString(PLATFORM_KEY, platform)
            fragment.arguments = bundle
            return fragment
        }
    }

    lateinit var binding: FragmentGameBinding

    private val platform: String? by lazy {
        arguments?.getString(PLATFORM_KEY)
    }
    private val playerService: PlayerService by lazy {
        fetchPlayerService()
    }
    var playerResponse = PlayerResponse(
        data = Data(
            player = Player(
                username = "Player Not Found",
                avatar = "null"
            )
        ),
        success = false,
        message = null
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(layoutInflater)

        fetchData(platform!!, playerParam)
        updateBackground()

        return binding.root
    }

    private fun fetchData(platform: String, user: String) {
        playerService.getPlayerData(platform, user).enqueue(object : Callback<PlayerResponse> {
            override fun onResponse(
                call: Call<PlayerResponse>,
                response: Response<PlayerResponse>
            ) {
                /*
                    if the response was successful and the response code is 200 (200 == ok)
                        then return the response data
                    else make playerResponse the "Player Not Found" value
                 */
                if (response.isSuccessful && response.body()?.success == true) {
                    playerResponse = response.body()!!
                }
                binding.tvGameUsername.text = playerResponse.data.player?.username

                Glide.with(binding.ivGameAvatar)
                    .load(playerResponse.data.player?.avatar)
                    .placeholder(R.drawable.ic_baseline_sync_problem_24)
                    .into(binding.ivGameAvatar)
            }

            override fun onFailure(call: Call<PlayerResponse>, t: Throwable) {
                Toast.makeText(context, "Error for $platform: ${t.message}", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }

    private fun updateBackground() {
        when (platform) {
            STEAM.lowercase() -> binding.root.rootView.setBackgroundColor(
                resources.getColor(
                    R.color.dark_blue,
                    null
                )
            )
            XBOX.lowercase() -> binding.root.rootView.setBackgroundColor(
                resources.getColor(
                    R.color.green,
                    null
                )
            )
            MINECRAFT.lowercase() -> binding.root.rootView.setBackgroundColor(
                resources.getColor(
                    R.color.teal_700,
                    null
                )
            )
        }
    }
}