package com.example.test13_16_17_18.test13

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityMain414Binding

class MainActivity414 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("life","onCreate()호출")
        val binding = ActivityMain414Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            val intent = Intent()
            intent.action = "ACTION_EDIT"
            intent.data = Uri.parse("http://www.google.com")
            startActivity(intent)
        }

        binding.button2.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.7749,127.4194"))
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("life","onStart()호출")
    }

    override fun onResume() {
        super.onResume()
        Log.d("life","onResume()호출")
    }

    override fun onPause() {
        super.onPause()
        Log.d("life","onPause()호출")
    }

    override fun onStop() {
        super.onStop()
        Log.d("life","onStop()호출")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("life","onRestart()호출")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("life","onDestroy()호출")
    }
}