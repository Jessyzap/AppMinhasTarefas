package com.ctt.minhastarefas.ui.viewmodel

import androidx.lifecycle.*
import com.ctt.minhastarefas.model.TarefaEmProgresso
import com.ctt.minhastarefas.model.TarefaFeita
import com.ctt.minhastarefas.repository.Repository
import kotlinx.coroutines.launch

class TarefaEmProgressoViewModel(
    private val repository: Repository
) : ViewModel() {

    val tarefasEmProgresso: LiveData<List<TarefaEmProgresso>> =
        repository.tarefasEmProgresso.asLiveData()

    private val _listaPesquisa = MutableLiveData<List<TarefaEmProgresso>>()
    val listaPesquisa: MutableLiveData<List<TarefaEmProgresso>>
        get() = _listaPesquisa

    fun popularListaPesquisa(listaPesquisa: List<TarefaEmProgresso>) {
        _listaPesquisa.value = listaPesquisa
    }

    fun insertDone(tarefa: TarefaFeita) = viewModelScope.launch {
        repository.insertDone(tarefa)
    }

    fun update(tarefa: TarefaEmProgresso) = viewModelScope.launch {
        repository.updateInProgress(tarefa)
    }

    fun removeDoing(id: Int) = viewModelScope.launch {
        repository.removeDoing(id)
    }
}

class TarefaEmProgressoViewModelFactory(private val repository: Repository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TarefaEmProgressoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TarefaEmProgressoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}