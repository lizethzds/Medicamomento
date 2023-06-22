package com.example.medicamomento

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        var i = Intent(p0,AlarmaOnActivity::class.java)
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        p0?.startActivity(i)

    }
}


/*var intent = Intent(p0, AlarmOnActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        p0?.startActivity(intent)*/