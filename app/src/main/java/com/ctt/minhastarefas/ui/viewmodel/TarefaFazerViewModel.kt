package com.ctt.minhastarefas.ui.viewmodel

import androidx.lifecycle.*
import com.ctt.minhastarefas.model.TarefaAFazer
import com.ctt.minhastarefas.model.TarefaEmProgresso
import com.ctt.minhastarefas.repository.Repository
import kotlinx.coroutines.launch

class TarefaViewModel(
    private val repository: Repository
) : ViewModel() {

    val tarefasAfazer: LiveData<List<TarefaAFazer>> = repository.tarefasAfazer.asLiveData()

    private val _listaPesquisa = MutableLiveData<List<TarefaAFazer>>()
    val listaPesquisa: MutableLiveData<List<TarefaAFazer>>
        get() = _listaPesquisa

    fun popularListaPesquisa(listaPesquisa: List<TarefaAFazer>) {
        _listaPesquisa.value = listaPesquisa
    }

    fun insert(tarefa: TarefaAFazer) = viewModelScope.launch {
        repository.insert(tarefa)
    }

    fun insertInProgress(tarefa: TarefaEmProgresso) = viewModelScope.launch {
        repository.insertInProgress(tarefa)
    }

    fun update(tarefa: TarefaAFazer) = viewModelScope.launch {
        repository.update(tarefa)
    }

    fun remove(id: Int) = viewModelScope.launch {
        repository.remove(id)
    }
}

class TarefaViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TarefaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TarefaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}