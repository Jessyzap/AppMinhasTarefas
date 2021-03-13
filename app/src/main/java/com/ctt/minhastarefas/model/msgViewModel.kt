package com.ctt.minhastarefas.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class msgViewModel : ViewModel() {

    val dados = MutableLiveData<Tarefa>()

    fun dadosTarefa(dadosTarefa: Tarefa) {
        dados.value = dadosTarefa
    }
}