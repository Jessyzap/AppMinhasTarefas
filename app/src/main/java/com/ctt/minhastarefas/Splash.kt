package com.ctt.minhastarefas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)
        animacao()
    }

    fun animacao() {
        Handler(
            Looper.getMainLooper()).postDelayed({
            val proximaTela =
                Intent(this, PrincipalActivity::class.java)
            startActivity(proximaTela)
            finish()
        }, 3000)}
}