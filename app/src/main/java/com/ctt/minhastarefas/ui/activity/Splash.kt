package com.ctt.minhastarefas.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.ctt.minhastarefas.R

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)
        animacao()
    }

    private fun animacao() {
        Handler(
            Looper.getMainLooper()
        ).postDelayed({
            val proximaTela =
                Intent(this, PrincipalActivity::class.java)
            startActivity(proximaTela)
            finish()
        }, 3000)
    }
}