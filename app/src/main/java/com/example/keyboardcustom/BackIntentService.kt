package com.example.keyboardcustom

import android.app.IntentService
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.util.Log
import android.view.View
import java.lang.reflect.Method

class BackIntentService: IntentService("BackIntentService") {
    init {
        instance = this
    }
    companion object{
        private lateinit var instance: BackIntentService
        private lateinit var packet: PackageManager
        private lateinit var launch: Intent
        var isRuning = false

        fun stopService(){
            Log.d("stoppingService", "stopService: ")
            isRuning = false
            instance.stopSelf()
        }
    }
    override fun onHandleIntent(intent: Intent?) {
                isRuning = true
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri: Uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    Log.d("runingIntentService", "running... ")

            }
        }
//                Log.d("runingIntentService", "running... ")
//                packet = instance.packageManager
//                launch = packet.getLaunchIntentForPackage("com.example.keyboardcustom")!!
//                instance.startActivity(launch)
//                instance.stopSelf()
//                Thread.sleep(10000)


