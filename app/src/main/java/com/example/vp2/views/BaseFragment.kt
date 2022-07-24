package com.example.vp2.views

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.vp2.api.PlayerService
import com.example.vp2.model.Data
import com.example.vp2.model.Player
import com.example.vp2.model.PlayerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseFragment: Fragment() {

    protected val playerParam = getPlayerParam()

    companion object {
        var param = ""

        fun setPlayerParam(text: String) {
            param = text
        }

        fun getPlayerParam() = param
    }
}