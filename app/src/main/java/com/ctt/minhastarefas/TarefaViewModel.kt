package com.ctt.minhastarefas

import androidx.lifecycle.*
import com.ctt.minhastarefas.model.TarefaAFazer
import com.ctt.minhastarefas.model.TarefaEmProgresso
import kotlinx.coroutines.launch

class TarefaViewModel(
    private val repository: Repository
) : ViewModel() {

    val tarefasAfazer: LiveData<List<TarefaAFazer>> = repository.tarefasAfazer.asLiveData()

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