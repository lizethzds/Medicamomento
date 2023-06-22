package com.example.medicamomento

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AlarmaOnActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarma_on)
        val btntomar:Button = findViewById(R.id.tomar)

        var mp = MediaPlayer.create(applicationContext,R.raw.marciano)
        mp.start()

        btntomar.setOnClickListener{
            mp.stop()
        }
    }
}