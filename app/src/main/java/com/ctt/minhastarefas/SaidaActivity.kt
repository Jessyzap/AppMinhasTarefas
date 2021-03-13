package com.ctt.minhastarefas

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SaidaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.animacao_final)
        animacaoFinal()
    }

    fun animacaoFinal() {
        Handler(
            Looper.getMainLooper()).postDelayed({
            finish()
        }, 3000)}
}