package com.example.vp2.api

import android.util.Log
import com.example.vp2.constants.BASE_URL
import com.example.vp2.model.PlayerResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


/*
    https://playerdb.co/api/player/ <- BASE_URL

    $ID == user's input
    https://playerdb.co/api/player/minecraft/$ID <- minecraft endpoint
    https://playerdb.co/api/player/steam/$ID <- steam endpoint
    https://playerdb.co/api/player/xbox/$ID <- xbox endpoint

*/

interface PlayerService {

    @GET("{platform}/{user}")
    fun getPlayerData(
        @Path("platform") platform: String,
        @Path("user") user: String
    ): Call<PlayerResponse>

    // Use @Path to replace what is in the @GET() with the function's parameters

    companion object {
        var retrofit: Retrofit? = null

        fun getInstance() : PlayerService {
            Log.d("*****", "Retrofit Before: $retrofit")
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            // once given value, should be the same everytime
            Log.d("*****", "Retrofit After: $retrofit")
            return retrofit!!.create(PlayerService::class.java)
        }
    }
}