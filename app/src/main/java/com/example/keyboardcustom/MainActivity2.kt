package com.example.keyboardcustom

import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity2 : AppCompatActivity() {
    private lateinit var btn_set: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        btn_set = findViewById(R.id.btn_addIntent)
        val intent = Intent(this, BroadcastIntent::class.java)
        intent.putExtra("id", 100)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        btn_set.setOnClickListener(View.OnClickListener {
            pendingIntent
            Log.d("pending Intent", "onReceive: $pendingIntent")
        })
    }
}