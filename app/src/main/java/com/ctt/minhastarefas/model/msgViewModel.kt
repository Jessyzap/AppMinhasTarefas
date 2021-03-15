package com.ctt.minhastarefas.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class msgViewModel : ViewModel() {

    val dados = MutableLiveData<Tarefa>()
    val textoNotificacao = MutableLiveData<String>()

    fun dadosTarefa(dadosTarefa: Tarefa) {
        dados.value = dadosTarefa
    }
    fun notificar(texto: String) {
        textoNotificacao.value = texto
    }

}