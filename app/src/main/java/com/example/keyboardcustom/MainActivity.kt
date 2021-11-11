package com.example.keyboardcustom

import android.content.Intent
import android.inputmethodservice.InputMethodService
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.media.AudioManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputConnection
import android.widget.Button
import kotlin.properties.Delegates

class MainActivity : InputMethodService(), KeyboardView.OnKeyboardActionListener {
    private lateinit var kv: KeyboardView
    private lateinit var keyboard: Keyboard
    private lateinit var btn_add: Button

    override fun onCreateInputView(): View {
        val root = layoutInflater.inflate(R.layout.activity_main, null)
        kv = root.findViewById(R.id.keyboard)
        keyboard = Keyboard(this, R.xml.qwerty)
        kv.keyboard = keyboard
        kv.setOnKeyboardActionListener(this)
        btn_add = root.findViewById(R.id.btn_add)
        btn_add.setOnClickListener(View.OnClickListener {
           Intent(this, BackIntentService::class.java).also {
               startService(it)
           }
        })
        return root
    }
    override fun onPress(primaryCode: Int) {

    }

    override fun onRelease(primaryCode: Int) {
    }

    override fun onKey(primaryCode: Int, keyCodes: IntArray?) {
        val ic = currentInputConnection
        playClick(primaryCode)
        when (primaryCode) {
            Keyboard.KEYCODE_DELETE -> ic.deleteSurroundingText(1, 0)
            Keyboard.KEYCODE_SHIFT -> {
                kv.invalidateAllKeys()
            }
            Keyboard.KEYCODE_DONE -> ic.sendKeyEvent(
                KeyEvent(
                    KeyEvent.ACTION_DOWN,
                    KeyEvent.KEYCODE_ENTER
                )
            )
            else -> {
                var code = primaryCode.toChar()
                ic.commitText(code.toString(), 1)
            }
        }
    }

    private fun playClick(i: Int) {
        val am = getSystemService(AUDIO_SERVICE) as AudioManager
        when (i) {
            32 -> am.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR)
            Keyboard.KEYCODE_DONE, 10 -> am.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN)
            Keyboard.KEYCODE_DELETE -> am.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE)
            else -> am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD)
        }
    }
    override fun onText(text: CharSequence?) {
        TODO("Not yet implemented")
    }

    override fun swipeLeft() {
        TODO("Not yet implemented")
    }

    override fun swipeRight() {
        TODO("Not yet implemented")
    }

    override fun swipeDown() {
        TODO("Not yet implemented")
    }

    override fun swipeUp() {
        TODO("Not yet implemented")
    }

}