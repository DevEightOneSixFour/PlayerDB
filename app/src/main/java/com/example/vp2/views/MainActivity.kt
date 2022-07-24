package com.example.vp2.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vp2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLoadPlayer.setOnClickListener {
            // setPlayerParam for the fragments to use
            BaseFragment.setPlayerParam(binding.tietGetPlayer.text.toString())
            val intent = Intent(this, TabsActivity::class.java)
            startActivity(intent)
        }
    }
}