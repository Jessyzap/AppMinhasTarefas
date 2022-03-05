package com.ctt.minhastarefas

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

fun Fragment.esconderPlaceHolder(
    imagem: ImageView,
    texto: TextView,
    segundoTexto: TextView
) {
    imagem.visibility = View.GONE
    texto.visibility = View.GONE
    segundoTexto.visibility = View.GONE
}

fun Fragment.mostrarPlaceHolder(
    imagem: ImageView,
    texto: TextView,
    segundoTexto: TextView
) {
    imagem.visibility = View.VISIBLE
    texto.visibility = View.VISIBLE
    segundoTexto.visibility = View.VISIBLE
}