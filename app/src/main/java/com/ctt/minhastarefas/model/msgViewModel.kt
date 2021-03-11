package com.ctt.minhastarefas.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class msgViewModel : ViewModel() {

    val listaDados = MutableLiveData<MutableList<Tarefa>>()

    fun dadosTarefa(dados: MutableList<Tarefa>) {
        listaDados.value = dados
    }
}