package com.example.keyboardcustom

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.core.app.NotificationManagerCompat

class BroadcastIntent : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationId = intent!!.getIntExtra("id", 0)
        val notificationManager = NotificationManagerCompat.from(context!!)
        notificationManager.cancel(notificationId)

        val sharedPref = context!!.getSharedPreferences("CutomKeyboard", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("keyboard", "Active")
        editor.apply()

    }
}